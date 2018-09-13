package solutions.hamza.hotelorders.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import solutions.hamza.hotelorders.R;

public class MyRoomFragment extends Fragment {


    @BindView(R.id.roomIV)
    ImageView roomIV;
    Unbinder unbinder;
    @BindView(R.id.roomMoreDetailsTV)
    TextView roomMoreDetailsTV;
    @BindView(R.id.roomNumberTV)
    TextView roomNumberTV;
    @BindView(R.id.roomPriceTV)
    TextView roomPriceTV;
    @BindView(R.id.bookingDurationTV)
    TextView bookingDurationTV;
    @BindView(R.id.cancleBookingBTN)
    Button cancleBookingBTN;
    @BindView(R.id.extendBookingBTN)
    Button extendBookingBTN;

    public MyRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_room, container, false);
        unbinder = ButterKnife.bind(this, view);

        Glide.with(getContext()).load(R.drawable.room).into(roomIV);


        return view;
    }


    public static Fragment newInstance() {
        Fragment fragment = new MyRoomFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.roomMoreDetailsTV)
    public void onViewClicked() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.cointaner, RoomDetailsFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @OnClick({R.id.cancleBookingBTN, R.id.extendBookingBTN})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancleBookingBTN:
                Toast.makeText(getContext(), "Room book Cancled", Toast.LENGTH_SHORT).show();
                break;
            case R.id.extendBookingBTN:
                Toast.makeText(getContext(), "Extend booking", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
