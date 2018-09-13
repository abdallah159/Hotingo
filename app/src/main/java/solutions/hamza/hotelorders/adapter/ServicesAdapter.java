package solutions.hamza.hotelorders.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.VH> {

    private int rowLayout;
    private Context context;
    private onServiceClickListner onServiceClickListner;


    public static class VH extends RecyclerView.ViewHolder {

        ImageView donateHIstoryIV;
        TextView donateHistoryTitleTV;
        TextView donateDateTV;
        TextView DonateDescriptionTV;


        public VH(View v) {
            super(v);
       /*     donateHIstoryIV = v.findViewById(R.id.donateHIstory_IV);
            donateHistoryTitleTV = v.findViewById(R.id.donateHistoryTitleTV);
            donateDateTV = v.findViewById(R.id.donateDateTV);
            DonateDescriptionTV = v.findViewById(R.id.DonateDescriptionTV);*/

        }
    }

    public ServicesAdapter(int rowLayout, Context context, onServiceClickListner listner) {

        this.rowLayout = rowLayout;
        this.context = context;
        this.onServiceClickListner = listner;

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
                onServiceClickListner.onServiceClickListner();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public interface onServiceClickListner {
        void onServiceClickListner();

    }


}

