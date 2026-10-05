package info.bbmail.auvterminal;

import android.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentSetup extends Fragment implements OnClickListener {

    MainActivity ma;
	sshCmnd		sshc;
    String AuvIp;
    int   AuvPort;
    String GpsIp;
    int   GpsPort;
    String QuadIp;
	String VncPackage;

	////// 以下部分を必要に応じて書き換えること
    String OS_user = "cpu-test";
    String OS_pass = "cpu-test";
	String SDownCmnd = ". /home/cpu-test/auv.sdown";
	String RebootCmnd = ". /home/cpu-test/auv.reboot";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ma = (MainActivity)getActivity();
        sshc = new sshCmnd();

        // fragment再生成抑止
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.subsetup, container, false);
		return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 設定ファイルから取得
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        AuvIp = prefs.getString("AuvIP", "192.168.1.45");
        AuvPort = prefs.getInt("AuvPort", 2000 );
        GpsIp = prefs.getString("GpsIP", "192.168.36.131");
        GpsPort = prefs.getInt("GpsPort", 8120 );
        QuadIp = prefs.getString("QuadIP", "192.168.36.1");
//        VncPackage = prefs.getString("VncPackage", "");

        ((EditText)(ma.findViewById(R.id.setup_edAuvIp))).setText( AuvIp );
        ((EditText)(ma.findViewById(R.id.setup_edAuvPort))).setText(  String.valueOf( AuvPort ) );
        ((EditText)(ma.findViewById(R.id.setup_edGpsIp))).setText( GpsIp );
        ((EditText)(ma.findViewById(R.id.setup_edGpsPort))).setText(  String.valueOf( GpsPort ) );
        ((EditText)(ma.findViewById(R.id.setup_edQuadIp))).setText( QuadIp );

//        ((EditText)(ma.findViewById(R.id.setup_edVncPackage))).setText( VncPackage );


        //　ボタンのリスナー設定
        (ma.findViewById(R.id.setup_btTabletGpsSetting)).setOnClickListener(this);
        (ma.findViewById(R.id.setup_btGpsStart)).setOnClickListener(this);
        (ma.findViewById(R.id.setup_btGpsStop)).setOnClickListener(this);

        (ma.findViewById(R.id.setup_btTabletWifiSetting)).setOnClickListener(this);
        (ma.findViewById(R.id.setup_btLinkStart)).setOnClickListener(this);
        (ma.findViewById(R.id.setup_btLinkStop)).setOnClickListener(this);

        (ma.findViewById(R.id.setup_btSaveSetting)).setOnClickListener(this);
    	
    	
        (ma.findViewById(R.id.setup_btClearList)).setOnClickListener(this);
        (ma.findViewById(R.id.setup_btTESTSET)).setOnClickListener(this);

        (ma.findViewById(R.id.setup_btReboot)).setOnClickListener(this);
        (ma.findViewById(R.id.setup_btShutdown)).setOnClickListener(this);


        String verInfoStr = ma.getVersionName( getActivity());
        ma.cc.SetStringToTextView( "Version : " + verInfoStr, R.id.setup_tvVersion);

    }


    // onClickListener で処理できるものを処理する
    public void onClick(View v) {

        Intent intent;
        TextView tvCourse;
        String outs;
        if(v.getId() == R.id.setup_btTabletGpsSetting) {
            intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);                      // アクティビティを起動
        }
        if(v.getId() == R.id.setup_btGpsStart) {
            try{
                if( ma.gpsEnabled == true )
                    ma.GpsStop();
                ma.GpsStart();
                ma.gpsEnabled = true;
                ma.cc.ToastOutShort( "Gps ON");
            } catch (ClassCastException e) {
                throw new ClassCastException("activity が btGpsOn のListener を実装していません.");
            }
        }
        if(v.getId() == R.id.setup_btGpsStop) {
            try {
                if( ma.gpsEnabled == true )
                    ma.GpsStop();
                ma.gpsEnabled = false;
                ma.cc.ToastOutShort( "Gps OFF");
            } catch (ClassCastException e) {
                throw new ClassCastException("activity が btGpsOff のListener を実装していません.");
            }
        }
        if(v.getId() == R.id.setup_btTabletWifiSetting) {
            intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
            startActivity(intent);                      // アクティビティを起動
        }
        if(v.getId() == R.id.setup_btLinkStart) {
            try {
                ma.UdpStart();
            } catch (ClassCastException e) {
                throw new ClassCastException("activity が btUdpOn のListener を実装していません.");
            }
        }
        if(v.getId() == R.id.setup_btLinkStop) {
            try {
                ma.UdpStop();
            } catch (ClassCastException e) {
                throw new ClassCastException("activity が btUdpOff のListener を実装していません.");
            }
        }
        if(v.getId() == R.id.setup_btSaveSetting) {
            try {
                ma.SaveSettings();
            } catch (ClassCastException e) {
                throw new ClassCastException("activity が btSaveSetting のListener を実装していません.");
            }
            FocusClear();
        }
        if(v.getId() == R.id.setup_btClearList) {
            ma.ReadyCheckClear();
            ma.cc.ToastOutShort( "CheckResult Cleared !");
        }
        if(v.getId() == R.id.setup_btTESTSET) {
            ma.ViecleData.TestDataSet();
            ma.DataRedraw();
        }

        if(v.getId() == R.id.setup_btReboot) {
           //確認ダイアログを表示します。
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(ma);
            //タイトルを設定する
            alertDialog.setTitle("Reboot");
            //メッセージ内容を設定する
            alertDialog.setMessage("if OK, AUV MainCPU goes reboot...");
            //確認ボタンん処理を設定する
            alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int whichButton) {
                    ma.cc.ToastOutShort( "CPU Reboot");
                    sshc.Setup( AuvIp, OS_user, OS_pass );
//                    sshc.sshcommand(". /home/cpu-test/auv.reboot");
                    sshc.sshcommand( RebootCmnd );
                }
            });
            alertDialog.setNegativeButton("Cancel", null);
            alertDialog.create();
            alertDialog.show();
        }
        if(v.getId() == R.id.setup_btShutdown) {
           //確認ダイアログを表示します。
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(ma);
            //タイトルを設定する
            alertDialog.setTitle("Reboot");
            //メッセージ内容を設定する
            alertDialog.setMessage("if OK, AUV MainCPU goes Shutdown...");
            //確認ボタンん処理を設定する
            alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int whichButton) {
                    ma.cc.ToastOutShort( "CPU Shutdown");
                    sshc.Setup( AuvIp, OS_user, OS_pass );
//					sshc.sshcommand(". /home/cpu-test/auv.sdown");
					sshc.sshcommand( SDownCmnd );
                }
            });
            alertDialog.setNegativeButton("Cancel", null);
            alertDialog.create();
            alertDialog.show();
        }
    }

    public void FocusClear() {
        (getActivity().findViewById(R.id.setup_tvSettingTitle)).requestFocus();

    }

}
