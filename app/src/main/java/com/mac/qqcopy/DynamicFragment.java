package com.mac.qqcopy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mac on 2016/11/16.
 */

public class DynamicFragment extends Fragment implements View.OnClickListener{
    private TextView tv_dynamic_more;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_dynamic,container,false);  //不懂傳入參數的意思??
        tv_dynamic_more = (TextView)view.findViewById(R.id.tv_dynamic_more);
        tv_dynamic_more.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
