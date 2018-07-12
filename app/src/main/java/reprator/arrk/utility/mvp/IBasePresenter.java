package reprator.arrk.utility.mvp;

/**
 * Created by MPROGA1\vikram.singh on 23/6/17.
 */

public interface IBasePresenter<ViewT>
{
    void onViewActive(ViewT view);
    void onViewInActive();
}
