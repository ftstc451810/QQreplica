package com.mac.qqcopy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mac on 2016/11/9.
 */

public class PhoneFragment extends Fragment implements View.OnClickListener {



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_phone, container, false);
        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
