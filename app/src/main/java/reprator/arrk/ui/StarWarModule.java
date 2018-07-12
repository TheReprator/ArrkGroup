package reprator.arrk.ui;

import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import reprator.arrk.di.CommonActivityModule;
import reprator.arrk.di.scopes.ActivityScoped;
import reprator.arrk.di.scopes.FragmentScoped;
import reprator.arrk.ui.characterDetail.CharacterDetail;
import reprator.arrk.ui.characterDetail.CharacterDetailModule;
import reprator.arrk.ui.characterList.CharacterList;
import reprator.arrk.ui.characterList.CharacterModule;

@Module(includes = CommonActivityModule.class)
public abstract class StarWarModule {

    @Binds
    @ActivityScoped
    abstract AppCompatActivity appCompatActivity(StarWars starWars);

    @FragmentScoped
    @ContributesAndroidInjector(modules = CharacterModule.class)
    abstract CharacterList characterListFragment();

    @FragmentScoped
    @ContributesAndroidInjector(modules = CharacterDetailModule.class)
    abstract CharacterDetail characterDetailFragment();
}
