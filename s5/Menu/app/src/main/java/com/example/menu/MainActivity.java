package com.example.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public TextView myMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myMenu =(TextView)findViewById(R.id.david);
        registerForContextMenu(myMenu);//注册事件
    }

    //////////////////////////////////////////////////////////
    //选项菜单
    //////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //四个参数的含义:
        // 1.group的id;2.item的id;3.是否排序;4.将要显示的内容
        menu.add(0, 1, 0, "河北大学计算机学院");
        menu.add(0, 2, 0, "河北大学电信学院");
        menu.add(0, 3, 0, "河北大学新闻学院");
//        menu.add(0, 4, 0, "河北大学艺术学院");
//        menu.add(0, 5, 0, "河北大学质检学院");
//        SubMenu sub = menu.addSubMenu("子菜单");
//        sub.add(0, 5, 0, "子菜单一");
//        sub.add(0, 6, 0, "子菜单二");
//        sub.add(0, 7, 0, "子菜单三");
//
//        menu.add(1, 6, 0, "河北大学计算机学院");
//        menu.add(1, 7, 1, "河北大学电信学院");
//        menu.add(1, 8, 5, "河北大学新闻学院");
//        menu.add(1, 9, 2, "河北大学艺术学院");
//        menu.add(1, 10, 3, "河北大学质检学院");

        return true;
    }

    //菜单添加点击事件,需要重写onOptionsItemSelected（）方法。
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(MainActivity.this, "我是计算机学院", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "我是电信学院", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this, "我是。。。", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(MainActivity.this, "I am 。。。", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////
    //上下文菜单
    //////////////////////////////////////////////////////////
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v == myMenu) {
            menu.add(0, 1, 0, "我是菜单1");
            menu.add(0, 2, 0, "我是菜单2");
            menu.add(0, 3, 0, "我是菜单3");

        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(MainActivity.this, "菜单1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MainActivity.this, "菜单2", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MainActivity.this, "菜单3", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }


}


