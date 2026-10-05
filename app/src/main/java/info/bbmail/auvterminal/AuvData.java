package info.bbmail.auvterminal;

/**
 * Data Format for AE2000a/f
 */


public class AuvData
{
    private Nmea nm = new Nmea();
	public String				AuvTime;
	public Gps					TabGps = new Gps();

	public AuvIO				io = new AuvIO();
	public AuvSensorQuad		SensorQuad = new AuvSensorQuad();
	public AuvSensorDepth		SensorDepth = new AuvSensorDepth();
	public AuvActuatorWing		ActuatorWing = new AuvActuatorWing();
	public AuvActuatorThrust	ActuatorThrust = new AuvActuatorThrust();
	public AuvSensorDvl			SensorDvl = new AuvSensorDvl();
	public AuvSensorMicron		SensorMicron = new AuvSensorMicron();
//	public AuvSensorMag			SensorMag = new AuvSensorMag();
	public AuvSensorBattery		SensorBattery = new AuvSensorBattery();
//	public AuvSensorMiniCT		SensorMiniCT = new AuvSensorMiniCT();
	public AuvSensorPayload		SensorPayload = new AuvSensorPayload();
	public AuvSensorPos			SensorPos = new AuvSensorPos();
	public AuvBattery			Battery = new AuvBattery();
	public AuvNfly				Nfly = new AuvNfly();
	public AuvError				Error = new AuvError();
    public AuvParam             Param = new AuvParam();

    // TASK_ID_MAIN,,,,,,,,,,,,,,,
    public String ErrTask01 = "Main task";
    public String ErrStr01 = "Error Main task";
    public String ErrorID_01 = "－,－,－,－,－,－,－,－,－,－,－,－,－,－,－,－";
    // TASK_ID_DVL,,,,,,,,,,,,,,,
    public String ErrTask02 = "DVL task";
    public String ErrStr02 = "Error DVL task";
    public String ErrorID_02 = "－,－,－,－,－,－,－,－,－,－,－,－,－,－,TransductionTimeout,Task Stop";
    // TASK_ID_CURMON,,,,,,,,,,,,,,,
    public String ErrTask03 = "■CurMon task";
    public String ErrStr03 = "Error CurrentMonitor task";
    public String ErrorID_03 = "－,－,－,－,－,－,－,－,－,－,－,－,－,－,TransductionTimeout,Task Stop";
    // TASK_ID_IO,,,,,,,,,,,,,,,
    public String ErrTask04 = "IO task";
    public String ErrStr04 = "Error IO task";
    public String ErrorID_04 = "Thruster Power,Elevator Power,Elevator Angle,Elevator Angle Diff,Control Vessel Temp,Battery1 Voltage,Battery2 Voltage,Battery1 Current,Battery2 Current,SSS Water leakage,DataTransVessel Water leakage,Control Vessel Water leakage,Battery1 Water leakage,Battery2 Water leakage,－,Task Stop";

    // TASK_ID_DELTAT,
    public String ErrTask05 = "Prof.Sonar task";
    public String ErrStr05 = "Error Prof.Sonar task";
    public String ErrorID_05 = "－,－,－,－,－,－,－,－,－,－,－,－,－,－,TransductionTimeout,Task Stop";
    // THREAD_ID_MAG,,,,,,,,,,,,,,,
    public String ErrTask06 = "■Mag task";
    public String ErrStr06 = "Error Mag task";
    public String ErrorID_06 = "－,－,－,－,－,－,－,－,－,－,－,－,－,－,TransductionTimeout,Task Stop";
    // TASK_ID_ACOSTIC
    public String ErrTask07 = "MODEM task";
    public String ErrStr07 = "Error MODEM";
    public String ErrorID_07 = "－,－,－,－,－,－,－,－,－,－,－,－,－,Power On Fault,－,Task Stop";
    // TASK_ID_POS,,,,,,,,,,,,,,,
    public String ErrTask08 = "Position task";
    public String ErrStr08 = "Error Position task";
    public String ErrorID_08 =  "Lat,Lon,VX,VY,VZ,ROLL,PITCH,rrate,prate,yrate,depth,Dist0deg,DistFwd,Height,－,Task Stop";
    // TASK_ID_UDP,,,,,,,,,,,,,,,
    public String ErrTask09 = "UDP task";
    public String ErrStr09 = "Error UDP task";
    public String ErrorID_09 = "－,－,－,－,－,－,－,－,－,－,－,－,－,－,TransductionTimeout,Task Stop";
	// TASK_ID_ABNORMAL
    public String ErrTask10 = "Error task";
    public String ErrStr10 = "Error task";
    public String ErrorID_10 = "－,－,－,－,－,－,－,－,－,－,－,－,－,－,－,－";
    // TASK_ID_PAYLOAD,,,,,,,,,,,,,,,
    public String ErrTask11 = "PAYLOAD task";
    public String ErrStr11 = "Error PAYLOAD task";
    public String ErrorID_11 = "－,－,－,－,－,－,－,－,－,－,－,－,－,－,TransductionTimeout,Task Stop";
    // TASK_ID_NEWFLY,,,,,,,,,,,,,,,
    public String ErrTask12 = "NewFly task";
    public String ErrStr12 = "Error NewFly task";
    public String ErrorID_12 ="Gain File Read Failure,Mission Course File Read Failure,Return Course File Read Failure,SafetyDepthMap File Read Failure,Course TimeOut,Pitch control Abnormal,Tide Speed is Abnormal,Return Start,WayPoint Approaching Abnormal,No NewFly Controll Dive,NewFly Start Reservation Timeout,－,－,－,－,Task Stop";

