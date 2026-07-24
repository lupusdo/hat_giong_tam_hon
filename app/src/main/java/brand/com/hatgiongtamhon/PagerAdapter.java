package brand.com.hatgiongtamhon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.Holder>{

    Context context;
    List<DataPager> dataPagerList;
    IClickItemListener listener;

    public PagerAdapter(Context context, List<DataPager> dataPagerList,IClickItemListener listener) {
        this.context = context;
        this.dataPagerList = dataPagerList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.fragment, parent, false);

        // Return a new holder instance
        PagerAdapter.Holder view = new PagerAdapter.Holder(contactView);
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        DataPager dataPager = dataPagerList.get(position);
        int orderTab = dataPager.getOrderPager();

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(holder.recyclerView.getContext(),dataPager.getDataRecyclerList(),listener);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return dataPagerList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerview);
        }
    }
}
