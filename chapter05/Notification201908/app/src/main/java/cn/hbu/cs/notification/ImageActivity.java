package cn.hbu.cs.notification;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView imageView = (ImageView) findViewById(R.id.image);
//        PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);
    }
}
