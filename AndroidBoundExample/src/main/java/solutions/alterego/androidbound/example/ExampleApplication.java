package solutions.alterego.androidbound.example;

import com.alterego.advancedandroidlogger.interfaces.IAndroidLogger;
import com.squareup.leakcanary.LeakCanary;

import android.app.Application;
import android.graphics.Typeface;

import lombok.Getter;
import lombok.experimental.Accessors;
import solutions.alterego.androidbound.NullLogger;
import solutions.alterego.androidbound.ViewBinder;
import solutions.alterego.androidbound.example.imageloader.UILImageLoader;
import solutions.alterego.androidbound.example.util.AdvancedAndroidLoggerAdapter;
import solutions.alterego.androidbound.example.util.CustomValueConverters;
import solutions.alterego.androidbound.interfaces.ILogger;
import solutions.alterego.androidbound.interfaces.IViewBinder;

@Accessors(prefix="m")
public class ExampleApplication extends Application {

    public static final String LOGGING_TAG = "TEST_APP_VIEWBINDER";

    private static final IAndroidLogger.LoggingLevel LOGGING_LEVEL = IAndroidLogger.LoggingLevel.VERBOSE;

    @Getter
    private static IViewBinder mViewBinder;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);
        ILogger logger = new AdvancedAndroidLoggerAdapter(LOGGING_TAG, LOGGING_LEVEL); //use for testing
        mViewBinder = new ViewBinder(this, NullLogger.instance);
//        mViewBinder.setDebug(true); //use for testing!
        mViewBinder.setImageLoader(new UILImageLoader(this, null));
        mViewBinder.getFontManager().setDefaultFont(Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf"));
        mViewBinder.getFontManager().registerFont("light", Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
        mViewBinder.getFontManager().registerFont("italic", Typeface.createFromAsset(getAssets(), "Roboto-Italic.ttf"));
        mViewBinder.getFontManager().registerFont("bold", Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf"));
        CustomValueConverters customValueConverters = new CustomValueConverters(this, mViewBinder);
    }
}
