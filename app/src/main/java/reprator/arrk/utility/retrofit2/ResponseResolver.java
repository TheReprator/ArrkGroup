package reprator.arrk.utility.retrofit2;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;

import reprator.arrk.controllers.constants.NumericEnums;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static reprator.arrk.controllers.constants.NumericEnums.NO_INTERNET;
import static reprator.arrk.controllers.constants.NumericEnums.PARSING_EXCEPTION;
import static reprator.arrk.controllers.constants.NumericEnums.SOCKET_TIMED_OUT;

public abstract class ResponseResolver<T> implements Callback<T> {

    public abstract void success(T t);

    public abstract void failure();

    public abstract void netWorkError(@NumericEnums.NetworkError int networkError);


    @Override
    public void onResponse(@NonNull Call<T> t, @NonNull Response<T> response) {
        int code = response.code();
        if (HttpURLConnection.HTTP_OK <= code &&
                HttpURLConnection.HTTP_MULT_CHOICE > code) {
            success(response.body());
        } else {
            failure();
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> t, @NonNull Throwable throwable) {
        @NumericEnums.NetworkError int resourceId = PARSING_EXCEPTION;
        if (throwable instanceof IOException) {
            if (throwable instanceof ConnectException)
                resourceId = NO_INTERNET;
            else if (throwable instanceof SocketTimeoutException)
                resourceId = SOCKET_TIMED_OUT;
            netWorkError(resourceId);
        } else {
            failure();
        }
    }

}

