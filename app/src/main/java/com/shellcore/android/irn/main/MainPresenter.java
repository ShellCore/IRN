package com.shellcore.android.irn.main;

import com.shellcore.android.irn.main.events.MainEvent;

/**
 * Created by Cesar on 01/08/2017.
 */

public interface MainPresenter {

    void onCreate();
    void onDestroy();

    void checkIrnValidation();
    void updateIrnList();

    void onEventMainThread(MainEvent event);
}
