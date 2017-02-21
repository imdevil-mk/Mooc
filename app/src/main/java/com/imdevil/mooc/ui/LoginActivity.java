package com.imdevil.mooc.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imdevil.mooc.HttpThread.HttpThreadForJson;
import com.imdevil.mooc.Jsonbinder.My;
import com.imdevil.mooc.R;

public class LoginActivity extends AppCompatActivity {

    private EditText account;
    private EditText password;
    private CheckBox check;
    private Button btn;
    private Button login_out;
    private String id;
    private String psw;
    private boolean isRem;
    private Gson gson = new Gson();
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    String url = "http://120.27.104.19:3002/Hubu/Interface/Android/student_login.php?format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register();
        final boolean isRemember = pref.getBoolean("remember_psw",false);
        if (isRemember){
            String id = pref.getString("ID","");
            String psw = pref.getString("PassWord","");
            account.setText(id);
            password.setText(psw);
            check.setChecked(true);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = account.getText().toString();
                psw = password.getText().toString();
                isRem = check.isChecked();
                editor = pref.edit();
                if(id.isEmpty()||id == null||psw.isEmpty()||psw == null){
                    Toast.makeText(getBaseContext(),"账户名或者密码错误，请重新登录！",Toast.LENGTH_SHORT).show();
                }else {
                    if (isRem){
                        editor.putBoolean("remember_psw",isRem);
                        editor.putString("ID",id);
                        editor.putString("PassWord",psw);
                    }else {
                        editor.clear();
                    }
                    editor.commit();
                    HttpThreadForJson.LoginIn(id, psw, url, new HttpThreadForJson.HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            My my = gson.fromJson(response,My.class);
                            if (my.getCode() == 418)
                            {
                                Intent intent = new Intent("com.imdevil.mooc.LOGIN");
                                intent.putExtra("ID",id);
                                intent.putExtra("PASSWORD",psw);
                                sendBroadcast(intent);
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this,"账号或密码错误，请重新登录",Toast.LENGTH_SHORT).show();
                            }

                        }
                        @Override
                        public void onError(Exception e) {

                        }
                    });
                }
            }
        });
        login_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void register() {
        account = (EditText) findViewById(R.id.login_account);
        password = (EditText) findViewById(R.id.login_psw);
        check = (CheckBox) findViewById(R.id.login_remember);
        btn = (Button) findViewById(R.id.login_login);
        login_out = (Button) findViewById(R.id.login_login_out);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
    }
}
