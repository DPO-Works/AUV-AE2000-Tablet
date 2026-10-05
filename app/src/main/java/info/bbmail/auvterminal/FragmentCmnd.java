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

public class FragmentCmnd extends Fragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // fragment再生成抑止
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.subcmnd, container, false);
		return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　ボタンのリスナー設定
        Button mButton01A = (Button) getActivity().findViewById(R.id.btTaskOn_01);
        mButton01A.setOnClickListener(this);
        Button mButton01B = (Button) getActivity().findViewById(R.id.btTaskOff_01);
        mButton01B.setOnClickListener(this);
        Button mButton02A = (Button) getActivity().findViewById(R.id.btTaskOn_02);
        mButton02A.setOnClickListener(this);
        Button mButton02B = (Button) getActivity().findViewById(R.id.btTaskOff_02);
        mButton02B.setOnClickListener(this);
        Button mButton03A = (Button) getActivity().findViewById(R.id.btTaskOn_03);
        mButton03A.setOnClickListener(this);
        Button mButton03B = (Button) getActivity().findViewById(R.id.btTaskOff_03);
        mButton03B.setOnClickListener(this);
        Button mButton04A = (Button) getActivity().findViewById(R.id.btTaskOn_04);
        mButton04A.setOnClickListener(this);
        Button mButton04B = (Button) getActivity().findViewById(R.id.btTaskOff_04);
        mButton04B.setOnClickListener(this);
        Button mButton05A = (Button) getActivity().findViewById(R.id.btTaskOn_05A);
        mButton05A.setOnClickListener(this);
        Button mButton05B = (Button) getActivity().findViewById(R.id.btTaskOff_05A);
        mButton05B.setOnClickListener(this);
        Button mButton05AA = (Button) getActivity().findViewById(R.id.btTaskOn_05B);
        mButton05AA.setOnClickListener(this);
        Button mButton05BB = (Button) getActivity().findViewById(R.id.btTaskOff_05B);
        mButton05BB.setOnClickListener(this);
        Button mButton06A = (Button) getActivity().findViewById(R.id.btTaskOn_06);
        mButton06A.setOnClickListener(this);
        Button mButton06B = (Button) getActivity().findViewById(R.id.btTaskOff_06);
        mButton06B.setOnClickListener(this);
        Button mButton07A = (Button) getActivity().findViewById(R.id.btTaskOn_07);
        mButton07A.setOnClickListener(this);
        Button mButton07B = (Button) getActivity().findViewById(R.id.btTaskOff_07);
        mButton07B.setOnClickListener(this);
        Button mButton08A = (Button) getActivity().findViewById(R.id.btTaskOn_08);
        mButton08A.setOnClickListener(this);
        Button mButton08B = (Button) getActivity().findViewById(R.id.btTaskOff_08);
        mButton08B.setOnClickListener(this);
        Button mButton09A = (Button) getActivity().findViewById(R.id.btTaskOn_09);
        mButton09A.setOnClickListener(this);
        Button mButton10A = (Button) getActivity().findViewById(R.id.btTaskOn_10);
        mButton10A.setOnClickListener(this);

/***
        Button mButton10B = (Button) getActivity().findViewById(R.id.btTaskOff_10);
        mButton10B.setOnClickListener(this);
        Button mButton11A = (Button) getActivity().findViewById(R.id.btTaskOn_11);
        mButton11A.setOnClickListener(this);
        Button mButton11B = (Button) getActivity().findViewById(R.id.btTaskOff_11);
        mButton11B.setOnClickListener(this);
        Button mButton12A = (Button) getActivity().findViewById(R.id.btTaskOn_12);
        mButton12A.setOnClickListener(this);
        Button mButton12B = (Button) getActivity().findViewById(R.id.btTaskOff_12);
        mButton12B.setOnClickListener(this);
**/

        Button mButton02 = (Button) getActivity().findViewById(R.id.btSelectCourseNo);
        mButton02.setOnClickListener(this);
        Button mBtNFly_01 = (Button) getActivity().findViewById(R.id.btNFly_01);
        mBtNFly_01.setOnClickListener(this);
        Button mBtNFly_02 = (Button) getActivity().findViewById(R.id.btNFly_02);
        mBtNFly_02.setOnClickListener(this);
        Button mBtNFly_03 = (Button) getActivity().findViewById(R.id.btNFly_03);
        mBtNFly_03.setOnClickListener(this);
