package solutions.hamza.hotelorders.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import solutions.hamza.hotelorders.R;
import solutions.hamza.hotelorders.adapter.RoomImagesAdapter;
import solutions.hamza.hotelorders.model.RoomResponce;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomDetailsFragment extends Fragment {

    @BindView(R.id.roomImagesRV)
    RecyclerView roomImagesRV;

    @BindView(R.id.roomDetailsNumTV)
    TextView roomDetailsNumTV;
    @BindView(R.id.roomDetailsDescTV)
    TextView roomDetailsDescTV;
    @BindView(R.id.roomDetailsPriceTV)
    TextView roomDetailsPriceTV;



    static RoomResponce room;

    private RoomImagesAdapter roomDetailsAdapter;


    public RoomDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_room_details, container, false);
        ButterKnife.bind(this, view);


        roomDetailsNumTV.setText(room.getNumber());
        roomDetailsDescTV.setText(room.getDesc());
        roomDetailsPriceTV.setText(room.getPrice() + " for night");


        roomImagesRV.setLayoutManager
                (new GridLayoutManager(getContext()
                        , 2));
        roomDetailsAdapter = new RoomImagesAdapter(room.getImgs(),
                R.layout.room_image_row, getContext());
        roomImagesRV.setAdapter(roomDetailsAdapter);
        return view;
    }

    public static Fragment newInstance(RoomResponce roomResponce) {
        Fragment fragment = new RoomDetailsFragment();
        room = roomResponce;
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.roomBookBTN)
    public void onViewClicked() {
        FragmentManager fm = getFragmentManager();
        BookRoomFragment bookRoomDialog =  BookRoomFragment.newInstance(room.getId());
        bookRoomDialog.show(fm, "Show fragment");
    }
}
