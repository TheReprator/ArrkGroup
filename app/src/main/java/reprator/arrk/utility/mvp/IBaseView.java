package reprator.arrk.utility.mvp;

import android.content.Context;

/**
 * Created by MPROGA1\vikram.singh on 23/6/17.
 */

public interface IBaseView
{
    void showToast(String message);
    void setProgressBar(boolean show);
    Context getContext();
}
