package com.example.zengyong.studydemo.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zengyong.studydemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btFirst)
    public void onBtFirstClicked() {
        FirstDialogFragment dialogFragment = new FirstDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "one");
    }

    @OnClick(R.id.btTwo)
    public void onBtTwoClicked() {
        TwoDialogFragment dialogFragment = new TwoDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "two");
    }
}
