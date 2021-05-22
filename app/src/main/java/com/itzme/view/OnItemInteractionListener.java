package com.itzme.view;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public interface OnItemInteractionListener {
    void onItemClicked(RecyclerView rv, RecyclerView.ViewHolder mViewHolderInFocus, int childAdapterPosition);

    void onLongItemClicked(RecyclerView recyclerView, RecyclerView.ViewHolder mViewHolderInFocus, int adapterPosition);

    void onMultipleViewHoldersSelected(RecyclerView rv, List<RecyclerView.ViewHolder> mRangeSelection);

    void onViewHolderHovered(RecyclerView rv, RecyclerView.ViewHolder viewHolder);
}
