package info.bbmail.auvterminal;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Created on 2014/08/21.
 */
public class ClassWifiTools {

    // コンストラクタ
    private Context context;
    public ClassWifiTools(Context context){
        this.context = context;
    }

    // Wifi 状態を取得
    public String getWifiInfo() {
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        int wifiState = wifiManager.getWifiState();
        String msgString = "";
        switch (wifiState) {
            case WifiManager.WIFI_STATE_DISABLING:
                msgString = "DISABLING";
                break;
            case WifiManager.WIFI_STATE_DISABLED:
                msgString = "DISABLED";
                break;
            case WifiManager.WIFI_STATE_ENABLED:
                msgString = "ENABLED";
                break;
            case WifiManager.WIFI_STATE_ENABLING:
                msgString = "ENABLING";
                break;
            case WifiManager.WIFI_STATE_UNKNOWN:
                msgString = "UNKNOWN";
                break;
            default:
                msgString = "（未定義）";
                break;
        }
        return msgString;
    }

    // IPアドレスを取得
    public String getIPAddr() {
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        String strIp = ((ip >> 0) & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                ((ip >> 24) & 0xFF);
        return strIp;
    }

    //　ブロードキャスト用アドレスの取得
    public InetAddress getBroadcastAddress() throws IOException
    {
        // ﾏﾙﾁｷｬｽﾄ用のAddressを取得する
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcp = wifiManager.getDhcpInfo();

        int broadcast = (dhcp.ipAddress & dhcp.netmask) | ~dhcp.netmask;
        byte[] quads = new byte[4];

        for (int k = 0; k < 4; k++)
            quads[k] = (byte) ((broadcast >> k * 8) & 0xFF);
        return InetAddress.getByAddress(quads);
    }
}
