package com.faramarz.tictacdev.permission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCamera, btnWriteExternalStorage, btnAccessFineLocation, btnContacts;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    List<String> listPermissionsNeeded = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        clickEvents();
    }

    private boolean checkCameraPermission() {
        int camera = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
            multiplePermission();
        }
        return true;
    }

    private boolean checkStoragePermission() {
        int storage = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            multiplePermission();
        }
        return true;
    }

    private boolean checkFineLocationPermission() {
        int loc = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (loc != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            multiplePermission();
        }
        return true;
    }

    private boolean checkContactsPermission() {
        int contacts = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (contacts != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
            multiplePermission();
        }
        return true;

    }

    private void bind() {
        btnCamera = findViewById(R.id.btnCamera);
        btnWriteExternalStorage = findViewById(R.id.btnWriteExternalStorage);
        btnAccessFineLocation = findViewById(R.id.btnAccessFineLocation);
        btnContacts = findViewById(R.id.btnContacts);
    }

    private void clickEvents() {
        btnContacts.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnWriteExternalStorage.setOnClickListener(this);
        btnAccessFineLocation.setOnClickListener(this);
    }

    private boolean multiplePermission() {
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnCamera:
                checkCameraPermission();
                break;
            case R.id.btnWriteExternalStorage:
                checkStoragePermission();
                break;
            case R.id.btnAccessFineLocation:
                checkFineLocationPermission();
                break;
            case R.id.btnContacts:
                checkContactsPermission();
                break;

            default:
                break;
        }

    }


}
