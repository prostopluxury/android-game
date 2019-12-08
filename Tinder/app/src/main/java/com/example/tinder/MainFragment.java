/**
 * Created by Alexander Lomat
 */


package com.example.tinder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import by.bsuir.tinder.adapter.ItemAdapter;
import by.bsuir.tinder.callback.ItemTouchHelperCallback;
import by.bsuir.tinder.pojo.TinderImage;

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
        return inflater.inflate(R.layout.fragment_1, container, false);
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

        return items;
    }
}
