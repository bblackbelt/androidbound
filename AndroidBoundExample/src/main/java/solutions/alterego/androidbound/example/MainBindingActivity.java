package solutions.alterego.androidbound.example;

import com.alterego.advancedandroidlogger.interfaces.IAndroidLogger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import solutions.alterego.androidbound.NullLogger;
import solutions.alterego.androidbound.ViewBinder;
import solutions.alterego.androidbound.android.BindingAppCompatActivity;
import solutions.alterego.androidbound.example.imageloader.UILImageLoader;
import solutions.alterego.androidbound.interfaces.ILogger;
import solutions.alterego.androidbound.interfaces.IViewBinder;

public class MainBindingActivity extends BindingAppCompatActivity {

    public static final String LOGGING_TAG = "TEST_APP";

    private static final IAndroidLogger.LoggingLevel LOGGING_LEVEL = IAndroidLogger.LoggingLevel.VERBOSE;

    private IViewBinder mViewBinder;

    private CustomValueConverters mCustomValueConverters;

    private MainBindableActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ILogger logger = new AdvancedAndroidLoggerAdapter(LOGGING_TAG, LOGGING_LEVEL);

        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("viewbinderTesting")) {
        IViewBinder viewBinder = new NullViewBinder();
            setViewBinder(viewBinder);

            mViewModel = new MainBindableActivityViewModel(this, logger);
            mViewModel.onCreate(savedInstanceState);

            logger.info("started inflating = " + System.currentTimeMillis());
            View view = viewBinder.inflate(this, mViewModel, R.layout.activity_bindable_main, null);
            setContentView(view);

            TextView activity_title = (TextView) findViewById(R.id.activity_title);
            Button open_normal_activity_button = (Button) findViewById(R.id.open_normal_activity_button);
            TextView visibility_text = (TextView) findViewById(R.id.visibility_text);
            Button visibility_button = (Button) findViewById(R.id.visibility_button);
            ImageView imageview = (ImageView) findViewById(R.id.imageview);
            TextView prettify_custom_converter = (TextView) findViewById(R.id.prettify_custom_converter);

            open_normal_activity_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent activityIntent = new Intent(MainBindingActivity.this, MainActivity.class);
                    startActivity(activityIntent);
                }
            });

            visibility_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            logger.info("finished inflating = " + System.currentTimeMillis());
        } else {
            ViewBinder viewBinder = new ViewBinder(this, NullLogger.instance);
//            viewBinder.getFontManager().setDefaultFont(Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf"));
//            viewBinder.getFontManager().registerFont("light", Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
//            viewBinder.getFontManager().registerFont("italic", Typeface.createFromAsset(getAssets(), "Roboto-Italic.ttf"));
//            viewBinder.getFontManager().registerFont("bold", Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf"));
            mCustomValueConverters = new CustomValueConverters(this, viewBinder);
            viewBinder.setImageLoader(new UILImageLoader(this, null));
            setViewBinder(viewBinder);

            mViewModel = new MainBindableActivityViewModel(this, logger);
            mViewModel.onCreate(savedInstanceState);

            logger.info("started inflating = " + System.currentTimeMillis());
            View view = viewBinder.inflate(this, mViewModel, R.layout.activity_bindable_main, null);
            logger.info("finished inflating = " + System.currentTimeMillis());
            setContentView(view);
        }

        setTitle("MainBindingActivity");
    }

    @Override
    public IViewBinder getViewBinder() {
        return mViewBinder;
    }

    @Override
    public void setViewBinder(IViewBinder viewBinder) {
        mViewBinder = viewBinder;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCustomValueConverters = null;
        if (getViewBinder() != null) {
            getViewBinder().dispose();
        }
    }
}
