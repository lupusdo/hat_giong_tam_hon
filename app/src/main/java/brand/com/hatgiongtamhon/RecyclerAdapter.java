package brand.com.hatgiongtamhon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private List<DataRecycler>  dataRecyclerList;
    private IClickItemListener iClickItemListener;


    public RecyclerAdapter(Context context, List<DataRecycler> dataRecyclerList, IClickItemListener listener) {
        this.context = context;
        this.dataRecyclerList = dataRecyclerList;
        this.iClickItemListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_recyclerview, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataRecycler dataRecycler = dataRecyclerList.get(position);

        holder.img.setImageResource(dataRecycler.getImg_recycler());
        holder.txt.setText(dataRecycler.getTxt_recycler());

        int order = dataRecycler.getOrder();
        int ordertab = dataRecycler.getOrdertab();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickItem(order,ordertab);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataRecyclerList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_recyclerview);
            txt = itemView.findViewById(R.id.txt_recyclerview);
        }
    }
}