    // THREAD_ID_SAILMANAGE,,,,,,,,,,,,,,,
    public String ErrTask13 = "■SailManage task";
    public String ErrStr13 = "Error SailManage task";
    public String ErrorID_13 = "－,－,－,－,－,－,－,－,－,－,－,－,－,－,－,－";
    // TASK_ID_DEPTH
    public String ErrTask14 = "Depth task";
    public String ErrStr14 = "Error Depth task";
    public String ErrorID_14 = "－,－,－,－,－,－,－,－,－,－,－,－,－,－,TransductionTimeout,Task Stop";
    // TASK_ID_QUADRANCE,,,,,,,,,,,,,,,
    public String ErrTask15 = "Quadrans task";
    public String ErrStr15 = "Error Quadrans task";
    public String ErrorID_15 = "NoAlignment,UpdeateStop,－,－,－,－,－,－,－,－,－,－,－,－,TransductionTimeout,Task Stop";

    public AuvData()
    {
//        TestDataSet();
    }

    public void TestDataSet()
    {
        AuvTime = "2016.07.01 10:11:12";
        io.DI[ io.DI_MAGPWR     ] = "1";
        io.DI[ io.DI_BALASTPWR1 ] = "2";
        io.DI[ io.DI_BALASTPWR2 ] = "3";
        io.DI[ io.DI_BALASTPWR3 ] = "4";
        io.DI[ io.DI_FSTRPWR    ] = "5";
        io.DI[ io.DI_THRUSTPWR  ] = "6";
        io.DI[ io.DI_DVLPWR     ] = "7";
		io.DI[ io.DI_ELVPWR     ] = "8";
        io.DI[ io.DI_LANPWR     ] = "9";
        io.DI[ io.DI_DIVEMODE   ] = "10";
        io.DI[ io.DI_SSSWLEAK   ] = "11";
        io.DI[ io.DI_TRNSWLEAK  ] = "12";
        io.DI[ io.DI_DIRWLEAK   ] = "13";
		io.DI[ io.DI_CONTWLEAK  ] = "14";
		io.DI[ io.DI_BAT1WLEAK  ] = "15";
		io.DI[ io.DI_BAT2WLEAK  ] = "16";
		io.DI[ io.DI_SSSPWR     ] = "17";
		io.DI[ io.DI_AUX01      ] = "18";
		io.DI[ io.DI_AUX02      ] = "19";
		io.DI[ io.DI_AUX03      ] = "20";

        io.AI[ io.AI_BAT_VOLT    ] = "130.0";
        io.AI[ io.AI_CCURRENT    ] = "1.2";
        io.AI[ io.AI_ELV         ] = "10.0";
        io.AI[ io.AI_BAT1_TEMP   ] = "11.1";
        io.AI[ io.AI_BAT2_TEMP   ] = "30.0";
        io.AI[ io.AI_CONT_TEMP   ] = "31.1";

        SensorQuad.roll = "5.0";
        SensorQuad.pitch = "10.0";
        SensorQuad.yaw = "15.0";

        SensorDvl.dvl_surgeVelWater = "100";
        SensorDvl.dvl_swayVelWater = "101";
        SensorDvl.dvl_surgeVelBottom = "102";
        SensorDvl.dvl_swayVelBottom = "103";
        SensorDvl.dvl_rangeBottom = "104";
        SensorDvl.dvl_validWater = "105";
        SensorDvl.dvl_validBottom = "106";

        SensorDepth.depth_pressure = "107";

        SensorMicron.Dist0deg = "108";
        SensorMicron.DistFwd = "109";

        ActuatorWing.Order = "20.0";
        ActuatorThrust.leftOrder = "20.2";
        ActuatorThrust.rightOrder = "20.3";

        SensorPayload.StatusStr = "#TSK:O,PWR:O,REC:O,CAL:O";

		SensorBattery.AIC_TREND		= "01";
		SensorBattery.AIC_SUMUP		= "02";
		SensorBattery.AIC_THRUST	= "03";
		SensorBattery.AIC_CURRENT	= "04";

        Error.ErrBit = "0x0010";
        Error.ErrTask = "1";
        Error.ErrProc = "3";
        Error.ErrTime = "0";

        Error.taskdat[ Error.TASK_ID_MAIN       ].TaskStatus = "1";
        Error.taskdat[ Error.TASK_ID_MAIN       ].ErrCode    = "0x0000";
        Error.taskdat[ Error.TASK_ID_DVL        ].TaskStatus = "1";
        Error.taskdat[ Error.TASK_ID_DVL        ].ErrCode    = "0x0000";
        Error.taskdat[ Error.TASK_ID_CURMON     ].TaskStatus = "1";
        Error.taskdat[ Error.TASK_ID_CURMON     ].ErrCode    = "0x0000";
        Error.taskdat[ Error.TASK_ID_IO         ].TaskStatus = "1";
        Error.taskdat[ Error.TASK_ID_IO         ].ErrCode    = "0x0000";
        Error.taskdat[ Error.TASK_ID_DELTAT     ].TaskStatus = "1";
        Error.taskdat[ Error.TASK_ID_DELTAT     ].ErrCode    = "0x0000";
        Error.taskdat[ Error.TASK_ID_MAG        ].TaskStatus = "1";
        Error.taskdat[ Error.TASK_ID_MAG        ].ErrCode    = "0x0000";
        Error.taskdat[ Error.TASK_ID_ACOSTIC    ].TaskStatus = "1";
        Error.taskdat[ Error.TASK_ID_ACOSTIC    ].ErrCode    = "0x0000";
        Error.taskdat[ Error.TASK_ID_POS        ].TaskStatus = "1";
        Error.taskdat[ Error.TASK_ID_POS        ].ErrCode    = "0x0000";
		Error.taskdat[ Error.TASK_ID_UDP        ].TaskStatus = "1";
		Error.taskdat[ Error.TASK_ID_UDP        ].ErrCode    = "0x0000";
		Error.taskdat[ Error.TASK_ID_ABNORMAL   ].TaskStatus = "1";
		Error.taskdat[ Error.TASK_ID_ABNORMAL   ].ErrCode    = "0x0000";
		Error.taskdat[ Error.TASK_ID_PAYLOAD    ].TaskStatus = "1";
		Error.taskdat[ Error.TASK_ID_PAYLOAD    ].ErrCode    = "0x0000";
		Error.taskdat[ Error.TASK_ID_NEWFLY     ].TaskStatus = "1";
		Error.taskdat[ Error.TASK_ID_NEWFLY     ].ErrCode    = "0x0000";
		Error.taskdat[ Error.TASK_ID_SAILMANAGE ].TaskStatus = "1";
		Error.taskdat[ Error.TASK_ID_SAILMANAGE ].ErrCode    = "0x0000";
		Error.taskdat[ Error.TASK_ID_DEPTH      ].TaskStatus = "1";
		Error.taskdat[ Error.TASK_ID_DEPTH      ].ErrCode    = "0x0000";
        Error.taskdat[ Error.TASK_ID_QUADRANS   ].TaskStatus = "1";
        Error.taskdat[ Error.TASK_ID_QUADRANS   ].ErrCode    = "0x0000";

        SensorPos.Lat    = nm.ToDMM(Double.valueOf(-33.3));
        SensorPos.Lon    = nm.ToDMM(Double.valueOf(-133.4));
        SensorPos.Depth  = "10.0";
        SensorPos.Height = "20.0";

        Nfly.nfly_ctime = "30.0";
        Nfly.nfly_go    = "101";
        Nfly.nfly_exec  = "1";

        Param.limit_time = "2025/12/03 18:17:00";

    }

// 2017.04.26 
	public boolean Decode( String s )
	{
        String[] data = s.split(",");
        if( data[0].equals("gettextdata") == false)
        {
            return false;
        }
        try{
//            addText( "gettextdata:" + data[1] );

			AuvTime = data[1];
			io.DI[ io.DI_MAGPWR     ] = data[ 2];
			io.DI[ io.DI_BALASTPWR1 ] = data[ 3];
			io.DI[ io.DI_BALASTPWR2 ] = data[ 4];
			io.DI[ io.DI_BALASTPWR3 ] = data[ 5];
			io.DI[ io.DI_FSTRPWR    ] = data[ 6];
			io.DI[ io.DI_THRUSTPWR  ] = data[ 7];
			io.DI[ io.DI_DVLPWR     ] = data[ 8];
			io.DI[ io.DI_ELVPWR     ] = data[ 9];
			io.DI[ io.DI_LANPWR     ] = data[10];
			io.DI[ io.DI_DIVEMODE   ] = data[11];
			io.DI[ io.DI_SSSWLEAK   ] = data[12];
			io.DI[ io.DI_TRNSWLEAK  ] = data[13];
			io.DI[ io.DI_DIRWLEAK   ] = data[14];
			io.DI[ io.DI_CONTWLEAK  ] = data[15];
			io.DI[ io.DI_BAT1WLEAK  ] = data[16];
			io.DI[ io.DI_BAT2WLEAK  ] = data[17];
			io.DI[ io.DI_SSSPWR     ] = data[18];
			io.DI[ io.DI_AUX01      ] = data[19];
			io.DI[ io.DI_AUX02      ] = data[20];
			io.DI[ io.DI_AUX03      ] = data[21];

			io.AI[ io.AI_BAT_VOLT  ] = data[22];
			io.AI[ io.AI_CCURRENT  ] = data[23];
			io.AI[ io.AI_ELV       ] = data[24];
			io.AI[ io.AI_BAT1_TEMP ] = data[25];
			io.AI[ io.AI_BAT2_TEMP ] = data[26];
			io.AI[ io.AI_CONT_TEMP ] = data[27];

			SensorQuad.roll = data[28];
			SensorQuad.pitch = data[29];
			SensorQuad.yaw = data[30];

			SensorDvl.dvl_surgeVelWater = data[31];
			SensorDvl.dvl_swayVelWater = data[32];
			SensorDvl.dvl_surgeVelBottom = data[33];
			SensorDvl.dvl_swayVelBottom = data[34];
			SensorDvl.dvl_rangeBottom = data[35];
			SensorDvl.dvl_validWater = data[36];	
			SensorDvl.dvl_validBottom = data[37];

			SensorDepth.depth_pressure = data[38];

			SensorMicron.Dist0deg = data[39];
			SensorMicron.DistFwd = data[40];

			ActuatorWing.Order = data[41];
			ActuatorThrust.leftOrder = data[42];
			ActuatorThrust.rightOrder = data[43];

			SensorPayload.StatusStr = data[44];

			SensorBattery.AIC_TREND		= data[45];
			SensorBattery.AIC_SUMUP		= data[46];
			SensorBattery.AIC_THRUST	= data[47];
			SensorBattery.AIC_CURRENT	= data[48];

			Error.ErrBit = data[49];
			Error.ErrTask = data[50];
			Error.ErrProc = data[51];
			Error.ErrTime = data[52];

			Error.taskdat[ Error.TASK_ID_MAIN       ].TaskStatus = data[53];
			Error.taskdat[ Error.TASK_ID_MAIN       ].ErrCode    = data[54];
			Error.taskdat[ Error.TASK_ID_DVL        ].TaskStatus = data[55];
			Error.taskdat[ Error.TASK_ID_DVL        ].ErrCode    = data[56];
			Error.taskdat[ Error.TASK_ID_CURMON     ].TaskStatus = data[57];
			Error.taskdat[ Error.TASK_ID_CURMON     ].ErrCode    = data[58];
			Error.taskdat[ Error.TASK_ID_IO         ].TaskStatus = data[59];
			Error.taskdat[ Error.TASK_ID_IO         ].ErrCode    = data[60];
			Error.taskdat[ Error.TASK_ID_DELTAT     ].TaskStatus = data[61];
			Error.taskdat[ Error.TASK_ID_DELTAT     ].ErrCode    = data[62];
			Error.taskdat[ Error.TASK_ID_MAG        ].TaskStatus = data[63];
			Error.taskdat[ Error.TASK_ID_MAG        ].ErrCode    = data[64];
			Error.taskdat[ Error.TASK_ID_ACOSTIC    ].TaskStatus = data[65];
			Error.taskdat[ Error.TASK_ID_ACOSTIC    ].ErrCode    = data[66];
			Error.taskdat[ Error.TASK_ID_POS        ].TaskStatus = data[67];
			Error.taskdat[ Error.TASK_ID_POS        ].ErrCode    = data[68];
			Error.taskdat[ Error.TASK_ID_UDP        ].TaskStatus = data[69];
			Error.taskdat[ Error.TASK_ID_UDP        ].ErrCode    = data[70];
			Error.taskdat[ Error.TASK_ID_ABNORMAL   ].TaskStatus = data[71];
			Error.taskdat[ Error.TASK_ID_ABNORMAL   ].ErrCode    = data[72];
			Error.taskdat[ Error.TASK_ID_PAYLOAD    ].TaskStatus = data[73];
			Error.taskdat[ Error.TASK_ID_PAYLOAD    ].ErrCode    = data[74];
			Error.taskdat[ Error.TASK_ID_NEWFLY     ].TaskStatus = data[75];
			Error.taskdat[ Error.TASK_ID_NEWFLY     ].ErrCode    = data[76];
			Error.taskdat[ Error.TASK_ID_SAILMANAGE ].TaskStatus = data[77];
			Error.taskdat[ Error.TASK_ID_SAILMANAGE ].ErrCode    = data[78];
			Error.taskdat[ Error.TASK_ID_DEPTH      ].TaskStatus = data[79];
			Error.taskdat[ Error.TASK_ID_DEPTH      ].ErrCode    = data[80];
			Error.taskdat[ Error.TASK_ID_QUADRANS   ].TaskStatus = data[81];
			Error.taskdat[ Error.TASK_ID_QUADRANS   ].ErrCode    = data[82];

			SensorPos.Lat    = nm.ToDMM(Double.valueOf(data[83]));
			SensorPos.Lon    = nm.ToDMM(Double.valueOf(data[84]));
			SensorPos.Depth  = data[85];
			SensorPos.Height = data[86];

			Nfly.nfly_ctime = data[87];
			Nfly.nfly_go    = data[88];
			Nfly.nfly_exec  = data[89];

			int ti = 90;
			Param.limit_time = data[ti] + "/" + data[ti + 1] + "/" + data[ti + 2] + " " +
					data[ti + 3] + ":" + data[ti + 4] + ":" + data[ti + 5];


        }
        catch(Exception e){
            System.out.println(e.toString());
// s=e.toString();
			return false;
        }
	return true;
	}
};

