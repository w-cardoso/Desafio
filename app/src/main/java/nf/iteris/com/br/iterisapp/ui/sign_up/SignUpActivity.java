package nf.iteris.com.br.iterisapp.ui.sign_up;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import nf.iteris.com.br.iterisapp.R;


public class SignUpActivity extends AppCompatActivity {

    private EditText edtCpf;
    private EditText edtName;
    private EditText edtPassword;
    private TextInputLayout tilCpf;
    private TextInputLayout tilName;
    private TextInputLayout tilPassword;
    private Button btnSignUp;
    private RadioGroup rdgSelectProfile;
    private RadioButton rdbProfileAnalista;
    private RadioButton rdbProfileGestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        loadComponents();
    }

    private void loadComponents() {
        edtCpf = (EditText) findViewById(R.id.sign_up_edt_cpf);
        edtName = (EditText) findViewById(R.id.sign_up_edt_name);
        edtPassword = (EditText) findViewById(R.id.sign_up_edt_password);
        tilCpf = (TextInputLayout) findViewById(R.id.sign_up_til_cpf);
        tilName = (TextInputLayout) findViewById(R.id.sign_up_til_name);
        tilPassword = (TextInputLayout) findViewById(R.id.sign_up_til_password);
        rdgSelectProfile = (RadioGroup) findViewById(R.id.sign_up_rdg_select_profile);
        rdbProfileAnalista = (RadioButton) findViewById(R.id.sign_up_rdb_profile_analista);
        rdbProfileGestor = (RadioButton) findViewById(R.id.sign_up_rdb_profile_gestor);
        btnSignUp = (Button) findViewById(R.id.sign_up_btn_sign_up);
    }
}
