package reprator.arrk.ui.characterList;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;
import reprator.arrk.R;
import reprator.arrk.data.modal.Characters;
import reprator.arrk.ui.ChangeFragment;
import reprator.arrk.ui.characterList.adapter.CharacterListAdapter;
import reprator.arrk.utility.ContextFunction;
import reprator.arrk.utility.Validation;

import static reprator.arrk.controllers.constants.RestConstants.DI_ACTIVITY;

public class CharacterList extends DaggerFragment implements CharacterListContract.View,
        View.OnClickListener, CharacterItemListener {

    private RecyclerView mRecyclerView;
    private ViewStub viewStubError;
    private LinearLayout linError;
    private LinearLayout linLoader;

    @Inject
    CharacterListAdapter characterListAdapter;

    @Inject
    @Named(DI_ACTIVITY)
    Context context;

    @Inject
    ChangeFragment showCharacterDetail;

    @Inject
    CharacterListContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.starwar_character_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewStubError = ContextFunction.findViewByIdAndCast(view, R.id.common_vs_error);
        mRecyclerView = ContextFunction.findViewByIdAndCast(view, R.id.common_recyclerView);

        presenter.initiateListForAdapter();

        ViewStub viewStubFooter = ContextFunction.findViewByIdAndCast(view, R.id.common_vs_loading);
        linLoader = (LinearLayout) viewStubFooter.inflate();

        presenter.getCharacters();
    }

    @Override
    public void setCharacterListAdapter(List<Characters> characterList) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                presenter.getNextPage(linearLayoutManager.getItemCount(), linearLayoutManager.findLastVisibleItemPosition());
            }
        });

        mRecyclerView.setAdapter(characterListAdapter);
    }

    @Override
    public void notifyForItemRangeInsertion(int insertionPosition, int totalItemInserted) {
        characterListAdapter.notifyItemRangeInserted(insertionPosition, totalItemInserted);
    }

    @Override
    public void notifyAdapterForItemRemoval(int removedItemIndex) {
        characterListAdapter.notifyItemRemoved(removedItemIndex);
    }

    @Override
    public void notifyAdapterForInsertion(int itemInserted) {
        characterListAdapter.notifyItemInserted(itemInserted);
    }

    @Override
    public void showErrorToast() {
        showToast(ContextFunction.getStringResource(context, R.string.retrofit_parsing_exception));
    }

    @Override
    public void showDownloadError() {
        if (!Validation.isNull(viewStubError.getParent())) {
            linError = (LinearLayout) viewStubError.inflate();

            Button btReload = ContextFunction.findViewByIdAndCast(linError, R.id.retry_button);
            btReload.setOnClickListener(this);
        } else
            ContextFunction.viewShow(linError);
    }

    @Override
    public void showSelectedCharacterDetail(Characters character) {
        //Move to next Fragment or Activity
        showCharacterDetail.showStarWarCharacterDetail(character);
    }

    @Override
    public void characterSelected(int position) {
        presenter.findSelectedCharcter(position);
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
        if (show)
            ContextFunction.viewShow(linLoader);
        else
            ContextFunction.viewGone(linLoader);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.retry_button: {
                ContextFunction.viewGone(linError);
                presenter.getCharacters();
            }
        }
    }
}
