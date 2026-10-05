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

public class FragmentSensor extends Fragment implements OnClickListener {

    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ma = (MainActivity)getActivity();

        // fragment再生成抑止
        setRetainInstance(true);
        return inflater.inflate(R.layout.subsensor, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　ボタンのリスナー設定
/***
        ((Button) ma.findViewById(R.id.sensor_btMagStart)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.sensor_btMagStop)).setOnClickListener(this);
        ((Button) getActivity().findViewById(R.id.sensor_btMagTaskError)).setOnClickListener(this);
***/

        Button bt;
        bt = (Button)ma.findViewById(R.id.sensor_btMagTaskError );
        bt.setEnabled(false);

        ((Button) ma.findViewById(R.id.sensor_btSensCheck)).setOnClickListener(this);
    }


    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Button bt;
        String ErrorCode;

/***
        if(v.getId() == R.id.sensor_btMagStart) {
            ma.UdpSend( "MAGON");
            ma.cc.ToastOutShort( "MAG Task ON");
        }

        if(v.getId() == R.id.sensor_btMagStop) {
            ma.UdpSend( "MAGOFF");
            ma.cc.ToastOutShort( "MAG Task OFF");
        }

        if(v.getId() == R.id.sensor_btMagTaskError) {
            bt = (Button)ma.findViewById( v.getId() );
            ErrorCode = bt.getText().toString();
	        String errOut = ma.cc.getErrorDetail(  ma.ViecleData.ErrStr10, ma.ViecleData.ErrorID_10, ErrorCode );
    	    showDialog( errOut );

        }
****/
        if(v.getId() == R.id.sensor_btSensCheck) {
            int result =  ma.GetCheckResult(2);
            result ++;
            result &= 1;
            ma.ShowCheckResult( 2, result );
        }
    }
    public void reDraw()
    {
/***

        ma.cc.SetStringToTextView( ma.ViecleData.SensorMag.X, R.id.sensor_tvMagX);
        ma.cc.SetStringToTextView( ma.ViecleData.SensorMag.Y, R.id.sensor_tvMagY);
        ma.cc.SetStringToTextView( ma.ViecleData.SensorMag.Z, R.id.sensor_tvMagZ);
        ma.cc.SetStringToTextView( ma.ViecleData.SensorMag.Temp, R.id.sensor_tvMagTemp);

        ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.THREAD_ID_MAG      ].TaskStatus, R.id.sensor_tvMagTaskStatus);
        ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.THREAD_ID_MAG      ].ErrCode, R.id.sensor_btMagTaskError);
****/

    }

    public void showDialog( String message ) {
        new AlertDialog.Builder(requireContext())
                .setMessage(message) // メッセージを設定
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()) // OKボタンで閉じる
                .show();
    }

}
