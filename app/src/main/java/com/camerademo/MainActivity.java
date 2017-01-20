package com.camerademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mCameraButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        mCameraButton = (Button) findViewById(R.id.open_camera);
        setListeners();
    }

    private void setListeners() {
        mCameraButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mCameraButton.getId()) {
            // call camera activity
            startActivity(new Intent(MainActivity.this, CameraActivity.class));
        }
    }
}