/***
        Button mBtNFly_04 = (Button) getActivity().findViewById(R.id.btNFly_04);
        mBtNFly_04.setOnClickListener(this);
***/
        Button mBtThP_01 = (Button) getActivity().findViewById(R.id.btThP_01);
        mBtThP_01.setOnClickListener(this);
        Button mBtThN_01 = (Button) getActivity().findViewById(R.id.btThN_01);
        mBtThN_01.setOnClickListener(this);
        Button mBtThS_01 = (Button) getActivity().findViewById(R.id.btThS_01);
        mBtThS_01.setOnClickListener(this);

        Button mBtThP_02 = (Button) getActivity().findViewById(R.id.btThP_02);
        mBtThP_02.setOnClickListener(this);
        Button mBtThN_02 = (Button) getActivity().findViewById(R.id.btThN_02);
        mBtThN_02.setOnClickListener(this);
        Button mBtThS_02 = (Button) getActivity().findViewById(R.id.btThS_02);
        mBtThS_02.setOnClickListener(this);

        Button mBtThP_03 = (Button) getActivity().findViewById(R.id.btThP_03);
        mBtThP_03.setOnClickListener(this);
        Button mBtThN_03 = (Button) getActivity().findViewById(R.id.btThN_03);
        mBtThN_03.setOnClickListener(this);
        Button mBtThS_03 = (Button) getActivity().findViewById(R.id.btThS_03);
        mBtThS_03.setOnClickListener(this);

        Button mPldButton01A = (Button) getActivity().findViewById(R.id.btPldOn_01);
        mPldButton01A.setOnClickListener(this);
        Button mPldButton01B = (Button) getActivity().findViewById(R.id.btPldOff_01);
        mPldButton01B.setOnClickListener(this);
        Button mPldButton02A = (Button) getActivity().findViewById(R.id.btPldOn_02);
        mPldButton02A.setOnClickListener(this);
        Button mPldButton02B = (Button) getActivity().findViewById(R.id.btPldOff_02);
        mPldButton02B.setOnClickListener(this);
        Button mPldButton03A = (Button) getActivity().findViewById(R.id.btPldOn_03);
        mPldButton03A.setOnClickListener(this);
        Button mPldButton03B = (Button) getActivity().findViewById(R.id.btPldOff_03);
        mPldButton03B.setOnClickListener(this);

