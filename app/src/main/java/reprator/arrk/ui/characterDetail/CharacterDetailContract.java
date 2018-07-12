package reprator.arrk.ui.characterDetail;

import reprator.arrk.ui.characterList.CharacterListContract;
import reprator.arrk.utility.mvp.IBasePresenter;
import reprator.arrk.utility.mvp.IBaseView;

public interface CharacterDetailContract
{
    interface View extends IBaseView {
        void setName(String name);
        void setHeight(double heightInMetres);
        void setWeight(double weight);
        void setCreationDateTime(String creationDateTime);
    }

    interface Presenter extends IBasePresenter<View> {
        void showDetailsOfCharacter();
    }
}