class Gps
{
	public String	Latitude;
	public String	Longitude;
	public String	Qty;
	public String	LastRecv;
	public String	RecvGGAStr;
}

class AuvIO
{
	public String[]	DI;
	public String[]	DO;
	public String[]	AI;
	public String[]	AO;

/*********************
**  for AE2000 Settings 
*********************/

/**** DI 2017.04.26 ****/
	public static final int DI_RESERVED01   = 0;			// （未使用）
	public static final int DI_MAGPWR		= 1;			// 地磁気計 電源確認
	public static final int DI_BALASTPWR1	= 2;			// バラストリリーサ電源確認(潜航用）
	public static final int DI_BALASTPWR2	= 3;			// バラストリリーサ電源確認(浮上用）
	public static final int DI_BALASTPWR3	= 4;			// バラストリリーサ電源確認(調整用）
	public static final int DI_FSTRPWR		= 5;			// 前探・データ伝送装置 電源確認
	public static final int DI_THRUSTPWR	= 6;			// スラスタ 電源確認
	public static final int DI_DVLPWR		= 7;			// DVL 電源確認
                                                            
	public static final int DI_RESERVED11	= 8;			// （未使用）
	public static final int DI_ELVPWR		= 9;			// 昇降舵 電源確認
	public static final int DI_LANPWR		= 10;			// 無線LAN 電源確認
	public static final int DI_DIVEMODE		= 11;			// 潜航モードスイッチ（未使用）
	public static final int DI_SSSWLEAK		= 12;			// 漏水センサ SSS
	public static final int DI_TRNSWLEAK	= 13;			// 漏水センサ データ伝送装置
	public static final int DI_DIRWLEAK		= 14;			// 漏水センサ　方位姿勢計測ユニット
	public static final int DI_CONTWLEAK	= 15;			// 漏水センサ 電子回路容器
                                                            
