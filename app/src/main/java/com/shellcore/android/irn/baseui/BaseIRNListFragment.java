package com.shellcore.android.irn.baseui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shellcore.android.irn.R;
import com.shellcore.android.irn.baseui.adapters.BaseIRNAdapter;
import com.shellcore.android.irn.db.entities.Afore;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseIRNListFragment extends Fragment {

    // Variables
    private List<Afore> list;
    private int color;
    private String title;
    private String subtitle;

    private BaseIRNAdapter adapter;

    // Components
    @BindView(R.id.lnr_irn_table)
    LinearLayout lnrIrnTable;
    @BindView(R.id.txt_inr_title)
    TextView txtInrTitle;
    @BindView(R.id.txt_inr_subtitle)
    TextView txtInrSubtitle;
    @BindView(R.id.rec_list)
    RecyclerView recList;

    public BaseIRNListFragment() {
        // Required empty public constructor
    }

    public void setParameters(List<Afore> list, int color, String title, String subtitle) {
        this.list = list;
        this.color = color;
        this.title = title;
        this.subtitle = subtitle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_base_irnlist, container, false);
        ButterKnife.bind(this, v);

        setupRecyclerView();
        setupData();

        return v;
    }

    private void setupRecyclerView() {
        adapter = new BaseIRNAdapter();
        adapter.setList(list);
        recList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recList.setAdapter(adapter);
    }

    private void setupData() {
        txtInrTitle.setText(title);
        txtInrSubtitle.setText(subtitle);
        lnrIrnTable.setBackgroundResource(color);
    }
}
