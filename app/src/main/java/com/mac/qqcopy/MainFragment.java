package com.mac.qqcopy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.sevenheaven.segmentcontrol.SegmentControl;

//import android.app.FragmentManager;

public class MainFragment extends Fragment implements View.OnClickListener, SegmentControl.OnSegmentControlClickListener {//           private static Context mContext;
//      private PopupWindow mPopupWindow;
//      private View mPopupWindowView;  //要放popupwindow
    private GestureDetector detector;
    private ImageButton btn_PopupWindow;
    private Button btn_search;
    private SegmentControl segment_control;
    private PhoneFragment mPhone;
    private MessageFragment mMessage;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);  //不懂傳入參數的意思??

        Intent it = getActivity().getIntent();  //從search activity切換回來，要先取得Activity

        btn_search = (Button) view.findViewById(R.id.btn_start_search);
        btn_search.setOnClickListener(this);

        segment_control = (SegmentControl) view.findViewById(R.id.segment_control);
        segment_control.setOnSegmentControlClickListener(this);
        //detector = new GestureDetector(this,getActivity());

        setDefaultFragment();
        return view;
    }

    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mMessage = new MessageFragment();
        transaction.replace(R.id.id_content, mMessage);
        transaction.commit();
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_search:
                goSearch();
                break;
            case R.id.action_right:
//                mPopupWindow.dismiss();
                break;
            default:
                break;
        }
    }


    //切換到search activity
    private void goSearch() {
        Intent it = new Intent(getActivity(), SearchActivity.class);  //在fragment裡面用intent，先獲取所在的Activity
        this.startActivity(it);
    }


    //segment control監聽事件
    @Override
    public void onSegmentControlClick(int index) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (index == 0) {
//            System.out.println("按下message按鈕");  //debug用
            if (mMessage == null) {
                mMessage = new MessageFragment();
            }
            transaction.replace(R.id.id_content, mMessage);
        } else if (index == 1) {
            if (mPhone == null) {
                mPhone = new PhoneFragment();
            }
            transaction.replace(R.id.id_content, mPhone);
        }
        transaction.commit();
    }


}























