package com.example.myapplication;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {

    private static final int[] IMAGES = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
    };
    private static List<Integer> items;
    private static int length = 8;

    private ItemViewHolder greenCell;
    private WinHandler winHandler;
    private final OnStartDragListener mDragStartListener;


    public RecyclerListAdapter(OnStartDragListener dragListener, WinHandler win) {
        reshuffle();
        winHandler = win;
        mDragStartListener = dragListener;
    }

    public static void setItems(List<Integer> items) {
        RecyclerListAdapter.items = items;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent,
                false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        if (items.get(position) == length) {
            greenCell = holder;
            holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        } else {
            holder.imageView.setImageResource(IMAGES[items.get(position)]);
        }

        holder.imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) ==
                        MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
        if (isWinner(items)){
            winHandler.win();
        }
    }

    public static boolean isWinner(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        Integer current, previous = iterator.next();
        while (iterator.hasNext()) {
            current = iterator.next();
            if (previous.compareTo(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemDismiss(int position) {
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(items, fromPosition, toPosition);
        notifyDataSetChanged();
    }

    public void reshuffle() {
        List<Integer> newItems = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            newItems.add(i, i);
        }
        Collections.shuffle(newItems);
        newItems.add(length);
        setItems(newItems);
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