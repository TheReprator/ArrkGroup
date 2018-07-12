package reprator.arrk.ui.characterList.adapter;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import reprator.arrk.data.modal.Characters;
import reprator.arrk.di.scopes.FragmentScoped;
import reprator.arrk.ui.characterList.CharacterItemListener;
import reprator.arrk.ui.characterList.CharacterList;

import static reprator.arrk.controllers.constants.RestConstants.DI_CHARACTER;

@Module
public class CharacterListAdapterModule
{
    @Provides
    @FragmentScoped
    public CharacterListAdapter getStarWarsPeopleLIst(@Named(DI_CHARACTER) List<Characters> charactersList,
                                                      CharacterItemListener characterItemListener) {
        return new CharacterListAdapter(charactersList, characterItemListener);
    }

    @Provides
    @FragmentScoped
    public CharacterItemListener characterItemListenerClickListener(CharacterList characterListFragment) {
        return characterListFragment;
    }
}
