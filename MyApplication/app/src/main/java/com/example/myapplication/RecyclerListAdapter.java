package com.example.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {

    private static List<Integer> INDEXES;

    private ItemViewHolder greenCell;
    private WinHandler winHandler;


    public RecyclerListAdapter(WinHandler win) {
        INDEXES = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            INDEXES.add(i, i);
        }
        INDEXES.add(8);
        Collections.swap(INDEXES, 8, 7);
        winHandler = win;
    }

    public static void setINDEXES(List<Integer> INDEXES) {
        RecyclerListAdapter.INDEXES = INDEXES;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent,
                false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (INDEXES.get(position) == 8) {
            greenCell = holder;
            holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        } else {
            switch (INDEXES.get(position)) {
            }
        }

        if (isSorted(INDEXES)){
            winHandler.finishGame();
        }
    }

    public static boolean isSorted(List<Integer> list) {
        if ((list) == null || list.size() == 1) {
            return true;
        }

        Iterator<Integer> iter = list.iterator();
        Integer current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous.compareTo(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    @Override
    public int getItemCount() {
        return INDEXES.size();
    }

    @Override
    public void onItemDismiss(int position) {
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(INDEXES, fromPosition, toPosition);
        notifyDataSetChanged();
    }

    public void reshuffle() {

        List<Integer> NEW_INDEXES = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            NEW_INDEXES.add(i, i);
        }
        Collections.shuffle(NEW_INDEXES);
        NEW_INDEXES.add(8);
        setINDEXES(NEW_INDEXES);
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        ItemViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        int makeDragFlags() {
            return GridMapper.isNeighbour(this.getAdapterPosition(), greenCell.getAdapterPosition());
        }
    }
}