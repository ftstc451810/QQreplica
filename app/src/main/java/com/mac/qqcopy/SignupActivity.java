package com.mac.qqcopy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mac on 2016/11/11.
 */

public class SignupActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener {
    private TextView tv_confirm;
    private TextView tv_cancel;
    private EditText et_your_phone;
    private EditText et_name;
    private EditText et_new_account;
    private EditText et_new_password;
    private EditText et_again_password;
    private TextView tv_check_password;

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        View v = inflater.inflate(R.layout.activity_signup, container,false);
//
//        tv_confirm = (TextView)v.findViewById(R.id.tv_confirm);  //這跟一般的activity不大一樣，需要先取得view
//        tv_confirm.setOnClickListener(this);
//
//        tv_cancel = (TextView)v.findViewById(R.id.tv_cancel);
//        tv_cancel.setOnClickListener(this);
//        return v;
//    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Intent it = getIntent();

        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        tv_confirm.setOnClickListener(this);

        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);

        et_your_phone = (EditText) findViewById(R.id.et_your_phone);
        et_name = (EditText) findViewById(R.id.et_name);
        et_new_account = (EditText) findViewById(R.id.et_new_account);
        et_new_account.setOnFocusChangeListener(this);
        et_new_password = (EditText) findViewById(R.id.et_new_password);
        et_new_password.setOnFocusChangeListener(this);
        et_again_password = (EditText) findViewById(R.id.et_again_password);
        et_again_password.setOnFocusChangeListener(this);
        tv_check_password = (TextView) findViewById(R.id.tv_check_password);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_confirm:
                goAdapter();
                break;
            case R.id.tv_cancel:
                goLogin();
                break;
        }
    }

    private void goAdapter() {
//        if(et_your_phone.getText() != null && et_name.getText() != null && et_new_account.getText() != null
//        && et_new_password.getText() != null && et_again_password.getText() != null)  //無法判斷成功
        if(et_your_phone.getText().toString() != null && et_name.getText().toString() != null
                && et_new_account.getText().toString() != null
                && et_new_password.getText().toString() != null && et_again_password.getText().toString() != null){
            Intent it = new Intent(this, MyFragmentActivity.class);
            this.startActivity(it);
        }else {
            new AlertDialog.Builder(this).setTitle("無法進行").setMessage("每一格都要填寫喔")
                    .setPositiveButton("確定",null).show();
        }

    }

    private void goLogin() {
        Intent it = new Intent(this, LoginActivity.class);
        this.startActivity(it);
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {        //輸入完後再行判斷
        // 每一項editText要分別寫，不然每一項內容改變其他項也要一起判斷(switch)
        switch (view.getId()) {
            case R.id.et_your_phone:

            case R.id.et_new_account:
                if (!hasFocus) {
                    if (et_new_account.getText().toString().trim().length() < 5) {
                        Toast.makeText(this, "帳號不能少於5個字符", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(),"登陸成功",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.et_new_password:
                if (!hasFocus) {
                    if (et_new_password.getText().toString().trim().length() < 8) {
                        Toast.makeText(this, "密碼不能少於8個字符", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.et_again_password:
                if (!hasFocus) {
//                    if (et_new_password.getText().toString().trim() != et_again_password.getText().toString().trim()) {  //始終不一致...
//                        tv_check_password.setVisibility(View.VISIBLE);
//                    } else if(et_new_password.getText() == et_again_password.getText()){
//                        tv_check_password.setVisibility(View.INVISIBLE);  //改正之後就要使它消失
//                    }
                    if((et_new_password.getText().toString().trim()).equals(et_again_password.getText().toString().trim())){
                        tv_check_password.setVisibility(View.INVISIBLE);  //改正之後就要使它消失  //如果填寫完沒按到其他地方就會有bug
                    }else {
                        tv_check_password.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
    }
}
