package nf.iteris.com.br.iterisapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.dao.user_registration_dao.DbHelper;
import nf.iteris.com.br.iterisapp.ui.antecipation_request.AntecipationRequestActivity;
import nf.iteris.com.br.iterisapp.ui.list_nf.ListNfActivity;
import nf.iteris.com.br.iterisapp.util.InputValidation;
import nf.iteris.com.br.iterisapp.util.Mask;
import nf.iteris.com.br.iterisapp.util.Validator;

public class LoginActivity extends AppCompatActivity {
    private final AppCompatActivity activity = LoginActivity.this;
    private RelativeLayout rLayout;
    private TextInputEditText edtCpf;
    private TextInputEditText edtPassword;
    private TextInputLayout tilCpf;
    private TextInputLayout tilPassword;
    private Button btnSignIn;

    private InputValidation inputValidation;
    private DbHelper databaseHelper;
    private String profileOne = "Analista";
    private String profileTwo = "Gestor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        loadComponents();
        initObjects();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DbHelper dao = new DbHelper(getApplicationContext());
                String cpf = edtCpf.getText().toString().trim();
                validateCpf();
                if (Validator.validateCPF(cpf)){
                    verifyFromSQLite();
                    finish();
                }else {
                    Snackbar.make(rLayout, getString(R.string.error_cpf_not_register), Snackbar.LENGTH_LONG).show();
                }



            }
        });
    }


    private void loadComponents() {
        rLayout = (RelativeLayout) findViewById(R.id.login_lot_rLayout);
        edtCpf = (TextInputEditText) findViewById(R.id.login_edt_cpf);
        edtCpf.addTextChangedListener(Mask.insert(Mask.CPF_MASK, edtCpf));
        edtPassword = (TextInputEditText) findViewById(R.id.login_edt_password);
        tilCpf = (TextInputLayout) findViewById(R.id.login_til_cpf);
        tilPassword = (TextInputLayout) findViewById(R.id.login_til_password);
        btnSignIn = (Button) findViewById(R.id.login_btn_sign_in);
    }

    private void initObjects() {
        databaseHelper = new DbHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(edtCpf, tilCpf, getString(R.string.error_message_cpf))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(edtPassword, tilPassword, getString(R.string.error_message_password))) {
            return;
        }

        if (databaseHelper.checkUser(edtCpf.getText().toString().trim()
                , edtPassword.getText().toString().trim(), profileOne)) {


            Intent accountsIntent = new Intent(activity, ListNfActivity.class);
            startActivity(accountsIntent);


        }
        if (databaseHelper.checkUser(edtCpf.getText().toString().trim()
                , edtPassword.getText().toString().trim(), profileTwo)) {
            Intent accountsIntent = new Intent(activity, AntecipationRequestActivity.class);
            startActivity(accountsIntent);

        }
        else{
            Snackbar.make(rLayout, getString(R.string.error_cpf_not_register), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        edtCpf.setText(null);
        edtPassword.setText(null);
    }

    private boolean validateCpf() {
        boolean cpfValido = Validator.validateCPF(edtCpf.getText().toString().trim());
        String cpf = edtCpf.getText().toString();
        if (!cpfValido || cpf.isEmpty()) {
            tilCpf.setError(getString(R.string.err_msg_cpf));
            requestFocus(edtCpf);
        } else {
            tilCpf.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}
