package com.example.shootingvirus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HighScoreActivity extends AppCompatActivity {

    TextView highScore30s, highScore60s;
    FragmentManager fragmentManager;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

//        fragmentManager = getSupportFragmentManager();


//        highScore30s = findViewById(R.id.high_score_30s);
//        highScore30s.setOnClickListener((view)->{
//            Log.d("logdd","onclicked");
////            fragment = getSupportFragmentManager().findFragmentById(R.id.high_score_fragment);
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            Bundle bundle = new Bundle();
//            bundle.putString("playTime","30");
////            fragmentTransaction.remove(fragment);
////            fragmentTransaction.detach(fragment).attach(fragment).commitNow();
////            fragment = HighScoreDetailFragment.newInstance("","");
////            fragment = fragmentManager.getFragmentFactory()
////                    .instantiate(getApplicationContext().getClassLoader(), "HighScoreDetailFragment");
////            fragmentTransaction.add(R.id.high_score_fragment, fragment).commit();
////            fragmentTransaction.add()
//            fragmentTransaction
//                    .replace(R.id.high_score_fragment, HighScoreMain30Fragment.class,bundle).commit();
//        });
//
//        highScore60s = findViewById(R.id.high_score_60s);
//        highScore60s.setOnClickListener((view)->{
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            Bundle bundle = new Bundle();
//            bundle.putString("playTime","60");
//            fragmentTransaction
//                    .replace(R.id.high_score_fragment, HighScoreMain30Fragment.class,bundle).commit();
////            NavHostFragment.create(R.id.);
//        });

    }
}