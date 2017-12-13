package nf.iteris.com.br.iterisapp.ui.home_screen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.ui.login.LoginActivity;
import nf.iteris.com.br.iterisapp.ui.sign_up.SignUpActivity;

public class HomeScreenActivity extends AppCompatActivity {
    private TextView txtSlogan;
    private TextView txtTitle;
    private Button btnSignIn;
    private Button btnSignUp;
    private Typeface faceSlogan, faceTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        getSupportActionBar().hide();
        loadComponents();

        faceSlogan = Typeface.createFromAsset(getAssets(), "fonts/angelina.ttf");
        txtSlogan.setTypeface(faceSlogan);

        faceTitle = Typeface.createFromAsset(getAssets(), "fonts/hanged_letters.ttf");
        txtTitle.setTypeface(faceTitle);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreenActivity.this, LoginActivity.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreenActivity.this, SignUpActivity.class));
            }
        });
    }

    private void loadComponents() {
        txtSlogan = (TextView) findViewById(R.id.home_screen_txt_slogan);
        txtTitle = (TextView) findViewById(R.id.home_screen_txt_title);
        btnSignIn = (Button) findViewById(R.id.home_screen_btn_signIn);
        btnSignUp = (Button) findViewById(R.id.home_screen_btn_signUp);
    }
}
