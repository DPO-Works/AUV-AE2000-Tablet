package info.bbmail.auvterminal;

/**
 * Auv Common Class
 * 2017.05.02 ToDMM を西経・南緯でも使えるように修正
 
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;
import android.graphics.Color;


/////////////////////////////////////////////////////////////////////////////////////
// Common Functions
/////////////////////////////////////////////////////////////////////////////////////
public class Nmea
{
    public String GetCurrentTimeStr() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        return now.format(formatter);
    }
    public static boolean isStrEmpty(String value)
    {
        if ( value == null || value.length() == 0 )
            return true;
        return false;
    }

    // Deg to DMM String
    public String ToDMM(double ddeg) {
        char Sign = '+';
        if( ddeg < 0 )
        {
            ddeg *= -1.0;
            Sign = '-';
        }
        long deg = (int)ddeg;
        double min = (ddeg - (double)deg) * 60.0;
        String outs = String.format( "%c%3d_%09.6f",Sign, deg, min );
        return outs;
    }

    // DMM String to Deg
    public double FromDMM(String instr) {

        int idx = instr.indexOf("_");
        double valDeg, valMin;
        double outDeg;

        try {

            if (idx == -1)         // _ が見つからない＝deg表記
            {
                outDeg = Double.parseDouble(instr);
            } else {
                String degStr = instr.substring(0, idx);
                String minStr = instr.substring(idx + 1);

                valDeg = Double.parseDouble(degStr);
                valMin = Double.parseDouble(minStr);
                if (valDeg >= 0)
                    outDeg = valDeg + valMin / 60.0;
                else
                    outDeg = valDeg - valMin / 60.0;
            }
            return outDeg;
        } catch (Exception e)
        {
            return 0;
        }
    }


    // DMM String to Deg
    // dddmm.mmmmmmmm -> ddd.dddddddd
    public double	nmea_degCnvt( double dat )
    {
        double Sign = 1.0;
        if( dat < 0 )
        {
            Sign = -1.0;
            dat *= -1.0;
        }

        long deg = (int)(dat / 100.0);
        double min = dat - (double)deg * 100;
        double rtn = (double)deg + min / 60.0;
        return( rtn * Sign );
    }

    public String MakeNmeaChecksum( String str )
    {
        String outs;
        byte [] bytes = str.getBytes();
        int  i;
        int  sum = 0;
        for( i = 0; i < bytes.length; i++ )
        {
            if (bytes[i] == '$')       sum = 0;
            else if (bytes[i] == '*')  break;
            else                       sum ^= bytes[i];
        }
        outs = String.format( "%02X", sum & 0xff );
        return outs;
   }

}
