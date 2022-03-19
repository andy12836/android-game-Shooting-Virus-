package com.example.shootingvirus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shootingvirus.database.ScoreEntry;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HighScoreDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HighScoreDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HighScoreDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HighScoreDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HighScoreDetailFragment newInstance(String param1, String param2) {
        HighScoreDetailFragment fragment = new HighScoreDetailFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_high_score_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = view.findViewById(R.id.detail_tv);

        ScoreEntry scoreEntry = HighScoreDetailFragmentArgs.fromBundle(getArguments()).getScoreEntry();

        if(scoreEntry != null){
            String scoreStr = String.valueOf(scoreEntry.getScore());
            String playTime = String.valueOf(scoreEntry.getPlayTime());
            String recordTime = scoreEntry.getRecordTime();

            textView.setText("\n\nPlaytime: "+ playTime + "\n\nScore: " + scoreStr + "\n\nRecord Time:\n" + recordTime);
        }

    }
}