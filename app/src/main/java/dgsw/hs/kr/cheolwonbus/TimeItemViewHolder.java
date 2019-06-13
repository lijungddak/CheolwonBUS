package dgsw.hs.kr.cheolwonbus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class TimeItemViewHolder extends RecyclerView.ViewHolder {
    TextView textViewTime;

    public TimeItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewTime = itemView.findViewById(R.id.textViewTime);
    }
}
