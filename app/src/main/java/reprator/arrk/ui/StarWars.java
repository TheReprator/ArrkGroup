package reprator.arrk.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import dagger.android.support.DaggerAppCompatActivity;
import reprator.arrk.R;
import reprator.arrk.controllers.constants.RestConstants;
import reprator.arrk.data.modal.Characters;
import reprator.arrk.ui.characterDetail.CharacterDetail;
import reprator.arrk.ui.characterList.CharacterList;
import reprator.arrk.utility.ContextFunction;
import reprator.arrk.utility.Intents;

public class StarWars extends DaggerAppCompatActivity implements ChangeFragment{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starwars);

        Toolbar toolbar = ContextFunction.findViewByIdAndCast(this, R.id.sw_lay_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        Intents.replaceFragment(this, R.id.sw_lay_container, new CharacterList());
    }

    @Override
    public void showStarWarCharacterDetail(Characters starWarCharacter) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RestConstants.BUNDLE_MSG, starWarCharacter);
        Intents.replaceFragmentBackStack(this, R.id.sw_lay_container, new CharacterDetail(), bundle);
    }
}
