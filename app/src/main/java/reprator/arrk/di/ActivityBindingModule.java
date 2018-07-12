package reprator.arrk.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import reprator.arrk.di.scopes.ActivityScoped;
import reprator.arrk.ui.StarWarModule;
import reprator.arrk.ui.StarWars;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = StarWarModule.class)
    abstract StarWars starWarsActivity();
}
