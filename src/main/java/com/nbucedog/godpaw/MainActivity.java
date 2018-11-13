package com.nbucedog.godpaw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.Random;

public class MainActivity extends Activity {
    public MiniHttpServer webserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn_DeelBegin,btn_DeelStop;
        btn_DeelBegin = (Button) findViewById(R.id.DeelBegin);
        btn_DeelStop = (Button) findViewById(R.id.DeelStop);
        btn_DeelBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String docroot = "/assets/www";
                int port = 8080;
                int num = 30;
                int[] Role;
                int[] RolePool;
                CheckBox[] CkBoxArr;
                CkBoxArr = getCkBoxArr(num);
                Log.d("DEMOLOG",CkBoxArr[3].getText().toString());
                int maxnum = getMaxnum(CkBoxArr,num);
                Role = getRoleArr(CkBoxArr,maxnum);
                RolePool = getRolePool(Role,maxnum);
                MiniHttpServer webs=new MiniHttpServer(docroot, port,RolePool,maxnum);
                webserver = webs;
                btn_DeelBegin.setVisibility(View.INVISIBLE);
                btn_DeelStop.setVisibility(View.VISIBLE);
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("maxnum",maxnum);
                bundle.putSerializable("DATA",RolePool);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btn_DeelStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webserver.stopService();
                btn_DeelStop.setVisibility(View.INVISIBLE);
                btn_DeelBegin.setVisibility(View.VISIBLE);
            }
        });
    }
    public static int[] getRolePool(int[] Role, int no){
        Random random = new Random();
        for (int i=0; i<no; i++ ) {
            int p = random.nextInt(no);
            int tmp = Role[i];
            Role[i] = Role[p];
            Role[p] = tmp;
        }
        random = null;
        int[] output = new int[no+1];
        output[0] = 0;
        for(int i=0;i<no;i++){
            output[i+1] = Role[i];
        }
        return output;
    }
    public CheckBox[] getCkBoxArr(int num){
        CheckBox[] Ckbox = new CheckBox[num];
        Ckbox[0] = (CheckBox)findViewById(R.id.CkJinyan);
        Ckbox[1] = (CheckBox)findViewById(R.id.CkXiujian);
        Ckbox[2] = (CheckBox)findViewById(R.id.CkQianxing);
        Ckbox[3] = (CheckBox)findViewById(R.id.CkShouwei);
        Ckbox[4] = (CheckBox)findViewById(R.id.CkYexiong);
        Ckbox[5] = (CheckBox)findViewById(R.id.CkNvwu);
        Ckbox[6] = (CheckBox)findViewById(R.id.CkYuyan);
        Ckbox[7] = (CheckBox)findViewById(R.id.CkLieren);
        Ckbox[8] = (CheckBox)findViewById(R.id.CkBaichi);
        Ckbox[9] = (CheckBox)findViewById(R.id.CkZhanglao);
        Ckbox[10] = (CheckBox)findViewById(R.id.CkQiubi);
        Ckbox[11] = (CheckBox)findViewById(R.id.CkCunmin1);
        Ckbox[12] = (CheckBox)findViewById(R.id.CkCunmin2);
        Ckbox[13] = (CheckBox)findViewById(R.id.CkCunmin3);
        Ckbox[14] = (CheckBox)findViewById(R.id.CkCunmin4);
        Ckbox[15] = (CheckBox)findViewById(R.id.CkCunmin5);
        Ckbox[16] = (CheckBox)findViewById(R.id.CkCunmin6);
        Ckbox[17] = (CheckBox)findViewById(R.id.CkCunmin7);
        Ckbox[18] = (CheckBox)findViewById(R.id.CkCunmin8);
        Ckbox[19] = (CheckBox)findViewById(R.id.CkLangren1);
        Ckbox[20] = (CheckBox)findViewById(R.id.CkLangren2);
        Ckbox[21] = (CheckBox)findViewById(R.id.CkLangren3);
        Ckbox[22] = (CheckBox)findViewById(R.id.CkLangren4);
        Ckbox[23] = (CheckBox)findViewById(R.id.CkLangren5);
        Ckbox[24] = (CheckBox)findViewById(R.id.CkLangren6);
        Ckbox[25] = (CheckBox)findViewById(R.id.CkLangren7);
        Ckbox[26] = (CheckBox)findViewById(R.id.CkLangren8);
        Ckbox[27] = (CheckBox)findViewById(R.id.CkDaozei);
        Ckbox[28] = (CheckBox)findViewById(R.id.CkYehai);
        Ckbox[29] = (CheckBox)findViewById(R.id.CkBailang);
        return Ckbox;
    }
    public int getMaxnum(CheckBox[] Ckbox,int num){
        int Maxnum = 0;
        Log.d("DEMOLOG", "getMaxnum: ");
        Log.d("DEMOLOG", Ckbox[4].getText().toString());
        for(int i=0;i<num;i++){
            if(Ckbox[i].isChecked()){
                Maxnum++;
                Log.d("DEMOLOG", "Maxnum++");
            }
        }
        return Maxnum;
    }
    public int[] getRoleArr(CheckBox[] Ckbox,int Maxnum){
        int[] RoleArr = new int[Maxnum];
        int i=0;
        if(Ckbox[0].isChecked()){
            RoleArr[i] = 4;
            i++;
        }
        if(Ckbox[1].isChecked()){
            RoleArr[i] = 5;
            i++;
        }
        if(Ckbox[2].isChecked()){
            RoleArr[i] = 6;
            i++;
        }
        if(Ckbox[3].isChecked()){
            RoleArr[i] = 7;
            i++;
        }
        if(Ckbox[4].isChecked()){
            RoleArr[i] = 8;
            i++;
        }
        if(Ckbox[5].isChecked()){
            RoleArr[i] = 11;
            i++;
        }
        if(Ckbox[6].isChecked()){
            RoleArr[i] = 12;
            i++;
        }
        if(Ckbox[7].isChecked()){
            RoleArr[i] = 13;
            i++;
        }
        if(Ckbox[8].isChecked()){
            RoleArr[i] = 14;
            i++;
        }
        if(Ckbox[9].isChecked()){
            RoleArr[i] = 15;
            i++;
        }
        if(Ckbox[10].isChecked()){
            RoleArr[i] = 2;//丘比特
            i++;
        }
        for (int j=11;j<11+8;j++){
            if(Ckbox[j].isChecked()){
                RoleArr[i] = 16;
                i++;
            }
        }
        for(int j=19;j<19+8;j++){
            if(Ckbox[j].isChecked()){
                RoleArr[i] = 9;
                i++;
            }
        }
        if(Ckbox[27].isChecked()){
            RoleArr[i] = 1;
            i++;
        }
        if(Ckbox[28].isChecked()){
            RoleArr[i] = 3;
            i++;
        }
        if(Ckbox[29].isChecked()){
            RoleArr[i] = 10;
            i++;
        }
        return RoleArr;
    }
}
