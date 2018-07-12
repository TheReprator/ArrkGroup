package reprator.arrk.appScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import reprator.arrk.data.DataSource;
import reprator.arrk.data.remote.ApiInterface;
import reprator.arrk.data.remote.RemoteDataSource;
import reprator.arrk.di.ApiModule;
import retrofit2.Retrofit;

@Module(includes = ApiModule.class)
public class TaskRepositoryModule {
    @Singleton
    @Provides
    DataSource provideDataSource(ApiInterface apiInterface, Retrofit retrofit) {
        return new RemoteDataSource(apiInterface, retrofit);
    }

}
