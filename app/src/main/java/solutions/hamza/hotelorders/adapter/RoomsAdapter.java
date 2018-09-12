package solutions.hamza.hotelorders.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import solutions.hamza.hotelorders.R;


public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.VH> {

    private int rowLayout;
    private Context context;
    private onRoomClickListner onRoomClickListner;


    public static class VH extends RecyclerView.ViewHolder {

        ImageView donateHIstoryIV;
        TextView donateHistoryTitleTV;
        TextView donateDateTV;
        TextView DonateDescriptionTV;


        public VH(View v) {
            super(v);
           donateHIstoryIV = v.findViewById(R.id.donateHIstory_IV);
           /*
            donateHistoryTitleTV = v.findViewById(R.id.donateHistoryTitleTV);
            donateDateTV = v.findViewById(R.id.donateDateTV);
            DonateDescriptionTV = v.findViewById(R.id.DonateDescriptionTV);*/

        }
    }

    public RoomsAdapter(int rowLayout, Context context, onRoomClickListner listner) {

        this.rowLayout = rowLayout;
        this.context = context;
        this.onRoomClickListner = listner;

    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRoomClickListner.onRoomClickListner();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public interface onRoomClickListner {
        void onRoomClickListner();

    }


}