	public static final int DI_BAT1WLEAK	= 16;			// 漏水センサ 電池回路容器１
	public static final int DI_BAT2WLEAK	= 17;			// 漏水センサ 電池回路容器２
	public static final int DI_SSSPWR		= 18;			// SSS 電源確認
	public static final int DI_RESERVED34	= 19;			// （未使用）
	public static final int DI_RESERVED35	= 20;			// （未使用）
	public static final int DI_AUX01		= 21;			// 補助入力１（未使用）
	public static final int DI_AUX02		= 22;			// 補助入力２（未使用）
	public static final int DI_AUX03		= 23;			// 補助入力３（未使用）

	public static final int DI_MAX         = 24;			//


/**** DO 2017.04.26 ****/
	public static final int DO_RESERVED01	= 0;			// （未使用）
	public static final int DO_MAGPWR		= 1;			// 地磁気計電源
	public static final int DO_DEBALAST1	= 2;			// バラストリリーサー 潜航用
	public static final int DO_DEBALAST2	= 3;			// バラストリリーサー 浮上用
	public static final int DO_DEBALAST3	= 4;			// バラストリリーサー 調整用
	public static final int DO_FSTRPWR		= 5;			// 前探・データ伝送装置 電源
	public static final int DO_THRUSTPWR	= 6;			// スラスタ電源
	public static final int DO_DVLPWR		= 7;			// DVL電源
                                                            
