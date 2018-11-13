package com.nbucedog.godpaw;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        int Maxnum = intent.getIntExtra("maxnum",0);
        int RolePool[] = intent.getIntArrayExtra("DATA");
        TextView[] TxArr = getTxArr();
        DisPlayTx(TxArr,RolePool,Maxnum);
        String ip = getHostIP();
        Log.d("DEMOLOG", ip);
        TextView Txip = (TextView)findViewById(R.id.textip);
        Txip.setText("在浏览器中输入："+ip+":8080");
        Button btn_back = (Button)findViewById(R.id.EndGame);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.this.finish();
            }
        });
    }
    public static String getHostIP() {

        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("yao", "SocketException");
            e.printStackTrace();
        }
        return hostIp;

    }
    public TextView[] getTxArr(){
        TextView[] TxArr = new TextView[21];
        TxArr[0] = (TextView)findViewById(R.id.text1);
        TxArr[1] = (TextView)findViewById(R.id.text2);
        TxArr[2] = (TextView)findViewById(R.id.text3);
        TxArr[3] = (TextView)findViewById(R.id.text4);
        TxArr[4] = (TextView)findViewById(R.id.text5);
        TxArr[5] = (TextView)findViewById(R.id.text6);
        TxArr[6] = (TextView)findViewById(R.id.text7);
        TxArr[7] = (TextView)findViewById(R.id.text8);
        TxArr[8] = (TextView)findViewById(R.id.text9);
        TxArr[9] = (TextView)findViewById(R.id.text10);
        TxArr[10] = (TextView)findViewById(R.id.text11);
        TxArr[11] = (TextView)findViewById(R.id.text12);
        TxArr[12] = (TextView)findViewById(R.id.text13);
        TxArr[13] = (TextView)findViewById(R.id.text14);
        TxArr[14] = (TextView)findViewById(R.id.text15);
        TxArr[15] = (TextView)findViewById(R.id.text16);
        TxArr[16] = (TextView)findViewById(R.id.text17);
        TxArr[17] = (TextView)findViewById(R.id.text18);
        TxArr[18] = (TextView)findViewById(R.id.text19);
        TxArr[19] = (TextView)findViewById(R.id.text20);
        TxArr[20] = (TextView)findViewById(R.id.text21);
        return TxArr;
    }
    public void DisPlayTx(TextView[] TxArr,int[] RolePool,int maxnum){
        for(int i=0;i<maxnum;i++){
            String RoleName = getRolestring(RolePool[i+1]);
            int j=i+1;
            TxArr[i].setText(j+"号:\n"+RoleName);
            TxArr[i].setVisibility(View.VISIBLE);
            if(RolePool[i+1]>=9&&RolePool[i+1]<=10){
                if(i%2==0) {
                    TxArr[i].setBackgroundColor(0x55FF6666);
                }
                else{
                    TxArr[i].setBackgroundColor(0x66FF8888);
                }
            }
            else if(i%2==0){
                TxArr[i].setBackgroundColor(0x668888FF);
            }
        }
        /*if(maxnum%3==1){
            TxArr[maxnum].setVisibility(View.VISIBLE);
            TxArr[maxnum+1].setVisibility(View.VISIBLE);
        }
        else if(maxnum%3==2){
            TxArr[maxnum].setVisibility(View.VISIBLE);
        }*/
    }
    public String getRolestring(int RoleId){
        String RoleName;
        switch (RoleId){
            case 1:RoleName = "盗贼";break;
            case 2:RoleName = "丘比特";break;
            case 3:RoleName = "野孩子";break;
            case 4:RoleName = "禁言长老";break;
            case 5:RoleName = "锈剑骑士";break;
            case 6:RoleName = "潜行者";break;
            case 7:RoleName = "守卫";break;
            case 8:RoleName = "野熊";break;
            case 9:RoleName = "狼人";break;
            case 10:RoleName = "白狼王";break;
            case 11:RoleName = "女巫";break;
            case 12:RoleName = "预言家";break;
            case 13:RoleName = "猎人";break;
            case 14:RoleName = "白痴";break;
            case 15:RoleName = "长老";break;
            case 16:RoleName = "村民";break;
            default:RoleName = "加载失败";break;
        }
        return RoleName;
    }
}
