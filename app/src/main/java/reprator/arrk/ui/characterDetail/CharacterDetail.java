package reprator.arrk.ui.characterDetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;
import reprator.arrk.R;
import reprator.arrk.utility.ContextFunction;

import static reprator.arrk.controllers.constants.RestConstants.DI_ACTIVITY;

public class CharacterDetail extends DaggerFragment implements  CharacterDetailContract.View
{
    @Inject
    @Named(DI_ACTIVITY)
    Context context;

    @Inject
    CharacterDetailContract.Presenter presenter;

    private TextView tvCharacterName;
    private TextView tvCharacterHeight;
    private TextView tvCharacterWeight;
    private TextView tvCharacterDateTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.starwar_character_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCharacterName = ContextFunction.findViewByIdAndCast(view, R.id.scd_tv_name);
        tvCharacterHeight = ContextFunction.findViewByIdAndCast(view, R.id.scd_tv_height);
        tvCharacterWeight = ContextFunction.findViewByIdAndCast(view, R.id.scd_tv_weight);
        tvCharacterDateTime = ContextFunction.findViewByIdAndCast(view, R.id.scd_tv_creationDate);

        presenter.showDetailsOfCharacter();
    }

    @Override
    public void setName(String name) {
        tvCharacterName.setText(name);
    }

    @Override
    public void setHeight(double heightInMetres) {
        tvCharacterHeight.setText(getString(R.string.cd_height, heightInMetres));
    }

    @Override
    public void setWeight(double weight) {
        tvCharacterWeight.setText(getString(R.string.cd_weight, weight));
    }

    @Override
    public void setCreationDateTime(String creationDateTime) {
        tvCharacterDateTime.setText(creationDateTime);
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void showToast(String message) {
        ContextFunction.showToast(context, message);
    }

    @Override
    public void setProgressBar(boolean show) {
    }

}
