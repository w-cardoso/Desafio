package nf.iteris.com.br.iterisapp.ui.home_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.ui.sign_up.SignUpActivity;

public class HomeScreenActivity extends AppCompatActivity {
    private TextView txtSlogan;
    private Button btnSignIn;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        getSupportActionBar().hide();

        txtSlogan = (TextView) findViewById(R.id.home_screen_txt_slogan);
        btnSignIn = (Button) findViewById(R.id.home_screen_btn_signIn);
        btnSignUp = (Button) findViewById(R.id.home_screen_btn_signUp);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(HomeScreenActivity.this, SignUpActivity.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreenActivity.this, SignUpActivity.class));
            }
        });
    }
}
