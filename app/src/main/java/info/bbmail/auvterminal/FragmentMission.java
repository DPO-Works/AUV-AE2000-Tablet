package info.bbmail.auvterminal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TextView;

public class FragmentMission extends Fragment implements OnClickListener {

    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ma = (MainActivity)getActivity();

        // fragment再生成抑止
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.submission, container, false);
		return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　ボタンのリスナー設\定
        ((Button) ma.findViewById(R.id.mission_btEditPos)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.mission_btGpsPos)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.mission_btDepth0Set)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.mission_btDepth03Set)).setOnClickListener(this);
        ((Button) ma.findViewById(R.id.mission_btMissionCheck)).setOnClickListener(this);
    }

    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Button bt;
        EditText et;
        String outs;
        String ErrorCode;
        
        if(v.getId() == R.id.mission_btEditPos){
                String Lats, Lons, wstr;
                try {
                    et = (EditText)ma.findViewById(R.id.mission_tvAuvLatEdit);
                    wstr = et.getText().toString();
                    Lats =  LatLonConvert(wstr);

                    et = (EditText)ma.findViewById(R.id.mission_tvAuvLonEdit);
                    wstr = et.getText().toString();
                    Lons = LatLonConvert(wstr);

                    if( Lats != "" && Lons !="" ) {
                        ma.UdpSend("ALAT" + Lats);
                        ma.UdpSend("ALON" + Lons);
                        ma.cc.ToastOutShort("Position Set");
                    }
                    else {
                        ma.cc.ToastOutShort("Position Value fault!");
                    }

                } catch (ClassCastException e) {
                    throw new ClassCastException("Position Strings Fault!");
                }
        }

        if(v.getId() == R.id.mission_btGpsPos){
                String outLat = ma.GpsLatDegString();
                String outLon = ma.GpsLonDegString();
                if( outLat != "" && outLon != "" ) {
                    ma.UdpSend(outLat);
                    ma.UdpSend(outLon);
                    ma.cc.ToastOutShort( "GPS Position Set");
                }
                else
                    ma.cc.ToastOutShort( "GPS Position Set Fault");
        }
        if(v.getId() == R.id.mission_btDepth0Set){
                ma.UdpSend( "DEPRST00");
                ma.cc.ToastOutShort( "Depth 0 Reset");
        }
        if(v.getId() == R.id.mission_btDepth03Set){
                ma.UdpSend( "DEPRST03");
                ma.cc.ToastOutShort( "Depth 0.3 Reset");
		}
        if(v.getId() == R.id.mission_btMissionCheck){
                int result =  ma.GetCheckResult(6);
                result ++;
                result &= 1;
                ma.ShowCheckResult( 6, result );
        }
        FocusClear();

    }
    public void reDrawGps()
    {
        ma.cc.SetStringToTextView( ma.ViecleData.TabGps.Qty, R.id.mission_tvSndGpsQty);
        ma.cc.SetStringToTextView( ma.ViecleData.TabGps.Latitude, R.id.mission_tvSndGpsLat);
        ma.cc.SetStringToTextView( ma.ViecleData.TabGps.Longitude, R.id.mission_tvSndGpsLon);
        ma.cc.SetStringToTextView( ma.ViecleData.TabGps.LastRecv, R.id.mission_tvSndGpsRec);
    }
    public void reDraw()
    {
        ma.cc.SetStringToTextView( ma.ViecleData.SensorPos.Lat, R.id.mission_tvAuvLat);
        ma.cc.SetStringToTextView( ma.ViecleData.SensorPos.Lon, R.id.mission_tvAuvLon);

        ma.cc.SetStringToTextView( ma.ViecleData.SensorPos.Depth, R.id.mission_tvDepth);

// 編集用に以下を仮設定
//        R.id.mission_tvAuvLatEdit
//        R.id.mission_tvAuvLonEdit

    }

    public void FocusClear() {
        (getActivity().findViewById(R.id.mission_tvSettingTitle)).requestFocus();
   }

    public String LatLonConvert( String instr )
    {
        String outstr;
        double val = ma.nm.FromDMM( instr );
        if( val == 0 )
            outstr="";
        else
            outstr =  String.format( "%+09.6f", val );
        return outstr;
    }

    public void showDialog( String message ) {
        new AlertDialog.Builder(requireContext())
                .setMessage(message) // メッセージを設定
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss()) // OKボタンで閉じる
                .show();
    }


}