	public static final int DO_RESERVED11	= 8;			// （未使用）
	public static final int DO_ELVPWR		= 9;			// 昇降舵電源
	public static final int DO_LANPWR		= 10;			// 無線LAN電源
	public static final int DO_CPURESET		= 11;			// CPUリセット（未使用）
	public static final int DO_RESERVED15	= 12;			// （未使用）
	public static final int DO_RESERVED16	= 13;			// （未使用）
	public static final int DO_RESERVED17	= 14;			// （未使用）
	public static final int DO_RESERVED18	= 15;			// （未使用）
                                                            
	public static final int DO_RESERVED21	= 16;			// （未使用）
	public static final int DO_RESERVED22	= 17;			// （未使用）
	public static final int DO_SSSPWR		= 18;			// SSS電源
	public static final int DO_RESERVED24	= 19;			// （未使用）
	public static final int DO_WDP			= 20;			// WatchDog パルス
	public static final int DO_AUX01		= 21;			// 補助出力１（未使用）
	public static final int DO_AUX02		= 22;			// 補助出力２（未使用）
	public static final int DO_AUX03		= 23;			// 補助出力３（未使用）
                                                            
	public static final int DO_MAX         = 24;			//

/**** AI 2017.04.26 ****/
	public static final int AI_CONT_TEMP	= 0;			// 00 メイン容器　温度
	public static final int AI_BAT1_TEMP	= 1;			// 01 バッテリ１　温度
	public static final int AI_BAT2_TEMP	= 2;			// 02 バッテリ２　温度
	public static final int AI_SPLASH		= 3;			// 03 着水センサ
	public static final int AI_BAT_VOLT		= 4;			// 04 バッテリ　電圧
	public static final int AI_ELV			= 5;			// 05 昇降舵舵角
	public static final int AI_REG_P5		= 6;			// 06 +5V電圧
	public static final int AI_REG_P12		= 7;			// 07 +12V電圧
	public static final int AI_REG_N12		= 8;			// 08 -12V電圧
	public static final int AI_REG_P24A		= 9;			// 09 +24V電圧A
	public static final int AI_REG_P24B		= 10;			// 10 +24V電圧B
	public static final int AI_CTHRUST		= 11;			// 11 スラスタ 電流
	public static final int AI_CREG_P24A	= 12;			// 12 24V(A) 電流
	public static final int AI_CREG_P24B	= 13;			// 13 24V(B) 電流
	public static final int AI_CCURRENT		= 14;			// 14 回路電流
	public static final int AI_RESERVED16	= 15;			//(未使用）

