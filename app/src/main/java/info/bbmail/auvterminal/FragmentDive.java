package info.bbmail.auvterminal;

import android.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;

public class FragmentDive extends Fragment implements OnClickListener {

    MainActivity ma;
    NumberPicker numPicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ma = (MainActivity)getActivity();

        // fragment再生成抑止
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.subdive, container, false);
		return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　ボタンのリスナー設\定
        ((Button) ma.findViewById(R.id.dive_btActOn)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.dive_btActOff)).setOnClickListener(this);

        ((Button) ma.findViewById(R.id.dive_btMissionTaskErr)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.dive_btReseve)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.dive_btStart)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.dive_Stop)).setOnClickListener(this);

        ((Button) ma.findViewById(R.id.mission_btMissionSelect)).setOnClickListener(this);

    }

    // コースNo選択ダイアログの処理
    public void CourseSelectDialogShow( View v ) {
        final CharSequence[]  CourseNoS = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10" };
        AlertDialog.Builder alertDialogBuilder =
                new AlertDialog.Builder( getActivity() );
        alertDialogBuilder.setTitle("Select Course No.");
        alertDialogBuilder.setItems( CourseNoS, new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int which ){
                // 表示対象を変更する
                ((Button)ma.findViewById(R.id.mission_btMissionSelect)).setText( CourseNoS[which] );
            }
        });
        alertDialogBuilder .show();
    }


    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Button bt;
        String outs;
        String ErrorCode;
        if(v.getId() ==  R.id.dive_btActOn){
            ma.UdpSend( "ACTON");
            ma.cc.ToastOutShort( "Actuator Power ON");
        }
        if(v.getId() ==  R.id.dive_btActOff){
            ma.UdpSend( "ACTOF");
            ma.cc.ToastOutShort( "Thruster Power OFF");
        }

        if(v.getId() ==  R.id.mission_btMissionSelect){
                CourseSelectDialogShow(v);
		}

        if(v.getId() ==  R.id.dive_btReseve){
                bt = (Button)ma.findViewById(R.id.mission_btMissionSelect);
                outs = "RESRV" + bt.getText();
                ma.UdpSend( outs );
                ma.cc.ToastOutShort( "NEWFLY Reservation" );
                // 予約時刻のセット
                Time time = new Time("Asia/Tokyo");
                time.setToNow();
                String TimeStr = String.format("%02d:%02d:%02d", time.hour, time.minute, time.second);
                ma.cc.SetStringToTextView( TimeStr, R.id.dive_tvReserveTime);
		}

        if(v.getId() ==  R.id.dive_btStart){
                bt = (Button)ma.findViewById(R.id.mission_btMissionSelect);
                outs = "START" + bt.getText();
                ma.UdpSend( outs );
                ma.cc.ToastOutShort( "NEWFLY Start" );
                ma.cc.SetStringToTextView( "", R.id.dive_tvReserveTime);
		}

        if(v.getId() ==  R.id.dive_Stop){
                ma.UdpSend( "STOP");
                ma.cc.ToastOutShort( "NEWFLY Stop");
                ma.cc.SetStringToTextView( "", R.id.dive_tvReserveTime);
		}

        if(v.getId() ==  R.id.dive_btMissionTaskErr) {
            bt = (Button) ma.findViewById(v.getId());
            ErrorCode = bt.getText().toString();

//            ma.cc.ShowErrorDetail(ma.ViecleData.ErrStr04, ma.ViecleData.ErrorID_04, ErrorCode);
            outs = ma.cc.getErrorDetail(ma.ViecleData.ErrStr12, ma.ViecleData.ErrorID_12, ErrorCode);

            showDialog( outs );

        }

    }

    public void reDraw() {
        ma.cc.SetStringToTextViewOnOff(ma.ViecleData.io.DI[ma.ViecleData.io.DI_THRUSTPWR], R.id.dive_tvThrustPwr);
        ma.cc.SetStringToTextViewOnOff(ma.ViecleData.io.DI[ma.ViecleData.io.DI_ELVPWR], R.id.dive_tvWingPwr);

        ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_NEWFLY   ].TaskStatus, R.id.dive_tvDiveMissionStatus);
        ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_NEWFLY ].ErrCode, R.id.dive_btMissionTaskErr);
        ma.cc.SetStringToTextView( ma.ViecleData.Nfly.nfly_go, R.id.dive_tvNflyStatus);

        ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_SSSWLEAK ], R.id.dive_tvWLGeoSworth);
        ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_TRNSWLEAK ], R.id.dive_tvWLAcoustic);
        ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_DIRWLEAK ], R.id.dive_tvWLQuadrans);
        ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_CONTWLEAK ], R.id.dive_tvWLController);
        ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BAT1WLEAK ], R.id.dive_tvWLBattery1);
        ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BAT2WLEAK ], R.id.dive_tvWLBattery2);

        ma.cc.SetStringToTextView(ma.ViecleData.Param.limit_time, R.id.dive_tvReturnTime);
    }
   
    public void showDialog( String message ) {
            new AlertDialog.Builder(requireContext())
                .setMessage(message) // メッセージを設定
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()) // OKボタンで閉じる
                .show();
    }

}
