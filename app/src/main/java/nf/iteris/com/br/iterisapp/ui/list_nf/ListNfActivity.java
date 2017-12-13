package nf.iteris.com.br.iterisapp.ui.list_nf;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.dao.nf_registration.NfRegistrationDao;
import nf.iteris.com.br.iterisapp.model.NfRegistration;
import nf.iteris.com.br.iterisapp.ui.nf_registration.NfRegistrationActivity;
import nf.iteris.com.br.iterisapp.util.RecyclerItemClickListener;

public class ListNfActivity extends AppCompatActivity {
    private AppCompatActivity activity = ListNfActivity.this;
    private RecyclerView recyclerViewNfs;
    private List<NfRegistration> listNotasFiscais;
    private NfRecyclerAdapter nfRecyclerAdapter;
    private NfRegistrationDao databaseHelper;
    private EditText edtSearch;
    private Button btnRegisterNfs;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nf);
        getSupportActionBar().setTitle("Lista de Notas Fiscais");

        loadComponents();
        initObjects();




        btnRegisterNfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListNfActivity.this, NfRegistrationActivity.class));
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nfRecyclerAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        recyclerViewNfs.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerViewNfs, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                NfRegistrationDao dao = new NfRegistrationDao(getApplicationContext());

                TextView txtId = (TextView) view.findViewById(R.id.item_nf_registration_txt_number_nf);
                long id = Long.parseLong(txtId.getText().toString());
                dao.pegarDados(id);


                final Dialog dialog = new Dialog(ListNfActivity.this);
                dialog.setTitle("Descrição");
                dialog.setContentView(R.layout.dialog_rcv_item);


                TextInputEditText edtNumberNf = (TextInputEditText) dialog.findViewById(R.id.dialog_rcv_edt_numbernf);
                TextInputEditText edtDescription = (TextInputEditText) dialog.findViewById(R.id.dialog_rcv_edt_description);
                TextInputEditText edtDateBilling = (TextInputEditText) dialog.findViewById(R.id.dialog_rcv_edt_date_billing);
                TextInputEditText edtDatePayment = (TextInputEditText) dialog.findViewById(R.id.dialog_rcv_edt_date_payment);
                TextInputLayout tilNumberNf = (TextInputLayout) dialog.findViewById(R.id.dialog_rcv_til_numbernf);
                TextInputLayout tilDescription = (TextInputLayout) dialog.findViewById(R.id.dialog_rcv_til_description);
                TextInputLayout tilDateBilling = (TextInputLayout) dialog.findViewById(R.id.dialog_rcv_til_date_billing);
                TextInputLayout tilDatePayment = (TextInputLayout) dialog.findViewById(R.id.dialog_rcv_til_date_payment);
                Button btnCancel = (Button) dialog.findViewById(R.id.dialog_rcv_btn_cancelar);
                Button btnAntecipe = (Button) dialog.findViewById(R.id.dialog_rcv_btn_antecipar);



                dialog.show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

    private void loadComponents() {
        recyclerViewNfs = (RecyclerView) findViewById(R.id.list_nf_rcv_nfs);
        edtSearch = (EditText) findViewById(R.id.list_nf_edt_search);
        btnRegisterNfs = (Button) findViewById(R.id.list_nf_btn_register_nfs);
    }

    private void initObjects() {
        listNotasFiscais = new ArrayList<>();
        nfRecyclerAdapter = new NfRecyclerAdapter(listNotasFiscais);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewNfs.setLayoutManager(mLayoutManager);
        recyclerViewNfs.setItemAnimator(new DefaultItemAnimator());
        recyclerViewNfs.setHasFixedSize(true);
        recyclerViewNfs.setAdapter(nfRecyclerAdapter);
        databaseHelper = new NfRegistrationDao(activity);


        getDataFromSQLite();
    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listNotasFiscais.clear();
                listNotasFiscais.addAll(databaseHelper.getAllUser());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                nfRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
