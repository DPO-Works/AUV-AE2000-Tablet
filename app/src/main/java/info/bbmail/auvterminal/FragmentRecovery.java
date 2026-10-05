package info.bbmail.auvterminal;

import android.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FragmentRecovery extends Fragment implements OnClickListener {

    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ma = (MainActivity)getActivity();

        // fragment再生成抑止
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.subrecovery, container, false);
		return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　ボタンのリスナー設定
        ((Button) ma.findViewById(R.id.recovery_btMissionStop)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.recovery_btActOff)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.recovery_btErrorCheckStop)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.recovery_btPayloadStop)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.recovery_btMicronStop)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.recovery_btDVLStop)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.recovery_btDiveRelease)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.recovery_btSurfaceRelease)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.recovery_btAdjustRelease)).setOnClickListener(this);

        ((Button) ma.findViewById(R.id.recovery_btMagStop)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.recovery_btAcousticStop)).setOnClickListener(this);

        ((Button) ma.findViewById(R.id.recovery_btAllQuitCheck)).setOnClickListener(this);
    }


    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Button bt;
        String outs;
        String ErrorCode;


        if(v.getId() == R.id.recovery_btMissionStop) {
            ma.UdpSend( "STOP");
            ma.cc.ToastOutShort( "NEWFLY Stop");
            ma.cc.SetStringToTextView( "", R.id.dive_tvReserveTime);
        }
        if(v.getId() == R.id.recovery_btActOff) {
            ma.UdpSend( "ACTOF");
            ma.cc.ToastOutShort( "Thruster Power OFF");
        }
        if(v.getId() == R.id.recovery_btErrorCheckStop) {
            ma.UdpSend( "ERRSTOP");
            ma.cc.ToastOutShort( "Error Check Task OFF");
        }
        if(v.getId() == R.id.recovery_btPayloadStop) {
            ma.UdpSend( "SSSOFF");
            ma.cc.ToastOutShort( "SBP Task OFF");
		}
        if(v.getId() == R.id.recovery_btMicronStop) {
            ma.UdpSend( "DELOF");
            ma.cc.ToastOutShort( "MICRON Task OFF");
        }
        if(v.getId() == R.id.recovery_btDVLStop) {
            ma.UdpSend( "DVLOF");
            ma.cc.ToastOutShort( "DVL Task OFF");
        }
        if(v.getId() == R.id.recovery_btMagStop) {
                ma.UdpSend( "MAGOF");
                ma.cc.ToastOutShort( "Mag Task OFF");
        }
        if(v.getId() == R.id.recovery_btAcousticStop) {
                ma.UdpSend( "DOF05");
                ma.cc.ToastOutShort( "MiniCT Task OFF");
        }
        if(v.getId() == R.id.recovery_btDiveRelease) {
            ma.UdpSend( "DOF02");
            ma.cc.ToastOutShort( "Dive Ballast Open");
        }
        if(v.getId() == R.id.recovery_btSurfaceRelease) {
            ma.UdpSend( "DOF03");
            ma.cc.ToastOutShort( "Surface Ballast Open");
        }
        if(v.getId() == R.id.recovery_btAdjustRelease) {
            ma.UdpSend( "DOF04");
            ma.cc.ToastOutShort( "Adjust Ballast Open");
        }
        if(v.getId() == R.id.recovery_btAllQuitCheck) {
            //確認ダイアログを表示します。
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(ma);
            //タイトルを設定する
            alertDialog.setTitle("ALL QUIT");
            //メッセージ内容を設定する
            alertDialog.setMessage("if OK, AUV Progurams goes Quit");
            //確認ボタンん処理を設定する
            alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int whichButton) {
                            ma.UdpSend( "ALLQUIT");
                            ma.cc.ToastOutShort( "ALL QUIT");
                }
            });
            alertDialog.setNegativeButton("Cancel", null);

            alertDialog.create();
            alertDialog.show();
        }
    }

    public void reDraw() {

        ma.cc.SetStringToTextView( ma.ViecleData.Nfly.nfly_go, R.id.recovery_tvNFlyCkeck);
        ma.cc.SetStringToTextViewOnOff( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_THRUSTPWR ], R.id.recovery_tvThrustPwrCkeck);
        ma.cc.SetStringToTextViewOnOff( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_ELVPWR ], R.id.recovery_tvWingPwrCkeck);

        ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_PAYLOAD   ].TaskStatus, R.id.recovery_tvTaskPayload);

        ma.cc.SetStringToTextViewBallast( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BALASTPWR1 ], R.id.recovery_tvDiveBallast);
        ma.cc.SetStringToTextViewBallast( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BALASTPWR2 ], R.id.recovery_tvSurfaceBallast);
        ma.cc.SetStringToTextViewBallast( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BALASTPWR3 ], R.id.recovery_tvAdjustRelease);

        ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_ABNORMAL   ].TaskStatus, R.id.recovery_tvTaskErrorCkeck);
        ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DELTAT   ].TaskStatus, R.id.recovery_tvTaskMicron);
        ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DVL   ].TaskStatus, R.id.recovery_tvTaskDVL);
        ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_MAG   ].TaskStatus, R.id.recovery_tvTaskMag);

        ma.cc.SetStringToTextViewOnOff( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_FSTRPWR ], R.id.recovery_tvAcoustic);

    }
}
