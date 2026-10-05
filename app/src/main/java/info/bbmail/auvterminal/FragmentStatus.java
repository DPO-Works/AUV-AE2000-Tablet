package info.bbmail.auvterminal;

import android.app.Activity;
import android.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentStatus extends Fragment implements OnClickListener {

    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // fragment再生成抑止
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.substatus, container, false);
		return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　ボタンのリスナー設定
        ((Button) getActivity().findViewById(R.id.stat_btErr_01)).setOnClickListener(this);

        ((Button) getActivity().findViewById(R.id.stat_btErr_05)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_06)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_07)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_08)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_09)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_10)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_11)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_12)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_13)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_14)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_15)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_16)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_17)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_18)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.stat_btErr_19)).setOnClickListener(this);

        ma = (MainActivity) getActivity();

        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask01, R.id.stat_tvErr_05t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask02, R.id.stat_tvErr_06t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask03, R.id.stat_tvErr_07t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask04, R.id.stat_tvErr_08t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask05, R.id.stat_tvErr_09t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask06, R.id.stat_tvErr_10t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask07, R.id.stat_tvErr_11t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask08, R.id.stat_tvErr_12t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask09, R.id.stat_tvErr_13t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask10, R.id.stat_tvErr_14t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask11, R.id.stat_tvErr_15t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask12, R.id.stat_tvErr_16t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask13, R.id.stat_tvErr_17t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask14, R.id.stat_tvErr_18t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask15, R.id.stat_tvErr_19t);
   }

    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Intent intent;
        TextView tvCourse;
        String outs;
        if(v.getId() == R.id.stat_btErr_01) {
//          TextView tv = (TextView) getActivity().findViewById(R.id.stat_tvErr_02);
//          String logs = (String) tv.getText();
            String logs = (String)ma.ViecleData.Error.ErrTask;
            ShowError( logs, v.getId() );
        }

        if(v.getId() == R.id.stat_btErr_05)	ShowErrorDetail(  ma.ViecleData.ErrStr01, ma.ViecleData.ErrorID_01, v.getId() );
        if(v.getId() == R.id.stat_btErr_06)	ShowErrorDetail(  ma.ViecleData.ErrStr02, ma.ViecleData.ErrorID_02, v.getId() );
        if(v.getId() == R.id.stat_btErr_07)	ShowErrorDetail(  ma.ViecleData.ErrStr03, ma.ViecleData.ErrorID_03, v.getId() );
        if(v.getId() == R.id.stat_btErr_08)	ShowErrorDetail(  ma.ViecleData.ErrStr04, ma.ViecleData.ErrorID_04, v.getId() );
        if(v.getId() == R.id.stat_btErr_09)	ShowErrorDetail(  ma.ViecleData.ErrStr05, ma.ViecleData.ErrorID_05, v.getId() );
        if(v.getId() == R.id.stat_btErr_10)	ShowErrorDetail(  ma.ViecleData.ErrStr06, ma.ViecleData.ErrorID_06, v.getId() );
        if(v.getId() == R.id.stat_btErr_11)	ShowErrorDetail(  ma.ViecleData.ErrStr07, ma.ViecleData.ErrorID_07, v.getId() );
        if(v.getId() == R.id.stat_btErr_12)	ShowErrorDetail(  ma.ViecleData.ErrStr08, ma.ViecleData.ErrorID_08, v.getId() );
        if(v.getId() == R.id.stat_btErr_13)	ShowErrorDetail(  ma.ViecleData.ErrStr09, ma.ViecleData.ErrorID_09, v.getId() );
        if(v.getId() == R.id.stat_btErr_14)	ShowErrorDetail(  ma.ViecleData.ErrStr10, ma.ViecleData.ErrorID_10, v.getId() );
        if(v.getId() == R.id.stat_btErr_15)	ShowErrorDetail(  ma.ViecleData.ErrStr11, ma.ViecleData.ErrorID_11, v.getId() );
        if(v.getId() == R.id.stat_btErr_16)	ShowErrorDetail(  ma.ViecleData.ErrStr12, ma.ViecleData.ErrorID_12, v.getId() );
        if(v.getId() == R.id.stat_btErr_17)	ShowErrorDetail(  ma.ViecleData.ErrStr13, ma.ViecleData.ErrorID_13, v.getId() );
        if(v.getId() == R.id.stat_btErr_18)	ShowErrorDetail(  ma.ViecleData.ErrStr14, ma.ViecleData.ErrorID_14, v.getId() );
        if(v.getId() == R.id.stat_btErr_19)	ShowErrorDetail(  ma.ViecleData.ErrStr15, ma.ViecleData.ErrorID_15, v.getId() );

    }

    // 概略エラー表示
    public void ShowError( String TaskNoStr, int tvID ) {
        int id;
        MainActivity ma = (MainActivity) getActivity();

        try{
            id = Integer.parseInt(TaskNoStr);
            id ++;
        }
        catch( Exception e )
        {
            id = 0;
        }
        switch (id) {
            case 0:
            default:
                ma.cc.ToastOutLong("NO ERROR");
                break;
            case 1:ShowErrorDetail( ma.ViecleData.ErrStr01, ma.ViecleData.ErrorID_01, tvID ); break;
            case 2:ShowErrorDetail( ma.ViecleData.ErrStr02, ma.ViecleData.ErrorID_02, tvID );break;
            case 3:ShowErrorDetail( ma.ViecleData.ErrStr03, ma.ViecleData.ErrorID_03, tvID );break;
            case 4:ShowErrorDetail( ma.ViecleData.ErrStr04, ma.ViecleData.ErrorID_04, tvID );break;
            case 5:ShowErrorDetail( ma.ViecleData.ErrStr05, ma.ViecleData.ErrorID_05, tvID );break;
            case 6:ShowErrorDetail( ma.ViecleData.ErrStr06, ma.ViecleData.ErrorID_06, tvID );break;
            case 7:ShowErrorDetail( ma.ViecleData.ErrStr07, ma.ViecleData.ErrorID_07, tvID );break;
            case 8:ShowErrorDetail( ma.ViecleData.ErrStr08, ma.ViecleData.ErrorID_08, tvID );break;
            case 9:ShowErrorDetail( ma.ViecleData.ErrStr09, ma.ViecleData.ErrorID_09, tvID );break;
            case 10:ShowErrorDetail( ma.ViecleData.ErrStr10, ma.ViecleData.ErrorID_10, tvID );break;
            case 11:ShowErrorDetail( ma.ViecleData.ErrStr11, ma.ViecleData.ErrorID_11, tvID );break;
            case 12:ShowErrorDetail( ma.ViecleData.ErrStr12, ma.ViecleData.ErrorID_12, tvID );break;
            case 13:ShowErrorDetail( ma.ViecleData.ErrStr13, ma.ViecleData.ErrorID_13, tvID );break;
            case 14:ShowErrorDetail( ma.ViecleData.ErrStr14, ma.ViecleData.ErrorID_14, tvID );break;
            case 15:ShowErrorDetail( ma.ViecleData.ErrStr15, ma.ViecleData.ErrorID_15, tvID );break;
        }
    }

    // エラー詳細表示
    public void ShowErrorDetail( String DialogName, String ErrTitle, int tvID ) {
        Button tv = (Button) getActivity().findViewById(tvID);
        String ErrorCode = tv.getText().toString();
        int hexVal;
        try{
            hexVal = Integer.decode( ErrorCode );
        }
        catch( Exception e )
        {
            hexVal = 0;
        }
        String outs = DialogName + "\n\n";
        String[] Title = ErrTitle.split(",");
        int jVal = 1;
        for( int i = 0; i < 16; i++, jVal *= 2 ) {
            if ((hexVal & jVal) != 0)
                outs += "[●]";
            else
                outs += "[＿]";
            outs += Title[i] + "\n";
        }
//        ma.cc.ToastOutLong(outs);
        showDialog( outs );
    }


	public void reDraw()
	{

            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_MAGPWR ], R.id.stat_tvIO_01);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BALASTPWR1 ] , R.id.stat_tvIO_02);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BALASTPWR2 ], R.id.stat_tvIO_03);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BALASTPWR3 ], R.id.stat_tvIO_04);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_FSTRPWR ], R.id.stat_tvIO_05);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_THRUSTPWR ], R.id.stat_tvIO_06);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_DVLPWR ], R.id.stat_tvIO_07);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_ELVPWR ], R.id.stat_tvIO_08);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_LANPWR ], R.id.stat_tvIO_09);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_DIVEMODE ], R.id.stat_tvIO_10);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_SSSWLEAK ], R.id.stat_tvIO_11);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_TRNSWLEAK ], R.id.stat_tvIO_12);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_DIRWLEAK ], R.id.stat_tvIO_13);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_CONTWLEAK ], R.id.stat_tvIO_14);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BAT1WLEAK ], R.id.stat_tvIO_15);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BAT2WLEAK ], R.id.stat_tvIO_16);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_SSSPWR ], R.id.stat_tvIO_17);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_AUX01 ], R.id.stat_tvIO_18);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_AUX02 ], R.id.stat_tvIO_19);
            ma.cc.SetStringToTextView( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_AUX03 ], R.id.stat_tvIO_20);

