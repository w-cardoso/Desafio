package nf.iteris.com.br.iterisapp.ui.antecipation_request;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.dao.nf_anticipation.NfAntecipationDao;
import nf.iteris.com.br.iterisapp.dao.nf_registration.NfRegistrationDao;
import nf.iteris.com.br.iterisapp.model.NfAntecipation;
import nf.iteris.com.br.iterisapp.model.NfRegistration;
import nf.iteris.com.br.iterisapp.util.RecyclerItemClickListener;

public class AntecipationRequestActivity extends AppCompatActivity {

    private AppCompatActivity activity = AntecipationRequestActivity.this;
    private RecyclerView rcv;
    private List<NfAntecipation> listNotasAntecipation;
    private AntecipationRecyclerAdapter antecipationRecyclerAdapter;
    private NfAntecipationDao dataAntecipationDao;
    private Context context;
    private TextView txtTitle;
    private Button btnReprovar;
    private Button btnAprovar;
    private NfRegistrationDao databaseHelper;
    private NfRegistration notaFiscal;
    private NfAntecipation nfAntecipation;
    String nf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_request);
        initObjects();

        rcv.addOnItemTouchListener(new RecyclerItemClickListener(context, rcv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                nf = antecipationRecyclerAdapter.notaFiscal(position);

                final Dialog dialog = new Dialog(AntecipationRequestActivity.this);

                dialog.setContentView(R.layout.dialog_aprov_reprov);
                txtTitle = (TextView) dialog.findViewById(R.id.dialog_aprov_reprov_txt_title);
                btnReprovar = (Button) dialog.findViewById(R.id.dialog_aprov_reprov_btn_reprovar);
                btnAprovar = (Button) dialog.findViewById(R.id.dialog_aprov_reprov_btn_aprovar);

                btnReprovar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        dialog.dismiss();
                    }
                });

                btnAprovar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        nfAntecipation = dataAntecipationDao.pegarDados(nf);
                        notaFiscal.setNumber(nfAntecipation.getNumber().toString());
                        notaFiscal.setDescription(nfAntecipation.getDescription().toString());
                        notaFiscal.setDateBilling(nfAntecipation.getDateBilling().toString());
                        notaFiscal.setDatePayment(nfAntecipation.getDatePayment().toString());
                        notaFiscal.setStatus(nfAntecipation.getStatus().toString());
                        databaseHelper.addNotaFiscal(notaFiscal);

                        String n = nfAntecipation.getNumber().toString();
                        dataAntecipationDao.deleteNf(n);
                        startActivity(new Intent(activity, AntecipationRequestActivity.class));
                        Toast.makeText(activity, "Antecipação confirmada", Toast.LENGTH_LONG).show();
                    }
                });

                dialog.show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


    }

    private void initObjects() {
        rcv = (RecyclerView) findViewById(R.id.advanced_request_recycler);
        listNotasAntecipation = new ArrayList<>();
        antecipationRecyclerAdapter = new AntecipationRecyclerAdapter(listNotasAntecipation);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcv.setLayoutManager(mLayoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator());
        rcv.setHasFixedSize(true);
        rcv.setAdapter(antecipationRecyclerAdapter);
        dataAntecipationDao = new NfAntecipationDao(activity);
        databaseHelper = new NfRegistrationDao(activity);
        notaFiscal = new NfRegistration();
        nfAntecipation = new NfAntecipation();


        getDataFromSQLite();
    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listNotasAntecipation.clear();
                listNotasAntecipation.addAll(dataAntecipationDao.getAllUser());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                antecipationRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
