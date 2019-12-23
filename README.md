# Permissions  

<p float="left">
  <img src="https://developer.android.com/images/permissions/runtime_permission_request_2x.png" />
</p>


The purpose of a permission is to protect the privacy of an Android user. Android apps must request permission to access sensitive user data (such as contacts and SMS), as well as certain system features (such as camera and internet). Depending on the feature, the system might grant the permission automatically or might prompt the user to approve the request.   

An app must publicize the permissions it requires by including `<uses-permission>` tags in the app *manifest*. For example, an app that needs to send SMS messages would have this line in the manifest:  
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="com.example.snazzyapp">

    <uses-permission android:name="android.permission.SEND_SMS"/>

    <application ...>
        ...
    </application>
</manifest>
```


- **Permissions for optional hardware features**  

Access to some hardware features (such as Bluetooth or the camera) require an app permission. However, not all Android devices actually have these hardware features. So if your app requests the CAMERA permission, it's important that you also include the `<uses-feature>` tag in your manifest to declare whether or not this feature is actually required. For example:  

```xml
<uses-feature android:name="android.hardware.camera" android:required="false" />
```

To check if you have a permission, call the `ContextCompat.checkSelfPermission()` method. For example, this snippet shows how to check if the activity has permission to write to the calendar:  
```java
if (ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.WRITE_CALENDAR)
        != PackageManager.PERMISSION_GRANTED) {
    // Permission is not granted
}
```
