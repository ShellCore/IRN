package com.shellcore.android.irn.main;

/**
 * Created by Cesar on 02/08/2017.
 */

public class MainCheckValidationInteractorImpl implements MainCheckValidationInteractor {

    private MainRepository repository;

    public MainCheckValidationInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.checkIrnValidation();
    }
}
