package info.bbmail.auvterminal;

import android.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragmentQuad extends Fragment implements OnClickListener {

    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ma = (MainActivity)getActivity();

        // fragment再生成抑止
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.subquad, container, false);
		return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        seekBarSet();

        //　ボタンのリスナー設定
        ((Button) ma.findViewById(R.id.quad_btSendToQuad)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.quad_btOpenQuadWeb)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.quad_btQuadCheck)).setOnClickListener(this);
    }


    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Intent intent;
        MainActivity activity = (MainActivity) getActivity();
        TextView tvCourse;
        String outs;

        if(v.getId() == R.id.quad_btSendToQuad){
            ma.SendStartGpsToAuv();
        }

        if(v.getId() == R.id.quad_btOpenQuadWeb){
            EditText tv = (EditText) ma.findViewById(R.id.setup_edQuadIp);
            String Outs = "http://" + tv.getText();
            Uri uri = Uri.parse( Outs );
            startActivity(new Intent(Intent.ACTION_VIEW,uri));
        }

        if(v.getId() == R.id.quad_btQuadCheck){
            int result =  ma.GetCheckResult(0);
            result ++;
            result &= 1;
            ma.ShowCheckResult( 0, result );
        }
    }

    public void reDrawGps()
    {
        ma.cc.SetStringToTextView( ma.ViecleData.TabGps.Qty, R.id.quad_tvSndGpsQty);
        ma.cc.SetStringToTextView( ma.ViecleData.TabGps.Latitude, R.id.quad_tvSndGpsLat);
        ma.cc.SetStringToTextView( ma.ViecleData.TabGps.Longitude, R.id.quad_tvSndGpsLon);
        ma.cc.SetStringToTextView( ma.ViecleData.TabGps.LastRecv, R.id.quad_tvSndGpsRec);

        ma.cc.SetStringToTextView( ma.ViecleData.TabGps.RecvGGAStr, R.id.quad_tvNmeaString);

    }
    public void reDraw()
    {
        ma.cc.SetStringToTextView( ma.ViecleData.SensorQuad.roll, R.id.quad_tvRoll);
        ma.cc.SetStringToTextView( ma.ViecleData.SensorQuad.pitch, R.id.quad_tvPitch);
        ma.cc.SetStringToTextView( ma.ViecleData.SensorQuad.yaw, R.id.quad_tvYaw);

        // アライメント状態表示
        // Roll, Pitch, Yaw の値からアライメント状況を推定する
        double Roll, Pitch, Yaw;
        try {
            Roll = Double.parseDouble( ma.ViecleData.SensorQuad.roll );
            Pitch = Double.parseDouble( ma.ViecleData.SensorQuad.pitch );
            Yaw = Double.parseDouble( ma.ViecleData.SensorQuad.yaw );
       } catch ( NumberFormatException e ) {
            Roll = 0;
            Pitch = 0;
            Yaw = 0;
        }
        //　Roll,Pitch,Yawがそれぞれ０近傍：アライメントしていない
        final double EPSILON = 1e-6;
        if (ma.ViecleData.SensorQuad.roll == null || ma.ViecleData.SensorQuad.roll.trim().isEmpty()) {
            ma.cc.SetStringToTextViewAlign( -1, R.id.quad_tvAlign);
            ma.ViecleData.Param.ArignmentStartTimeSecs = System.currentTimeMillis() / 1000;     // 開始時刻を記憶
        }
        else if (Math.abs(Roll) < EPSILON &&
                Math.abs(Pitch) < EPSILON &&
                Math.abs(Yaw) < EPSILON)
        {
            ma.cc.SetStringToTextViewAlign( -1, R.id.quad_tvAlign);
            ma.ViecleData.Param.ArignmentStartTimeSecs = System.currentTimeMillis() / 1000;     // 開始時刻を記憶
        }
        //　姿勢角が０出なければアライメント中かアライメント完了
        else
        {
            long currentSec = System.currentTimeMillis() / 1000;
            long pastsec = currentSec - ma.ViecleData.Param.ArignmentStartTimeSecs;
            ma.cc.SetStringToTextViewAlign( pastsec, R.id.quad_tvAlign);
        }

    }
}
