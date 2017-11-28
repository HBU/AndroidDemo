package com.androidbook.tabactivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class TabActivityExampleActivity extends TabActivity {
    
	private TabHost tabHost;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabHost = this.getTabHost();
       
        LayoutInflater.from(this).inflate(R.layout.tabs1, tabHost.getTabContentView(), true);

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("tab1")
                .setContent(R.id.view1));
        
        Intent intent = new Intent(this, IntentTabActivity.class);
        tabHost.addTab(tabHost.newTabSpec("tab2")
        		.setIndicator("tab2")
        		.setContent(intent));
    }
}