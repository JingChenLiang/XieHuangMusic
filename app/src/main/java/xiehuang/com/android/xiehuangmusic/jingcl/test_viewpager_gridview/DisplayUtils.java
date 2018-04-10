package xiehuang.com.android.xiehuangmusic.jingcl.test_viewpager_gridview;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;

import xiehuang.com.android.xiehuangmusic.jingcl.others.application.MusicApplication;
import xiehuang.com.android.xiehuangmusic.jingcl.others.utils.LogUtil;

public class DisplayUtils {
    private static final String TAG = "DisplayUtils";

    private static float getDensity(Context context) {
        if (null == context) {
            LogUtil.e(TAG, "getDensity context == null");
            return 0;
        }
        return context.getResources().getDisplayMetrics().density;
    }

    private static float getScaledDensity(Context context) {
        if (null == context) {
            LogUtil.e(TAG, "getScaledDensity context == null");
            return 0;
        }
        return context.getResources().getDisplayMetrics().scaledDensity;
    }
  
    /** 
     * 将dip或dp值转换为px值，保证尺寸大小不变 
     *
     * @param context
     * @param dipValue
     *            （DisplayMetrics类中属性density）
     * @return 
     */  
    public static int dip2px(Context context, float dipValue) {
        final float scale = getDensity(context);
        return (int) (dipValue * scale + 0.5f);  
    }
  
    /** 
     * 将sp值转换为px值，保证文字大小不变 
     *
     * @param context
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return 
     */  
    public static int sp2px(Context context, float spValue) {
        final float fontScale = getScaledDensity(context);
        return (int) (spValue * fontScale + 0.5f);  
    }  
    
    public static int getStatusBarHeight(Context context) {

        int height = 0; 
        try{     
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int height_px = Integer.parseInt(field.get(obj).toString());
            height = context.getResources().getDimensionPixelSize(height_px); 
        } catch(Exception e) {
            e.printStackTrace(); 
        }

        return height;
    }
    
    private static int sAmigoActionBarHeight = 0;
    public static int getAmigoActionBarHeight(Context context) {
        if (sAmigoActionBarHeight == 0) {
            int resId = context.getResources().getIdentifier("amigo_actionbar_height", "dimen", "amigo");
            if (resId > 0) {
                sAmigoActionBarHeight = context.getResources().getDimensionPixelSize(resId);
                LogUtil.d(TAG, "getAmigoActionBarHeight1 " + sAmigoActionBarHeight);
            } else {
                try {
                    Class<?> c = Class.forName("amigo.R$dimen");
                    int heightDp = c.getField("amigo_actionbar_height").getInt(c.newInstance());
                    float scale = context.getResources().getDisplayMetrics().density;
                    sAmigoActionBarHeight = (int) (heightDp * scale);
                    LogUtil.d(TAG, "getAmigoActionBarHeight2 " + sAmigoActionBarHeight);
                } catch (IllegalAccessException | IllegalArgumentException
                        | NoSuchFieldException | InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return sAmigoActionBarHeight;
    }
    
    public static Rect getFontRect(Paint paint, String str) {
        if(paint == null) {
            return null;
        }
        
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect;
    }

    private static int sScreenWidth = -1;
    private static int sScreenHeigth = -1;

    public static int getScreenWidth() {
        if(sScreenWidth == -1) {
            DisplayMetrics dm = MusicApplication.getInstance().getResources().getDisplayMetrics();
            sScreenWidth = dm.heightPixels;
        }
        return sScreenWidth;
    }

    public static int getScreenHeigth() {
        if(sScreenHeigth == -1) {
            DisplayMetrics dm = MusicApplication.getInstance().getResources().getDisplayMetrics();
            sScreenHeigth = dm.heightPixels;
        }
        return sScreenHeigth;
    }
}  