package com.example.myapplication;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RecyclerListFragment extends Fragment {

    private ItemTouchHelper mItemTouchHelper;
    private Button restartButton;
    private boolean isWinner = false;

    public RecyclerListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        WinHandler win = new WinHandler() {
            @Override
            public void finishGame() {
                isWinner = true;
                restartButton.setText("You won!");
                restartButton.setTextColor(Color.GREEN);
            }
        };

        final RecyclerListAdapter adapter = new RecyclerListAdapter(win);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        restartButton = view.findViewById(R.id.restartButton);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new SpanningGridLayoutManager(this.getContext(), 3));
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isWinner) {
                    restartButton.setText("Restart");
                    restartButton.setTextColor(Color.RED);
                    isWinner = false;
                }
                adapter.reshuffle();
            }
        });

    }

}