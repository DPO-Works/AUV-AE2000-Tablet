package info.bbmail.auvterminal;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.location.OnNmeaMessageListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.text.format.Time;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;



/////////////////////////////////////////////////////////////////////////////////////
//  MainActivity class
/////////////////////////////////////////////////////////////////////////////////////
// GPS
//public class MainActivity extends Activity implements LocationListener, GpsStatus.NmeaListener
// GNSS
public class MainActivity<KeyEvent> extends FragmentActivity implements LocationListener, OnNmeaMessageListener
{

    public boolean gpsEnabled = false;                 // gps動作指令中かどうか　Pause/Resume用
    private int count = 0;
    private boolean UdpAutoReq = false;
	private	boolean	UdpEnabled = false;

//    private Handler timerhandler = new Handler();
    private Handler timerhandler;
    private Timer mytimer = new Timer();

    public double GpsLat = 0.0;
    public double GpsLon = 0.0;
    public boolean GpsPosEnable = false;
    public int GpsSatNum = 0;
    public double GpsHDOP = 0.0;
    public long GpsLastTime = 0;

    public FragmentStatus FStatus = new FragmentStatus();
    public FragmentCmnd FCmnd = new FragmentCmnd();
    public FragmentSetup FSetup = new FragmentSetup();
    public FragmentQuad FQuad = new FragmentQuad();
    public FragmentDvl FDvl = new FragmentDvl();
    public FragmentSensor FSens = new FragmentSensor();
    public FragmentPayload FPay = new FragmentPayload();
    public FragmentBallast FBallast = new FragmentBallast();
    public FragmentAct FAct = new FragmentAct();
    public FragmentMission FMission = new FragmentMission();
    public FragmentError FError = new FragmentError();
    public FragmentDive FDive = new FragmentDive();
    public FragmentRecovery FRecovery = new FragmentRecovery();

    public int ScreenNum = 0;
    public boolean ScreenChange = false;

    public AuvData ViecleData = new AuvData();
    public ClassCommon cc = new ClassCommon(this);
    public Nmea nm = new Nmea();

    ClassWifiTools cWifi = new ClassWifiTools(this);

    private int GpsSendCount = 0;
    private final int GpsSendCntMax = 5;            // 5秒間送り続ける



//     * ***************************
//     * クラス生成
//     * ***************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // メインスレッドから送るためのおまじない
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

        // スリープ禁止
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // 横画面固定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        createFragment();
        SetRightWidnow(101);       // イニシャル画面指定

        ReadyCheckClear();

        // Timer インターバル設定
        timerhandler = new Handler(Looper.getMainLooper());
        mytimer.schedule(new MyTimer(), 0, 2000);

        // スワイプ検出用GestureDetector生成
        mGestureDetector = new GestureDetector(this, mOnGestureListener);

        // 戻るキーの動作をカスタマイズ
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 何もせずに戻る動作を無効化
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


    }

    public void createFragment()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.right_pane, FSetup);
        transaction.add(R.id.right_pane, FStatus);
        transaction.add(R.id.right_pane, FCmnd);
        transaction.add(R.id.right_pane, FQuad);
        transaction.add(R.id.right_pane, FDvl);
        transaction.add(R.id.right_pane, FSens);
        transaction.add(R.id.right_pane, FPay);
        transaction.add(R.id.right_pane, FBallast);
        transaction.add(R.id.right_pane, FAct);
        transaction.add(R.id.right_pane, FMission);
        transaction.add(R.id.right_pane, FError);
        transaction.add(R.id.right_pane, FDive);
        transaction.add(R.id.right_pane, FRecovery);

//        transaction.addToBackStack(null);
// oldver        transaction.commitNow();

        transaction.addToBackStack(null);
        transaction.commit();
    }

//     * ***************************
//     * Optionメニューの生成
//     * ***************************
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);
    }

//     * ***************************
//     * Optionメニューのアイテム選択
//     * ***************************
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.
         // 画面要素を選択した場合の処理
         if(item.getItemId()== R.id.F_Setting)
                SetRightWidnow(100 + ScreenNum % 100);
         if(item.getItemId()==R.id.F_Status)
                SetRightWidnow(200 + ScreenNum % 100);
         if(item.getItemId()==R.id.F_Command)
                SetRightWidnow(300 + ScreenNum % 100);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        if (gpsEnabled == true)
            GpsStart();
		UdpAutoReq = UdpEnabled;			// true にする

        super.onResume();

	    // 表示中の Fragment を取得して再描画を促す
    	Fragment currentFragment = getCurrentFragment(ScreenNum);
    	if (currentFragment != null && currentFragment.getView() != null) {
//		currentFragment.getView().invalidate();
		currentFragment.getView().requestLayout();
    	}
    }

    @Override
    public void onPause() {
        if (gpsEnabled == true)
            GpsStop();
		UdpAutoReq = false;

        super.onPause();
    }

