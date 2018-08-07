package com.caragiz_studioz.library.camera2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.caragiz_studioz.library.easycamera2.CameraView;

public class ActivityMain extends AppCompatActivity {
//// TODO: 07-08-2018 add features
    /*
    * camera to use(front or rear or external)
    * method for return (data stream or bitmap)
    * focus control
    * exposure control
    * frame rate control
    * */
    CameraView camera;
FloatingActionButton click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    7003);
        else
        {
            setContentView(R.layout.activity_main);
            camera = findViewById(R.id.camera_view);
            click = findViewById(R.id.fab_take_photo);
            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    grabImage();
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 7003) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                setContentView(R.layout.activity_main);
                camera = findViewById(R.id.camera_view);
                click = findViewById(R.id.fab_take_photo);
                click.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        grabImage();
                    }
                });
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        camera.onStop();
    }

    private void grabImage(){
        camera.getImage();
    }
}
