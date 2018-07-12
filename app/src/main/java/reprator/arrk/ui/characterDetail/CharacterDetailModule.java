package reprator.arrk.ui.characterDetail;

import android.os.Bundle;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import reprator.arrk.controllers.constants.RestConstants;
import reprator.arrk.data.modal.Characters;
import reprator.arrk.di.scopes.FragmentScoped;
import reprator.arrk.utility.dateUtility.DateUtils;
import reprator.arrk.utility.dateUtility.DateUtilsImpl;

@Module
public abstract class CharacterDetailModule {

    @FragmentScoped
    @Provides
    static Characters provideCharacterDetail(CharacterDetail detailFragment){
        Bundle bundle = detailFragment.getArguments();
        return bundle.getParcelable(RestConstants.BUNDLE_MSG);
    }

    @FragmentScoped
    @Provides
    static DateUtils provideDateImplementation()
    {
        return new DateUtilsImpl();
    }

    @FragmentScoped
    @Binds
    abstract CharacterDetailContract.View characterListView(CharacterDetail characterDetailFragment);

    @FragmentScoped
    @Binds
    abstract CharacterDetailContract.Presenter characterDetailPresenter(CharacterDetailPresenter characterDetailPresenter);
}
