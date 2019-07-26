package marty_library.ration.com.library.utils;

import android.content.Context;

public abstract class RefreshAdapter<T,S extends BaseVH> extends BaseAdapter<T,S> {
    public RefreshAdapter(Context mCon, Integer mLayout) {
        super(mCon, mLayout);

    }

    protected int position;
    protected int item_count;
    public int current_pages;
    public int total_pages;
    public int current_item_cnt;
    public int total_item_cnt;


    abstract  void onlistLastItem();






}
