package com.shellcore.android.irn.main.ui;

/**
 * Created by Cesar on 01/08/2017.
 */

public interface MainView {
    void showStatusImage();
    void hideStatusImage();
    void showProgressBar();
    void hideProgressBar();

    void onIrnTableSucess();
    void onGetIrnTablesWarning(String error);
    void onGetIrnTablesError(String error);
}
