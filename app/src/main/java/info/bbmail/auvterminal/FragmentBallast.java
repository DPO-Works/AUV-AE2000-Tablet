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

public class FragmentBallast extends Fragment implements OnClickListener {

    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ma = (MainActivity)getActivity();

        // fragment再生成抑止
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.subballast, container, false);
		return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　ボタンのリスナー設定
        ((Button) ma.findViewById(R.id.blst_btDiveBallastKeep)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.blst_btDiveBallastOpen)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.blst_btSurfaceBallastKeep)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.blst_btSurfaceBallastOpen)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.blst_btAdjustBallastKeep)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.blst_btAdjustBallastOpen)).setOnClickListener(this);

        ((Button) getActivity().findViewById(R.id.blst_btBallastCheck)).setOnClickListener(this);

    }


    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Button bt;
        String ErrorCode;
        if(v.getId()== R.id.blst_btDiveBallastKeep){
                ma.UdpSend( "DON02");
                ma.cc.ToastOutShort( "Dive Ballast Hold");
        }
        if(v.getId()== R.id.blst_btDiveBallastOpen){
                ma.UdpSend( "DOF02");
                ma.cc.ToastOutShort( "Dive Ballast Open");
        }
        if(v.getId()== R.id.blst_btSurfaceBallastKeep){
                ma.UdpSend( "DON03");
                ma.cc.ToastOutShort( "Surface Ballast Hold");
        }
        if(v.getId()== R.id.blst_btSurfaceBallastOpen){
                ma.UdpSend( "DOF03");
                ma.cc.ToastOutShort( "Surface Ballast Open");
        }
        if(v.getId()== R.id.blst_btAdjustBallastKeep){
                ma.UdpSend( "DON04");
                ma.cc.ToastOutShort( "Adjustment Ballast Hold");
        }
        if(v.getId()== R.id.blst_btAdjustBallastOpen){
                ma.UdpSend( "DOF04");
                ma.cc.ToastOutShort( "Adjustment Ballast Open");
        }
        if(v.getId()== R.id.blst_btBallastCheck) {
            int result = ma.GetCheckResult(4);
            result++;
            result &= 1;
            ma.ShowCheckResult(4, result);
        }
    }
    public void reDraw()
    {
        ma.cc.SetStringToTextViewBallast( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BALASTPWR1 ], R.id.blst_tvDiveBallast);
        ma.cc.SetStringToTextViewBallast( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BALASTPWR2 ], R.id.blst_tvSurfaceBallast);
        ma.cc.SetStringToTextViewBallast( ma.ViecleData.io.DI[ ma.ViecleData.io.DI_BALASTPWR3 ], R.id.blst_tvAdjustBallast);
    }
}
