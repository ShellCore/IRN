package com.shellcore.android.irn.lists;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.shellcore.android.irn.R;
import com.shellcore.android.irn.baseui.BaseIRNListFragment;
import com.shellcore.android.irn.db.entities.Afore;
import com.shellcore.android.irn.db.entities.Afore_Table;
import com.shellcore.android.irn.lists.adapter.ListSectionsPagerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IRNListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irnlist);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setupAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void setupAdapter() {
        String[] titles = new String[] {
                "SB4",
                "SB3",
                "SB2",
                "SB1"
        };
        Fragment[] fragments = getListFragments();

        ListSectionsPagerAdapter adapter = new ListSectionsPagerAdapter(getFragmentManager(), titles, fragments);

        container.setAdapter(adapter);
        tabs.setupWithViewPager(container);
    }

    private Fragment[] getListFragments() {
        BaseIRNListFragment sb4 = new BaseIRNListFragment();
        BaseIRNListFragment sb3 = new BaseIRNListFragment();
        BaseIRNListFragment sb2 = new BaseIRNListFragment();
        BaseIRNListFragment sb1 = new BaseIRNListFragment();

        sb4.setParameters(getAforeList(4), R.color.colorIrn4, getString(R.string.sb4), getString(R.string.sb4_range));
        sb3.setParameters(getAforeList(3), R.color.colorIrn3, getString(R.string.sb3), getString(R.string.sb3_range));
        sb2.setParameters(getAforeList(2), R.color.colorIrn2, getString(R.string.sb2), getString(R.string.sb2_range));
        sb1.setParameters(getAforeList(1), R.color.colorIrn1, getString(R.string.sb1), getString(R.string.sb1_range));

        return new Fragment[] {
                sb4,
                sb3,
                sb2,
                sb1
        };
    }

    private List<Afore> getAforeList(int i) {
        List<Afore> list = SQLite.select()
                .from(Afore.class)
                .where(Afore_Table.numeroHoja.eq(i))
                .orderBy(Afore_Table.rendimientoNeto, false)
                .queryList();

        return list;
    }
}
