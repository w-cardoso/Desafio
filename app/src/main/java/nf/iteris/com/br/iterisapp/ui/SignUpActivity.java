package nf.iteris.com.br.iterisapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nf.iteris.com.br.iterisapp.R;


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
    }
}
