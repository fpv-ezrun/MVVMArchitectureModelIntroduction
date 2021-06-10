package com.mindorks.framework.mvvm;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    TrainingViewModel ViewModel;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    EditText EditId;
    EditText EditString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Insert Trainings"));
        tabLayout.addTab(tabLayout.newTab().setText("View Trainings"));
        tabLayout.addTab(tabLayout.newTab().setText("Others"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
               tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        updateText();
    }

    void updateText(){

        findViewById(R.id.buttonChercher).setOnClickListener(EditId = (EditText)findViewById(R.id.IdTraining));
    }
    /*private Button btn1;
private Button btn2;
private Button btn3;
private Button btn4;

@Override
public void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.yourPage);

    btn1=(Button)findViewById(R.id.btn_1);
    btn2=(Button)findViewById(R.id.btn_2);
    btn3=(Button)findViewById(R.id.btn_3);
    btn4=(Button)findViewById(R.id.btn_4);

    btn1.setOnClickListener(this);
    btn2.setOnClickListener(this);
    btn3.setOnClickListener(this);
    btn4.setOnClickListener(this);
    }

public void onClick(View v) {
    // TODO Auto-generated method stub

    switch (v.getId()) {

    case R.id.btn_1:
                    //do your stuff
        break;
    case R.id.btn_2:
                    //do your stuff
        break;
    case R.id.btn_3:
                    //do your stuff
        break;
    case R.id.btn_4:
                    //do your stuff
        break;
     }
}*/
}
