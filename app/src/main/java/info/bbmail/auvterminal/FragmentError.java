package info.bbmail.auvterminal;

import android.app.Activity;
import android.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
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


public class FragmentError extends Fragment implements OnClickListener {

    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ma = (MainActivity)getActivity();

        // fragment再生成抑止
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.suberror, container, false);
		return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　ボタンのリスナー設定
        ((Button) getActivity().findViewById(R.id.err_btErrorCheckStart)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErrorCheckStop)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErrorListCheck)).setOnClickListener(this);

        ((Button) getActivity().findViewById(R.id.err_btErr_01)).setOnClickListener(this);

        ((Button) getActivity().findViewById(R.id.err_btErr_05)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_06)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_07)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_08)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_09)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_10)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_11)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_12)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_13)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_14)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_15)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_16)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_17)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_18)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_19)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.err_btErr_20)).setOnClickListener(this);

        // タスク名のセット
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask01, R.id.err_tvErr_05t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask02, R.id.err_tvErr_06t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask03, R.id.err_tvErr_07t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask04, R.id.err_tvErr_08t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask05, R.id.err_tvErr_09t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask06, R.id.err_tvErr_10t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask07, R.id.err_tvErr_11t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask08, R.id.err_tvErr_12t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask09, R.id.err_tvErr_13t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask10, R.id.err_tvErr_14t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask11, R.id.err_tvErr_15t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask12, R.id.err_tvErr_16t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask13, R.id.err_tvErr_17t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask14, R.id.err_tvErr_18t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask15, R.id.err_tvErr_19t);
        ma.cc.SetStringToTextView( ma.ViecleData.ErrTask16, R.id.err_tvErr_20t);
   }


    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Intent intent;
        TextView tvCourse;
        String outs;
        String errOut = null;
    	Button tv = (Button) getActivity().findViewById(v.getId());
        String ErrorCode = tv.getText().toString();
    	
        if(v.getId() ==  R.id.err_btErr_01) {
//            String logs = (String) ((TextView) ma.findViewById(R.id.err_tvErr_02)).getText();
            String logs = (String) ma.ViecleData.Error.ErrTask;
            ShowError(logs, v.getId());
        }
        if(v.getId() ==  R.id.err_btErr_05) showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr01, ma.ViecleData.ErrorID_01, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_06)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr02, ma.ViecleData.ErrorID_02, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_07)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr03, ma.ViecleData.ErrorID_03, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_08)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr04, ma.ViecleData.ErrorID_04, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_09)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr05, ma.ViecleData.ErrorID_05, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_10)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr06, ma.ViecleData.ErrorID_06, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_11)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr07, ma.ViecleData.ErrorID_07, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_12)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr08, ma.ViecleData.ErrorID_08, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_13)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr09, ma.ViecleData.ErrorID_09, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_14)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr10, ma.ViecleData.ErrorID_10, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_15)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr11, ma.ViecleData.ErrorID_11, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_16)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr12, ma.ViecleData.ErrorID_12, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_17)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr13, ma.ViecleData.ErrorID_13, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_18)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr14, ma.ViecleData.ErrorID_14, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_19)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr15, ma.ViecleData.ErrorID_15, ErrorCode ));
        if(v.getId() ==  R.id.err_btErr_20)	showDialog( ma.cc.getErrorDetail(  ma.ViecleData.ErrStr16, ma.ViecleData.ErrorID_16, ErrorCode ));

        if(v.getId() == R.id.err_btErrorCheckStart) {
            ma.UdpSend( "ERRSTART");
            ma.cc.ToastOutShort( "Error Check Task ON");
        }
        if(v.getId() == R.id.err_btErrorCheckStop) {
            ma.UdpSend( "ERRSTOP");
            ma.cc.ToastOutShort( "Error Check Task OFF");
        }
        if(v.getId() == R.id.err_btErrorListCheck) {
            int result = ma.GetCheckResult(7);
            result++;
            result &= 1;
            ma.ShowCheckResult(7, result);
        }
    }

    // 概略エラー表示
    public void ShowError( String TaskNoStr, int tvID ) {
        int id;
        try{
            id = Integer.parseInt(TaskNoStr);
            id ++;
        }
        catch( Exception e )
        {
            id = 0;
        }

        Button tv = (Button) getActivity().findViewById(tvID);
        String ErrorCode = tv.getText().toString();
        String errOut = null;
    	switch (id) {
            case 0:
            default:
                MainActivity activity = (MainActivity) getActivity();

                activity.cc.ToastOutLong("NO ERROR");
                break;
            case 1:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr01, ma.ViecleData.ErrorID_01, ErrorCode ); break;
            case 2:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr02, ma.ViecleData.ErrorID_02, ErrorCode ); break;
            case 3:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr03, ma.ViecleData.ErrorID_03, ErrorCode ); break;
            case 4:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr04, ma.ViecleData.ErrorID_04, ErrorCode ); break;
            case 5:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr05, ma.ViecleData.ErrorID_05, ErrorCode ); break;
            case 6:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr06, ma.ViecleData.ErrorID_06, ErrorCode ); break;
            case 7:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr07, ma.ViecleData.ErrorID_07, ErrorCode ); break;
            case 8:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr08, ma.ViecleData.ErrorID_08, ErrorCode ); break;
            case 9:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr09, ma.ViecleData.ErrorID_09, ErrorCode ); break;
            case 10:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr10, ma.ViecleData.ErrorID_10, ErrorCode );break;
            case 11:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr11, ma.ViecleData.ErrorID_11, ErrorCode );break;
            case 12:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr12, ma.ViecleData.ErrorID_12, ErrorCode );break;
            case 13:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr13, ma.ViecleData.ErrorID_13, ErrorCode );break;
            case 14:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr14, ma.ViecleData.ErrorID_14, ErrorCode );break;
            case 15:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr15, ma.ViecleData.ErrorID_15, ErrorCode );break;
            case 16:errOut = ma.cc.getErrorDetail( ma.ViecleData.ErrStr16, ma.ViecleData.ErrorID_16, ErrorCode );break;
        }
        if (errOut != null && !errOut.isEmpty())
            showDialog( errOut );

    }

	public void reDraw()
	{
            ma.cc.SetStringToButton( ma.ViecleData.Error.ErrBit, R.id.err_btErr_01);

            if( ma.ViecleData.Error.ErrProc == "0" )
                ma.cc.SetStringToTextView( ma.TaskIDToName("-1"), R.id.err_tvErr_02);
            else
                ma.cc.SetStringToTextView( ma.TaskIDToName(ma.ViecleData.Error.ErrTask), R.id.err_tvErr_02);

            ma.cc.SetStringToTextView( ma.ErrorProcIDToName(ma.ViecleData.Error.ErrProc), R.id.err_tvErr_03);
            ma.cc.SetStringToTextView( ma.cc.SerialSec2Time(ma.ViecleData.Error.ErrTime), R.id.err_tvErr_04);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_ABNORMAL].TaskStatus, R.id.err_tvAbnormalTaskStatus);

            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_MAIN       ].TaskStatus, R.id.err_tvErr_05A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_MAIN       ].ErrCode, R.id.err_btErr_05);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DVL      ].TaskStatus, R.id.err_tvErr_06A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DVL      ].ErrCode, R.id.err_btErr_06);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_CURMON   ].TaskStatus, R.id.err_tvErr_07A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_CURMON   ].ErrCode, R.id.err_btErr_07);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_GPS   ].TaskStatus, R.id.err_tvErr_08A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_GPS   ].ErrCode, R.id.err_btErr_08);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_IO      ].TaskStatus, R.id.err_tvErr_09A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_IO      ].ErrCode, R.id.err_btErr_09);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DELTAT ].TaskStatus, R.id.err_tvErr_10A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DELTAT ].ErrCode, R.id.err_btErr_10);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_MAG      ].TaskStatus, R.id.err_tvErr_11A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_MAG      ].ErrCode, R.id.err_btErr_11);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_ACOSTIC    ].TaskStatus, R.id.err_tvErr_12A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_ACOSTIC    ].ErrCode, R.id.err_btErr_12);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_POS   ].TaskStatus, R.id.err_tvErr_13A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_POS   ].ErrCode, R.id.err_btErr_13);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_UDP      ].TaskStatus, R.id.err_tvErr_14A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_UDP      ].ErrCode, R.id.err_btErr_14);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_ABNORMAL  ].TaskStatus, R.id.err_tvErr_15A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_ABNORMAL  ].ErrCode, R.id.err_btErr_15);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_PAYLOAD      ].TaskStatus, R.id.err_tvErr_16A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_PAYLOAD      ].ErrCode, R.id.err_btErr_16);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_NEWFLY ].TaskStatus, R.id.err_tvErr_17A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_NEWFLY ].ErrCode, R.id.err_btErr_17);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_SAILMANAGE ].TaskStatus, R.id.err_tvErr_18A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_SAILMANAGE ].ErrCode, R.id.err_btErr_18);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DEPTH ].TaskStatus, R.id.err_tvErr_19A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_DEPTH ].ErrCode, R.id.err_btErr_19);
            ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_QUADRANS ].TaskStatus, R.id.err_tvErr_20A);
            ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_QUADRANS ].ErrCode, R.id.err_btErr_20);

	}

    public void showDialog( String message ) {
        new AlertDialog.Builder(requireContext())
                .setMessage(message) // メッセージを設定
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()) // OKボタンで閉じる
                .show();
    }

}
