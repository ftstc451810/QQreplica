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

public class LoginActivity extends Activity implements View.OnClickListener{
    private TextView tv_login;
    private TextView tv_signup;
    private EditText et_account;
    private EditText et_password;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent it = getIntent();

        tv_login = (TextView)findViewById(R.id.tv_login);
        tv_login.setOnClickListener(this);

        tv_signup = (TextView)findViewById(R.id.tv_signup);
        tv_signup.setOnClickListener(this);

        et_account = (EditText)findViewById(R.id.et_account);
        et_password = (EditText)findViewById(R.id.et_password);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tv_login:
                goAdapter();
                break;
            case R.id.tv_signup:
                goSignup();
                break;
        }
    }

    private void goAdapter() {
        String account = et_account.getText().toString();
        String password = et_password.getText().toString();
        if(account.equals("") || password.equals("")){
            new AlertDialog.Builder(this).setTitle("無法進行")
                    .setMessage("帳號或密碼不能為空噢").setPositiveButton("確定",null).show();
        }else {
            Toast.makeText(getApplicationContext(),"登陸成功",Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, MyFragmentActivity.class);
            this.startActivity(it);
        }
    }

    private void goSignup() {
        Intent it = new Intent(this, SignupActivity.class);
        this.startActivity(it);
    }


}

