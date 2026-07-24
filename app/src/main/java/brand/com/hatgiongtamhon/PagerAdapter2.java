package brand.com.hatgiongtamhon;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PagerAdapter2 extends RecyclerView.Adapter<PagerAdapter2.Holder>{

    Context context;
    List<String> ndList, titleList;
    private int textSize = 24;
    Typeface typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC);

    public PagerAdapter2(Context context, List<String> ndList,List<String> titleList) {
        this.context = context;
        this.ndList = ndList;
        this.titleList = titleList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.fragmen2, parent, false);

        // Return a new holder instance
        PagerAdapter2.Holder view = new PagerAdapter2.Holder(contactView);
        return view;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String nd = ndList.get(position);
        String tt = titleList.get(position);
        holder.textView.setText(nd);
        holder.txttitle.setText(tt);
        holder.textView.setTextSize(textSize);
        holder.txttitle.setTextSize(textSize+2);
        holder.txttitle.setTypeface(typeface);
        holder.textView.setTypeface(typeface);

    }

    @Override
    public int getItemCount() {
        return ndList.size();
    }

    public void increaseText() {
        textSize = textSize+ 2;
        notifyDataSetChanged();
    }
    public void decreaseText() {
        textSize = textSize - 2;
        notifyDataSetChanged();
    }

    public void changeDefaultFont() {
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC);
        notifyDataSetChanged();
    }

    public void changePhilosopherFont() {
        typeface = ResourcesCompat.getFont(context, R.font.philosopher_italic);
        notifyDataSetChanged();
    }


    public class Holder extends RecyclerView.ViewHolder {
        private NestedScrollView nestedScrollView2;
        public TextView textView, txttitle;
        public Holder(@NonNull View itemView) {
            super(itemView);
            nestedScrollView2 = itemView.findViewById(R.id.nestedScrollView2);
            textView = itemView.findViewById(R.id.ndText);
            txttitle= itemView.findViewById(R.id.tv_title);
        }
    }
}
