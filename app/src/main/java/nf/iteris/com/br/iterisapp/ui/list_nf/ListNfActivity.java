package nf.iteris.com.br.iterisapp.ui.list_nf;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.dao.nf_registration.NfRegistrationDao;
import nf.iteris.com.br.iterisapp.model.NfRegistration;
import nf.iteris.com.br.iterisapp.ui.nf_registration.NfRegistrationActivity;

public class ListNfActivity extends AppCompatActivity {
    private AppCompatActivity activity = ListNfActivity.this;
    private RecyclerView recyclerViewNfs;
    private List<NfRegistration> listNotasFiscais;
    private NfRecyclerAdapter nfRecyclerAdapter;
    private NfRegistrationDao databaseHelper;
    private EditText edtSearch;
    private RecyclerView rcvListNfs;
    private Button btnRegisterNfs;


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
