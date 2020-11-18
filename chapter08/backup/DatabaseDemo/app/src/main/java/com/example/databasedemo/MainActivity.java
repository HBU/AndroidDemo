package com.example.databasedemo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.idescout.sql.SqlScoutServer;

public class MainActivity extends Activity {

    private MyOpenHelper oh;//用于创建帮助器对象
    private SQLiteDatabase db;//用于创建数据库对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SqlScoutServer.create(this, getPackageName());
    }

    public void createDatabase(View view) {//创建数据库
        oh = new MyOpenHelper(this, "people.db", null, 1);//创建帮助器对象
        db = oh.getWritableDatabase();//创建数据库对象
    }

    public void Insert(View view) {//向数据库中添加数据
        //向学生表中添加10名学生
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"刘得意", 19, 1001, 60, 98, 75});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"王锐", 20, 1002, 63, 90, 96});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"何煜中", 19, 1003, 90, 73, 82});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"王磊", 21, 1004, 87, 86, 92});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"冯松", 19, 1005, 89, 98, 83});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"裴培", 20, 1006, 75, 82, 91});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"马骁", 19, 1007, 62, 67, 90});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"马婧", 20, 1008, 98, 84, 87});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"周俊升", 19, 1009, 57, 68, 96});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"贺祺", 21, 1010, 61, 96, 72});
    }

    public void Delete(View view) {//删除数据库中的数据
        db.execSQL("delete from Student where name = ?", new Object[]{"刘得意"});//删除姓名为"刘得意"的学生的信息
    }

    public void Update(View view) {//修改数据库中的数据
        db.execSQL("update student set sno = sno -1");//将数据库中所有人的学号减少1
    }

    public void Clear(View view) {//清空数据库中的数据
        db.execSQL("delete from student");//
    }

    public void Select(View view) {//查询数据库中的数据
        Cursor cursor = db.rawQuery("select name,sno, cpp from student", null);//查询姓名和C++成绩,返回值为一个结果集

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));//cursor.getColumnIndex("name")获得姓名所在的列
            int no = cursor.getInt(cursor.getColumnIndex("sno"));
            float cpp = cursor.getFloat(cursor.getColumnIndex("cpp"));
            Log.d("MainActivity", "[" + no + ","+ name + ", " + cpp + "]");//输出学生的学号、姓名和与姓名对应的C++成绩
        }
        Log.d("MainActivity","================");
    }
}
