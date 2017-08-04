package com.shellcore.android.irn;

import android.app.Activity;
import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.shellcore.android.irn.libs.di.LibsModule;
import com.shellcore.android.irn.main.di.DaggerMainComponent;
import com.shellcore.android.irn.main.di.MainComponent;
import com.shellcore.android.irn.main.di.MainModule;
import com.shellcore.android.irn.main.ui.MainView;

/**
 * Created by Cesar on 01/08/2017.
 */

public class IRNApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setupDatabase();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        tearDownDatabase();
    }

    private void setupDatabase() {
        FlowManager.init(this);
    }

    private void tearDownDatabase() {
        FlowManager.destroy();
    }

    public MainComponent getMainComponent(Activity activity, MainView view) {
        return DaggerMainComponent.builder()
                .libsModule(new LibsModule(activity))
                .mainModule(new MainModule(view))
                .build();
    }
}
