package cn.hbu.cs.intent20190824;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

/**
 * Created by David on 2017/10/16.
 */

public class SecondActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);

    }
}
