package com.imdevil.mooc.control;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.imdevil.mooc.R;

/**
 * Created by imdevil on 2017/2/13 0013.
 */
public class Login extends TableLayout {

    private EditText account;
    private EditText psw;
    private CheckBox remember;
    private Button login;

    public Login(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.login,null);
        account = (EditText) findViewById(R.id.login_account);
        psw = (EditText) findViewById(R.id.login_psw);
        remember = (CheckBox) findViewById(R.id.login_remember);
        login = (Button) findViewById(R.id.login_login);

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = account.getText().toString();
                String psw = account.getText().toString();
                Log.d("id+psw",id+"+"+psw);
            }
        });

    }

    public CheckBox getRemember() {
        return remember;
    }

    public EditText getAccount() {
        return account;
    }

    public EditText getPsw() {
        return psw;
    }

    public Button getLogin() {
        return login;
    }
}
