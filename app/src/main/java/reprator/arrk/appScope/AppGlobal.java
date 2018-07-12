package reprator.arrk.appScope;

import android.content.Context;
import android.support.multidex.MultiDex;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import reprator.arrk.data.DataRepository;
import reprator.arrk.di.DaggerAppComponent;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppGlobal extends DaggerApplication {

    @Inject
    DataRepository tasksRepository;

    @Inject
    CalligraphyConfig mCalligraphyConfig;


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}