package gangbo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class baseActivity extends Activity {
    private MyBaseActiviy_Broad oBaseActiviy_Broad;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //动态注册广播
        oBaseActiviy_Broad = new MyBaseActiviy_Broad();

        IntentFilter intentFilter = new IntentFilter("drc.xxx.yyy.baseActivity");
        registerReceiver(oBaseActiviy_Broad, intentFilter);
    }

    //在销毁的方法里面注销广播
    protected void onDestroy() {

        super.onDestroy();
        unregisterReceiver(oBaseActiviy_Broad);//注销广播
    }

    //定义一个广播
    public class MyBaseActiviy_Broad extends BroadcastReceiver {

        public void onReceive(Context arg0, Intent intent) {
//接收发送过来的广播内容
            int closeAll = intent.getIntExtra("closeAll", 0);
            if (closeAll == 1) {
                finish();//销毁BaseActivity
            }
        }

    }
    public void showToast(String text) {
        Toast.makeText(this, text, 2000).show();
    }
}
