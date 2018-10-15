package cn.hbu.cs.listviewexamplep106;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
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
        ListView listView = (ListView) findViewById(R.id.mListView);
        setListAdapter(listView);
    }
    public void setListAdapter(ListView listView){
        List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
        for (int i = 0; i < 20; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("data1", "data_1_"+i);
            map.put("data2", "data_2_"+i);
            data.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,data,
                R.layout.listview_item,
                new String[]{"data1","data2"},
                new int[]{R.id.textView1,R.id.textView2});
        listView.setAdapter(simpleAdapter);
    }
}
