package com.shellcore.android.irn.main;

import com.shellcore.android.irn.libs.base.EventBus;
import com.shellcore.android.irn.main.events.MainEvent;
import com.shellcore.android.irn.main.ui.MainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 02/08/2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private EventBus eventBus;
    private MainView view;
    private MainCheckValidationInteractor checkInteractor;
    private MainUpdateInteractor updateInteractor;

    public MainPresenterImpl(EventBus eventBus, MainView view, MainCheckValidationInteractor checkInteractor, MainUpdateInteractor updateInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.checkInteractor = checkInteractor;
        this.updateInteractor = updateInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void checkIrnValidation() {
        if (view != null) {
            view.hideStatusImage();
            view.showProgressBar();
        }
        checkInteractor.execute();
    }

    @Override
    public void updateIrnList() {
        if (view != null) {
            view.hideStatusImage();
            view.showProgressBar();
        }
        updateInteractor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(MainEvent event) {
        if (view != null) {
            view.hideProgressBar();
            view.showStatusImage();
            switch (event.getType()) {
                case MainEvent.DOWNLOAD_ERROR:
                case MainEvent.QUERY_ERROR:
                    view.onGetIrnTablesError(event.getError());
                    break;
                case MainEvent.VALIDATION_ERROR:
                    view.onGetIrnTablesWarning(event.getError());
                    break;
                case MainEvent.SUCCESS :
                case MainEvent.VALIDATION_SUCCESS:
                    view.onIrnTableSucess();
                    break;
            }
        }
    }
}