	public static final int AI_MAX         = 16;			//

/**** AO 2017.04.26 ****/
	public static final int AO_ELEVETOR		= 0;			// 舵機指令
	public static final int AO_RESERVED02	= 1;			// 未使用）
	public static final int AO_THRUSTER_L	= 2;			// スラスタ左回転指令
	public static final int AO_THRUSTER_R	= 3;			// スラスタ右回転指令

	public static final int AO_RESERVED04  = 4;				// (未使用）
	public static final int AO_RESERVED05  = 5;				// (未使用）
	public static final int AO_RESERVED06  = 6;				// (未使用）
	public static final int AO_RESERVED07  = 7;				// (未使用）

	public static final int AO_MAX         = 8;			//


    /***
     *　コンストラクタ
     * 　メモリを確保
     */
    AuvIO()
    {
        DI = new String[DI_MAX];
        DO = new String[DO_MAX];
        AI = new String[AI_MAX];
        AO = new String[AO_MAX];
    }

}

class AuvSensorQuad
{
	public String		roll;					// ロール角
	public String		pitch;					// ピッチ角
	public String		yaw;					// ヨー角
//	public String		Valid;					// センサ値使用可能フラグ
}

class AuvSensorDepth
{
	public String		depth_pressure;								// 水圧値(mpa)
//	public String		depth_depth;								// 深度値(m)
//	public String		depth_temp;									// 温度
}

