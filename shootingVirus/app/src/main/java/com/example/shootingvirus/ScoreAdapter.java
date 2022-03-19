package com.example.shootingvirus;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shootingvirus.database.ScoreEntry;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    List<ScoreEntry> scoreEntries;
    Context context;
    ListItemListener listItemListener;

    public ScoreAdapter(List<ScoreEntry> scoreEntries, Context context, ListItemListener listItemListener) {
        this.scoreEntries = scoreEntries;
        this.context = context;
        this.listItemListener = listItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.score_item, parent, false);
        return new ViewHolder(view, listItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int score = scoreEntries.get(position).getScore();
        holder.highScoreText.setText(String.valueOf(score));
    }

    @Override
    public int getItemCount() {
        return scoreEntries.size();
    }

    public void setScoreEntries(List<ScoreEntry> scoreEntries) {
        this.scoreEntries = scoreEntries;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView highScoreText;
        ListItemListener listItemListener;

        public ViewHolder(@NonNull View itemView, ListItemListener listItemListener) {
            super(itemView);
            highScoreText = itemView.findViewById(R.id.score_item_tv);
            this.listItemListener = listItemListener;
            highScoreText.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
//            Log.d("logdd","onListItemClicked");
            listItemListener.onListItemClicked(scoreEntries.get(getAdapterPosition()));
        }
    }

    public interface ListItemListener{
        void onListItemClicked(ScoreEntry scoreEntry);
    }

}
