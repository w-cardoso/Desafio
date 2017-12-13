package nf.iteris.com.br.iterisapp.ui.list_nf;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.model.NfRegistration;

/**
 * Created by re034850 on 13/12/2017.
 */

public class NfRecyclerAdapter extends RecyclerView.Adapter<NfRecyclerAdapter.NfViewHolder> {

    private List<NfRegistration> listNotasFicais;

    public NfRecyclerAdapter(List<NfRegistration> listNotasFicais) {
        this.listNotasFicais = listNotasFicais;
    }

    @Override
    public NfViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nf_registration, parent, false);

        return new NfViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NfViewHolder holder, int position) {
        holder.txtNumberNf.setText(listNotasFicais.get(position).getNumber());
        holder.txtDescription.setText(listNotasFicais.get(position).getDescription());
        holder.txtDatePayment.setText(listNotasFicais.get(position).getDatePayment());
        holder.txtStatus.setText(listNotasFicais.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        Log.v(NfRecyclerAdapter.class.getSimpleName(), "" + listNotasFicais.size());
        return listNotasFicais.size();
    }

    public class NfViewHolder extends RecyclerView.ViewHolder {

        public TextView txtNumberNf;
        public TextView txtDescription;
        public TextView txtDatePayment;
        public TextView txtStatus;


        public NfViewHolder(View view) {
            super(view);
            txtNumberNf = view.findViewById(R.id.item_nf_registration_txt_number_nf);
            txtDescription = view.findViewById(R.id.item_nf_registration_txt_description);
            txtDatePayment = view.findViewById(R.id.item_nf_registration_txt_date_payment);
            txtStatus = view.findViewById(R.id.item_nf_registration_txt_status);
        }
    }

}