class AuvActuatorWing
{
	public String		Order;						// 指令		deg
//	public String		leftOrder;					// 左指令		deg
//	public String		rightOrder;					// 右指令		deg
//	public String		MleftOrder;					// 左手動指令	deg
//	public String		MrightOrder;				// 右手動指令	deg
//	public String		leftPos;					// 左FB			deg
//	public String		rightPos;					// 右FB			deg
}

class AuvActuatorThrust
{
//	public String		mainOrder;					// 指令		-1.0 ～ +1.0
	public String		leftOrder;					// 左指令		-1.0 ～ +1.0
	public String		rightOrder;					// 右指令		-1.0 ～ +1.0
//	public String		MleftOrder;					// 左手動指令	-1.0 ～ +1.0
//	public String		MrightOrder;				// 右手動指令	-1.0 ～ +1.0
}

class AuvSensorDvl
{
	public String		dvl_surgeVelWater;							// 前後方向対水速度(mm/s)
	public String		dvl_swayVelWater;							// 左右方向対水速度(mm/s)
///	public String		dvl_heaveVelWater;							// 上下方向対水速度(mm/s)
	public String		dvl_surgeVelBottom;							// 前後方向対水速度(mm/s)
	public String		dvl_swayVelBottom;							// 左右方向対水速度(mm/s)
//	public String		dvl_heaveVelBottom;							// 上下方向対水速度(mm/s)
	public String		dvl_rangeBottom;							// 高度
	public String		dvl_validWater;								// 対水データ有効
	public String		dvl_validBottom;							// 対地データ有効
}

class  AuvSensorMicron
{
//	public String		range;										// 最接近対象距離(m)
//	public String		angle;										// 現在の角度
    public String		Dist0deg;										// 最接近対象距離(m)
    public String		DistFwd;										// 現在の角度
}
/****
class  AuvSensorMag
{
	public String		X;											// [nT]
	public String		Y;											// [nT]
	public String		Z;											// [nT]
	public String		Temp;										// 温度[C]
}
****/
class  AuvSensorBattery
{
//	public String		fullChargedCellCnt;				// 満充電セル数
//	public String		MaxTemp;						// 最大温度
//	public String		startAH;						// 開始時の残りバッテリ容量AH
//	public String		remainAH;						// 現在の残りバッテリ容量AH

	public String		AIC_TREND;						// 現在使用AH
	public String		AIC_SUMUP;						// 使用AH累計
	public String		AIC_THRUST ;					// スラスタA
	public String		AIC_CURRENT;					// 回路A
}
/****
class  AuvSensorMiniCT
{
	public String		Cond;										// Conductivity[mS/cm]
	public String		Temp;										// 温度[C]
}
****/
class AuvSensorPayload
{
	public String		StatusStr;		// ステータス文字列
}


class AuvSensorPos
{
//	public String		Roll;										// 艇体姿勢
//	public String		Pitch;										// 艇体姿勢
//	public String		Yaw;										// 艇体方位
//	public String		rrate;										// ロール速度
//	public String		prate;										// ピッチ速度
//	public String		yrate;										// ヨー速度
//	public String		vxe;										// 対地速度（地球座標系）北＋
//	public String		vye;										// 対地速度（地球座標系）東＋
//	public String		vxw;										// 対水速度 艇体前方＋
//	public String		vyw;										// 対水速度 艇体右舷＋
//	public String		vx;											// 対地速度 艇体前方＋：対地が見えないときは対水
//	public String		vy;											// 対地速度 艇体右舷＋：対地が見えないときは対水
//	public String		vz;											// 対地速度 下方向＋
	public String		Lat;										// 緯度
	public String		Lon;										// 経度
	public String		Depth;										// 深度
	public String		Height;										// 高度
//	public String		Dist0deg;									// deltaT 0度水平　(m)
//	public String		DistFwd;									// deltaT 艇体前方　計測距離(m)
//	public String		WaterTemp;									// 深度計：水温
//	public String		Valid;										// センサ値使用可能フラグ
//	public String		Status;										// ステータス
}

