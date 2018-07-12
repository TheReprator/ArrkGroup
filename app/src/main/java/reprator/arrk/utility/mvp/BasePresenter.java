package reprator.arrk.utility.mvp;

/**
 * Created by MPROGA1\vikram.singh on 23/6/17.
 */

public class BasePresenter<ViewT> implements IBasePresenter<ViewT>
{
    protected ViewT     view;

    @Override
    public void onViewActive(ViewT view) {
        this.view = view;
    }

    @Override
    public void onViewInActive() {
        view = null;
    }
}
