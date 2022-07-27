package com.solo_app;

import static android.content.pm.PackageManager.FEATURE_CAMERA_FLASH;

import androidx.annotation.NonNull;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RCTTorchModule extends ReactContextBaseJavaModule {

    private CameraManager cameraManager;
    private String cameraID;
    private boolean flashState;

    public RCTTorchModule(ReactApplicationContext Context) {
        super(Context);
    }

    @NonNull
    @Override
    public String getName() {
        return "RCTTorchModule";
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    public void flashOn(){
        cameraManager = (CameraManager)getCurrentActivity().getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraID = cameraManager.getCameraIdList()[0]; // 0 is for back camera and 1 is for front camera//
            Log.d("flashOn", cameraID+"");
            cameraManager.setTorchMode(cameraID, true);
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    public void flashOff(){
        cameraManager = (CameraManager)getCurrentActivity().getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraID = cameraManager.getCameraIdList()[0]; // 0 is for back camera and 1 is for front camera//
            Log.d("flashOff", cameraID+"");
            cameraManager.setTorchMode(cameraID, false);
        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    public void toggleFlash(){
        CameraManager.TorchCallback torchCallback = new CameraManager.TorchCallback() {
            @Override
            public void onTorchModeUnavailable(String cameraId) {
                super.onTorchModeUnavailable(cameraId);
            }

            @Override
            public void onTorchModeChanged(String cameraId, boolean enabled) {
                super.onTorchModeChanged(cameraId, enabled);
                flashState = enabled;
            }
        };

        cameraManager = (CameraManager)getCurrentActivity().getSystemService(Context.CAMERA_SERVICE);
        cameraManager.registerTorchCallback(torchCallback, null);
        try {
            cameraID = cameraManager.getCameraIdList()[0]; // 0 is for back camera and 1 is for front camera//
            Log.d("toggleFlash", cameraID+"");
            Log.d("flashState", flashState+"");
            cameraManager.setTorchMode(cameraID, !flashState);

        }catch (CameraAccessException e){
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @ReactMethod
    public void flashLevel(int level){
//        CameraManager.TorchCallback torchCallback = new CameraManager.TorchCallback() {
//            @Override
//            public void turnOnTorchWithStrengthLevel(String cameraId, int newStrengthLevel) {
//                super.turnOnTorchWithStrengthLevel(cameraId, newStrengthLevel);
//            }
//            @Override
//            public void onTorchStrengthLevelChanged(String cameraId, int newStrengthLevel) {
//                super.onTorchStrengthLevelChanged(cameraId, newStrengthLevel);
//                flashState = enabled;
//            }
//        };

        if(getCurrentActivity().getPackageManager().hasSystemFeature(FEATURE_CAMERA_FLASH)){
            Log.d("flash available === ", FEATURE_CAMERA_FLASH+"");

            cameraManager = (CameraManager)getCurrentActivity().getSystemService(Context.CAMERA_SERVICE);
//            cameraManager.registerTorchCallback(torchCallback1, null);
            try {
                cameraID = cameraManager.getCameraIdList()[0]; // 0 is for back camera and 1 is for front camera//
                Log.d("flash Level", level+"");

                //          If this value is greater than 1, applications can call this API to control the flashlight brightness level.
//                Log.d("FLASH_INFO_STRENGTH_MAXIMUM_LEVEL", CameraCharacteristics.FLASH_INFO_STRENGTH_MAXIMUM_LEVEL+"");
//
                // change brightness level
//                getTorchStrengthLevel
                if (Build.VERSION.SDK_INT >= 33) {
//                    CameraManager.turnOnTorchWithStrengthLevel(cameraID, level);
                }

            }catch (CameraAccessException e){
                e.printStackTrace();
            }
        } else {
            Log.d("flash not available", "");
        }
    }
}
