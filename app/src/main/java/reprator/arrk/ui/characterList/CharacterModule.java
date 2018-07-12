package reprator.arrk.ui.characterList;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import reprator.arrk.data.modal.Characters;
import reprator.arrk.di.scopes.FragmentScoped;
import reprator.arrk.ui.ChangeFragment;
import reprator.arrk.ui.characterList.adapter.CharacterListAdapterModule;

import static reprator.arrk.controllers.constants.RestConstants.DI_ACTIVITY;
import static reprator.arrk.controllers.constants.RestConstants.DI_CHARACTER;

@Module(includes = CharacterListAdapterModule.class)
public abstract class CharacterModule {
    @FragmentScoped
    @Binds
    abstract CharacterListContract.Presenter characterListPresenter(CharacterListPresenter presenter);

    @FragmentScoped
    @Binds
    abstract CharacterListContract.View characterListView(CharacterList loginFragment);

    @FragmentScoped
    @Provides
    static ChangeFragment changeFragment(@Named(DI_ACTIVITY) Context context) {
        return (ChangeFragment) context;
    }

    @Named(DI_CHARACTER)
    @Provides
    @FragmentScoped
    static List<Characters> providesCharacterList() {
        return new ArrayList<Characters>();
    }
}