/*
        mButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.ToastOutShort( "select Course !");

//                    CourseSelectDialogShow( v );
                } catch (ClassCastException e) {
                    throw new ClassCastException("activity が btGpsOn のListener を実装していません.");
                }
            }

        });
*/
    }


    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Intent intent;
        MainActivity activity = (MainActivity) getActivity();
        TextView tvCourse;
        String outs;

		if( v.getId() == R.id.btTaskOn_01 ) {
                activity.UdpSend( "DVLON");
                activity.cc.ToastOutShort( "DVL Task ON");
		}
		if( v.getId() == R.id.btTaskOff_01 ) {
                activity.UdpSend( "DVLOF");
                activity.cc.ToastOutShort( "DVL Task OFF");
		}
		if( v.getId() == R.id.btTaskOn_02 ) {
                activity.UdpSend( "DON05");
                activity.cc.ToastOutShort( "Acoustic Power ON");
		}
		if( v.getId() == R.id.btTaskOff_02 ) {
                activity.UdpSend( "DOF05");
                activity.cc.ToastOutShort( "Acoustic Power OFF");
		}
		if( v.getId() == R.id.btTaskOn_03 ) {
                activity.UdpSend( "DELON");
                activity.cc.ToastOutShort( "Payload Task ON");
		}
		if( v.getId() == R.id.btTaskOff_03 ) {
                activity.UdpSend( "DELOF");
                activity.cc.ToastOutShort( "Payload Task OFF");
		}
		if( v.getId() == R.id.btTaskOn_04 ) {
                activity.UdpSend( "ERRSTART");
                activity.cc.ToastOutShort( "Error Check Task ON");
		}
		if( v.getId() == R.id.btTaskOff_04 ) {
                activity.UdpSend( "ERRSTOP");
                activity.cc.ToastOutShort( "Error Check Task OFF");
		}

		if( v.getId() == R.id.btTaskOn_05A ) {
                activity.UdpSend( "MAGON");
                activity.cc.ToastOutShort( "MAG Task ON");
		}
		if( v.getId() == R.id.btTaskOff_05A ) {
                activity.UdpSend( "MAGOFF");
                activity.cc.ToastOutShort( "MAG TASK OFF");
		}
		if( v.getId() == R.id.btTaskOn_05B ) {
                activity.UdpSend( "ACTON");
                activity.cc.ToastOutShort( "MiniCT Task ON");
		}
		if( v.getId() == R.id.btTaskOff_05B ) {
                activity.UdpSend( "ACTOF");
                activity.cc.ToastOutShort( "MiniCT Task OFF");
		}

		if( v.getId() == R.id.btTaskOn_06 ) {
                activity.UdpSend( "DON02");
                activity.cc.ToastOutShort( "Actuator Power ON");
		}
		if( v.getId() == R.id.btTaskOff_06 ) {
                activity.UdpSend( "DOF02");
                activity.cc.ToastOutShort( "Thruster Power OFF");
		}
		if( v.getId() == R.id.btTaskOn_07 ) {
                activity.UdpSend( "DON03");
                activity.cc.ToastOutShort( "Dive Ballast Hold");
		}
		if( v.getId() == R.id.btTaskOff_07 ) {
                activity.UdpSend( "DOF03");
                activity.cc.ToastOutShort( "Dive Ballast Open");
		}
		if( v.getId() == R.id.btTaskOn_08 ) {
                activity.UdpSend( "DON04");
                activity.cc.ToastOutShort( "Surface Ballast Hold");
		}
		if( v.getId() == R.id.btTaskOff_08 ) {
                activity.UdpSend( "DOF04");
                activity.cc.ToastOutShort( "Surface Ballast Open");
		}

		if( v.getId() == R.id.btTaskOn_09 ) {
                activity.UdpSend( "DEPRST00");
                activity.cc.ToastOutShort( "Depth 0 Reset");
		}
		if( v.getId() == R.id.btTaskOn_10 ) {
                String outLat = activity.GpsLatDegString();
                String outLon = activity.GpsLonDegString();
                if( outLat != "" && outLon != "" ) {
                    activity.UdpSend(outLat);
                    activity.UdpSend(outLon);
                    activity.cc.ToastOutShort( "GPS Position Set");
                }
                else
                    activity.cc.ToastOutShort( "GPS Position Set Fault");
		}

		if( v.getId() == R.id.btSelectCourseNo ) {
                CourseSelectDialogShow(v);
		}

		if( v.getId() == R.id.btNFly_01 ) {
                tvCourse = (TextView)getActivity().findViewById(R.id.NflyCourseNo);
                outs = "RESRV" + tvCourse.getText();
                activity.UdpSend( outs );
                activity.cc.ToastOutShort( "NEWFLY Reservation" );
                // 予約時刻のセット
                Time time = new Time("Asia/Tokyo");
                time.setToNow();
                String TimeStr = String.format("%02d:%02d:%02d", time.hour, time.minute, time.second);
                activity.cc.SetStringToTextView( TimeStr, R.id.dive_tvReserveTime);
		}

		if( v.getId() == R.id.btNFly_02 ) {
                tvCourse = (TextView)getActivity().findViewById(R.id.NflyCourseNo);
                outs = "START" + tvCourse.getText();
                activity.UdpSend( outs );
                activity.cc.ToastOutShort( "NEWFLY Start" );
                activity.cc.SetStringToTextView( "", R.id.dive_tvReserveTime);
		}
		if( v.getId() == R.id.btNFly_03 ) {
                activity.UdpSend( "STOP");
                activity.cc.ToastOutShort( "NEWFLY Stop");
                activity.cc.SetStringToTextView( "", R.id.dive_tvReserveTime);
		}
		if( v.getId() == R.id.btThP_01 ) {
                activity.UdpSend( "THL+10");
                activity.cc.ToastOutShort( "Left Thruster +10%");
		}

		if( v.getId() == R.id.btThN_01 ) {
                activity.UdpSend( "THL-10");
                activity.cc.ToastOutShort( "Left Thruster -10%");
		}
		if( v.getId() == R.id.btThS_01 ) {
                activity.UdpSend( "THL+00");
                activity.cc.ToastOutShort( "Left Thruster Stop");
		}
		if( v.getId() == R.id.btThP_02 ) {
                activity.UdpSend( "THR+10");
                activity.cc.ToastOutShort( "Right Thruster +10%");
		}
		if( v.getId() == R.id.btThN_02 ) {
                activity.UdpSend( "THR-10");
                activity.cc.ToastOutShort( "Right Thruster -10%");
		}
		if( v.getId() == R.id.btThS_02 ) {
                activity.UdpSend( "THR+00");
                activity.cc.ToastOutShort( "Right Thruster Stop");
		}
		if( v.getId() == R.id.btThP_03 ) {
                activity.UdpSend( "ELV+20");
                activity.cc.ToastOutShort( "Elevator +20deg");
		}
		if( v.getId() == R.id.btThN_03 ) {
                activity.UdpSend( "ELV-20");
                activity.cc.ToastOutShort( "Elevator -20deg");
		}
		if( v.getId() == R.id.btThS_03 ) {
                activity.UdpSend( "ELV+00");
                activity.cc.ToastOutShort( "Elevator Center");
		}
		if( v.getId() == R.id.btPldOn_01 ) {
                activity.UdpSend( "SSSON");
                activity.cc.ToastOutShort( "Payload Power/Task ON");
		}
		if( v.getId() == R.id.btPldOff_01 ) {
                activity.UdpSend( "SSSOFF");
                activity.cc.ToastOutShort( "Payload Power/Task OFF");
		}
		if( v.getId() == R.id.btPldOn_02 ) {
                activity.UdpSend( "PLDRECSTART");
                activity.cc.ToastOutShort( "Payload Recording ON");
		}
		if( v.getId() == R.id.btPldOff_02 ) {
                activity.UdpSend( "PLDRECSTOP");
                activity.cc.ToastOutShort( "Payload Recording  OFF");
		}
		if( v.getId() == R.id.btPldOn_03 ) {
                activity.UdpSend( "PLDCALSTART");
                activity.cc.ToastOutShort( "Payload Calibration ON");
		}
		if( v.getId() == R.id.btPldOff_03 ) {
                activity.UdpSend( "PLDCALSTOP");
                activity.cc.ToastOutShort( "Payload Calibration OFF");
		}
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
                TextView tvCourse = (TextView)getActivity().findViewById(R.id.NflyCourseNo);
                 tvCourse.setText( CourseNoS[which] );
             }
        });
        alertDialogBuilder .show();
     }

//                MainActivity activity = (MainActivity) getActivity();


}
