package nf.iteris.com.br.iterisapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.ui.home_screen.HomeScreenActivity;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar indeterminateBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        indeterminateBar = (ProgressBar) findViewById(R.id.indeterminateBar);

        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }

    private void doWork() {
        for (int progress = 0; progress < 100; progress += 3) {
            try {
                Thread.sleep(100);
                indeterminateBar.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(SplashActivity.this, HomeScreenActivity.class);
        startActivity(intent);
    }

}

