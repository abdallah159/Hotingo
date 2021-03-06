package solutions.hamza.hotelorders.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import solutions.hamza.hotelorders.R;
import solutions.hamza.hotelorders.model.RoomResponce;


public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.VH> {

    private int rowLayout;
    private Context context;
    private onRoomClickListner onRoomClickListner;
    ArrayList<RoomResponce> rooms;


    public static class VH extends RecyclerView.ViewHolder {
        ImageView room_IV;
        TextView roomNumTV;

        public VH(View v) {
            super(v);

            room_IV = v.findViewById(R.id.room_IV);
            roomNumTV = v.findViewById(R.id.roomNumTV);
        }
    }

    public RoomsAdapter(ArrayList<RoomResponce> roomResponces, int rowLayout, Context context, onRoomClickListner listner) {

        this.rowLayout = rowLayout;
        this.context = context;
        this.onRoomClickListner = listner;
        this.rooms = roomResponces;

    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {

        if (rooms.get(position).getImgs().size() != 0) {
            Glide.with(context).load(rooms.get(position).getImgs().get(0)).into(holder.room_IV);
        }
        holder.roomNumTV.setText(rooms.get(position).getNumber());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRoomClickListner.onRoomClickListner(rooms.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public interface onRoomClickListner {
        void onRoomClickListner(RoomResponce roomResponce);

    }


}

