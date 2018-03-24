package com.levo017.crossoverpractice.old;

import com.levo017.crossoverpractice.injection.AppModule;
import com.levo017.crossoverpractice.injection.ApplicationComponent;
import com.levo017.crossoverpractice.ui.MainView;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Created by dyan017 on 3/15/2018.
 */

public class JUnitDaggerMockRule extends DaggerMockRule<ApplicationComponent> {
    public JUnitDaggerMockRule() {
        super(ApplicationComponent.class, AppModule.class);
        providesMock(MainView.class);
    }
}

