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
import solutions.hamza.hotelorders.adapter.RoomsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsFragment extends Fragment {


    @BindView(R.id.roomsRV)
    RecyclerView roomsRV;

    private RoomsAdapter roomsAdapter;


    public RoomsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);
        ButterKnife.bind(this, view);
        roomsRV.setLayoutManager
                (new GridLayoutManager(getContext()
                        , 2));

        roomsAdapter = new RoomsAdapter(
                R.layout.room_row_layout, getContext(), new RoomsAdapter.onRoomClickListner() {


            @Override
            public void onRoomClickListner() {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.cointaner, RoomDetailsFragment.newInstance())
                        .addToBackStack(null)
                        .commit();

            }
        });
        roomsRV.setAdapter(roomsAdapter);

        return view;
    }


    public static Fragment newInstance() {
        Fragment fragment = new RoomsFragment();
        return fragment;
    }

}
