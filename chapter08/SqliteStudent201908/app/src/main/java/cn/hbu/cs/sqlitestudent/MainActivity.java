package cn.hbu.cs.sqlitestudent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;//定义RecyclerView控件
    private RecyclerView.Adapter mAdapter;//定义RecyclerView控件的适配器
    private RecyclerView.LayoutManager mLayoutManager;//定义RecyclerView控件的布局管理器
    private DBHelper dbHelper;    //用于创建帮助器对象（处理数据库相关操作）
    private SQLiteDatabase database;    //用于创建数据库对象

    Button btnCreate, btnInit, btnList, btnInsert, btnDelete, btnUpdate, btnQuery;//定义七个按钮
    EditText editTextID;//定义三个编辑对话框
    EditText editTextName;
    EditText editTextAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);//禁止软键盘打开界面时自动跳出

        btnCreate = findViewById(R.id.db_create);//xml定义的控件与程序定义的变量绑定
        btnInit   = findViewById(R.id.db_init);
        btnList   = findViewById(R.id.db_list);
        btnInsert = findViewById(R.id.db_insert);
        btnDelete = findViewById(R.id.db_delete);
        btnUpdate = findViewById(R.id.db_update);
        btnQuery  = findViewById(R.id.db_query);

        editTextName =  findViewById(R.id.stu_name);
        editTextID   =  findViewById(R.id.stu_no);
        editTextAge  =  findViewById(R.id.stu_age);
        editTextID.setCursorVisible(false);//取消编辑控件闪烁效果
        editTextAge.setCursorVisible(false);
        editTextName.setCursorVisible(false);

        btnCreate.setOnClickListener(lisenter);//设置按钮的侦听器
        btnInit.setOnClickListener(lisenter);
        btnList.setOnClickListener(lisenter);
        btnInsert.setOnClickListener(lisenter);
        btnDelete.setOnClickListener(lisenter);
        btnUpdate.setOnClickListener(lisenter);
        btnQuery.setOnClickListener(lisenter);
    }


    private View.OnClickListener lisenter = new View.OnClickListener() {//侦听器
        @Override
        public void onClick(View view) {
            Button button = (Button) view;//把点击获得的参数传递给button
            try{
                switch (button.getId()) {//根据按钮id，判断点击了那个按钮，进一步执行相关代码
                    case R.id.db_create: {                        //create Database
                        dbHelper = new DBHelper(MainActivity.this, "school.db", null, 3);//创建帮助器对象
                        database = dbHelper.getWritableDatabase();//创建数据库对象
                        Toast.makeText(MainActivity.this,"数据库已建立",Toast.LENGTH_SHORT).show();
                        database.close();
                        break;
                    }
                    case R.id.db_init: {                        //Init Table
                        Toast.makeText(MainActivity.this,"新写入10条数据",Toast.LENGTH_SHORT).show();
                        database = dbHelper.getWritableDatabase();
                        dbHelper.onInit(database);
                        database.close();
                        break;
                    }
                    case R.id.db_list: {                        //Show all data
                        Toast.makeText(MainActivity.this,"显示全部数据",Toast.LENGTH_SHORT).show();
                        initDbData();//重新初始化RecyclerView
                        initView();
                        break;
                    }
                    case R.id.db_insert:{                        //Insert one line data
                        Toast.makeText(MainActivity.this,"插入一条数据",Toast.LENGTH_SHORT).show();
                        Log.e("TestString0",editTextID.getText().toString() +editTextName.getText().toString()+editTextAge.getText().toString());
                        database = dbHelper.getWritableDatabase();
                        Log.e("TestString1",editTextID.getText().toString() +editTextName.getText().toString()+editTextAge.getText().toString());
                        int InsertId,InsertAge;
                        String InsertName;
                        InsertId = Integer.parseInt(editTextID.getText().toString());
                        InsertName = editTextName.getText().toString();
                        InsertAge = Integer.parseInt(editTextAge.getText().toString());
                        Log.e("TestString2",InsertId + "" + InsertName + InsertAge);
                        // 此处应增加：if 符合条件，才能插入数据。else 提示数据不符合要求。
                        dbHelper.onInsert(database,InsertId, InsertName,InsertAge);
                        Log.e("TestString3",InsertId + "" + InsertName + InsertAge);
                        database.close();
                        initDbData();//重新初始化RecyclerView
                        initView();
                        break;
                    }
                    case R.id.db_delete:{                        //Delete the selected data
                        Toast.makeText(MainActivity.this,"删除一条数据",Toast.LENGTH_SHORT).show();
                        database = dbHelper.getWritableDatabase();
                        int DeleteId;
                        DeleteId = Integer.parseInt(editTextID.getText().toString());
                        // 应增加：if 符合条件，才能删除数据。else 提示数据不符合要求。
                        dbHelper.onDelete(database,DeleteId);
                        database.close();
                        break;
                    }
                    case R.id.db_update:{                        //Update the selected data
                        Toast.makeText(MainActivity.this,"更新一条数据",Toast.LENGTH_SHORT).show();
                        database = dbHelper.getWritableDatabase();
                        int UpdateId,UpdateAge;
                        String UpdateName;
                        UpdateId = Integer.parseInt(editTextID.getText().toString());
                        UpdateName = editTextName.getText().toString();
                        UpdateAge = Integer.parseInt(editTextAge.getText().toString());
                        Log.d("sql test update:", UpdateName);
                        // 应增加：if 符合条件，才能更新数据。else 提示数据不符合要求。
                        dbHelper.onUpdate(database,UpdateId, UpdateName,UpdateAge);
                        database.close();
                        break;
                    }
                    case R.id.db_query:{                        //Query target data
                        Toast.makeText(MainActivity.this,"查询当前学号学生",Toast.LENGTH_SHORT).show();
                        initLineData();//重新初始化RecyclerView
                        initView();
                        break;
                    }
                    default:
                        break;
                }
            }
            catch(Exception e)
            {
            }
        }
    };

    private void initDbData() {// 初始化RecyclerView数据，把数据库内容写入
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyAdapter(getStudentList());
    }
    private void initLineData() {// 初始化RecyclerView数据，把数据库一条查询结果写入
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new MyAdapter(getStudentLine());
    }
    private void initView() {// 设置RecyclerView适配器
        mRecyclerView = findViewById(R.id.my_recycler_view);        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
    }

    public ArrayList<Student> getStudentList() {//获取全部数据
        dbHelper = new DBHelper(MainActivity.this, "school.db", null, 3);//This is the key. 2017.6.21 4:10 AM
        database = dbHelper.getWritableDatabase();
        Cursor cursor= dbHelper.onList(database);//游标记录数据集
        ArrayList<Student> studentList = new ArrayList<Student>();//对象数组，用于接收游标的数值
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.student_ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Student.KEY_ID)) );
                student.name = cursor.getString(cursor.getColumnIndex(Student.KEY_name));
                student.age = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Student.KEY_age)));
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return studentList;
    }

    public ArrayList<Student>  getStudentLine() {// 处理查询结果，类似获取全部数据
        dbHelper = new DBHelper(MainActivity.this, "school.db", null, 3);
        database = dbHelper.getWritableDatabase();

        ArrayList<Student> studentLine = new ArrayList<Student>();
        int QueryId = Integer.parseInt(editTextID.getText().toString());
        Cursor cursor = dbHelper.onQuery(database,QueryId);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.student_ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Student.KEY_ID)) );
                student.name = cursor.getString(cursor.getColumnIndex(Student.KEY_name));
                student.age = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Student.KEY_age)));
                studentLine.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return studentLine;
    }
}
