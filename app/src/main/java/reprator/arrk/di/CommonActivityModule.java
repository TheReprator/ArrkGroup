package reprator.arrk.di;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import reprator.arrk.di.scopes.ActivityScoped;

import static reprator.arrk.controllers.constants.RestConstants.DI_ACTIVITY;

@Module
public abstract class CommonActivityModule {
    @Binds
    @ActivityScoped
    abstract Activity activity(AppCompatActivity appCompatActivity);

    @Binds
    @ActivityScoped
    @Named(DI_ACTIVITY)
    abstract Context activityContext(Activity activity);
}