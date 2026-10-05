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

public class FragmentPayload extends Fragment implements OnClickListener {

    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ma = (MainActivity)getActivity();

        // fragment再生成抑止
        setRetainInstance(true);
        return inflater.inflate(R.layout.subpayload, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　ボタンのリスナー設定
        ((Button) ma.findViewById(R.id.pld_btPayloadOn)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.pld_btPayloadOff)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.pld_btPayloadRecStart)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.pld_btPayloadRecStop)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.pld_btPayloadCalbStart)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.pld_btPayloadCalbStop)).setOnClickListener(this);

        ((Button) ma.findViewById(R.id.pld_btPayloadCheck)).setOnClickListener(this);

        ((Button) getActivity().findViewById(R.id.pld_btPldTaskError)).setOnClickListener(this);
    }


    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Button bt;
        String ErrorCode;

        if(v.getId() == R.id.pld_btPayloadOn){
                ma.UdpSend( "SSSON");
                ma.cc.ToastOutShort( "Payload Task ON");
        }

        if(v.getId() == R.id.pld_btPayloadOff){
                ma.UdpSend( "SSSOFF");
                ma.cc.ToastOutShort( "Payload Task OFF");
        }

        if(v.getId() == R.id.pld_btPayloadRecStart){
                ma.UdpSend( "PLDRECSTART");
                ma.cc.ToastOutShort( "Payload Task ON");
        }

        if(v.getId() == R.id.pld_btPayloadRecStop){
                ma.UdpSend( "PLDRECSTOP");
                ma.cc.ToastOutShort( "Payload Task OFF");
        }

        if(v.getId() == R.id.pld_btPayloadCalbStart){
                ma.UdpSend( "PLDCALSTART");
                ma.cc.ToastOutShort( "Payload Task ON");
        }

        if(v.getId() == R.id.pld_btPayloadCalbStop){
                ma.UdpSend( "PLDCALSTOP");
                ma.cc.ToastOutShort( "Payload Task OFF");
        }

        if(v.getId() == R.id.pld_btPldTaskError){
                bt = (Button)ma.findViewById( v.getId() );
                ErrorCode = bt.getText().toString();
	            String errOut = ma.cc.getErrorDetail(  ma.ViecleData.ErrStr11, ma.ViecleData.ErrorID_11, ErrorCode );
				showDialog( errOut );
        }

        if(v.getId() == R.id.pld_btPayloadCheck){
                int result =  ma.GetCheckResult(3);
                result ++;
                result &= 1;
                ma.ShowCheckResult( 3, result );
        }

    }
    public void reDraw()
    {
        ma.cc.SetStringToTextView( ma.ViecleData.SensorPayload.StatusStr, R.id.pld_tvPldStatus);
        ma.cc.SetStringToTextViewStatus(ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_PAYLOAD ].TaskStatus, R.id.pld_tvPldTaskStatus);
        ma.cc.SetStringToButton(        ma.ViecleData.Error.taskdat[ ma.ViecleData.Error.TASK_ID_PAYLOAD ].ErrCode, R.id.pld_btPldTaskError);
    }

    public void showDialog( String message ) {
        new AlertDialog.Builder(requireContext())
                .setMessage(message) // メッセージを設定
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()) // OKボタンで閉じる
                .show();
    }

}
