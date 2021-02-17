package com.kg.Headlines;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class ScreenSlidePagerActivity extends FragmentActivity {

    private Button button;
    private ViewPager2 viewPager;
    private static final int NUM_PAGES=2;





        @Override
    public void onBackPressed() {

        if(viewPager.getCurrentItem()==0){
            super.onBackPressed();}
        else
        {
            viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
        }
    }

    public static class ScreenSlidePagerAdapter extends FragmentStateAdapter {

        public ScreenSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position)
        {

            switch (position){
                case 0:return new ScreenSlidePageFragment();
                case 1:return new ScreenSlidePageFragmentB();
                default:break;
            }
            return null;

        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}
