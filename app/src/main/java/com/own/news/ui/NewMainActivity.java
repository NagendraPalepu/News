package com.own.news.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.own.news.R;
import com.own.news.ui.fragment.CountryFragment;
import com.own.news.ui.fragment.LiveFragment;
import com.own.news.ui.fragment.SourceFragment;
import com.own.news.utils.AppUtils;
import com.own.news.utils.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class NewMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main_new);

        TextView title = findViewById (R.id.source);
        title.setTypeface (AppUtils.getOpenSansSemiBold (this));

        ImageView back = findViewById (R.id.back);
        CircleImageView logo = findViewById (R.id.logo);

        back.setVisibility (View.GONE);

        logo.setImageResource (R.mipmap.ic_launcher_round);


        ViewPager viewPager = findViewById (R.id.viewpager);
        setupViewPager (viewPager);

        TabLayout tabLayout = findViewById (R.id.tabs);
        tabLayout.setupWithViewPager (viewPager);
    }

    private void setupViewPager (ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter (getSupportFragmentManager ());
        adapter.addFragment (new SourceFragment (), "Source");
        adapter.addFragment (new CountryFragment (), "Countries");
        adapter.addFragment (new LiveFragment (), "Live Tv");
        viewPager.setAdapter (adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<> ();
        private final List<String> mFragmentTitleList = new ArrayList<> ();

        ViewPagerAdapter (FragmentManager manager) {
            super (manager);
        }

        @Override
        public Fragment getItem (int position) {
            return mFragmentList.get (position);
        }

        @Override
        public int getCount () {
            return mFragmentList.size ();
        }

        void addFragment (Fragment fragment, String title) {
            mFragmentList.add (fragment);
            mFragmentTitleList.add (title);
        }

        @Override
        public CharSequence getPageTitle (int position) {
            return mFragmentTitleList.get (position);
        }
    }
}

