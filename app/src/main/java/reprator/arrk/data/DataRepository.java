package reprator.arrk.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import reprator.arrk.data.modal.StarWarProfile;

@Singleton()
public class DataRepository {

    private final DataSource remoteDataSource;

    @Inject
    DataRepository(DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public void getStarWarCharacters(int nextPage, final DataSource.GetHttpCallback<StarWarProfile> callback) {
        remoteDataSource.getStarWarCharacters(nextPage, new DataSource.GetHttpCallback<StarWarProfile>() {
            @Override
            public void onSuccess(StarWarProfile starWarProfile) {
                callback.onSuccess(starWarProfile);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }

            @Override
            public void onNetworkFailure(int stringResource) {
                callback.onNetworkFailure(stringResource);
            }
        });
    }

}