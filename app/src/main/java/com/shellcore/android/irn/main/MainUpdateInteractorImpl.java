package com.shellcore.android.irn.main;

/**
 * Created by Cesar on 02/08/2017.
 */

public class MainUpdateInteractorImpl implements MainUpdateInteractor {

    private MainRepository repository;

    public MainUpdateInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.updateIrnList();
    }
}
