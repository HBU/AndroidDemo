package cn.m15.xys;




import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ColorList extends ListActivity {
    private String[] mListTitle = { "����", "�Ա�", "����", "��ס��","����"};
    private String[] mListStr = { "����MOMO", "��", "25", "����",
	    "xuanyusong@gmail.com" };
    ListView mListView = null;
    MyListAdapter myAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	mListView = getListView();
	myAdapter = new MyListAdapter(this);
	setListAdapter(myAdapter);
	mListView.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> adapterView, View view, int position,
		    long id) {
		View v=adapterView.getChildAt(position);
		v.setBackgroundColor(Color.RED);
		Toast.makeText(ColorList.this,"��ѡ����" + mListStr[position], Toast.LENGTH_LONG).show();
	    }
	});
	
	super.onCreate(savedInstanceState);
    }

    class MyListAdapter extends BaseAdapter {
	private int[] colors = new int[] { 0xff626569, 0xff4f5257 };
	public MyListAdapter(Context context) {
	    mContext = context;
	}

	public int getCount() {
	    return mListStr.length;
	}

	@Override
	public boolean areAllItemsEnabled() {
	    return false;
	}

	public Object getItem(int position) {
	    return position;
	}

	public long getItemId(int position) {
	    return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
	    ImageView iamge = null;
	    TextView title = null;
	    TextView text = null;
	    if (convertView == null) {
		convertView = LayoutInflater.from(mContext).inflate(R.layout.colorlist, null);
		iamge = (ImageView) convertView.findViewById(R.id.color_image);
		title =(TextView) convertView.findViewById(R.id.color_title);
		text= (TextView) convertView.findViewById(R.id.color_text);
	    } 
	    int colorPos = position % colors.length;
	    convertView.setBackgroundColor(colors[colorPos]);
	    title.setText(mListTitle[position]);
	    text.setText(mListStr[position]);
	     iamge.setImageResource(R.drawable.jay);
	    return convertView;
	}

	private Context mContext;
    }
}