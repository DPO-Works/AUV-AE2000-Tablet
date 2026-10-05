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

public class FragmentAct extends Fragment implements OnClickListener {

    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ma = (MainActivity)getActivity();

        // fragment再生成抑止
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.subact, container, false);
		return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　ボタンのリスナー設定
        ((Button) ma.findViewById(R.id.act_btActOn)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.act_btActOff)).setOnClickListener(this);

        ((Button) ma.findViewById(R.id.act_btThR_P)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.act_btThR_Stop)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.act_btThR_N)).setOnClickListener(this);

        ((Button) ma.findViewById(R.id.act_btThL_P)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.act_btThL_Stop)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.act_btThL_N)).setOnClickListener(this);

        ((Button) ma.findViewById(R.id.act_btElL_P)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.act_btElL_Stop)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.act_btElL_N)).setOnClickListener(this);


        ((Button) getActivity().findViewById(R.id.act_btActCheck)).setOnClickListener(this);
    }

    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Button bt;
        String ErrorCode;
		if( v.getId() == R.id.act_btActOn ){
                ma.UdpSend( "ACTON");
                ma.cc.ToastOutShort( "Actuator Power ON");
		}

		if( v.getId() == R.id.act_btActOff ){
                ma.UdpSend( "ACTOF");
                ma.cc.ToastOutShort( "Thruster Power OFF");
		}

		if( v.getId() == R.id.act_btThR_P ) {
                ma.UdpSend( "THR+10");
                ma.cc.ToastOutShort( "Right Thruster +10%");
		}
		if( v.getId() == R.id.act_btThR_Stop ){
                ma.UdpSend( "THR+00");
                ma.cc.ToastOutShort( "Right Thruster Stop");
		}
		if( v.getId() == R.id.act_btThR_N ){
                ma.UdpSend( "THR-10");
                ma.cc.ToastOutShort( "Right Thruster -10%");
		}

		if( v.getId() == R.id.act_btThL_P ){
                ma.UdpSend( "THL+10");
                ma.cc.ToastOutShort( "Left Thruster +10%");
		}
		if( v.getId() == R.id.act_btThL_Stop ) {
                ma.UdpSend( "THL+00");
                ma.cc.ToastOutShort( "Left Thruster Stop");
		}
		if( v.getId() == R.id.act_btThL_N ) {
                ma.UdpSend( "THL-10");
                ma.cc.ToastOutShort( "Left Thruster -10%");
		}

		if( v.getId() == R.id.act_btElL_P ) {
                ma.UdpSend( "ELV+20");
                ma.cc.ToastOutShort( "Elevator +20deg");
		}
		if( v.getId() == R.id.act_btElL_Stop ) {
                ma.UdpSend( "ELV+00");
                ma.cc.ToastOutShort( "Elevator Center");
		}
		if( v.getId() == R.id.act_btElL_N ) {
                ma.UdpSend( "ELV-20");
                ma.cc.ToastOutShort( "Elevator -20deg");
		}

		if( v.getId() == R.id.act_btActCheck ) {
                int result =  ma.GetCheckResult(5);
                result ++;
                result &= 1;
                ma.ShowCheckResult( 5, result );
		}
    }


    public void reDraw()
    {
        ma.cc.SetStringToTextViewOnOff( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_THRUSTPWR ], R.id.act_tvThrustPwr);
        ma.cc.SetStringToTextViewOnOff( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_ELVPWR ], R.id.act_tvWingPwr);

        ma.cc.SetStringToTextView( ma.ViecleData.io.AI[ ma.ViecleData.io.AI_ELV ], R.id.act_tvWingFbL);
        ma.cc.SetStringToTextView( ma.ViecleData.ActuatorWing.Order, R.id.act_tvWingOrderL);

        ma.cc.SetStringToTextView( ma.ViecleData.ActuatorThrust.leftOrder , R.id.act_tvThrustOrderL);
        ma.cc.SetStringToTextView( ma.ViecleData.ActuatorThrust.rightOrder, R.id.act_tvThrustOrderR);

    }
}
