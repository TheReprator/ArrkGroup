package reprator.arrk.di;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Locale;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import reprator.arrk.data.remote.ApiInterface;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module(includes = NetworkModule.class)
public class ApiModule {

    @Provides
    @Singleton
    public ApiInterface apiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return objectMapper;
    }

    @Provides
    @Singleton
    public Retrofit retrofit(OkHttpClient okHttpClient, ObjectMapper objectMapper, String host) {
        return new Retrofit.Builder()
                .baseUrl(String.format(Locale.getDefault(), "https://%s/api/", host))
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(okHttpClient)
                .build();
    }

}