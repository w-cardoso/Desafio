package nf.iteris.com.br.iterisapp.ui.antecipation_request;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.dao.nf_anticipation.NfAntecipationDao;
import nf.iteris.com.br.iterisapp.model.NfAntecipation;
import nf.iteris.com.br.iterisapp.util.RecyclerItemClickListener;

public class AntecipationRequestActivity extends AppCompatActivity {

    private AppCompatActivity activity = AntecipationRequestActivity.this;
    private RecyclerView rcv;
    private List<NfAntecipation> listNotasAntecipation;
    private AntecipationRecyclerAdapter antecipationRecyclerAdapter;
    private NfAntecipationDao dataAntecipation;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_request);
        initObjects();

        rcv.addOnItemTouchListener(new RecyclerItemClickListener(context, rcv, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final Dialog dialog = new Dialog(AntecipationRequestActivity.this);

                dialog.setContentView(R.layout.dialog_aprov_reprov);

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
        dataAntecipation = new NfAntecipationDao(activity);


        getDataFromSQLite();
    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listNotasAntecipation.clear();
                listNotasAntecipation.addAll(dataAntecipation.getAllUser());

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
