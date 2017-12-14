package nf.iteris.com.br.iterisapp.ui.antecipation_request;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nf.iteris.com.br.iterisapp.R;

/**
 * Created by re034850 on 14/12/2017.
 */

public class AntecipationRecyclerAdapter extends RecyclerView.Adapter<AntecipationRecyclerAdapter.AntecipationViewHolder> {

    @Override
    public AntecipationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AntecipationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AntecipationViewHolder extends RecyclerView.ViewHolder {

        public TextView txtStatus;
        public TextView txtDatePayment;
        public TextView txtNotaFiscal;

        public AntecipationViewHolder(View view) {
            super(view);
            txtStatus = (TextView) view.findViewById(R.id.item_antecipation_txt_status);
            txtDatePayment = (TextView) view.findViewById(R.id.item_antecipation_txt_date_payment);
            txtNotaFiscal = (TextView) view.findViewById(R.id.item_antecipation_txt_status);
        }
    }
}