class AuvBattery
{
	public String				UseAh;								// 今回使用
	public String				SumAh;								// 積算使用量
//	public String				LastA;								// 前回最後の電流値
}

class AuvNfly
{
	public String		nfly_ctime;								// (data)コース実行時間(ms)
	public String		nfly_go;								// (CMD)
	public String		nfly_exec;								// (CMD)

}

class TaskInfo
{
	public String		TaskCnt;										// タスクエラーカウント
	public String		LastComTime;									// 最終通信時間
	public String		ErrCode;										// エラーコード
	public String		TaskStatus;										// タスクステータス  0:待機 1:起動中 2:通常状態 3:停止中 4:再起動中 -1:状態不明

	public static final int TASKSTATUS_STANDBY  = 0;				// 停止・待機
	public static final int TASKSTATUS_ONSTART  = 1;				// 起動中
	public static final int TASKSTATUS_ONRUN    = 2;				// 動作中
	public static final int TASKSTATUS_ONSTOP   = 3;				// 停止中
	public static final int TASKSTATUS_CONSTRUN = 10;				// 常時動作

    public TaskInfo() {
        TaskCnt="";
        LastComTime="";
        ErrCode= "";
        TaskStatus = "";
    }

}

class AuvError
{
	public String		ErrBit;								// エラー内容
	public String		ErrTask;							// エラーとなったタスク
	public String		ErrProc;							// 実行した処理
	public String		ErrTime;							// 発生した時刻(当日通算秒）

	public TaskInfo[]		taskdat;								// タスクエラー情報

	public static final int TASK_ID_MAIN		= 0;			// 0  メインタスク
	public static final int TASK_ID_DVL			= 1;			// 1  ドップラソナータスク
	public static final int TASK_ID_CURMON		= 2;			// 2  電流確認基板タスク (Linux化で未使用）
	public static final int TASK_ID_IO			= 3;			// 3  IOタスク
	public static final int TASK_ID_DELTAT		= 4;			// 4  DeltaTタスク
	public static final int TASK_ID_MAG			= 5;			// 5  磁力計タスク
	public static final int TASK_ID_ACOSTIC		= 6;			// 6  データ伝送装置タスク(新）
	public static final int TASK_ID_POS			= 7;			// 7  位置推測タスク
	public static final int TASK_ID_UDP			= 8;			// 8  UDP上位通信タスク
	public static final int TASK_ID_ABNORMAL	= 9;			// 9  異常検知、異常処理
	public static final int TASK_ID_PAYLOAD		= 10;			// 10 PAYLOAD GeoSworth/SeaXerocks
	public static final int TASK_ID_NEWFLY		= 11;			// 11 NewFly
	public static final int TASK_ID_SAILMANAGE	= 12;			// 12 航行管理タスク (Linux化で未使用）
	public static final int TASK_ID_DEPTH		= 13;			// 13 深度計タスク
	public static final int TASK_ID_QUADRANS	= 14;			// 14 QUADRANS
	public static final int TASK_ID_SIM			= 15;			// 15 シミュレーション
	public static final int TASK_ID_DIGICAM		= 16;			// 16 デジタルカメラタスク (Linux化で未使用）
	public static final int TASK_ID_CYCLIC		= 17;			// 17 周期ハンドラタスク
	public static final int TASK_ID_CYCLIC_WAKE	= 18;			// 18 周期ハンドラタスク（定期起床タスク）
	public static final int TASK_ID_MAX			= 19;			//    ID最大

	public AuvError()
	{
        taskdat = new TaskInfo[ TASK_ID_MAX ];
        for( int i = 0; i < TASK_ID_MAX; i++ ) {
            taskdat[i] = new TaskInfo();
        }
	}
}

class AuvParam
{
    public String   limit_time;                                 // 帰還時刻
    public long ArignmentStartTimeSecs;                         // アライメント開始時刻

    public AuvParam()
    {
        limit_time = "";
        ArignmentStartTimeSecs = 0;
    }
}