//     * ***************************
//     * クラスの破棄
//     * ***************************
    @Override
    public void onDestroy() {
        super.onDestroy();
        // タイマー関連：タイマーを破棄する
        if (mytimer != null) {
            mytimer.cancel();
            mytimer = null;
        }

    }

    // Backキーの無効化
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            // バックキー押下時の処理
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // Timmer 開始処理
    /////////////////////////////////////////////////////////////////////////////////////
/*
    private class TimerThread extends Thread {
        public void run() {
            timerhandler.post(new Runnable() {
                public void run() {
                    
                    if (UdpAutoReq != true)
                        return;
                    UdpSendN("GETTEXTDATA");          // トーストなし
                }
            });
        }
    }

    private class MyTimer extends TimerTask {
        public void run() {
            TimerThread Tthread = new TimerThread();
            Tthread.start();
        }
    }
*/
    private class MyTimer extends TimerTask {
        @Override
        public void run() {
            timerhandler.post(new Runnable() {
                @Override
                public void run() {
                    if (!UdpAutoReq) return;
                    UdpSendN("GETTEXTDATA");  // UIスレッドで安全に呼べる
                }
            });
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // Gps 開始処理
    /////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    public String GpsLatDegString() {
        long tt = System.currentTimeMillis() - GpsLastTime;
        if (tt > 5000)
            return "";
        if (GpsHDOP == 0.0 || GpsHDOP > 2.0)
            return "";

        if (GpsLat != 0.0)
            return String.format("ALAT%13f", GpsLat);
        else
            return "";
    }

    public String GpsLonDegString() {
        long tt = System.currentTimeMillis() - GpsLastTime;
        if (tt > 5000)
            return "";
        if (GpsHDOP == 0.0 || GpsHDOP > 2.0)
            return "";

        if (GpsLon != 0.0)
            return String.format("ALON%13f", GpsLon);
        else
            return "";
    }

    // 座標変化検出
    @Override
    public void onLocationChanged(Location location) {
        String lat = String.valueOf(nm.ToDMM(location.getLatitude()));
        String lon = String.valueOf(nm.ToDMM(location.getLongitude()));
// 左画面にあったのを消去
//        ((TextView) findViewById(R.id.tvGpsLatString)).setText(lat);
//        ((TextView) findViewById(R.id.tvGpsLonString)).setText(lon);
        ViecleData.TabGps.Latitude = new String(lat);
        ViecleData.TabGps.Longitude = new String(lon);
    }

    // NMEA 受信
// GPS
//    @Override
//    public void onNmeaReceived(long timestamp, String nmea) { NmeaProc( nmea ); }
// GNSS
    @Override
    public void onNmeaMessage( String nmea, long timestamp) { NmeaProc( nmea );}

    public void NmeaProc( String nmea){
        String[] data = nmea.split(",");
        String zdaOut;
        int hh, mm;
        // GPGSV,GPGSA,GPRMC,GPVTG,GPGGA

        if (data[0].equals("$GPZDA")) {
//            data[1] : utc hhmmss.ss
//            data[2] : date
//            data[3] : month
//            data[4] : year  ex.2001
//            data[5] : hour (local)
//            data[6] : min (local)
//            data[7] : checksum
            // Quadrans ヘ送出
            SendGpsNmea(nmea);
        } else if (data[0].equals("$GPRMC") || data[0].equals("$GNRMC")) {
//            data[1] : utc hhmmss.ss
//            data[2] : status
//            data[3] : lat
//            data[4] : lat sign
//            data[5] : lon
//            data[6] : lon sign
//            data[7] : velo
//            data[8] : vero dir
//            data[9] : utc ddmmyy
//            data[10] : noth-trueNoth diff
//            data[11] : noth-trueNoth diff dir
//            data[12] : Mode
            zdaOut = "$GPZDA," + data[1] + ",";
            if (nm.isStrEmpty(data[9]) == false) {
                zdaOut += data[9].substring(0, 2) + ",";
                zdaOut += data[9].substring(2, 4) + ",";
                zdaOut += "20" + data[9].substring(4, 6) + ",";
            } else {
                zdaOut += ",,,";
            }
            if (nm.isStrEmpty(data[1]) == false) {
                hh = Integer.parseInt(data[1].substring(0, 2)) + 9;
                hh %= 24;
                mm = Integer.parseInt(data[1].substring(2, 4));
                zdaOut += String.format("%02d,%02d*", hh, mm);
            } else
                zdaOut += ",*";

            zdaOut += nm.MakeNmeaChecksum(zdaOut);
            zdaOut += String.format("\r\n");
            SendGpsNmea(zdaOut);
        } else if (data[0].equals("$GPGGA") || data[0].equals("$GNGGA")) {
            if (GpsSendCount > 0)
                GpsSendCount--;

            // Quadrans ヘ送出
            SendGpsNmea(nmea);
            ViecleData.TabGps.RecvGGAStr = nmea;

            // OS現在時刻を取得して表示
//            ((TextView) findViewById(R.id.tvGpsRecvString)).setText(nm.GetCurrentTimeStr());
            ViecleData.TabGps.LastRecv = nm.GetCurrentTimeStr();

            // GPSくおりてぃ、衛星数等表示
            String status = "";
//            if (data[6].equals(""))
//                status += "NON";
//            else
            {
                switch (Integer.parseInt(data[6]))                                 // status
                {
                    case 0:
                        status += "NON ";
                        GpsPosEnable = false;
                        break;
                    case 1:
                        status += "GPS ";
                        GpsPosEnable = true;
                        break;
                    case 2:
                        status += "DGPS";
                        GpsPosEnable = true;
                        break;
                }
                status += " / " + data[7];     // 衛星数
                status += " / " + data[8];     // hdop

                if (GpsPosEnable == true) {
                    GpsLat = nm.nmea_degCnvt(Double.parseDouble(data[2]));
                    if (data[3].equals("S"))
                        GpsLat *= -1.0;
                    GpsLon = nm.nmea_degCnvt(Double.parseDouble(data[4]));
                    if (data[5].equals("W"))
                        GpsLon *= -1.0;
                    GpsSatNum = Integer.parseInt(data[7]);
                    GpsHDOP = Double.parseDouble(data[8]);
                    GpsLastTime = System.currentTimeMillis();
                } else {
                    GpsLat = 0.0;
                    GpsLon = 0.0;
                    GpsSatNum = 0;
                    GpsHDOP = 0;
                }
            }
// 左画面にあったのを消去
//            ((TextView) findViewById(R.id.tvGpsQtyString)).setText(status);
            ViecleData.TabGps.Qty = status;
            // Fragment のGPSを再描画
            ((FragmentQuad) FQuad).reDrawGps();
            ((FragmentMission) FMission).reDrawGps();
        }
    }

    public void SendStartGpsToAuv() {
        GpsSendCount = GpsSendCntMax;           // カウンタ初期化
        addText( "Start GPS Data Send" );
    }

    DatagramSocket udpSocketGps = null;

    public void GpsStart() {
        LocationManager lcManager = (LocationManager)getSystemService(LOCATION_SERVICE);

// GNSS
        // We need permission to get location updates
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // A problem occurred auto-granting the permission
            String outs = "LOCATION_SERVICE:A problem occurred auto-granting the permission";
            Toast.makeText(this, outs, Toast.LENGTH_LONG).show();
            return;
        }

        lcManager.addNmeaListener(this);
        LocationProvider lcProvider = lcManager.getProvider("gps");
        lcManager.requestLocationUpdates( lcProvider.getName(), 0, 0, this);
        Toast.makeText(this,  "GPS start!",  Toast.LENGTH_LONG).show();
        if( udpSocketGps != null )
            udpSocketGps.close();
        try{
            udpSocketGps = new DatagramSocket(null);
        } catch (SocketException e1)
        {
            String outs = "SocketException:" + e1.toString();
            Toast.makeText(this, outs, Toast.LENGTH_LONG).show();
        }

        GpsPosEnable = false;
    }
    public void GpsStop() {
        LocationManager lcManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        lcManager.removeUpdates(this);
        lcManager.removeNmeaListener(this);
        Toast.makeText(this,  "GPS stop!",  Toast.LENGTH_LONG).show();
        udpSocketGps.close();
        udpSocketGps = null;
        GpsPosEnable = false;
    }

    public void sendUDPToGps( String sendStr, String HostIp, int HostPort )
    {
        DatagramSocket udpSocketTmp;
        try
        {
            if(udpSocketGps != null ) {
                InetAddress inetAddress = InetAddress.getByName(HostIp);
                DatagramPacket packet = new DatagramPacket(sendStr.getBytes(), sendStr.length(), inetAddress, HostPort);
                udpSocketGps.send(packet);
            }
        } catch (SocketException e1)
        {
//            String outs = "SocketException:" + e1.toString();
//            Toast.makeText(this, outs, Toast.LENGTH_LONG).show();
        } catch (IOException e1)
        {
            String outs = "IOException:" + e1.toString();
            Toast.makeText(this, outs, Toast.LENGTH_LONG).show();
        }
    }

	// 設定の保存
	public void SaveSettings()
	{
		// 設定ファイルへ書き出し
		EditText etAuvIp = (EditText) findViewById(R.id.setup_edAuvIp);
		String AuvIp = etAuvIp.getText().toString();

		EditText etAuvPort = (EditText) findViewById(R.id.setup_edAuvPort);
		int AuvPort = Integer.parseInt(etAuvPort.getText().toString());


		EditText etGpsIp = (EditText)findViewById(R.id.setup_edGpsIp);
        String GpsIp = etGpsIp.getText().toString();

		EditText etGpsPort = (EditText)findViewById(R.id.setup_edGpsPort);
		int GpsPort = Integer.parseInt(etGpsPort.getText().toString());

		EditText etQuadIp = (EditText)findViewById(R.id.setup_edQuadIp);
        String QuadIp = etQuadIp.getText().toString();

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

		editor.putString("AuvIP", AuvIp);
        editor.putInt("AuvPort", AuvPort );
        editor.putString("GpsIP",GpsIp);
        editor.putInt("GpsPort", GpsPort);
        editor.putString("QuadIP",QuadIp);
//        editor.putString("VncPackage",vncPackage);

		editor.apply();

        // キーボードを消す
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

		cc.ToastOutLong("Setting Saved.");

	}

    /////////////////////////////////////////////////////////////////////////////////////
    // UDP Functions
    /////////////////////////////////////////////////////////////////////////////////////
    public void SendGpsNmea(String sendStr) {

        // GPS送出許可であれば送る

//        CheckBox checkBox = (CheckBox) findViewById(R.id.cbQuadSendEnable);
//        if (checkBox.isChecked() == false)
//            return;
        if( GpsSendCount <= 0 ) {
            GpsSendCount = 0;
            return;
        }

        try {
            EditText etHostIp = (EditText) findViewById(R.id.setup_edGpsIp);
            EditText etHostPort = (EditText) findViewById(R.id.setup_edGpsPort);

            String HostIp = etHostIp.getText().toString();
            int HostPort = Integer.parseInt(etHostPort.getText().toString());
            sendUDPToGps(sendStr, HostIp, HostPort);

        } catch (Exception e) {
            String outs = "Exception:" + e.toString();
            Toast.makeText(this, outs, Toast.LENGTH_LONG).show();
        }
    }

    public void UdpOpen() {
        EditText etPort = (EditText) findViewById(R.id.setup_edAuvPort);
        int HostPort = Integer.parseInt(etPort.getText().toString());
        startUDPReceive(HostPort);
        startUDPSend( HostPort);
        cc.ToastOutShort("UDP Receive Start");
        cc.SetStringToTextView("MyIP: " + cWifi.getIPAddr(), R.id.setup_tvMyIP);
		UdpEnabled = true;
	}

    public void UdpClose() {
        stopUDPReceive();
        stopUDPSend();
		UdpEnabled = false;
    }

    public void UdpStart() {
		UdpOpen();
        UdpAutoReq = true;
    }

    public void UdpStop() {
        UdpAutoReq = false;
        cc.ToastOutShort("UDP Receive Stop");
		UdpClose();
	}

    // UDP 通信テスト
    public void sendGetTextData() {
        UdpSend( "GETTEXTDATA" );
    }

    // 送出
    public void UdpSend( String ss ) {
        EditText etPort = (EditText) findViewById(R.id.setup_edAuvPort);
        int HostPort = Integer.parseInt(etPort.getText().toString());
        EditText etHostIp = (EditText) findViewById(R.id.setup_edAuvIp);
        String HostIp = etHostIp.getText().toString();
        sendUDPFromSendSock(ss, HostIp, HostPort);
        addText( "send:" + ss );
    }
    public void UdpSendN( String ss ) {
        EditText etPort = (EditText) findViewById(R.id.setup_edAuvPort);
        int HostPort = Integer.parseInt(etPort.getText().toString());
        EditText etHostIp = (EditText) findViewById(R.id.setup_edAuvIp);
        String HostIp = etHostIp.getText().toString();
        sendUDPFromSendSock(ss, HostIp, HostPort);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    // UDP 下位 Functions
    /////////////////////////////////////////////////////////////////////////////////////

    private DatagramSocket udpSendSocket = null;
    public void startUDPSend( final int SendUdpPort ) {
//        DatagramSocket udpSocketTmp;
//        try
//        {
//            if( udpSendSocket != null )
//                udpSendSocket.close();
//            udpSendSocket = new DatagramSocket(null);
//
//        } catch (SocketException e1)
//        {
//            String outs = "SocketException:" + e1.toString();
//            Toast.makeText(this, outs, Toast.LENGTH_LONG).show();
//        }
    }
    public void stopUDPSend(){
//        if( udpSendSocket != null)
//            udpSendSocket.close();
//        udpSendSocket = null;
    }

    // UDP 送信
    public void sendUDPCall( String sendStr, String HostIp, int HostPort )
    {
        DatagramSocket udpSocketTmp;
        try
        {
            udpSocketTmp = new DatagramSocket(null);
            InetAddress inetAddress = InetAddress.getByName( HostIp );

            DatagramPacket packet = new DatagramPacket(sendStr.getBytes(), sendStr.length(), inetAddress, HostPort);
            udpSocketTmp.send(packet);
            udpSocketTmp.close();

        } catch (SocketException e1)
        {
            String outs = "SocketException:" + e1.toString();
            Toast.makeText(this, outs, Toast.LENGTH_LONG).show();
        } catch (IOException e1)
        {
            String outs = "IOException:" + e1.toString();
            Toast.makeText(this, outs, Toast.LENGTH_LONG).show();
        }
    }

    public void sendUDPFromSendSock( String sendStr, String HostIp, int HostPort )
    {
        DatagramSocket udpSocketTmp;
        try
        {
            if(udpSocket != null ) {
                InetAddress inetAddress = InetAddress.getByName(HostIp);
                DatagramPacket packet = new DatagramPacket(sendStr.getBytes(), sendStr.length(), inetAddress, HostPort);
                udpSocket.send(packet);
            }
        } catch (SocketException e1)
        {
//            String outs = "SocketException:" + e1.toString();
//            Toast.makeText(this, outs, Toast.LENGTH_LONG).show();
        } catch (IOException e1)
        {
            String outs = "IOException:" + e1.toString();
            Toast.makeText(this, outs, Toast.LENGTH_LONG).show();
        }
    }



    private Thread      receiveRunnerThread = null;
    public  Thread      UdpReceiveThread = null;
    private DatagramSocket udpSocket = null;
    // ＵＤＰ受信スレッドランチャー
    public void startUDPReceive( final int RecvUdpPort ){
        // 受信部の起動
        if (receiveRunnerThread == null)
        {
            receiveRunnerThread = new Thread(
                    new Runnable() {
                        public void run() { receiveUdp(RecvUdpPort); }
                    }
            );
            receiveRunnerThread.start();
        }
    }
    public void stopUDPReceive(){
        if( udpSocket != null)          // 強引に閉じて例外を発生させることにより受信待ちを抜ける
            udpSocket.close();
    }


    // UDP受信スレッド実体
    public void receiveUdp( int Port )
    {
        byte []buf = new byte[16384];
        SocketAddress sockAddress;
        String msg;
        UdpReceiveThread = Thread.currentThread();
        DatagramPacket packet= new DatagramPacket( buf, buf.length );

        try{
            udpSocket = new DatagramSocket(Port);
//            udpSocket.bind(new InetSocketAddress(Port));

            while( UdpReceiveThread == receiveRunnerThread )
            {
                udpSocket.receive(packet);//受信 & wait

                sockAddress = packet.getSocketAddress();        //送信元情報取得
                int len = packet.getLength();
                msg = new String(buf, 0, len);
                UIaccess( msg, sockAddress.toString() );
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
        udpSocket.close();
        receiveRunnerThread = null;
    }

    // スレッド間の同期のようなもの。
    private final Handler UdpReceiveHandler = new Handler();
    public void UIaccess( final String s, final String SockAddr ){
        UdpReceiveHandler.post(new Runnable(){
            public void run(){
                Analisys( s, SockAddr );
            }
        });
    }

    // ＵＩへのアクセス付で解析
    public void Analisys( String s, String SockAddr ){

        if( ViecleData.Decode(s) == false)
        {
            addText( s );
            return;
        }
        DataRedraw();
        cc.SetStringToTextView(nm.GetCurrentTimeStr(), R.id.tvUdpRecvMon);
//        cc.SetStringToTextView( ViecleData.AuvTime, R.id.tvUdpRecvMon);

    }

    // 再描画
    public void DataRedraw() {
        // 各画面更新処理
        ((FragmentStatus)FStatus).reDraw();
        ((FragmentQuad)FQuad).reDraw();
        ((FragmentDvl)FDvl).reDraw();
        ((FragmentSensor)FSens).reDraw();
        ((FragmentPayload)FPay).reDraw();
        ((FragmentBallast)FBallast).reDraw();
        ((FragmentAct)FAct).reDraw();
        ((FragmentMission)FMission).reDraw();
        ((FragmentError)FError).reDraw();
        ((FragmentDive)FDive).reDraw();
        ((FragmentRecovery)FRecovery).reDraw();


        // 左画面は動的生成じゃないので不本意ながらべたに書く
        cc.SetStringToTextView( ViecleData.io.AI[ ViecleData.io.AI_BAT_VOLT ], R.id.tvBattVolt);
        cc.SetStringToTextView( ViecleData.io.AI[ ViecleData.io.AI_CCURRENT ], R.id.tvPowerTemp);
        cc.SetStringToTextView( ViecleData.io.AI[ ViecleData.io.AI_CONT_TEMP ], R.id.tvControllerTemp);

        String outs = ViecleData.io.AI[ ViecleData.io.AI_BAT1_TEMP ] + "  " + ViecleData.io.AI[ ViecleData.io.AI_BAT2_TEMP ];
        cc.SetStringToTextView(outs, R.id.tvBattTemp);

    }


    public  String TaskIDToName( String TaskidStr ) {
            int id;
            try{
                id = Integer.parseInt(TaskidStr);
                id += 1;
            }
            catch( Exception e ){
                id = 0;
            }

        String outs;
        switch (id) {
            case 0:
            default: outs = "NON";       break;
            case  1: outs = ViecleData.ErrTask01; break;
            case  2: outs = ViecleData.ErrTask02; break;
            case  3: outs = ViecleData.ErrTask03; break;
            case  4: outs = ViecleData.ErrTask04; break;
            case  5: outs = ViecleData.ErrTask05; break;
            case  6: outs = ViecleData.ErrTask06; break;
            case  7: outs = ViecleData.ErrTask07; break;
            case  8: outs = ViecleData.ErrTask08; break;
            case  9: outs = ViecleData.ErrTask09; break;
            case 10: outs = ViecleData.ErrTask10; break;
            case 11: outs = ViecleData.ErrTask11; break;
            case 12: outs = ViecleData.ErrTask12; break;
            case 13: outs = ViecleData.ErrTask13; break;
            case 14: outs = ViecleData.ErrTask14; break;
            case 15: outs = ViecleData.ErrTask15; break;
        }
        return outs;
    }

    public  String ErrorProcIDToName( String idStr ) {
        int id;
        try{
            id = Integer.parseInt(idStr);
        }
        catch( Exception e ){
            id = 0;
        }
        String outs = "";
        switch (id) {
            default:
            case 0: outs = "(0)No Action Execute";  break;
            case 1: outs = "(1)Log Write";          break;
            case 2: outs = "(2)Return Surface";     break;
            case 3: outs = "(3)Emerge ballast";     break;
        }
        return outs;

    }

    /////////////////////////////////////////////////////////////////////////////////////
    // ライブラリ Functions
    /////////////////////////////////////////////////////////////////////////////////////

    // ログ機能
    private final Handler addTextHandler = new Handler();
    public void addText(final String s ) {
         //ハンドラによるユーザーインタフェース操作(ﾃﾞﾘｹﾞｰﾄのようなもの)
         addTextHandler.post(new Runnable() {
             public void run() {

                int LogMaxLine = 7;                                     // +1 行されるのに注意
                TextView tv = (TextView) findViewById(R.id.tvUdpLog);
                if( tv != null ) {

                    Time time = new Time("Asia/Tokyo");
                    time.setToNow();
                    String date = String.format("[%02d:%02d:%02d]",
                            time.hour, time.minute, time.second);
                    String Newlogs = date + s;

                    String logs = (String) tv.getText();
                    String[] logDevide = logs.split(System.getProperty("line.separator"), 0);
                    int LineCnt = logDevide.length;
                    if (LineCnt > LogMaxLine)
                        LineCnt = LogMaxLine;

                    // 新しいものが上
                    String Outlogs = Newlogs;
                    for (int i = 0; i < LineCnt; i++)
                        Outlogs += System.getProperty("line.separator") + logDevide[i];

                    tv.setText(Outlogs);
                }
             }
        });
    }

    // 移動方向から画面遷移
    // directon 0: 移動なし 1:右から左 2:左から右 3:下から上 4:上から下
    public void SelectFragment( int directon ) {

        int		Page = ScreenNum % 100;
		int		Mode = ScreenNum / 100;
		final int MinMode = 1;
		final int MaxMode = 4;
        if (directon == 1 && Mode < MaxMode ) Mode ++;
        if (directon == 2 && Mode > MinMode ) Mode --;

		if( Mode == 4 )
		{
			final int MinPage = 1;
			final int MaxPage = 10;

            if (directon == 3 && Page < MaxPage ) Page ++;    // 下から : +1
            if (directon == 4 && Page > MinPage ) Page --;    // 上から : -1
        }
        SetRightWidnow(Mode * 100 + Page);

//        String outs = String.format( "ScreenNum:%d", ScreenNum );
//        cc.ToastOutLong(outs);
    }

	// 現在右側のフラグメントを取得
	public Fragment getCurrentFragment(int screenNum) {
	    switch (screenNum) {
        case 100: return FSetup;
        case 200: return FCmnd;
        case 300: return FStatus;
        case 1: return FQuad;
        case 2: return FDvl;
        case 3: return FSens;
        case 4: return FPay;
        case 5: return FBallast;
        case 6: return FAct;
        case 7: return FMission;
        case 8: return FError;
        case 9: return FDive;
        case 10: return FRecovery;
        default: return null;
    	}
	}

    // 画面遷移
    public void SetRightWidnow(final int fid ) {

        int selection = 0;

        if(      (fid / 100) == 1 )  selection = 100;
        else if( (fid / 100) == 2 )  selection = 200;
        else if( (fid / 100) == 3 )  selection = 300;
        else if( (fid % 100) == 1 )  selection = 1;
        else if( (fid % 100) == 2 )  selection = 2;
        else if( (fid % 100) == 3 )  selection = 3;
        else if( (fid % 100) == 4 )  selection = 4;
        else if( (fid % 100) == 5 )  selection = 5;
        else if( (fid % 100) == 6 )  selection = 6;
        else if( (fid % 100) == 7 )  selection = 7;
        else if( (fid % 100) == 8 )  selection = 8;
        else if( (fid % 100) == 9 )  selection = 9;
        else if( (fid % 100) == 10 )  selection = 10;

        if( selection == 0 )
            selection = 100;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed( true );
        if( selection == 100 )   transaction.show(FSetup); else transaction.hide(FSetup);
        if( selection == 200 )  transaction.show(FCmnd); else transaction.hide(FCmnd);
        if( selection == 300 )  transaction.show(FStatus); else transaction.hide(FStatus);
        if( selection == 1 )  transaction.show(FQuad); else transaction.hide(FQuad);
        if( selection == 2 )  transaction.show(FDvl); else transaction.hide(FDvl);
        if( selection == 3 )  transaction.show(FSens); else transaction.hide(FSens);
        if( selection == 4 )  transaction.show(FPay); else transaction.hide(FPay);
        if( selection == 5 )  transaction.show(FBallast); else transaction.hide(FBallast);
        if( selection == 6 )  transaction.show(FAct);  else transaction.hide(FAct);
        if( selection == 7 )  transaction.show(FMission); else transaction.hide(FMission);
        if( selection == 8 )  transaction.show(FError); else transaction.hide(FError);
        if( selection == 9 )  transaction.show(FDive); else transaction.hide(FDive);
        if( selection == 10 )  transaction.show(FRecovery); else transaction.hide(FRecovery);

// old ver        transaction.commitNow();
        transaction.commit();

		if( ScreenNum == fid )
			ScreenChange = false;
		else
			ScreenChange = true;

        ScreenNum = fid;

    }

    /////スワイプ検出用

    // 最低スワイプ距離
    private static final int SWIPE_MIN_DISTANCE = 50;

    // 最低スワイプスピード
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    // タッチイベントを処理するためのインタフェース
    private GestureDetector mGestureDetector;

    // タッチイベント
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    // タッチイベントのリスナー
    private final GestureDetector.SimpleOnGestureListener mOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

        // フリックイベント
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

            try {

                // 移動距離・スピードを出力
                float distance_x = Math.abs((event1.getX() - event2.getX()));
                float distance_y = Math.abs((event1.getY() - event2.getY()));
                float velocity_x = Math.abs(velocityX);
                float velocity_y = Math.abs(velocityY);
//                cc.ToastOutLong("横:" + distance_x + "/" + velocity_x + " 縦:" + distance_y + " /" + velocity_y );

                float dx = Math.abs(event1.getX() - event2.getX());
                float dy = Math.abs(event1.getY() - event2.getY());

                if( dx >= dy  && velocity_x > SWIPE_THRESHOLD_VELOCITY ) {
                    // 開始位置から終了位置の移動距離が指定値より大きい
                    // X軸の移動速度が指定値より大きい
                    if  (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE)  {
//                        ToastOutLong("右から左");
                        SelectFragment(1);
                    }
                    // 終了位置から開始位置の移動距離が指定値より大きい
                    // X軸の移動速度が指定値より大きい
                    else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE) {
//                        ToastOutLong("左から右");
                        SelectFragment(2);
                    }
                }
                else if( velocity_y > SWIPE_THRESHOLD_VELOCITY ){
                    // 開始位置から終了位置の移動距離が指定値より大きい
                    // X軸の移動速度が指定値より大きい
                    if  (event1.getY() - event2.getY() > SWIPE_MIN_DISTANCE) {
//                        ToastOutLong("下から上");
                        SelectFragment(3);
                    }
                    // 終了位置から開始位置の移動距離が指定値より大きい
                    // X軸の移動速度が指定値より大きい
                    else if (event2.getY() - event1.getY() > SWIPE_MIN_DISTANCE) {
//                        ToastOutLong("上から下");
                        SelectFragment(4);
                    }
                }
            } catch (Exception e) {
                // TODO
            }

//            return false;
            return true;
        }
    };

    public int ReadyCheck[] = new int [10];

    public void ReadyCheckClear()
    {
        // 内部データクリア
        for( int i = 0; i < 8; i++ )
            ShowCheckResult( i, 0 );
        ShowCheckResult( 8, -1 );
        ShowCheckResult( 9, -1 );
    }


    public void ShowCheckResult( int ID, int Result )
    {
        ReadyCheck[ID] = Result;
        switch( ID ) {
            case 0:cc.SetResultToTextViewStatus( R.id.tvPage01, Result ); break;
            case 1:cc.SetResultToTextViewStatus( R.id.tvPage02, Result ); break;
            case 2:cc.SetResultToTextViewStatus( R.id.tvPage03, Result ); break;
            case 3:cc.SetResultToTextViewStatus( R.id.tvPage04, Result ); break;
            case 4:cc.SetResultToTextViewStatus( R.id.tvPage05, Result ); break;
            case 5:cc.SetResultToTextViewStatus( R.id.tvPage06, Result ); break;
            case 6:cc.SetResultToTextViewStatus( R.id.tvPage07, Result ); break;
            case 7:cc.SetResultToTextViewStatus( R.id.tvPage08, Result ); break;
            case 8:cc.SetResultToTextViewStatus( R.id.tvPage09, Result ); break;
            case 9:cc.SetResultToTextViewStatus( R.id.tvPage10, Result ); break;
        }
    }
    public int GetCheckResult( int ID ) {
        return ReadyCheck[ID];
    }

    // Ver情報の取得
    public String getVersionName(Activity activity) {

        try {
            // Java パッケージ名を取得
            // android.content.Context#getPackageName
            String name = activity.getPackageName();

            // インストールされているアプリケーションパッケージの
            // 情報を取得するためのオブジェクトを取得
            // android.content.Context#getPackageManager
            PackageManager pm = activity.getPackageManager();

            // アプリケーションパッケージの情報を取得
            PackageInfo info = pm.getPackageInfo(name, PackageManager.GET_META_DATA);

            // バージョン番号の文字列を返す
            return info.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
