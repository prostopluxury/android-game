package com.example.tinder.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tinder.FragmentHelper;
import com.example.tinder.ItemAdapter;
import com.example.tinder.ItemTouchHelperCallback;
import com.example.tinder.R;
import com.example.tinder.TinderImage;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * The type Main fragment.
 */
public class MainFragment extends Fragment {

    private static final int ITEMS_PER_SCREEN = 1;
    private FragmentHelper fragmentHelper;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentHelper = (FragmentHelper) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        ItemAdapter adapter = new ItemAdapter(initTinderImages(), fragmentHelper, ITEMS_PER_SCREEN);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((Context) fragmentHelper);
        recyclerView.setLayoutManager(layoutManager);
    }

    private List<TinderImage> initTinderImages() {

        List<TinderImage> items = new ArrayList<>();

        items.add(new TinderImage("Tom, 2", "https://icatcare.org/app/uploads/2018/07/Thinking-of-getting-a-cat.png", 4.7f));
        items.add(new TinderImage("Cat, 4","https://ichef.bbci.co.uk/wwfeatures/live/976_549/images/live/p0/7r/yy/p07ryyyj.jpg",  676f));
        items.add(new TinderImage("Yarik, 8", "https://www.nationalgeographic.com/content/dam/news/2018/05/17/you-can-train-your-cat/02-cat-training-NationalGeographic_1484324.jpg", 3.2f));
        items.add(new TinderImage("Lev, 4", "https://upload.wikimedia.org/wikipedia/commons/6/66/An_up-close_picture_of_a_curious_male_domestic_shorthair_tabby_cat.jpg", 1.4f));
        items.add(new TinderImage("Franchesco, 5", "https://www.humanesociety.org/sites/default/files/styles/768x326/public/2018/08/kitten-440379.jpg?h=f6a7b1af&itok=vU0J0uZR", 11073f));
        return items;
    }
}
