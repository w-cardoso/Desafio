package nf.iteris.com.br.iterisapp.ui.antecipation_request;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.dao.nf_anticipation.NfAntecipationDao;
import nf.iteris.com.br.iterisapp.dao.nf_registration.NfRegistrationDao;
import nf.iteris.com.br.iterisapp.model.NfAntecipation;
import nf.iteris.com.br.iterisapp.model.NfRegistration;
import nf.iteris.com.br.iterisapp.ui.home_screen.HomeScreenActivity;
import nf.iteris.com.br.iterisapp.ui.list_nf.ListNfActivity;
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
    TextInputEditText edtNumberNf;
    TextInputEditText edtDescription;
    TextInputEditText edtDateBilling;
    TextInputEditText edtDatePayment;
    TextInputLayout tilNumberNf;
    TextInputLayout tilDescription;
    TextInputLayout tilDateBilling;
    TextInputLayout tilDatePayment;
    Button btnCancel;
    Button btnAntecipe;
    DatePickerDialog datePickerDialog;
    String date;


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
                                nfAntecipation = dataAntecipationDao.pegarDados(nf);
                                final Dialog dialogDados = new Dialog(AntecipationRequestActivity.this);
                                dialogDados.setTitle("Descrição");
                                dialogDados.setContentView(R.layout.dialog_rcv_item);

                                edtNumberNf = (TextInputEditText) dialogDados.findViewById(R.id.dialog_rcv_edt_numbernf);
                                edtDescription = (TextInputEditText) dialogDados.findViewById(R.id.dialog_rcv_edt_description);
                                edtDateBilling = (TextInputEditText) dialogDados.findViewById(R.id.dialog_rcv_edt_date_billing);
                                edtDatePayment = (TextInputEditText) dialogDados.findViewById(R.id.dialog_rcv_edt_date_payment);
                                tilNumberNf = (TextInputLayout) dialogDados.findViewById(R.id.dialog_rcv_til_numbernf);
                                tilDescription = (TextInputLayout) dialogDados.findViewById(R.id.dialog_rcv_til_description);
                                tilDateBilling = (TextInputLayout) dialogDados.findViewById(R.id.dialog_rcv_til_date_billing);
                                tilDatePayment = (TextInputLayout) dialogDados.findViewById(R.id.dialog_rcv_til_date_payment);
                                btnCancel = (Button) dialogDados.findViewById(R.id.dialog_rcv_btn_cancelar);
                                btnAntecipe = (Button) dialogDados.findViewById(R.id.dialog_rcv_btn_antecipar);

                                edtNumberNf.setText(nfAntecipation.getNumber());
                                edtNumberNf.setFocusableInTouchMode(false);


                                edtDescription.setText(nfAntecipation.getDescription());
                                edtDescription.setFocusableInTouchMode(false);

                                edtDateBilling.setText(nfAntecipation.getDateBilling());
                                edtDateBilling.setFocusableInTouchMode(false);

                                edtDatePayment.setText(nfAntecipation.getDatePayment());
                                date = edtDatePayment.toString();
                                edtDatePayment.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        final Calendar c = Calendar.getInstance();
                                        int mYear = c.get(Calendar.YEAR); // current year
                                        int mMonth = c.get(Calendar.MONTH); // current month
                                        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                                        datePickerDialog = new DatePickerDialog(AntecipationRequestActivity.this,
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


                                btnAntecipe.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        nfAntecipation = dataAntecipationDao.pegarDados(nf);
                                        notaFiscal.setNumber(nfAntecipation.getNumber().toString());
                                        notaFiscal.setDescription(nfAntecipation.getDescription().toString());
                                        notaFiscal.setDateBilling(nfAntecipation.getDateBilling().toString());
                                        notaFiscal.setDatePayment(edtDatePayment.getText().toString());
                                        notaFiscal.setStatus(nfAntecipation.getStatus().toString());
                                        databaseHelper.addNotaFiscal(notaFiscal);

                                        String n = nfAntecipation.getNumber().toString();
                                        dataAntecipationDao.deleteNf(n);
                                        startActivity(new Intent(activity, AntecipationRequestActivity.class));
                                        Toast.makeText(activity, "Antecipação confirmada", Toast.LENGTH_LONG).show();
                                        finish();
                                    }
                                });

                                btnCancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialogDados.dismiss();
                                        finish();

                                    }
                                });


                                dialogDados.show();

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

    //Inicialização ods xmls
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
        // AsyncTask   é usado para que a operação SQLite não bloqueie a UI Thread.
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

    // botão voltar na Toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(activity, HomeScreenActivity.class));
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    //Enviar dados banco de dados
    private void postDataToSQLite() {
        if (!edtDatePayment.getText().toString().trim().equals(date)) {


            notaFiscal.setNumber(edtNumberNf.getText().toString().trim());
            notaFiscal.setDescription(edtDescription.getText().toString().trim());
            notaFiscal.setDateBilling(edtDateBilling.getText().toString().trim());
            notaFiscal.setDatePayment(edtDatePayment.getText().toString().trim());
            notaFiscal.setStatus("Antecipado dado do Gestor");


            databaseHelper.addNotaFiscal(notaFiscal);


            Toast.makeText(activity, "Solicitação enviada com sucesso", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(activity, "A data não pode ser a mesma", Toast.LENGTH_LONG).show();
        }

    }
    //Limpar campos
    private void emptyInputEditText() {
        edtNumberNf.setText(null);
        edtDescription.setText(null);
        edtDateBilling.setText(null);
        edtDatePayment.setText(null);

    }
}
