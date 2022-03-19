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
 * Use the {@link HighScoreMain60Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HighScoreMain60Fragment extends Fragment implements ScoreAdapter.ListItemListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "playTime";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String playTime = "60";
    private String mParam2;

    RecyclerView recyclerView;
    ScoreAdapter adapter;

    public HighScoreMain60Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HighScoreMain30Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HighScoreMain60Fragment newInstance(String param1, String param2) {
        HighScoreMain60Fragment fragment = new HighScoreMain60Fragment();
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
            playTime = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_high_score_main60, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView to30sTv = view.findViewById(R.id.to30s_tv);

        adapter = new ScoreAdapter(new ArrayList<>(), getContext(), this);
        setAdapterData(adapter);

        recyclerView = view.findViewById(R.id.high_score_recycler_view60);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        to30sTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_highScoreMain60Fragment_to_highScoreMainFragment);
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

        HighScoreMain60FragmentDirections.ActionHighScoreMain60FragmentToHighScoreDetailFragment action
                = HighScoreMain60FragmentDirections.actionHighScoreMain60FragmentToHighScoreDetailFragment();
        action.setScoreEntry(scoreEntry);

        Navigation.findNavController(getView()).navigate(action);

    }

}