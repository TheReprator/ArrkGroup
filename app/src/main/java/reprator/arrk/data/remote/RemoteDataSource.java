package reprator.arrk.data.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import reprator.arrk.data.DataSource;
import reprator.arrk.data.modal.StarWarProfile;
import reprator.arrk.utility.retrofit2.ResponseResolver;
import retrofit2.Retrofit;


/**
 * The class for fetching data from  API on a background thread and returning data via
 * callbacks on the main UI thread
 */
@Singleton
public class RemoteDataSource implements DataSource {

    private ApiInterface apiInterface;
    private Retrofit retrofit;

    @Inject
    public RemoteDataSource(ApiInterface apiInterface, Retrofit retrofit) {
        this.apiInterface = apiInterface;
        this.retrofit = retrofit;
    }

    @Override
    public void getStarWarCharacters(int nextPage, final GetHttpCallback<StarWarProfile> callback) {
        apiInterface.starWarCharacters(nextPage).enqueue(new ResponseResolver<StarWarProfile>() {
            @Override
            public void success(StarWarProfile starWarProfile) {
                callback.onSuccess(starWarProfile);
            }

            @Override
            public void failure() {
                callback.onFailure();
            }

            @Override
            public void netWorkError(int networkError) {
                callback.onNetworkFailure(networkError);
            }
        });
    }

}