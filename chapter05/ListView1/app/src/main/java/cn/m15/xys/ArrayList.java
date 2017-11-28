package cn.m15.xys;





import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ArrayList extends ListActivity {
    private String[] mListTitle = { "姓名", "性别", "年龄", "居住地","邮箱"};
    private String[] mListStr = { "雨松MOMO", "男", "25", "北京",
	    "xuanyusong@gmail.com" };
    ListView mListView = null;
    MyListAdapter myAdapter = null;
    ArrayList arrayList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	arrayList = this;
	mListView = getListView();
	myAdapter = new MyListAdapter(this,R.layout.arraylist);
	setListAdapter(myAdapter);
	super.onCreate(savedInstanceState);
    }

    public class MyListAdapter extends ArrayAdapter<Object> {
	int mTextViewResourceID = 0;
	private Context mContext;
	public MyListAdapter(Context context, int textViewResourceId) {
	    super(context, textViewResourceId);
	    mTextViewResourceID = textViewResourceId;
	    mContext = context;
	}

	private int[] colors = new int[] { 0xff626569, 0xff4f5257 };

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

	public View getView(final int position, View convertView, ViewGroup parent) {
	    ImageView iamge = null;
	    TextView title = null;
	    TextView text = null;
	    Button button = null;
	    if (convertView == null) {
		convertView = LayoutInflater.from(mContext).inflate(
			mTextViewResourceID, null);
		iamge = (ImageView) convertView.findViewById(R.id.array_image);
		title = (TextView) convertView.findViewById(R.id.array_title);
		text = (TextView) convertView.findViewById(R.id.array_text);
		button = (Button)convertView.findViewById(R.id.array_button);
		button.setOnClickListener(new OnClickListener() {
		    
		    @Override
		    public void onClick(View arg0) {
			Toast.makeText(arrayList,"您点击的第"+position +"个按钮", Toast.LENGTH_LONG).show();
			
		    }
		});
	    }
	    int colorPos = position % colors.length;
	    convertView.setBackgroundColor(colors[colorPos]);
	    title.setText(mListTitle[position]);
	    text.setText(mListStr[position]);
	    if(colorPos == 0)
		iamge.setImageResource(R.drawable.jay);
	    else
		iamge.setImageResource(R.drawable.image);
	    return convertView;
	}
    }
}