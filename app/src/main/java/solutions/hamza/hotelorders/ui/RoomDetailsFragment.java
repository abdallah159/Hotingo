package solutions.hamza.hotelorders.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import solutions.hamza.hotelorders.R;
import solutions.hamza.hotelorders.adapter.RoomImagesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomDetailsFragment extends Fragment {

    @BindView(R.id.roomImagesRV)
    RecyclerView roomImagesRV;

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
        roomImagesRV.setLayoutManager
                (new GridLayoutManager(getContext()
                        , 2));
        roomDetailsAdapter = new RoomImagesAdapter(
                R.layout.room_image_row, getContext());
        roomImagesRV.setAdapter(roomDetailsAdapter);
        return view;
    }

    public static Fragment newInstance() {
        Fragment fragment = new RoomDetailsFragment();
        return fragment;
    }

}
