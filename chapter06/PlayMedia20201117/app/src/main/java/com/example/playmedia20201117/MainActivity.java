package com.example.playmedia20201117;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initView();
    }

    private void initView(){
        Button playButton = ( Button )super.findViewById( R.id.play );
        Button stopButton = ( Button )super.findViewById( R.id.stop );
        playButton.setOnClickListener( clickListener );
        stopButton.setOnClickListener( clickListener );
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        Intent intent = new Intent();
        @Override
        public void onClick(View v) {
            switch ( v.getId() ) {
                case R.id.play:
//                    Intent intent = new Intent();
                    Log.d("MyService","intent executed");
                    intent.setAction("com.example.playmedia20201117.MyService");
                    //不加这句话的话 android 5.0以上会报：Service Intent must be explitict 2018.12.10
                    intent.setPackage("com.example.playmedia20201117");
                    Log.d("MyService","startService executed");
                    startService(intent);
                    break;

                case R.id.stop:
                    stopService(intent);
                    break;

                default:
                    break;
            }

        }
    };
}