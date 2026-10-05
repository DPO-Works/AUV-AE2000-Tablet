package info.bbmail.auvterminal;

import android.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;

public class FragmentDvl extends Fragment implements OnClickListener {

    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ma = (MainActivity)getActivity();

        // fragment再生成抑止
        setRetainInstance(true);
        return inflater.inflate(R.layout.subdvl, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　ボタンのリスナー設定
        ((Button) ma.findViewById(R.id.dvl_btDvlStart)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.dvl_btDvlStop)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.dvl_btAcosticOn)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.dvl_btAcosticOff)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.dvl_btDeltaTStart)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.dvl_btDeltaTStop)).setOnClickListener(this);

        ((Button) ma.findViewById(R.id.dvl_btDvlCheck)).setOnClickListener(this);

        ((Button) getActivity().findViewById(R.id.dvl_btDvlTaskError)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.dvl_btDeltaTTaskError)).setOnClickListener(this);
    }


    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Button bt;
        String ErrorCode;

        if(v.getId() ==  R.id.dvl_btDvlStart){
                ma.UdpSend( "DVLON");
                ma.cc.ToastOutShort( "DVL Task ON");
        }

        if(v.getId() ==  R.id.dvl_btDvlStop){
                ma.UdpSend( "DVLOF");
                ma.cc.ToastOutShort( "DVL Task OFF");
        }

        if(v.getId() ==  R.id.dvl_btAcosticOn){
                ma.UdpSend( "DON05");
                ma.cc.ToastOutShort( "Acostic Power ON");
        }

        if(v.getId() ==  R.id.dvl_btAcosticOff){
                ma.UdpSend( "DOF05");
                ma.cc.ToastOutShort( "Acostic Power OFF");
        }

        if(v.getId() ==  R.id.dvl_btDeltaTStart){
                ma.UdpSend( "DELON");
                ma.cc.ToastOutShort( "DeltaT Task ON");
        }

        if(v.getId() ==  R.id.dvl_btDeltaTStop){
                ma.UdpSend( "DELOF");
                ma.cc.ToastOutShort( "DeltaT Task OFF");
        }

        if(v.getId() ==  R.id.dvl_btDvlTaskError){
                bt = (Button)ma.findViewById( v.getId() );
                ErrorCode = bt.getText().toString();

        	    String errOut = ma.cc.getErrorDetail(  ma.ViecleData.ErrStr02, ma.ViecleData.ErrorID_02, ErrorCode );
            showDialog( errOut );
        }

        if(v.getId() ==  R.id.dvl_btDeltaTTaskError){
                bt = (Button)ma.findViewById( v.getId() );
                ErrorCode = bt.getText().toString();

	            String errOut = ma.cc.getErrorDetail(  ma.ViecleData.ErrStr05, ma.ViecleData.ErrorID_05, ErrorCode );
    	        showDialog( errOut );
        }

        if(v.getId() ==  R.id.dvl_btDvlCheck){
                int result =  ma.GetCheckResult(1);
                result ++;
                result &= 1;
                ma.ShowCheckResult( 1, result );
		}
    }

    public void reDraw()
    {
        ma.cc.SetStringToTextView( ma.ViecleData.SensorDvl.dvl_surgeVelWater, R.id.dvl_tvDvlWLSurge);
        ma.cc.SetStringToTextView( ma.ViecleData.SensorDvl.dvl_swayVelWater, R.id.dvl_tvDvlWLSway);
        ma.cc.SetStringToTextView( ma.ViecleData.SensorDvl.dvl_validWater, R.id.dvl_tvDvlWTrack);

        ma.cc.SetStringToTextView( ma.ViecleData.SensorMicron.Dist0deg, R.id.dvl_tvMicDist);
        ma.cc.SetStringToTextView( ma.ViecleData.SensorMicron.DistFwd, R.id.dvl_tvMicAngle);

        ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DELTAT ].TaskStatus, R.id.dvl_tvDeltaTTaskStatus);
        ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DELTAT ].ErrCode, R.id.dvl_btDeltaTTaskError);
        ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DVL      ].TaskStatus, R.id.dvl_tvDvlTaskStatus);
        ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DVL      ].ErrCode, R.id.dvl_btDvlTaskError);

        ma.cc.SetStringToTextViewOnOff(ma.ViecleData.io.DI[ ma.ViecleData.io.DI_FSTRPWR    ], R.id.dvl_tvAcPwrStatus);
    }

    public void showDialog( String message ) {
        new AlertDialog.Builder(requireContext())
                .setMessage(message) // メッセージを設定
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()) // OKボタンで閉じる
                .show();
    }
}
