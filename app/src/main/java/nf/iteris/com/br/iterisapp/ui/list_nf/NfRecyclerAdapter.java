package nf.iteris.com.br.iterisapp.ui.list_nf;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nf.iteris.com.br.iterisapp.R;
import nf.iteris.com.br.iterisapp.model.NfRegistration;

/**
 * Created by re034850 on 13/12/2017.
 */

public class NfRecyclerAdapter extends RecyclerView.Adapter<NfRecyclerAdapter.NfViewHolder> implements Filterable{
    private List<NfRegistration> itemList;
    private List<NfRegistration> filterListNotasFicais;

    public NfRecyclerAdapter(List<NfRegistration> listNotasFicais) {
        this.filterListNotasFicais = listNotasFicais;
        this.itemList = listNotasFicais;
    }

    @Override
    public NfViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nf_registration, parent, false);

        return new NfViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NfViewHolder holder, int position) {
        holder.txtNumberNf.setText(filterListNotasFicais.get(position).getNumber());
        holder.txtDescription.setText(filterListNotasFicais.get(position).getDescription());
        holder.txtDatePayment.setText(filterListNotasFicais.get(position).getDatePayment());
        holder.txtStatus.setText(filterListNotasFicais.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        if (filterListNotasFicais != null) {
            return filterListNotasFicais.size();
        }
        return 0;

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

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                filterListNotasFicais = (List<NfRegistration>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<NfRegistration> FilteredArray = new ArrayList<NfRegistration>();
                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < itemList.size(); i++) {
                    NfRegistration item = itemList.get(i);
                    String dataValue = item.getNumber().toLowerCase();
                    if (dataValue.toLowerCase().contains(constraint.toString())) {
                        FilteredArray.add(item);
                    }
                }
                results.count = FilteredArray.size();
                results.values = FilteredArray;

                //setEmptyMessage(FilteredArray.size());

                return results;
            }
        };
        return filter;
    }

}