//        io.AI[ io.AI_BAT_VOLT  ] = data[22];

            ma.cc.SetStringToTextView( ma.ViecleData.io.AI[ ma.ViecleData.io.AI_CCURRENT ], R.id.stat_tvIO_21);
            ma.cc.SetStringToTextView( ma.ViecleData.io.AI[ ma.ViecleData.io.AI_ELV ], R.id.stat_tvIO_22);
            ma.cc.SetStringToTextView( ma.ViecleData.io.AI[ ma.ViecleData.io.AI_BAT1_TEMP ], R.id.stat_tvIO_23);
            ma.cc.SetStringToTextView( ma.ViecleData.io.AI[ ma.ViecleData.io.AI_BAT2_TEMP ], R.id.stat_tvIO_24);
            ma.cc.SetStringToTextView( ma.ViecleData.io.AI[ ma.ViecleData.io.AI_CONT_TEMP ], R.id.stat_tvIO_25);

            ma.cc.SetStringToTextView( ma.ViecleData.SensorQuad.roll, R.id.stat_tvSens_01); 
            ma.cc.SetStringToTextView( ma.ViecleData.SensorQuad.pitch, R.id.stat_tvSens_02); 
            ma.cc.SetStringToTextView( ma.ViecleData.SensorQuad.yaw, R.id.stat_tvSens_03); 
            ma.cc.SetStringToTextView( ma.ViecleData.SensorDvl.dvl_surgeVelWater, R.id.stat_tvSens_04); 
            ma.cc.SetStringToTextView( ma.ViecleData.SensorDvl.dvl_swayVelWater, R.id.stat_tvSens_05); 
            ma.cc.SetStringToTextView( ma.ViecleData.SensorDvl.dvl_surgeVelBottom, R.id.stat_tvSens_06); 
            ma.cc.SetStringToTextView( ma.ViecleData.SensorDvl.dvl_swayVelBottom, R.id.stat_tvSens_07); 
            ma.cc.SetStringToTextView( ma.ViecleData.SensorDvl.dvl_validWater, R.id.stat_tvSens_08); 
            ma.cc.SetStringToTextView( ma.ViecleData.SensorDvl.dvl_validBottom, R.id.stat_tvSens_09); 
            ma.cc.SetStringToTextView( ma.ViecleData.SensorMicron.Dist0deg, R.id.stat_tvSens_10);
            ma.cc.SetStringToTextView( ma.ViecleData.SensorMicron.DistFwd, R.id.stat_tvSens_11);
            ma.cc.SetStringToTextView( ma.ViecleData.ActuatorWing.Order, R.id.stat_tvSens_12);
