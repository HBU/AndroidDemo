package cn.hbu.cs.sqlitestudent;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version //each time if you Add, Edit table, you need to change the version number.
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//建立数据库
        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Student.TABLE  + "("
                + Student.KEY_ID  + " INTEGER PRIMARY KEY ,"
                + Student.KEY_name + " TEXT, "
                + Student.KEY_age + " INTEGER )";
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    public void onInit(SQLiteDatabase db) {//清空数据，并向数据库学生表中添加10名学生，初始化数据。
        db.execSQL("delete from " + Student.TABLE);
        db.execSQL("insert into " + Student.TABLE + "("+ Student.KEY_ID+","+Student.KEY_name+","+Student.KEY_age+")" + " values(?, ?, ?)", new Object[]{101,"刘得意", 19});
        db.execSQL("insert into " + Student.TABLE + "("+ Student.KEY_ID+","+Student.KEY_name+","+Student.KEY_age+")" + " values(?, ?, ?)", new Object[]{102,"王锐", 20});
        db.execSQL("insert into " + Student.TABLE + "("+ Student.KEY_ID+","+Student.KEY_name+","+Student.KEY_age+")" + " values(?, ?, ?)", new Object[]{103,"何煜中", 19});
        db.execSQL("insert into " + Student.TABLE + "("+ Student.KEY_ID+","+Student.KEY_name+","+Student.KEY_age+")" + " values(?, ?, ?)", new Object[]{104,"王磊", 21});
        db.execSQL("insert into " + Student.TABLE + "("+ Student.KEY_ID+","+Student.KEY_name+","+Student.KEY_age+")" + " values(?, ?, ?)", new Object[]{105,"冯松", 19});
        db.execSQL("insert into " + Student.TABLE + "("+ Student.KEY_ID+","+Student.KEY_name+","+Student.KEY_age+")" + " values(?, ?, ?)", new Object[]{106,"得意刘", 19});
        db.execSQL("insert into " + Student.TABLE + "("+ Student.KEY_ID+","+Student.KEY_name+","+Student.KEY_age+")" + " values(?, ?, ?)", new Object[]{107,"锐王", 20});
        db.execSQL("insert into " + Student.TABLE + "("+ Student.KEY_ID+","+Student.KEY_name+","+Student.KEY_age+")" + " values(?, ?, ?)", new Object[]{108,"煜中何", 19});
        db.execSQL("insert into " + Student.TABLE + "("+ Student.KEY_ID+","+Student.KEY_name+","+Student.KEY_age+")" + " values(?, ?, ?)", new Object[]{109,"磊王", 21});
        db.execSQL("insert into " + Student.TABLE + "("+ Student.KEY_ID+","+Student.KEY_name+","+Student.KEY_age+")" + " values(?, ?, ?)", new Object[]{110,"松冯", 19});

    }
    public Cursor onList(SQLiteDatabase db) {//查询全部数据
        String selectQuery =  "SELECT  " + Student.KEY_ID + "," +  Student.KEY_name + "," +  Student.KEY_age +
                " FROM " + Student.TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
         }

    public void onInsert(SQLiteDatabase db, int id, String name, int age) {//向学生表中添加1名学生。
        db.execSQL("insert into " + Student.TABLE + "("+ Student.KEY_ID+","+Student.KEY_name+","+Student.KEY_age+")" +
                    " values("+id+","+name+","+age+")");
    }
    public void onDelete(SQLiteDatabase db, int id) {//从学生表删除1名学生。
        db.execSQL("delete from " + Student.TABLE + " where "+ Student.KEY_ID + " = "+ id);
    }
    public void onUpdate(SQLiteDatabase db, int id, String name, int age) {//修改数据。
        db.execSQL(" update " + Student.TABLE +" " +
                    " set "+ Student.KEY_ID + " = " + id + "," + Student.KEY_name + " = '" + name + "'," + Student.KEY_age + " = " + age +
                    " where "+ Student.KEY_ID + " = "+ id );
    }
    public Cursor onQuery(SQLiteDatabase db, int id) {//通过id查询学生
        Cursor cursor;
        String selectQuery =  "SELECT  " + Student.KEY_ID + "," +  Student.KEY_name + "," +  Student.KEY_age +
                " FROM " + Student.TABLE + " where "+ Student.KEY_ID + " = "+ id;
        cursor = db.rawQuery(selectQuery,null);
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//更新数据库
        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE);// Drop older table if existed, all data will be gone!!!
        onCreate(db);// Create tables again
    }
}