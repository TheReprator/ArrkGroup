package reprator.arrk.ui.characterList.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import reprator.arrk.data.modal.Characters;
import reprator.arrk.data.modal.MRecyclerListItem;

public class VHLoader extends VHBase {
    public VHLoader(View view) {
        super(view);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        view.setLayoutParams(layoutParams);
    }

    @Override
    public void bindView(MRecyclerListItem mRecyclerListItem) {

    }
}
