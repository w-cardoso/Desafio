package nf.iteris.com.br.iterisapp.ui.antecipation_request;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.model.NfAntecipation;

/**
 * Created by re034850 on 14/12/2017.
 */

public class AntecipationRecyclerAdapter extends RecyclerView.Adapter<AntecipationRecyclerAdapter.AntecipationViewHolder> {
    private List<NfAntecipation> listNotasAntecipation;

    public AntecipationRecyclerAdapter(List<NfAntecipation> listNotasAntecipation) {
        this.listNotasAntecipation = listNotasAntecipation;
    }

    @Override
    public AntecipationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_advanced_request, parent, false);

        return new AntecipationViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AntecipationViewHolder holder, int position) {
        holder.txtStatus.setText("Status" + listNotasAntecipation.get(position).getStatus());
        holder.txtDatePayment.setText("Antecipar para: " + listNotasAntecipation.get(position).getDatePayment());
        holder.txtNotaFiscal.setText("Número NF: " + listNotasAntecipation.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        Log.v(AntecipationRecyclerAdapter.class.getSimpleName(), "" + listNotasAntecipation.size());
        return listNotasAntecipation.size();
    }

    public class AntecipationViewHolder extends RecyclerView.ViewHolder {

        public TextView txtStatus;
        public TextView txtDatePayment;
        public TextView txtNotaFiscal;

        public AntecipationViewHolder(View view) {
            super(view);
            txtStatus = (TextView) view.findViewById(R.id.item_antecipation_txt_status);
            txtDatePayment = (TextView) view.findViewById(R.id.item_antecipation_txt_date_payment);
            txtNotaFiscal = (TextView) view.findViewById(R.id.item_antecipation_txt_nf);
        }
    }

    public String notaFiscal(int position){
        return listNotasAntecipation.get(position).getNumber();
    }
}
