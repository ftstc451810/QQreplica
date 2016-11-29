package com.mac.qqcopy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by mac on 2016/11/16.
 */


//public class MyPagerAdapter extends PagerAdapter { //改寫以便分別寫各分頁的代碼
//    private List<View> pageList;
//
//    public FragAdapter(List<View> pageList){
//        this.pageList = pageList;
//    }
//    @Override
//    public int getCount() {
//        return pageList.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    public void destroyItem(ViewGroup container, int position, Object object){
//        container.removeView(pageList.get(position));
//    }
//    public Object instantiateItem(ViewGroup container, int position) {
//        container.addView(pageList.get(position));
//        return pageList.get(position);
//    }


public class FragAdapter extends FragmentPagerAdapter {

    private List<Fragment> myFragments;

    public FragAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        myFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }


    @Override
    public int getCount() {
        return myFragments.size();
    }
}
