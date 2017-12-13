package nf.iteris.com.br.iterisapp.ui.list_nf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.ui.nf_registration.NfRegistrationActivity;

public class ListNfActivity extends AppCompatActivity {
    private EditText edtSearch;
    private RecyclerView rcvListNfs;
    private Button btnRegisterNfs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nf);

        btnRegisterNfs = (Button) findViewById(R.id.list_nf_btn_register_nfs);
        btnRegisterNfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListNfActivity.this, NfRegistrationActivity.class));
            }
        });


    }
}
