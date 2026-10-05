package info.bbmail.auvterminal;

/**
 * Auv Common Class
 */
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;
import android.graphics.Color;

import com.google.android.material.snackbar.Snackbar;

/////////////////////////////////////////////////////////////////////////////////////
// Common Functions
/////////////////////////////////////////////////////////////////////////////////////
public class ClassCommon
{
    public MainActivity ma;

    public ClassCommon(MainActivity mm)
    {
        this.ma = mm;
    }

	// toast output
    public void ToastOutShort( String outs ){
        Toast.makeText( ma, outs,  Toast.LENGTH_SHORT).show();
    }
    public void ToastOutLong( String outs ){
        Toast toast = Toast.makeText( ma, outs,  Toast.LENGTH_LONG);
        toast.show();
    }

    public void SetStringToTextView( String outs, int ID ) {
        TextView tv = (TextView) ma.findViewById(ID);
        if( tv != null ) {
            tv.setText(outs);
        }
    }
    public void SetStringToTextViewAlign( long timesec, int ID ) {
        TextView tv = (TextView) ma.findViewById(ID);
        if( tv != null ) {
            if( timesec < 0 )         { tv.setText("Not Alignment");            tv.setBackgroundColor(Color.RED );  tv.setTextColor(Color.WHITE); }
            else if( timesec > 3600 * 24 *365 )  { tv.setText("Not Alignment"); tv.setBackgroundColor(Color.RED );  tv.setTextColor(Color.BLACK); }
            else if( timesec < 300 )  { tv.setText("In Alignment");             tv.setBackgroundColor(Color.YELLOW );  tv.setTextColor(Color.BLACK); }
            else                      { tv.setText("READY");                    tv.setBackgroundColor( Color.BLUE);  tv.setTextColor(Color.WHITE); }
        }
    }
    public void SetStringToTextViewOnOff( String outs, int ID ) {
        TextView tv = (TextView) ma.findViewById(ID);
        if( tv != null ) {
            int id;
            try{
                id = Integer.decode(outs);
            }
            catch( Exception e ){
                id = -1;
            }
            if( id == 0 )       { tv.setText("OFF");     tv.setBackgroundColor(Color.YELLOW );  tv.setTextColor(Color.BLACK); }
            else if( id == 1 )  { tv.setText("ON");     tv.setBackgroundColor( Color.BLUE);  tv.setTextColor(Color.WHITE); }
            else { tv.setText(""); tv.setBackgroundColor(Color.WHITE); tv.setTextColor(Color.BLACK); }
        }
    }
    public void SetStringToTextViewBallast( String outs, int ID ) {
        TextView tv = (TextView) ma.findViewById(ID);
        if( tv != null ) {
            int id;
            try{
                id = Integer.decode(outs);
            }
            catch( Exception e ){
                id = -1;
            }
           if( id == 0 )       { tv.setText("Open");     tv.setBackgroundColor(Color.YELLOW );  tv.setTextColor(Color.BLACK); }
            else if( id == 1 )  { tv.setText("Keep");     tv.setBackgroundColor( Color.BLUE);  tv.setTextColor(Color.WHITE); }
            else { tv.setText(""); tv.setBackgroundColor(Color.GRAY ); tv.setTextColor(Color.BLACK); }
        }
    }
    public void SetStringToTextViewWL( String outs, int ID ) {
        TextView tv = (TextView) ma.findViewById(ID);
        if( tv != null ) {
            int id;
            try{
                id = Integer.decode(outs);
            }
            catch( Exception e ){
                id = -1;
            }
            if( id == 0 )       { tv.setText("OK");     tv.setBackgroundColor(Color.BLUE );  tv.setTextColor(Color.WHITE); }
            else if( id == 1 )  { tv.setText("Leak");     tv.setBackgroundColor( Color.RED);  tv.setTextColor(Color.WHITE); }
            else { tv.setText(""); tv.setBackgroundColor(Color.GRAY ); tv.setTextColor(Color.BLACK); }
        }
    }

