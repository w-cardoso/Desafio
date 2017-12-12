package nf.iteris.com.br.iterisapp.ui.nf_registration;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import nf.iteris.com.br.iterisapp.R;

public class NfRegistrationActivity extends AppCompatActivity {
    private EditText edtNumberNf;
    private EditText edtDescription;
    private EditText edtDateBilling;
    private EditText edtDatePayment;
    private TextInputLayout tilNumberNf;
    private TextInputLayout tilDescription;
    private TextInputLayout tilDateBilling;
    private TextInputLayout tilDatePayment;
    private Spinner spnStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nf_registration);

        loadComponents();

    }

    private void loadComponents() {
        edtNumberNf = (EditText) findViewById(R.id.nf_registration_edt_numbernf);
        edtDescription = (EditText) findViewById(R.id.nf_registration_edt_description);
        edtDateBilling = (EditText) findViewById(R.id.nf_registration_edt_date_billing);
        edtDatePayment = (EditText) findViewById(R.id.nf_registration_edt_date_payment);
        tilNumberNf = (TextInputLayout) findViewById(R.id.nf_registration_til_numbernf);
        tilDescription = (TextInputLayout) findViewById(R.id.nf_registration_til_description);
        tilDateBilling = (TextInputLayout) findViewById(R.id.nf_registration_til_date_billing);
        tilDatePayment = (TextInputLayout) findViewById(R.id.nf_registration_til_date_payment);
        spnStatus = (Spinner) findViewById(R.id.nf_registration_spn_status);
    }
}
