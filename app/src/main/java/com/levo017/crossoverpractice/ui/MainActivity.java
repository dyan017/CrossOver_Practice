package com.levo017.crossoverpractice.ui;

import android.support.v4.app.Fragment;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.levo017.crossoverpractice.R;
import com.levo017.crossoverpractice.repositories.ApplicationRepository;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
        import dagger.android.support.DaggerAppCompatActivity;
        import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends DaggerAppCompatActivity implements HasSupportFragmentInjector, MainView {
    @Inject
    ApplicationRepository applicationRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
