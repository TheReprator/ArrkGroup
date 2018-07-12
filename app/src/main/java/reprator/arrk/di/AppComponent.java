package reprator.arrk.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import reprator.arrk.appScope.AppGlobal;
import reprator.arrk.appScope.TaskRepositoryModule;
import reprator.arrk.data.DataRepository;

@Singleton
@Component(modules = {TaskRepositoryModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<AppGlobal> {

    DataRepository getDataRepository();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
