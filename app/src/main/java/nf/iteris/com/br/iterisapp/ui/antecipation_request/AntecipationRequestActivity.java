package nf.iteris.com.br.iterisapp.ui.antecipation_request;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
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
        getSupportActionBar().setTitle("Antecipação de Notas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initObjects();

        rcv.addOnItemTouchListener(new RecyclerItemClickListener(context, rcv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                nf = antecipationRecyclerAdapter.notaFiscal(position);

                final Dialog dialogg = new Dialog(AntecipationRequestActivity.this);

                dialogg.setContentView(R.layout.dialog_aprov_reprov);
                txtTitle = (TextView) dialogg.findViewById(R.id.dialog_aprov_reprov_txt_title);
                btnReprovar = (Button) dialogg.findViewById(R.id.dialog_aprov_reprov_btn_reprovar);
                btnAprovar = (Button) dialogg.findViewById(R.id.dialog_aprov_reprov_btn_aprovar);

                btnReprovar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final AlertDialog.Builder builder = new AlertDialog.Builder(AntecipationRequestActivity.this);
                        builder.setTitle("Gostaria de infomar uma nova data?");

                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        });
                        builder.setNeutralButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });


                        AlertDialog dialog = builder.create();
                        dialog.show();

                        dialogg.dismiss();
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

                dialogg.show();

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
