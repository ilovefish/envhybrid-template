package com.envisioncn.cordova.shareddata;


import android.util.Log;

import com.envisioncn.mobile.hybrid.support.AppData;
import com.envisioncn.mobile.hybrid.support.IJSValueCallback;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zihan.chen on 16/3/3.
 */
public class SharedDataPlugin extends CordovaPlugin {
    private static final String TAG = "SharedDataPlugin";

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param data              JSONArray of arguments for the plugin.
     * @param callbackContext   The callback context used when calling back into JavaScript.
     * @return                  True when the action was valid, false otherwise.
     */
    @Override
    public boolean execute(String action, JSONArray data,
                           CallbackContext callbackContext) throws JSONException {
        if(action.equals("isInitialized")) {
            this.isInitialized(callbackContext);
        }else if(action.equals("initialize")) {
            this.initialize(data.get(0), callbackContext);
        }
        else if (action.equals("removeItem")) {
            this.removeItem(data.getString(0), callbackContext);
        }
        else if (action.equals("setItem")) {
            this.setItem(data.getString(0), data.get(1), callbackContext);
        }
        else if (action.equals("getItem")) {
            this.getItem(data.getString(0), callbackContext);
        }
        return true;
    }

    private void isInitialized(CallbackContext callbackContext){
        boolean initialized = AppData.getInstance().isInitialized();
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, initialized));
    }

    private void initialize(Object rootObject, final CallbackContext callbackContext){
        if(rootObject instanceof JSONObject){
            AppData.getInstance().initialize((JSONObject)rootObject, new IJSValueCallback() {
                @Override
                public void onResolveValue(Object value) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
                }
            });
        }else{
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, "invalid argument"));
        }
    }

    private void setItem(String key, Object value, final CallbackContext callbackContext){
        AppData.getInstance().setItem(key, value, new IJSValueCallback() {
            @Override
            public void onResolveValue(Object value) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            }
        });
    }

    private void getItem(final String key, final CallbackContext callbackContext){

        AppData.getInstance().getItem(key, new IJSValueCallback() {
            @Override
            public void onResolveValue(final Object value) {
                if(value instanceof String){
                    callbackContext.success((String)value);
                }else if(value instanceof JSONObject){
                    callbackContext.success((JSONObject)value);
                }else if(value instanceof JSONArray){
                    callbackContext.success((JSONArray)value);
                }else if(value instanceof Boolean){
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, ((Boolean) value).booleanValue()));
                }else if(value instanceof Integer){
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, ((Integer)value).intValue()));
                }else if(value instanceof Float){
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, ((Float)value).floatValue()));
                }else{
                    Log.e(TAG, "@@ not fount key : " + key);
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
                }
            }
        });

    }

    private void removeItem(String key, final CallbackContext callbackContext){
        AppData.getInstance().removeItem(key, new IJSValueCallback() {
            @Override
            public void onResolveValue(Object value) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
            }
        });
    }

}
