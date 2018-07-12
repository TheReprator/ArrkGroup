package reprator.arrk.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import reprator.arrk.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public abstract class ApplicationModule {
    @Binds
    abstract Context bindContext(Application application);

    @Provides
    @Singleton
    static CalligraphyConfig provideCalligraphyDefaultConfig(Context context) {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath(context.getString(R.string.font_os_regular))
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}

