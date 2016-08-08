package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    public static final int REGISTER_RESULT = 2;
    private EditText regUserNameEt,regPasswordEt;
    private Button regRegisterBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }


    private void initView() {
        regUserNameEt = (EditText) findViewById(R.id.reg_username);
        regPasswordEt = (EditText) findViewById(R.id.reg_password);
        regRegisterBtn = (Button) findViewById(R.id.reg_register);

        regRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser bu = new BmobUser();
                bu.setUsername(regUserNameEt.getText().toString());
                bu.setPassword(regPasswordEt.getText().toString());
                bu.signUp(RegisterActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(RegisterActivity.this,"register successfully",Toast.LENGTH_SHORT).show();
                        initData();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Log.i("onfailure",s);
                    }
                });

            }
        });
    }
    private void initData() {
        Intent intent = new Intent();
        intent.putExtra("username",regUserNameEt.getText().toString());
        intent.putExtra("password",regPasswordEt.getText().toString());
        setResult(REGISTER_RESULT,intent);
        finish();
    }

}
