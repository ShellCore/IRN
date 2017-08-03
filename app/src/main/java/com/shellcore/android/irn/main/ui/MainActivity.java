package com.shellcore.android.irn.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shellcore.android.irn.IRNApp;
import com.shellcore.android.irn.R;
import com.shellcore.android.irn.libs.base.ImageLoader;
import com.shellcore.android.irn.lists.IRNListActivity;
import com.shellcore.android.irn.main.MainPresenter;
import com.shellcore.android.irn.main.di.MainComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    // Services
    @Inject
    MainPresenter presenter;
    @Inject
    ImageLoader imageLoader;

    // Components
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_status)
    ImageView imgStatus;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.progress_status)
    ProgressBar progressStatus;
    @BindView(R.id.content)
    CoordinatorLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setupInjection();
        presenter.onCreate();
        presenter.checkIrnValidation();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update:
                presenter.updateIrnList();
                break;
            case R.id.action_show_tables:
                navigateToListScreen();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showStatusImage() {
        imgStatus.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideStatusImage() {
        imgStatus.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressStatus.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressStatus.setVisibility(View.GONE);
    }

    @Override
    public void onIrnTableSucess() {
        imageLoader.load(imgStatus, R.drawable.calendar_good);
    }

    @Override
    public void onGetIrnTablesWarning(String error) {
        imageLoader.load(imgStatus, R.drawable.calendar_warning);
        Snackbar.make(content, error, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onGetIrnTablesError(String error) {
        imageLoader.load(imgStatus, R.drawable.calendar_error);
        Snackbar.make(content, error, Snackbar.LENGTH_SHORT)
                .show();
    }

    private void setupInjection() {
        IRNApp app = (IRNApp) getApplication();
        MainComponent component = app.getMainComponent(this, this);
        component.inject(this);
    }

    private void navigateToListScreen() {
        startActivity(new Intent(this, IRNListActivity.class));
    }
}
