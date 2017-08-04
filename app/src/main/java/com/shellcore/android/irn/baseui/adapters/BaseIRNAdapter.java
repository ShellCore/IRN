package com.shellcore.android.irn.baseui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shellcore.android.irn.R;
import com.shellcore.android.irn.db.entities.Afore;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cesar on 03/08/2017.
 */

public class BaseIRNAdapter extends RecyclerView.Adapter<BaseIRNAdapter.ViewHolder> {

    private List<Afore> list;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_afore, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Afore afore = list.get(position);
        holder.txtAforeName.setText(afore.getDescripcionCorta());
        holder.txtAforeRendimiento.setText(afore.getStrRendimientoNeto());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Afore> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_afore_name)
        TextView txtAforeName;
        @BindView(R.id.txt_afore_rendimiento)
        TextView txtAforeRendimiento;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
