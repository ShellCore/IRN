package com.shellcore.android.irn.main.di;

import com.shellcore.android.irn.api.IRNClient;
import com.shellcore.android.irn.api.IRNService;
import com.shellcore.android.irn.libs.base.EventBus;
import com.shellcore.android.irn.main.MainCheckValidationInteractor;
import com.shellcore.android.irn.main.MainCheckValidationInteractorImpl;
import com.shellcore.android.irn.main.MainPresenter;
import com.shellcore.android.irn.main.MainPresenterImpl;
import com.shellcore.android.irn.main.MainRepository;
import com.shellcore.android.irn.main.MainRepositoryImpl;
import com.shellcore.android.irn.main.MainUpdateInteractor;
import com.shellcore.android.irn.main.MainUpdateInteractorImpl;
import com.shellcore.android.irn.main.ui.MainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 02/08/2017.
 */

@Module
public class MainModule {
    
    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }
    
    @Provides
    @Singleton
    MainPresenter providesMainPresenter(EventBus eventBus, MainView view, MainCheckValidationInteractor checkInteractor, MainUpdateInteractor updateInteractor) {
        return new MainPresenterImpl(eventBus, view, checkInteractor, updateInteractor);
    }

    @Provides
    @Singleton
    MainView providesMainView() {
        return view;
    }

    @Provides
    @Singleton
    MainCheckValidationInteractor providesMainCheckValidationInteractor(MainRepository repository) {
        return new MainCheckValidationInteractorImpl(repository);
    }

    @Provides
    @Singleton
    MainUpdateInteractor providesMainUpdateInteractor(MainRepository repository) {
        return new MainUpdateInteractorImpl(repository);
    }

    @Provides
    @Singleton
    MainRepository providesMainRepository(EventBus eventBus, IRNService service) {
        return new MainRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    IRNService providesIRNService() {
        return new IRNClient().getIrnService();
    }
}
