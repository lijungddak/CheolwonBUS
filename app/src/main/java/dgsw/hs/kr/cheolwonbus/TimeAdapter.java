package dgsw.hs.kr.cheolwonbus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeItemViewHolder>{

    private ArrayList<String> times;

    public TimeAdapter(ArrayList<String> times){
        this.times = times;
    }

    @NonNull
    @Override
    public TimeItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_bus_time, viewGroup, false);
        return new TimeItemViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull TimeItemViewHolder timeItemViewHolder, int i) {
        String time = times.get(i);
        timeItemViewHolder.textViewTime.setText(time);
    }

    @Override
    public int getItemCount() {
        if(times == null){
            return 0;
        }
        return times.size();
    }
}
