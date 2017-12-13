package nf.iteris.com.br.iterisapp.ui.list_nf;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;


import java.util.List;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.model.NfRegistration;

public class ListNfActivity extends AppCompatActivity {
    private FloatingActionButton fabAddNf;
    final Context context = this;

    private TextInputEditText edtNumberNf;
    private TextInputEditText edtDescription;
    private TextInputEditText edtDateBilling;
    private TextInputEditText edtDatePayment;
    private TextInputLayout tilNumberNf;
    private TextInputLayout tilDescription;
    private TextInputLayout tilDateBilling;
    private TextInputLayout tilDatePayment;
    private Spinner spnStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nf);

        fabAddNf = (FloatingActionButton) findViewById(R.id.list_nf_fab);

        fabAddNf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
    }
}
