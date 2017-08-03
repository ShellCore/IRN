package com.shellcore.android.irn.main.di;

import com.shellcore.android.irn.MainActivity;
import com.shellcore.android.irn.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cesar on 02/08/2017.
 */

@Singleton
@Component(modules = {MainModule.class, LibsModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
