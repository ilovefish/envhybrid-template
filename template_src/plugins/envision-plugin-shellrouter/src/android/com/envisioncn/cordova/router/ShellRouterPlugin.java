package com.envisioncn.cordova.router;

import android.app.Activity;

import com.envisioncn.mobile.hybrid.ui.view.BaseActivityView;
import com.usepropeller.routable.Router;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by zihan.chen on 16/3/3.
 */
public class ShellRouterPlugin extends CordovaPlugin {
    private static final String TAG = "ShellRouterPlugin";

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
        /*
    	 * Don't run any of these if the current activity is finishing
    	 * in order to avoid android.view.WindowManager$BadTokenException
    	 * crashing the app. Just return true here since false should only
    	 * be returned in the event of an invalid action.
    	 */
        if(this.cordova.getActivity().isFinishing()) return true;

        if (action.equals("replaceState")) {
            this.replaceState(data.getString(0), callbackContext);
        }
        else if (action.equals("pushState")) {
            this.pushState(data.getString(0), callbackContext);
        }
        else if (action.equals("goBack")) {
            this.goBack(callbackContext);
        }else if(action.equals("showNativeMask")){
            this.showNativeMask(data.getBoolean(0), callbackContext);
        }else if(action.equals("showTabBarBadge")){
            this.showTabBarBadge(true, data.getInt(0), callbackContext);
        }else if(action.equals("hideTabBarBadge")){
            this.showTabBarBadge(false, data.getInt(0), callbackContext);
        }else if(action.equals("setNavigationBarTitle")){
            this.setNavigationBarTitle(data.getString(0), callbackContext);
        }

        // Only alert and confirm are async.
        callbackContext.success();
        return true;
    }

    private void replaceState(String path, CallbackContext callbackContext){
        if(path != null && path.length()>0 ){
            Router.sharedRouter().open(path);
            cordova.getActivity().finish();
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
    }

    private void pushState(String path, CallbackContext callbackContext){
        if(path != null && path.length()>0){
            //WLog.w(TAG, "@@@ pushState " + path);
           Router.sharedRouter().open(path);
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
    }

    private void goBack(CallbackContext callbackContext){
        final Activity activity = this.cordova.getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.onBackPressed();
            }
        });
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
    }

    private void showNativeMask(boolean show, CallbackContext callbackContext){
        /*
        MyApplication application = MyApplication.getInstance();
        if(show){
            application.disableNativeInteraction();
        }else{
            application.enableNativeInteraction();
        }
        */
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
    }

    private void showTabBarBadge(boolean show, int index,  CallbackContext callbackContext){
        Activity activity = this.cordova.getActivity();
        if(activity instanceof MainActivity){
           //  ((MainActivity) activity).showBadgeAtIndex(show, index);
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
    }

    private void setNavigationBarTitle(final String title, CallbackContext callbackContext){
        final Activity activity = this.cordova.getActivity();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if( activity instanceof BaseActivityView){
                    ((BaseActivityView) activity).getActionBarProvider().setTitle(title);
                } else{

                }
            }
        });

        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
    }
}
