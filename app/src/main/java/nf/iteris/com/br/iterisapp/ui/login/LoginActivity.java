package nf.iteris.com.br.iterisapp.ui.login;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import nf.iteris.com.br.iterisapp.R;

public class LoginActivity extends AppCompatActivity {
    private EditText edtCpf;
    private EditText edtPassword;
    private TextInputLayout tilCpf;
    private TextInputLayout tilPassword;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        loadComponents();
    }

    private void loadComponents() {
        edtCpf = (EditText) findViewById(R.id.login_edt_cpf);
        edtPassword = (EditText) findViewById(R.id.login_edt_password);
        tilCpf = (TextInputLayout) findViewById(R.id.login_til_cpf);
        tilPassword = (TextInputLayout) findViewById(R.id.login_til_password);
        btnSignIn = (Button) findViewById(R.id.login_btn_sign_in);
    }
}
