package cn.hbu.cs.listviewqq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.mListView);
        setListAdapter(listView);
    }
    public void setListAdapter(ListView listView){
        List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("data0",R.drawable.qq0);
        map.put("data1", "机器猫");
        map.put("data2", "小叮当");
        data.add(map);
        HashMap<String, Object> map0 = new HashMap<String, Object>();
        map0.put("data0",R.drawable.qq1);
        map0.put("data1", "Donkey");
        map0.put("data2", "驴肉火烧");
        data.add(map0);
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("data0",R.drawable.qq2);
        map1.put("data1", "Cat");
        map1.put("data2", "爱吃鱼");
        data.add(map1);
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("data0",R.drawable.qq3);
        map2.put("data1", "Fox");
        map2.put("data2", "天生我材必有用");
        data.add(map2);
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("data0",R.drawable.qq4);
        map3.put("data1", "Little Girl");
        map3.put("data2", "好好学习天天向上");
        data.add(map3);
        HashMap<String, Object> map4 = new HashMap<String, Object>();
        map4.put("data0",R.drawable.qq5);
        map4.put("data1", "用户名_1");
        map4.put("data2", "个性签名_");
        data.add(map4);

        map4.put("data0",R.drawable.qq5);
        map4.put("data1", "用户名_2");
        map4.put("data2", "个性签名_");
        data.add(map4);

        map4.put("data0",R.drawable.qq5);
        map4.put("data1", "用户名_3");
        map4.put("data2", "个性签名_");
        data.add(map4);

        map4.put("data0",R.drawable.qq5);
        map4.put("data1", "用户名_4");
        map4.put("data2", "个性签名_");
        data.add(map4);

        map4.put("data0",R.drawable.qq5);
        map4.put("data1", "用户名_5");
        map4.put("data2", "个性签名_");
        data.add(map4);

        map4.put("data0",R.drawable.qq5);
        map4.put("data1", "用户名_6");
        map4.put("data2", "个性签名_");
        data.add(map4);

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,data,
                R.layout.listview_qq,
                new String[]{"data0","data1","data2"},
                new int[]{R.id.qq_image,R.id.qq_name,R.id.qq_text});
        listView.setAdapter(simpleAdapter);
    }
}
