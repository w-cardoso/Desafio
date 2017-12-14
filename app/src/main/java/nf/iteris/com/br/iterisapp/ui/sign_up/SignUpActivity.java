package nf.iteris.com.br.iterisapp.ui.sign_up;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import nf.iteris.com.br.iterisapp.util.InputValidation;
import nf.iteris.com.br.iterisapp.util.Mask;
import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.util.Validator;
import nf.iteris.com.br.iterisapp.dao.user_registration_dao.DbHelper;
import nf.iteris.com.br.iterisapp.model.UserRegistration;


public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText edtCpf;
    private TextInputEditText edtName;
    private TextInputEditText edtPassword;
    private TextInputLayout tilCpf;
    private TextInputLayout tilName;
    private TextInputLayout tilPassword;
    private Button btnSignUp;
    private RadioGroup rdgSelectProfile;
    private RadioButton rdbProfileAnalista;
    private RadioButton rdbProfileGestor;
    private RadioButton selectedButton;
    private RelativeLayout rLayout;

    private final AppCompatActivity activity = SignUpActivity.this;

    private InputValidation inputValidation;
    private DbHelper databaseHelper;
    private UserRegistration user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        loadComponents();
        initObjects();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateCpf()) {
                    postDataToSQLite();
                    finish();
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setTitle("Informação");
                    builder.setMessage("Sua conta não foi criada, preencha o(s) campo(s) informado(s)");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });


    }

    private void loadComponents() {
        edtCpf = (TextInputEditText) findViewById(R.id.sign_up_edt_cpf);
        edtCpf.addTextChangedListener(Mask.insert(Mask.CPF_MASK, edtCpf));
        edtName = (TextInputEditText) findViewById(R.id.sign_up_edt_name);
        edtPassword = (TextInputEditText) findViewById(R.id.sign_up_edt_password);
        tilCpf = (TextInputLayout) findViewById(R.id.sign_up_til_cpf);
        tilName = (TextInputLayout) findViewById(R.id.sign_up_til_name);
        tilPassword = (TextInputLayout) findViewById(R.id.sign_up_til_password);
        rdgSelectProfile = (RadioGroup) findViewById(R.id.sign_up_rdg_select_profile);
        rdbProfileAnalista = (RadioButton) findViewById(R.id.sign_up_rdb_profile_analista);
        rdbProfileGestor = (RadioButton) findViewById(R.id.sign_up_rdb_profile_gestor);
        btnSignUp = (Button) findViewById(R.id.sign_up_btn_sign_up);
        rLayout = (RelativeLayout) findViewById(R.id.sign_up_lot_rlt);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DbHelper(activity);
        user = new UserRegistration();

    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(edtCpf, tilCpf, getString(R.string.error_message_cpf))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(edtName, tilName, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(edtPassword, tilPassword, getString(R.string.error_message_password))) {
            return;
        }

        if (!databaseHelper.checkUser(edtCpf.getText().toString().trim())) {

            user.setCpf(edtCpf.getText().toString().trim());
            user.setName(edtName.getText().toString().trim());
            user.setPassword(edtPassword.getText().toString().trim());
            int id = rdgSelectProfile.getCheckedRadioButtonId();
            selectedButton = (RadioButton) findViewById(id);
            user.setProfile(selectedButton.getText().toString());


            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(rLayout, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(rLayout, getString(R.string.error_not_sign_up), Snackbar.LENGTH_LONG).show();
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        edtCpf.setText(null);
        edtName.setText(null);
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
