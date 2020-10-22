package com.traderevcoding.utils;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private GridLayoutManager mLinearLayoutManager;


    /** is number of items that we could have after our
     *  current scroll position before we start loading
     more items */
    private int visibleThreshold = 5;
    /** to keep track of the page that we would like to
     * retrieve from a server our database
     * */
    private int currentPage = 0;
    /** total number of items that we retrieve lastly*/
    private int previousTotalItemCount = 0;
    /** indicating whether we are loading new dataset or not*/
    private boolean loading = true;
    /** the initial index of the page that'll start from */
    private int startingPageIndex = 0;

    /******* variables we could get from linearLayoutManager *******/

    /** the total number of items that we currently have on our recyclerview and we
     * get it from linearLayoutManager */
    private int totalItemCount;

    /** the position of last visible item in our view currently
     * get it from linearLayoutManager */
    private int lastVisibleItemPosition;

    public PaginationScrollListener(GridLayoutManager linearLayoutManager) {
        mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        totalItemCount = mLinearLayoutManager.getItemCount();
        lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();

        // first case
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) { this.loading = true; }
        }

        // second case
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        // third case
        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, recyclerView);
            loading = true;
        }

    }

    // Define the place where we load the dataset
    public abstract void onLoadMore(int pageNum, RecyclerView recyclerView);
}
