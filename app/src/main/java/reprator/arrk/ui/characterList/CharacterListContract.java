package reprator.arrk.ui.characterList;


import java.util.List;

import reprator.arrk.data.modal.Characters;
import reprator.arrk.utility.mvp.IBasePresenter;
import reprator.arrk.utility.mvp.IBaseView;

public interface CharacterListContract {
    interface View extends IBaseView {
        void setCharacterListAdapter(List<Characters> characterListAdapter);

        void notifyForItemRangeInsertion(int insertionPosition, int totalItemInserted);

        void notifyAdapterForItemRemoval(int removedItemIndex);

        void notifyAdapterForInsertion(int itemInserted);

        void showErrorToast();

        void showDownloadError();

        void showSelectedCharacterDetail(Characters characters);
    }

    interface Presenter extends IBasePresenter<View> {
        void initiateListForAdapter();
        void getCharacters();

        void getNextPage(int totalItemCount, int lastVisibleItem);

        void findSelectedCharcter(int index);
    }
}