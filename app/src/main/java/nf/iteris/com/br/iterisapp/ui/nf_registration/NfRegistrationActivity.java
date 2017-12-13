package nf.iteris.com.br.iterisapp.ui.nf_registration;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.dao.nf_registration.NfRegistrationDao;
import nf.iteris.com.br.iterisapp.model.NfRegistration;
import nf.iteris.com.br.iterisapp.util.InputValidation;

public class NfRegistrationActivity extends AppCompatActivity {
    private TextInputEditText edtNumberNf;
    private TextInputEditText edtDescription;
    private TextInputEditText edtDateBilling;
    private TextInputEditText edtDatePayment;
    private TextInputLayout tilNumberNf;
    private TextInputLayout tilDescription;
    private TextInputLayout tilDateBilling;
    private TextInputLayout tilDatePayment;
    private Spinner spnStatus;
    private Button btnRegisterNf;
    private List<String> status = new ArrayList<String>();
    private String statusSelected;

    DatePickerDialog datePickerDialog;
    private InputValidation inputValidation;
    private NfRegistrationDao databaseHelper;
    private NfRegistration notaFiscal;
    private RelativeLayout rLayout;
    private final AppCompatActivity activity = NfRegistrationActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nf_registration);

        getSupportActionBar().hide();
        loadComponents();
        generateSpinner();
        initObjects();

        btnRegisterNf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToSQLite();
            }
        });



        edtDateBilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                datePickerDialog = new DatePickerDialog(NfRegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                edtDateBilling.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        edtDatePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                datePickerDialog = new DatePickerDialog(NfRegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                edtDatePayment.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, status);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnStatus.setAdapter(spinnerArrayAdapter);

        //Método do Spinner para capturar o item selecionado
        spnStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                statusSelected = parent.getItemAtPosition(posicao).toString();
                //imprime um Toast na tela com o nome que foi selecionado
                Toast.makeText(NfRegistrationActivity.this, "Nome Selecionado: " + statusSelected, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void generateSpinner() {
        status.add("Status 1");
        status.add("Status 2");
        status.add("Status 3");
        status.add("Status 4");
        status.add("Status 5");
    }

    private void loadComponents() {
        edtNumberNf = (TextInputEditText) findViewById(R.id.nf_registration_edt_numbernf);
        edtDescription = (TextInputEditText) findViewById(R.id.nf_registration_edt_description);
        edtDateBilling = (TextInputEditText) findViewById(R.id.nf_registration_edt_date_billing);
        edtDatePayment = (TextInputEditText) findViewById(R.id.nf_registration_edt_date_payment);
        tilNumberNf = (TextInputLayout) findViewById(R.id.nf_registration_til_numbernf);
        tilDescription = (TextInputLayout) findViewById(R.id.nf_registration_til_description);
        tilDateBilling = (TextInputLayout) findViewById(R.id.nf_registration_til_date_billing);
        tilDatePayment = (TextInputLayout) findViewById(R.id.nf_registration_til_date_payment);
        spnStatus = (Spinner) findViewById(R.id.nf_registration_spn_status);
        btnRegisterNf = (Button) findViewById(R.id.nf_registration_btn_cadastrar);
        rLayout = (RelativeLayout) findViewById(R.id.nf_registration_lot);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new NfRegistrationDao(activity);
        notaFiscal = new NfRegistration();

    }

    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(edtNumberNf, tilNumberNf, getString(R.string.error_message_cpf))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(edtDescription, tilDescription, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(edtDateBilling, tilDateBilling, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(edtDatePayment, tilDatePayment, getString(R.string.error_message_password))) {
            return;
        }


        if (!edtNumberNf.equals(null) || edtDescription.equals(null) || edtDateBilling.equals(null) || edtDatePayment.equals(null)) {

            notaFiscal.setNumber(edtNumberNf.getText().toString().trim());
            notaFiscal.setDescription(edtDescription.getText().toString().trim());
            notaFiscal.setDateBilling(edtDateBilling.getText().toString().trim());
            notaFiscal.setDatePayment(edtDatePayment.getText().toString().trim());
            notaFiscal.setStatus(statusSelected);


            databaseHelper.addNotaFiscal(notaFiscal);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(rLayout, getString(R.string.success_message_nf), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(rLayout, getString(R.string.error_not_sign_up), Snackbar.LENGTH_LONG).show();
        }


    }

    private void emptyInputEditText() {
        edtNumberNf.setText(null);
        edtDescription.setText(null);
        edtDateBilling.setText(null);
        edtDatePayment.setText(null);

    }
}
