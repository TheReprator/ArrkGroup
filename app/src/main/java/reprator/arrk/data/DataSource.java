package reprator.arrk.data;

import android.support.annotation.StringRes;

import reprator.arrk.data.modal.StarWarProfile;

public interface DataSource {

    void getStarWarCharacters(int nextPage, GetHttpCallback<StarWarProfile> callback);

    interface GetHttpCallback<T> {
        void onSuccess(T data);

        void onFailure();

        void onNetworkFailure(@StringRes int stringResource);
    }
}