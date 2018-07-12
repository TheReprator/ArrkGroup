package reprator.arrk.ui.characterList.adapter;

import android.view.View;
import android.widget.TextView;

import reprator.arrk.R;
import reprator.arrk.data.modal.Characters;
import reprator.arrk.data.modal.MRecyclerListItem;
import reprator.arrk.ui.characterList.CharacterItemListener;
import reprator.arrk.utility.ContextFunction;

public class VHCharacters extends VHBase implements View.OnClickListener {

    private TextView tvCharacterName;
    private CharacterItemListener characterItemListener;

    public VHCharacters(View view, CharacterItemListener characterItemListener) {
        super(view);

        this.characterItemListener = characterItemListener;

        tvCharacterName = ContextFunction.findViewByIdAndCast(view, R.id.rc_tv_name);
        tvCharacterName.setOnClickListener(this);
    }

    @Override
    public void bindView(MRecyclerListItem mRecyclerListItem) {
        tvCharacterName.setText(((Characters) mRecyclerListItem).getName());
    }

    @Override
    public void onClick(View view) {
        if (R.id.rc_tv_name == view.getId())
            characterItemListener.characterSelected(getAdapterPosition());
    }
}
