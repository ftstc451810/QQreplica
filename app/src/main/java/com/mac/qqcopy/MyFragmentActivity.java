package com.mac.qqcopy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//import android.app.Fragment;

/**
 * Created by mac on 2016/11/16.
 */

public class MyFragmentActivity extends FragmentActivity implements GestureDetector.OnGestureListener,
        View.OnClickListener, ViewPager.OnPageChangeListener {
    private ImageButton btn_start_menu;
    private View mPopupWindowView;  //要放popupwindow
    private PopupWindow mPopupWindow;
    private GestureDetector detector;

    private MainFragment myMain;
    private ContactFragment myContact;
    private DynamicFragment myDynamic;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private ViewPager myViewPager;
    private FragAdapter adapter;

    private RelativeLayout ry_message_tab;
    private RelativeLayout ry_contact_tab;
    private RelativeLayout ry_dynamic_tab;
    private ImageView im_message;
    private ImageView im_contact;
    private ImageView im_dynamic;
    private TextView tv_message;
    private TextView tv_contact;
    private TextView tv_dynamic;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewadapter);

        Intent it = getIntent();  //從search activity切換回來

        initPopupWindow();
        btn_start_menu = (ImageButton) findViewById(R.id.btn_start_menu);
        btn_start_menu.setOnClickListener(this);
        detector = new GestureDetector(this, this);  //為何兩個this??

        initFrag();


    }

    private void initFrag() {
        fragments.add(new MainFragment());
        fragments.add(new ContactFragment());
        fragments.add(new DynamicFragment());
        adapter = new FragAdapter(getSupportFragmentManager(), fragments);
        myViewPager = (ViewPager) findViewById(R.id.myViewPager);
        myViewPager.setAdapter(adapter);
        //vp.setCurrentItem(0);  //預設第一個fragment
        myViewPager.addOnPageChangeListener(this);

        ry_message_tab = (RelativeLayout)findViewById(R.id.ry_message_tab);
        ry_message_tab.setOnClickListener(this);
        ry_contact_tab = (RelativeLayout)findViewById(R.id.ry_contact_tab);
        ry_contact_tab.setOnClickListener(this);
        ry_dynamic_tab = (RelativeLayout)findViewById(R.id.ry_dynamic_tab);
        ry_dynamic_tab.setOnClickListener(this);

        im_message = (ImageView)findViewById(R.id.im_message);
        im_message.setBackgroundResource(R.drawable.message);  //第一次會停留在第一頁
        im_contact = (ImageView)findViewById(R.id.im_contact);
        im_dynamic = (ImageView)findViewById(R.id.im_dynamic);
        tv_message = (TextView)findViewById(R.id.tv_message);
        tv_contact = (TextView)findViewById(R.id.tv_contact);
        tv_dynamic = (TextView)findViewById(R.id.tv_dynamic);
    }
//重設字的顏色，回歸原貌
    private void resetTextView(){
//        tv_message.setTextColor(getResources().getColor(R.color.color_sky)); 無法用
        tv_message.setTextColor(Color.parseColor("#808080"));
        tv_contact.setTextColor(ContextCompat.getColor(this, R.color.color_darkgray));
        tv_dynamic.setTextColor(ContextCompat.getColor(this, R.color.color_darkgray));
    }
    //重置圖案
    private  void resetImageView(){
        im_message.setBackgroundResource(R.drawable.message_ori);
        im_contact.setBackgroundResource(R.drawable.contact_ori);
        im_dynamic.setBackgroundResource(R.drawable.dynamic_ori);

    }
    //將touch事件交給gesturedetector處理
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    private void initPopupWindow() {
        //載入menu的內容，並設定監聽事件
        mPopupWindowView = LayoutInflater.from(this).inflate(R.layout.action_menu, null);  //初始化放置的view
        TextView tv_right = (TextView) mPopupWindowView.findViewById(R.id.action_right);
        tv_right.setOnClickListener(this);

        //menu出現的長寬及一些畫面點擊設定
        mPopupWindow = new PopupWindow(mPopupWindowView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.AnimationLeftFade);
        mPopupWindow.update();

    }

    //menu動態顯示，並設定長寬
    private void showPopupWindow() {
        if (!mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(mPopupWindowView, Gravity.LEFT, 0, 0);
        } else {
            mPopupWindow.dismiss();
        }
    }
    @Override
    public void onClick(View v) {
        resetTextView();
        resetImageView();
        switch (v.getId()){
            case R.id.ry_message_tab:
                myViewPager.setCurrentItem(0);
                tv_message.setTextColor(ContextCompat.getColor(this, R.color.color_sky));
                im_message.setBackgroundResource(R.drawable.message);
                break;
            case R.id.ry_contact_tab:
                myViewPager.setCurrentItem(1);
                tv_contact.setTextColor(ContextCompat.getColor(this, R.color.color_sky));
                im_contact.setBackgroundResource(R.drawable.contact);
                break;
            case R.id.ry_dynamic_tab:
                myViewPager.setCurrentItem(2);
                tv_dynamic.setTextColor(ContextCompat.getColor(this, R.color.color_sky));
                im_dynamic.setBackgroundResource(R.drawable.dynamic);
                break;
            case R.id.btn_start_menu:
                showPopupWindow();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {    //gesture相關方法
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {  //gesture相關方法

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) { //gesture相關方法
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) { //gesture相關方法
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {  //gesture相關方法

    }

    @Override
    public boolean onFling(MotionEvent me, MotionEvent me2, float vx, float vy) {  //滑動事件
        float minMove = 120;    //為了判斷是否移動與方向
        float minVelocity = 0;
        float beginX = me.getX();
        float endX = me2.getX();
        float beginY = me.getY();
        float endY = me2.getY();
        //判定右滑（位置＋速度）
        if (endX - beginX > minMove && Math.abs(vx) > minVelocity) {  //取絕對值
//            showPopupWindow();
        } else if (beginX - endX > minMove && Math.abs(vx) > minVelocity) {  //左滑可讓popupwindow消失，
            // 已經把判斷window是否存在寫在showPopupWindow();裡了
//            showPopupWindow();
        }

        return false;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        resetTextView();
        resetImageView();
        switch (position){
            case 0:
                myViewPager.setCurrentItem(0);
                tv_message.setTextColor(ContextCompat.getColor(this, R.color.color_sky));
                im_message.setBackgroundResource(R.drawable.message);
                break;
            case 1:
                myViewPager.setCurrentItem(1);
                tv_contact.setTextColor(ContextCompat.getColor(this, R.color.color_sky));
                im_contact.setBackgroundResource(R.drawable.contact);
                break;
            case 2:
                myViewPager.setCurrentItem(2);
                tv_dynamic.setTextColor(ContextCompat.getColor(this, R.color.color_sky));
                im_dynamic.setBackgroundResource(R.drawable.dynamic);
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

