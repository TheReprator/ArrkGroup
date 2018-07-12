package reprator.arrk.ui.characterDetail;

import javax.inject.Inject;

import reprator.arrk.data.modal.Characters;
import reprator.arrk.di.scopes.FragmentScoped;
import reprator.arrk.utility.Validation;
import reprator.arrk.utility.dateUtility.DateUtils;
import reprator.arrk.utility.mvp.BasePresenter;

import static reprator.arrk.utility.dateUtility.DateUtils.FOR_RESERVATION_FORMAT_FULL_YEAR;
import static reprator.arrk.utility.dateUtility.DateUtils.FOR_SERVER_CONSTANT;

@FragmentScoped
class CharacterDetailPresenter extends BasePresenter<CharacterDetailContract.View> implements
        CharacterDetailContract.Presenter {

    @Inject
    Characters characterDetail;

    @Inject
    DateUtils dateUtils;

    @Inject
    CharacterDetailPresenter(CharacterDetailContract.View view)
    {
        this.view = view;
    }

    @Override
    public void showDetailsOfCharacter() {
        view.setName(characterDetail.getName());
        findCharcterHeight(characterDetail.getHeight());
        findCharacterWeight(characterDetail.getMass());
        convertStringDateTime(characterDetail.getCreated());
    }

    void findCharcterHeight(String charcterHeight)
    {
        double height = 0;
        if(Validation.isEmptyorNull(charcterHeight))
            try {
                height = Double.parseDouble(charcterHeight);
                height = height/100;
            }catch (Exception e)
            {
                height = 0;
            }
        view.setHeight(height);
    }

    void findCharacterWeight(String characterWeight)
    {
        double weight = 0 ;

        if(Validation.isEmptyorNull(characterWeight))
            try {
                weight = Double.parseDouble(characterWeight);
            }catch (Exception e)
            {
                weight = 0;
            }
        view.setWeight(weight);
    }

    void convertStringDateTime(String dateTime)
    {
        if(Validation.isNull(dateTime))
            view.setCreationDateTime("");
        else
        {
            String creationDateTime = dateUtils.format(dateTime,
                    FOR_SERVER_CONSTANT, FOR_RESERVATION_FORMAT_FULL_YEAR);
            view.setCreationDateTime(creationDateTime);
        }
    }
}
