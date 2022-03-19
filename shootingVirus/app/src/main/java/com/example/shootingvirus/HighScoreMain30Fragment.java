package com.example.shootingvirus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shootingvirus.database.Database;
import com.example.shootingvirus.database.ScoreEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HighScoreMain30Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HighScoreMain30Fragment extends Fragment implements ScoreAdapter.ListItemListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "playTime";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String playTime = "30";
    private String mParam2;

    RecyclerView recyclerView;
    ScoreAdapter adapter;



    public HighScoreMain30Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HighScoreMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HighScoreMain30Fragment newInstance(String param1, String param2) {
        HighScoreMain30Fragment fragment = new HighScoreMain30Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.d("logdd", "arguments not null");
            playTime = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        else Log.d("logdd", "arguments null");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_high_score_main30, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv = view.findViewById(R.id.high_score_main30_tv);
        TextView to60sTv = view.findViewById(R.id.to60s_tv);

        adapter = new ScoreAdapter(new ArrayList<>(), getContext(), this);
        setAdapterData(adapter);

        recyclerView = view.findViewById(R.id.high_score_recycler_view30);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        to60sTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view)
                        .navigate(R.id.action_highScoreMainFragment_to_highScoreMain60Fragment);
            }
        });
    }

    void setAdapterData(ScoreAdapter scoreAdapter){

        Log.d("logdd","playtime: "+playTime);

        LiveData<List<ScoreEntry>> liveData = Database.getInstance(getContext())
                .Dao().loadScoreByPlayTime(Integer.parseInt(playTime));

        liveData.observe(getViewLifecycleOwner(), scoreEntries -> {
            Log.d("logdd","number of data: "+scoreEntries.size());
            scoreAdapter.setScoreEntries(scoreEntries);
        });// method reference

    }

    @Override
    public void onListItemClicked(ScoreEntry scoreEntry) {

        Log.d("logdd", "onListItemClickTriggered");

        HighScoreMain30FragmentDirections.ActionHighScoreMainFragmentToHighScoreDetailFragment action
                = HighScoreMain30FragmentDirections.actionHighScoreMainFragmentToHighScoreDetailFragment();
        action.setScoreEntry(scoreEntry);

        Navigation.findNavController(getView()).navigate(action);

    }
}