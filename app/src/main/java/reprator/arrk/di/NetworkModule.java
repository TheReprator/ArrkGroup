package reprator.arrk.di;

import android.app.Application;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import reprator.arrk.BuildConfig;
import reprator.arrk.utility.retrofit2.NetworkUtilsAcceptSelfSignedSslCert;
import timber.log.Timber;

@Module
public class NetworkModule {

    private static final long RETROFIT_CONNECTION_TIME = 20;

    @Provides
    @Singleton
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor loggingInterceptor(boolean isDebug) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        interceptor.setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    @Provides
    @Singleton
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); //10MB Cahe
    }

    @Provides
    @Singleton
    public File cacheFile(Application context) {
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @Provides
    @Singleton
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache,
                                     NetworkUtilsAcceptSelfSignedSslCert selfSignedSslCert) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        builder.connectTimeout(RETROFIT_CONNECTION_TIME, TimeUnit.SECONDS);
        builder.readTimeout(RETROFIT_CONNECTION_TIME, TimeUnit.SECONDS);
        builder.writeTimeout(RETROFIT_CONNECTION_TIME, TimeUnit.SECONDS);

        builder = builder.addInterceptor(loggingInterceptor)
                .cache(cache);

        return selfSignedSslCert.setSelfSignedSSL(builder);
    }

    @Provides
    @Singleton
    public NetworkUtilsAcceptSelfSignedSslCert getNetworkUtilsAcceptSelfSignedSslCert(String host) {
        return new NetworkUtilsAcceptSelfSignedSslCert(host);
    }

    @Provides
    @Singleton
    public String host() {
        return BuildConfig.HOST;
    }
}