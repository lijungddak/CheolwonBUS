package dgsw.hs.kr.cheolwonbus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class BusStopItemViewHolder extends RecyclerView.ViewHolder {
    TextView textViewBusName;
//    RecyclerView recyclerView;
    TextView textViewRemainTime;
    TextView textViewNextTime;

    public BusStopItemViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewBusName = itemView.findViewById(R.id.tvBusName);
        //recyclerView = itemView.findViewById(R.id.timeList);
        textViewRemainTime = itemView.findViewById(R.id.tvRemainingTime);
        textViewNextTime = itemView.findViewById(R.id.tvNextTime);
    }
}
