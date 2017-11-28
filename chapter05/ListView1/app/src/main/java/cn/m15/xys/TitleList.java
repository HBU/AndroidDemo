package cn.m15.xys;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TitleList extends ListActivity {
    private String[] mListTitle = { "姓名", "性别", "年龄", "居住地","邮箱"};
    private String[] mListStr = { "雨松MOMO", "男", "25", "北京",
	    "xuanyusong@gmail.com" };
    ListView mListView = null;
    ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	mListView = getListView();
      
	int lengh = mListTitle.length;
	for(int i =0; i < lengh; i++) {
	    Map<String,Object> item = new HashMap<String,Object>();
	    item.put("title", mListTitle[i]);
	    item.put("text", mListStr[i]);
	    mData.add(item); 
	}
	SimpleAdapter adapter = new SimpleAdapter(this,mData,android.R.layout.simple_list_item_2,
		new String[]{"title","text"},new int[]{android.R.id.text1,android.R.id.text2});
        setListAdapter(adapter);
	mListView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> adapterView, View view, int position,
		    long id) {
		Toast.makeText(TitleList.this,"您选择了标题：" + mListTitle[position] + "内容："+mListStr[position], Toast.LENGTH_LONG).show();
	    }
	});
	super.onCreate(savedInstanceState);
    }
}
