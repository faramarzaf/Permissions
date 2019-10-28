package com.faramarz.tictacdev.permission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCamera, btnWriteExternalStorage, btnAccessFineLocation, btnContacts;

    public static final int MY_PERMISSIONS_REQUEST_CONTACT = 2;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 3;
    public static final int MY_PERMISSIONS_REQUEST_STORAGE = 4;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 5;


    /*
    FOR FRAGMENT

if you are trying this code in a fragment, change

checkSelfPermission()

to

ActivityCompact.checkSelfPermission()

and Also change

ActivityCompat.requestPermissions()

to

requestPermissions()

Handling of Permission Result (Allow or Deny) are same as activity.

For a More Complete Example See This Answer Here
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        clickEvents();
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
                checkLocationPermission();
                break;

            case R.id.btnContacts:
                checkContactPermission();
                break;

            default:
                break;
        }

    }


    void checkContactPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_CONTACT);
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }

    }

    void checkCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        }

    }

    void checkStoragePermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_STORAGE);
        }
    }


    void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(getApplicationContext(), "Contact granted", Toast.LENGTH_SHORT).show();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Contact denied", Toast.LENGTH_SHORT).show();
                }
                return;

            }
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Camera granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Camera denied", Toast.LENGTH_SHORT).show();
                }
                return;

            }

            case MY_PERMISSIONS_REQUEST_STORAGE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Storage granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Storage denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            case MY_PERMISSIONS_REQUEST_LOCATION: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Location granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Location denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }


    }


}