//            ma.cc.SetStringToTextView( ma.ViecleData.ActuatorWing.rightOrder, R.id.stat_tvSens_13);
            ma.cc.SetStringToTextView( ma.ViecleData.ActuatorThrust.leftOrder , R.id.stat_tvSens_14); 
            ma.cc.SetStringToTextView( ma.ViecleData.ActuatorThrust.rightOrder, R.id.stat_tvSens_15); 
            ma.cc.SetStringToTextView( ma.ViecleData.SensorBattery.AIC_TREND, R.id.stat_tvSens_16);
            ma.cc.SetStringToTextView( ma.ViecleData.SensorBattery.AIC_SUMUP, R.id.stat_tvSens_17);
            ma.cc.SetStringToTextView( ma.ViecleData.SensorPos.Lat   , R.id.stat_tvSens_18);
            ma.cc.SetStringToTextView( ma.ViecleData.SensorPos.Lon   , R.id.stat_tvSens_19);
            ma.cc.SetStringToTextView( ma.ViecleData.SensorPos.Depth , R.id.stat_tvSens_20);
            ma.cc.SetStringToTextView( ma.ViecleData.SensorPos.Height, R.id.stat_tvSens_21);
            ma.cc.SetStringToTextView( ma.ViecleData.Nfly.nfly_ctime, R.id.stat_tvSens_22);
            ma.cc.SetStringToTextView( ma.ViecleData.Nfly.nfly_go   , R.id.stat_tvSens_23);
            ma.cc.SetStringToTextView( ma.ViecleData.SensorPayload.StatusStr, R.id.stat_tvSens_24);
            ma.cc.SetStringToTextView( ma.ViecleData.AuvTime, R.id.stat_tvSens_25);

            ma.cc.SetStringToButton( ma.ViecleData.Error.ErrBit, R.id.stat_btErr_01);
            ma.cc.SetStringToTextView( ma.TaskIDToName(ma.ViecleData.Error.ErrTask), R.id.stat_tvErr_02);
            ma.cc.SetStringToTextView( ma.ErrorProcIDToName(ma.ViecleData.Error.ErrProc), R.id.stat_tvErr_03);
            ma.cc.SetStringToTextView( ma.cc.SerialSec2Time(ma.ViecleData.Error.ErrTime), R.id.stat_tvErr_04);

            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_MAIN       ].TaskStatus, R.id.stat_tvErr_05A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_MAIN       ].ErrCode, R.id.stat_btErr_05);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DVL      ].TaskStatus, R.id.stat_tvErr_06A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DVL      ].ErrCode, R.id.stat_btErr_06);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_CURMON   ].TaskStatus, R.id.stat_tvErr_07A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_CURMON   ].ErrCode, R.id.stat_btErr_07);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_IO      ].TaskStatus, R.id.stat_tvErr_08A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_IO      ].ErrCode, R.id.stat_btErr_08);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DELTAT ].TaskStatus, R.id.stat_tvErr_09A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DELTAT ].ErrCode, R.id.stat_btErr_09);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_MAG      ].TaskStatus, R.id.stat_tvErr_10A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_MAG      ].ErrCode, R.id.stat_btErr_10);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_ACOSTIC    ].TaskStatus, R.id.stat_tvErr_11A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_ACOSTIC    ].ErrCode, R.id.stat_btErr_11);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_POS   ].TaskStatus, R.id.stat_tvErr_12A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_POS   ].ErrCode, R.id.stat_btErr_12);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_UDP      ].TaskStatus, R.id.stat_tvErr_13A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_UDP      ].ErrCode, R.id.stat_btErr_13);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_ABNORMAL  ].TaskStatus, R.id.stat_tvErr_14A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_ABNORMAL  ].ErrCode, R.id.stat_btErr_14);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_PAYLOAD      ].TaskStatus, R.id.stat_tvErr_15A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_PAYLOAD      ].ErrCode, R.id.stat_btErr_15);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_NEWFLY ].TaskStatus, R.id.stat_tvErr_16A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_NEWFLY ].ErrCode, R.id.stat_btErr_16);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_SAILMANAGE ].TaskStatus, R.id.stat_tvErr_17A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_SAILMANAGE ].ErrCode, R.id.stat_btErr_17);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DEPTH ].TaskStatus, R.id.stat_tvErr_18A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DEPTH ].ErrCode, R.id.stat_btErr_18);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_QUADRANS ].TaskStatus, R.id.stat_tvErr_19A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_QUADRANS ].ErrCode, R.id.stat_btErr_19);

	}


    public void showDialog( String message ) {
        new AlertDialog.Builder(requireContext())
                .setMessage(message) // メッセージを設定
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()) // OKボタンで閉じる
                .show();
    }
}

