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

import com.imdevil.mooc.HttpThread.HttpThreadForJson;
import com.imdevil.mooc.R;

public class LoginActivity extends AppCompatActivity {

    private EditText account;
    private EditText password;
    private TextView test;
    private CheckBox check;
    private Button btn;
    private String id;
    private String psw;
    private boolean isRem;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    String url = "http://192.168.31.140:3002/Hubu/Interface/testAPI_1.php";
    private static final int OK = 1;

    private Handler handler = new Handler(){
        public void dispatchMessage(Message msg) {
            switch (msg.what){
                case OK:
                    test.setText((String)msg.obj);
            }
                /*Log.d("Handler","..................");
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();*/
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        account = (EditText) findViewById(R.id.login_account);
        password = (EditText) findViewById(R.id.login_psw);
        check = (CheckBox) findViewById(R.id.login_remember);
        btn = (Button) findViewById(R.id.login_login);
        test = (TextView) findViewById(R.id.test);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = pref.getBoolean("remember_psw",false);
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
                Log.d("<<<<<<<<<<<<<<<<",""+isRem);
                editor = pref.edit();
                if (isRem){
                    Log.d("isRem1",""+isRem);
                    editor.putBoolean("remember_psw",isRem);
                    editor.putString("ID",id);
                    editor.putString("PassWord",psw);
                    editor.putBoolean("Login",true);
                }else {
                    Log.d("isRem2",""+isRem);
                    editor.clear();
                }
                editor.commit();
                HttpThreadForJson.LoginIn(id, psw, url, new HttpThreadForJson.HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        Log.d("返回的数据", response);
                        String mData = response.toString();
                        Message message = new Message();
                        message.obj = response;
                        message.what = OK;
                        handler.sendMessage(message);
                        Log.d("handler",(String)message.obj);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });
    }
}
