package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText usernameEt,passwordEt;
    private Button loginBtn,registerBtn;
    private CheckBox rememberCB;
    private SharedPreferences sp;

    public static final int REGISTER_REQUSET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        initView();
        login();
    }



    private void initView() {
        usernameEt = (EditText) findViewById(R.id.username);
        passwordEt = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login);
        registerBtn = (Button) findViewById(R.id.register);
        rememberCB = (CheckBox) findViewById(R.id.remember);

        if (sp.getString("username", "") != null && sp.getString("username", "") != "") {
            usernameEt.setText(sp.getString("username", ""));
            passwordEt.setText(sp.getString("password", ""));
        }

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    private void login() {
        BmobUser bu = new BmobUser();
        bu.setUsername(usernameEt.getText().toString());
        bu.setPassword(passwordEt.getText().toString());
        bu.login(MainActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this,"login successfully",Toast.LENGTH_SHORT).show();
                if(rememberCB.isChecked()) {
                    initData();
                }
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });

    }

    private void initData() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username",usernameEt.getText().toString());
        editor.putString("password",passwordEt.getText().toString());
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                login();
                break;
            case R.id.register:
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivityForResult(intent,REGISTER_REQUSET);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REGISTER_REQUSET && resultCode == RegisterActivity.REGISTER_RESULT){

            usernameEt.setText(data.getStringExtra("username"));
            Log.i("main.username",data.getStringExtra("username").toString());
        }
    }
}
