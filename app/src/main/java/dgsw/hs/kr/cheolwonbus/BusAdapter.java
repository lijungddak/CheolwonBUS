package dgsw.hs.kr.cheolwonbus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusStopItemViewHolder> implements ItemClickListener {

    private ArrayList<BusBean> busBeans;
    private ItemClickListener listener;

    public BusAdapter(ArrayList<BusBean> busBeans, ItemClickListener listener){
        this.busBeans = busBeans;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BusStopItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.activity_item_bus_info, viewGroup, false);
        return new BusStopItemViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull BusStopItemViewHolder busStopItemViewHolder, int i) {
        BusBean busBean = busBeans.get(i);
        busStopItemViewHolder.textViewBusName.setText(busBean.getRouteId() + "(" + busBean.getStartStation() + "~" + busBean.getEndStation() + ")");
        busStopItemViewHolder.textViewRemainTime.setText(busBean.getRemainTime());
        busStopItemViewHolder.textViewNextTime.setText(busBean.getNextTime());

        final int index = i;
        busStopItemViewHolder.itemView.setOnClickListener(v ->{
            listener.onItemClick(v, index);
        });
    }

    @Override
    public int getItemCount() {
        if(busBeans == null){
            return 0;
        }
        return busBeans.size();
    }

    @Override
    public void onItemClick(View v, int position) {

    }

    public void changedData(ArrayList<BusBean> data) {
        this.busBeans = data;
    }
}
