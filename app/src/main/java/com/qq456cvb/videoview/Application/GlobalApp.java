package com.qq456cvb.videoview.Application;

import android.app.Application;

import com.qq456cvb.videoview.Utils.Channel;
import com.qq456cvb.videoview.Utils.Programme;
import com.qq456cvb.videoview.Utils.UserClient;


/**
 * Created by qq456cvb on 8/27/15.
 */
public class GlobalApp extends Application {
    public static UserClient user;
    public static Channel currentChannel;
    public static Programme currentProgramme;
    public static String endTime;

    @Override
    public void onCreate() {
        super.onCreate();
        user = new UserClient();
        currentChannel = new Channel(1, "CCTV-1", "", "http://220.250.58.250:7000/D*5005*");
        currentChannel.id = "1";
        currentChannel.hdnDyass = "0";
        currentChannel.hdnType = "3";
    }


}
