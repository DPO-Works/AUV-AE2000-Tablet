package info.bbmail.auvterminal;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class FragmentLeft extends Fragment implements OnClickListener {

    MainActivity ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ma = (MainActivity)getActivity();

        // fragment再生成抑止
//        setRetainInstance(true);
        return inflater.inflate(R.layout.subleft, container, false);
    }
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //　右側画面差し替えのボタンのリスナー設定
        (ma.findViewById(R.id.btPage01)).setOnClickListener(this);
        (ma.findViewById(R.id.btPage02)).setOnClickListener(this);
        (ma.findViewById(R.id.btPage03)).setOnClickListener(this);
        (ma.findViewById(R.id.btPage04)).setOnClickListener(this);
        (ma.findViewById(R.id.btPage05)).setOnClickListener(this);
        (ma.findViewById(R.id.btPage06)).setOnClickListener(this);
        (ma.findViewById(R.id.btPage07)).setOnClickListener(this);
        (ma.findViewById(R.id.btPage08)).setOnClickListener(this);
        (ma.findViewById(R.id.btPage09)).setOnClickListener(this);
        (ma.findViewById(R.id.btPage10)).setOnClickListener(this);
    }

    // onClickListener で処理できるものを処理する
    public void onClick(View v) {
//    	ma.HideKeybord();

           // 右側の表示を切り替える
        if(v.getId() ==  R.id.btPage01)     ma.SetRightWidnow( 401 );
        if(v.getId() ==  R.id.btPage02)     ma.SetRightWidnow( 402 );
        if(v.getId() ==  R.id.btPage03)     ma.SetRightWidnow( 403 );
        if(v.getId() ==  R.id.btPage04)     ma.SetRightWidnow( 404 );
        if(v.getId() ==  R.id.btPage05)     ma.SetRightWidnow( 405 );
        if(v.getId() ==  R.id.btPage06)     ma.SetRightWidnow( 406 );
        if(v.getId() ==  R.id.btPage07)     ma.SetRightWidnow( 407 );
        if(v.getId() ==  R.id.btPage08)     ma.SetRightWidnow( 408 );
        if(v.getId() ==  R.id.btPage09)     ma.SetRightWidnow( 409 );
        if(v.getId() ==  R.id.btPage10)     ma.SetRightWidnow( 410 );
    }

    // 定義はしたがMainActivityからのCallがわからないので使っていない
    public void reDraw() {
/* **
        ma.cc.SetStringToTextView( ma.ViecleData.io.AI[ ma.ViecleData.io.AI_BAT_VOLT ], R.id.tvBattVolt);
        ma.cc.SetStringToTextView( ma.ViecleData.io.AI[ ma.ViecleData.io.AI_POWER_TEMP ], R.id.tvPowerTemp);
        ma.cc.SetStringToTextView( ma.ViecleData.io.AI[ ma.ViecleData.io.AI_CONT_TEMP ], R.id.tvControllerTemp);
        ma.cc.SetStringToTextView( ma.ViecleData.SensorBattery.MaxTemp, R.id.tvBattTemp);
** */
    }
}
