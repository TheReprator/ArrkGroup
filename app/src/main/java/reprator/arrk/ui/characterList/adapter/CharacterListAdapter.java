package reprator.arrk.ui.characterList.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import reprator.arrk.R;
import reprator.arrk.data.modal.Characters;
import reprator.arrk.data.modal.MRecyclerListItem;
import reprator.arrk.di.scopes.FragmentScoped;
import reprator.arrk.ui.characterList.CharacterItemListener;

import static reprator.arrk.controllers.constants.RestConstants.DI_CHARACTER;

@FragmentScoped
public class CharacterListAdapter extends RecyclerView.Adapter<VHBase> {

    private List<Characters> charactersList;
    private CharacterItemListener characterItemListener;

    @Inject
    CharacterListAdapter(@Named(DI_CHARACTER) List<Characters> charactersList,
                         CharacterItemListener characterItemListener) {
        this.characterItemListener = characterItemListener;
        this.charactersList = charactersList;
    }

    @Override
    public int getItemViewType(int position) {
        return charactersList.get(position).getListItemType();
    }

    @NonNull
    @Override
    public VHBase onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (MRecyclerListItem.TYPE_LOADER == viewType)
            return new VHLoader(LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_footer,
                    parent, false));
        else
            return new VHCharacters(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_characters,
                    parent, false), characterItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VHBase holder, int position) {
        holder.bindView(charactersList.get(position));
    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }
}
