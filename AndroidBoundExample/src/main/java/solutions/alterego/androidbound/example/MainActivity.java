package solutions.alterego.androidbound.example;

import com.alterego.advancedandroidlogger.interfaces.IAndroidLogger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import solutions.alterego.androidbound.NullLogger;
import solutions.alterego.androidbound.ViewBinder;
import solutions.alterego.androidbound.ViewModel;
import solutions.alterego.androidbound.example.imageloader.UILImageLoader;
import solutions.alterego.androidbound.interfaces.ILogger;
import solutions.alterego.androidbound.interfaces.IViewBinder;


public class MainActivity extends AppCompatActivity {

    public static final String LOGGING_TAG = "TEST_APP";

    private static final IAndroidLogger.LoggingLevel LOGGING_LEVEL = IAndroidLogger.LoggingLevel.VERBOSE;

    private IViewBinder mViewBinder;

    private ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ILogger logger = new AdvancedAndroidLoggerAdapter(LOGGING_TAG, LOGGING_LEVEL);

        mViewModel = new MainActivityViewModel(this, logger);
        mViewModel.onCreate(savedInstanceState);

        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("viewbinderTesting")) {
            mViewBinder = new NullViewBinder();

            logger.info("started inflating = " + System.currentTimeMillis());
            View view = mViewBinder.inflate(this, mViewModel, R.layout.activity_main, null);
            setContentView(view);

            TextView activity_title = (TextView) findViewById(R.id.activity_title);
            TextView edittext_textview = (TextView) findViewById(R.id.edittext_textview);
            Button open_bindable_activity_button = (Button) findViewById(R.id.open_bindable_activity_button);
            ImageView imageview = (ImageView) findViewById(R.id.imageview);

            Button open_listview_activity_button = (Button) findViewById(R.id.open_listview_activity_button);
            Button open_listviewwithobjects_activity_button = (Button) findViewById(R.id.open_listviewwithobjects_activity_button);
            Button open_rvwithobjects_activity_button = (Button) findViewById(R.id.open_rvwithobjects_activity_button);
            Button open_paginatedrv_activity_button = (Button) findViewById(R.id.open_paginatedrv_activity_button);

            EditText edittext_et = (EditText) findViewById(R.id.edittext_et);
            Button edittext_button = (Button) findViewById(R.id.edittext_button);

            open_bindable_activity_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent activityIntent = new Intent(MainActivity.this, MainBindingActivity.class);
                    startActivity(activityIntent);
                }
            });

            open_listview_activity_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent activityIntent = new Intent(MainActivity.this, ListViewActivity.class);
                    startActivity(activityIntent);
                }
            });

            open_listviewwithobjects_activity_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent activityIntent = new Intent(MainActivity.this, ListViewWithObjectsActivity.class);
                    startActivity(activityIntent);
                }
            });

            open_rvwithobjects_activity_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent activityIntent = new Intent(MainActivity.this, RecyclerViewWithObjectsActivity.class);
                    startActivity(activityIntent);
                }
            });

            open_paginatedrv_activity_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent activityIntent = new Intent(MainActivity.this, PaginatedRecyclerViewActivity.class);
                    startActivity(activityIntent);
                }
            });

            logger.info("finished inflating = " + System.currentTimeMillis());
        } else {
            mViewBinder = new ViewBinder(this, NullLogger.instance);
//            mViewBinder.getFontManager().setDefaultFont(Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf"));
//            mViewBinder.getFontManager().registerFont("light", Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf"));
//            mViewBinder.getFontManager().registerFont("italic", Typeface.createFromAsset(getAssets(), "Roboto-Italic.ttf"));
//            mViewBinder.getFontManager().registerFont("bold", Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf"));
            mViewBinder.setImageLoader(new UILImageLoader(this, null));

            logger.info("started inflating = " + System.currentTimeMillis());
            View view = mViewBinder.inflate(this, mViewModel, R.layout.activity_main, null);
            setContentView(view);
            logger.info("finished inflating = " + System.currentTimeMillis());
        }

        setTitle("MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewModel.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModel.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.dispose();

        //you shouldn't actually dispose of the view binder, it should be global (injected via Dagger maybe)!
        //this is just for demonstration purposes
        if (mViewBinder != null) {
            mViewBinder.dispose();
        }
    }
}
