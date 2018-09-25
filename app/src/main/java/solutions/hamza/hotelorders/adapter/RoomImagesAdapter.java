package solutions.hamza.hotelorders.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import solutions.hamza.hotelorders.R;


public class RoomImagesAdapter extends RecyclerView.Adapter<RoomImagesAdapter.VH> {

    private int rowLayout;
    private Context context;
    private List<String> imgs;

//    private On_allCommunity_ClickListener on_allCommunity_clickListener;

    public static class VH extends RecyclerView.ViewHolder {

        ImageView roomIV;

        public VH(View v) {
            super(v);
            roomIV = v.findViewById(R.id.roomDetailIV);
        }
    }

    public RoomImagesAdapter(List<String> images, int rowLayout, Context context) {

        this.rowLayout = rowLayout;
        this.context = context;
        this.imgs = images;

    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {

        Glide.with(context).load(imgs.get(position)).into(holder.roomIV);


    }

    @Override
    public int getItemCount() {
        return imgs.size();
    }

}

