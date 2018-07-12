package reprator.arrk.ui.characterList;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import reprator.arrk.data.DataRepository;
import reprator.arrk.data.DataSource;
import reprator.arrk.data.modal.Characters;
import reprator.arrk.data.modal.StarWarProfile;
import reprator.arrk.di.scopes.FragmentScoped;
import reprator.arrk.utility.Validation;
import reprator.arrk.utility.mvp.BasePresenter;

import static reprator.arrk.controllers.constants.RestConstants.DI_CHARACTER;

@FragmentScoped
class CharacterListPresenter extends BasePresenter<CharacterListContract.View> implements
        CharacterListContract.Presenter {

    private DataRepository dataRepository;

    private int nextpage = 1;
    private int visibleThreshold = 10;
    private int totalItemCount;
    private boolean loading = false;

    @Inject
    @Named(DI_CHARACTER)
    List<Characters> charactersList;

    @Inject
    CharacterListPresenter(CharacterListContract.View view,
                           DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        this.view = view;
    }

    @Override
    public void initiateListForAdapter() {
        view.setCharacterListAdapter(charactersList);
    }

    @Override
    public void getCharacters() {
        if(!Validation.isEmptyorNull(charactersList)){
            view.setProgressBar(false);
            initiateListForAdapter();
        }else{
            view.setProgressBar(true);
            hitServerForStarWarCharacter();
        }
    }

    private void hitServerForStarWarCharacter(){
        dataRepository.getStarWarCharacters(nextpage, new DataSource.GetHttpCallback<StarWarProfile>() {
            @Override
            public void onSuccess(StarWarProfile data) {
                if (view != null) {
                    stopLoading();

                    totalItemCount = data.getCount();
                    nextpage++;

                    int previousSize = charactersList.size();
                    charactersList.addAll(data.getResults());
                    view.notifyForItemRangeInsertion(previousSize, data.getResults().size());
                }
            }

            @Override
            public void onFailure() {
                if (view != null) {
                    if(Validation.isEmptyorNull(charactersList)) {
                        showFailureInUi();
                    } else {
                        view.showErrorToast();
                        removeProgressFooterRowInUi();
                    }
                }
            }

            @Override
            public void onNetworkFailure(int stringResource) {
                if (view != null) {
                    if(Validation.isEmptyorNull(charactersList)){
                        showFailureInUi();
                    } else {
                        view.showToast(view.getContext().getString(stringResource));
                        removeProgressFooterRowInUi();
                    }
                }
            }
        });
    }

    @Override
    public void getNextPage(int totalItemCount, int lastVisibleItem) {
        if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold))
        {
            // End has been reached
            // Do something
            int currentCharacterListSize = charactersList.size();
            if (this.totalItemCount > currentCharacterListSize)
            {
                loading = true;
                addProgressFooterRowInUi();
                hitServerForStarWarCharacter();
            }
        }
    }

    @Override
    public void findSelectedCharcter(int index) {
        view.showSelectedCharacterDetail(charactersList.get(index));
    }

    private void showFailureInUi() {
        view.setProgressBar(false);
        view.showDownloadError();
    }

    private void addProgressFooterRowInUi() {
        //hide loader footer
        Characters characters = new Characters();
        charactersList.add(characters);
        view.notifyAdapterForInsertion(charactersList.size());
    }

    private void removeProgressFooterRowInUi() {
        loading = false;
        //hide loader footer
        int lastIndex = charactersList.size() - 1;
        charactersList.remove(lastIndex);
        view.notifyAdapterForItemRemoval(lastIndex);
    }

    private void stopLoading() {
        if(Validation.isEmptyorNull(charactersList))
            view.setProgressBar(false);
        else
            removeProgressFooterRowInUi();
    }
}