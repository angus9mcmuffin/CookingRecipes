package android.lee.cookingrecipes;

import android.content.Context;

/**
 * Created by jason on 4/2/18.
 */

public class CameraUtils {
    private static Context mAppContext;


    // Context does not need to be destroyed because app should be destroyed by OS
    public static synchronized void initFileUtils(Context appContext) {
        mAppContext = appContext;
    }

    public static String putImageInFile() {
        throw new RuntimeException("NOT IMPLEMENTED");
    }
}
