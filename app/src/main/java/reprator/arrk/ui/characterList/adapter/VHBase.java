package reprator.arrk.ui.characterList.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import reprator.arrk.data.modal.MRecyclerListItem;

public abstract class VHBase extends RecyclerView.ViewHolder {
    public VHBase(View view) {
        super(view);
    }

    public abstract void bindView(MRecyclerListItem characters);
}
