package reprator.arrk.data.modal;

public interface MRecyclerListItem {
    int TYPE_HEADER = 1;
    int TYPE_NORMAL = 2;
    int TYPE_ERROR = 3;
    int TYPE_LOADER = 4;
    int TYPE_SUB_EMPTY = 5;


    int getListItemType();
}