    // TaskStaus, nfly_go 用
    public void SetStringToTextViewStatus( String outs, int ID ) {
        TextView tv = (TextView) ma.findViewById(ID);
        if( tv != null ) {
            int id;
            try{
                id = Integer.decode(outs);
            }
            catch( Exception e ){
                id = -1;
            }
            if( id == 0 )       { tv.setText("READY");     tv.setBackgroundColor(Color.YELLOW );  tv.setTextColor(Color.BLACK); }
            else if( id == 1 )  { tv.setText("START");     tv.setBackgroundColor( Color.DKGRAY);  tv.setTextColor(Color.WHITE); }
            else if( id == 2 )  { tv.setText("RUN"  );     tv.setBackgroundColor(Color.BLUE );    tv.setTextColor(Color.WHITE); }
            else if( id == 3 )  { tv.setText("STOP" );     tv.setBackgroundColor( Color.DKGRAY);  tv.setTextColor(Color.WHITE); }
            else if( id == 10 ) { tv.setText("ON"   );     tv.setBackgroundColor(Color.parseColor("#000080"));    tv.setTextColor(Color.WHITE); }
            else if( id == 100 ){ tv.setText("GO"  );      tv.setBackgroundColor(Color.BLUE );    tv.setTextColor(Color.WHITE); }
            else if( id == 200 ){ tv.setText("RESERVE");   tv.setBackgroundColor( Color.DKGRAY);  tv.setTextColor(Color.WHITE); }
            else { tv.setText(""); tv.setBackgroundColor(Color.GRAY ); tv.setTextColor(Color.BLACK); }

        }
    }
    public void SetResultToTextViewStatus( int ID, int Result ) {
        TextView tv = (TextView) ma.findViewById(ID);
        if( tv != null ) {
            if( Result == 0 )        { tv.setText("-");     tv.setBackgroundColor(Color.YELLOW );  tv.setTextColor(Color.BLACK); }
            else if( Result == 1 )  { tv.setText("OK"  );     tv.setBackgroundColor(Color.BLUE );    tv.setTextColor(Color.WHITE); }
            else { tv.setText(""); tv.setBackgroundColor(Color.GRAY ); tv.setTextColor(Color.BLACK); }
        }
    }

    public void SetStringToButton( String outs, int ID ) {
        Button tv = (Button) ma.findViewById(ID);
        if( tv != null ) {
            int id;
            try{
                id = Integer.decode(outs);
            }
            catch( Exception e ){
                id = 0;
            }
            if( id == 0 )   tv.setBackgroundColor(0x999A9CFF);
            else            tv.setBackgroundColor(0x99FF8AA2);
            tv.setText(outs);
        }
    }
	
    // エラー詳細表示
    public void ShowErrorDetail( String DialogName, String ErrTitle, String ErrorCode ) {
        int hexVal;
        try{
            hexVal = Integer.decode( ErrorCode );
        }
        catch( Exception e )
        {
            hexVal = 0;
        }
        String outs = DialogName + "\n\n";
        String[] Title = ErrTitle.split(",");
        int jVal = 1;
        for( int i = 0; i < 16; i++, jVal *= 2 ) {
            if ((hexVal & jVal) != 0)
                outs += "[●]";
            else
                outs += "[＿]";
            outs += Title[i] + "\n";
        }
        ma.cc.ToastOutLong(outs);
    }

    public String getErrorDetail( String DialogName, String ErrTitle, String ErrorCode ) {
        int hexVal;
        try{
            hexVal = Integer.decode( ErrorCode );
        }
        catch( Exception e )
        {
            hexVal = 0;
        }
        String outs = DialogName + "\n\n";
        String[] Title = ErrTitle.split(",");
        int jVal = 1;
        for( int i = 0; i < 16; i++, jVal *= 2 ) {
            if ((hexVal & jVal) != 0)
                outs += "[●]";
            else
                outs += "[＿]";
            outs += Title[i] + "\n";
        }
        return outs;
    }

    // 当日シリアル秒からHH:MM:SSへの変換
    public String SerialSec2Time( int serialsec )
    {
        try {
            int hh = serialsec / 3600;
            int mm = (serialsec % 3600) / 60;
            int ss = serialsec % 60;

            String outs;
            outs = String.format("%02d:%02d:%02d", hh, mm, ss);
            return outs;
        }
        catch( Exception e ){
            return "";
        }
    }

    public String SerialSec2Time( String serialsec )
    {
        try{
            int timesec = Integer.parseInt(serialsec);
            return SerialSec2Time( timesec );
        }
        catch( Exception e ){
            return "";
        }
    }
}
