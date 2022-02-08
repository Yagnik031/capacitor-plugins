package com.yagnik.flash.light;

import android.Manifest;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;

// @CapacitorPlugin(name = "Flash")
// public class FlashPlugin extends Plugin {

//     private Flash implementation = new Flash();

//     @PluginMethod
//     public void echo(PluginCall call) {
//         String value = call.getString("value");

//         JSObject ret = new JSObject();
//         ret.put("value", implementation.echo(value));
//         call.resolve(ret);
//     }
// }

@CapacitorPlugin(name = "Flash", permissions = { @Permission(alias = "camera", strings = { Manifest.permission.CAMERA }) })
public class FlashPlugin extends Plugin {

    private String cameraId;
    boolean isFlashStateOn = false;

    private CameraManager cameraManager;
    private Flash implementation = new Flash();

    @Override
    public void load() {
        cameraManager = (CameraManager) this.bridge.getContext().getSystemService(Context.CAMERA_SERVICE);
        try {
            if (cameraManager != null) {
                cameraId = cameraManager.getCameraIdList()[0];
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @PluginMethod
    public void isAvailable(PluginCall call) {
        JSObject ret = new JSObject();
        ret.put("value", false);
        if (getPermissionState("camera") != PermissionState.GRANTED) {
            this.checkPermissions(call);
            return;
        } else {
            if (cameraManager != null) {
                try {
                    boolean flashAvailable = cameraManager
                        .getCameraCharacteristics(cameraId)
                        .get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    ret.put("value", flashAvailable);
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                    ret.put("value", false);
                }
            }
        }
        call.resolve(ret);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @PluginMethod
    public void switchOn(PluginCall call) {
        String value = call.getString("instensity");
        JSObject ret = new JSObject();
        try {
            if (cameraManager != null) {
                cameraManager.setTorchMode(cameraId, true);
                isFlashStateOn = true;
                ret.put("value", true);
            } else {
                ret.put("value", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret.put("value", false);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @PluginMethod
    public void switchOff(PluginCall call) {
        JSObject ret = new JSObject();
        try {
            if (cameraManager != null) {
                cameraManager.setTorchMode(cameraId, false);
                isFlashStateOn = false;
                ret.put("value", true);
            } else {
                ret.put("value", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret.put("value", false);
        }
        call.resolve(ret);
    }

    @PluginMethod
    public void isSwitchedOn(PluginCall call) {
        JSObject ret = new JSObject();
        try {
            if (cameraManager != null) {
                ret.put("value", isFlashStateOn);
            } else {
                ret.put("value", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret.put("value", false);
        }
        call.resolve(ret);
    }
}
