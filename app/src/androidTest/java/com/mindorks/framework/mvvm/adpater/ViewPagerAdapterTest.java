package com.mindorks.framework.mvvm.adpater;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.mindorks.framework.mvvm.FragmentAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.robolectric.RobolectricTestRunner;

//import static com.google.common.truth.Truth.assertThat;

//@RunWith(AndroidJUnit4ClassRunner.class)
public class ViewPagerAdapterTest extends AppCompatActivity {

    FragmentAdapter adapter;
    ViewPager2 pager2;
    TabLayout tabLayout;
    FragmentManager fm = getSupportFragmentManager();

    @Before
    public void setUp() {
        adapter=new FragmentAdapter(fm,getLifecycle());
        pager2.setAdapter(adapter);
    }

    @Test
    public void getCount() {
        {
            assert(adapter.getItemCount()==0);

        }
    }

}