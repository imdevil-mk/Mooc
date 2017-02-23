package com.xuyike.videoplayer.Utils;

import android.content.Context;

/**
 * Created by Numb on 2017/2/24.
 */

public class PixeUtil {
    public static void initContext(Context context){
        mContext=context;
    }
    private static Context mContext;

    /**
     * dp 转px
     */
    public static int dp2px(float value){
        final float scale=mContext.getResources().getDisplayMetrics().densityDpi;
        return (int)(value*(scale/160)+0.5f);
    }
}
