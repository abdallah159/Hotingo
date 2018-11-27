package solutions.hamza.hotelorders.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solutions.hamza.hotelorders.R;
import solutions.hamza.hotelorders.adapter.RoomsAdapter;
import solutions.hamza.hotelorders.model.RoomResponce;
import solutions.hamza.hotelorders.service.ApiClient;
import solutions.hamza.hotelorders.service.ApiEndpointInterface;
import solutions.hamza.hotelorders.service.AuthInterceptor;
import solutions.hamza.hotelorders.utils.MyApplication;
import solutions.hamza.hotelorders.utils.Utilities;

public class RoomsFragment extends Fragment {


    @BindView(R.id.roomsRV)
    RecyclerView roomsRV;

    private RoomsAdapter roomsAdapter;
    ArrayList<RoomResponce> roomResponces;


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


        Utilities.showLoadingDialog(getContext(), R.color.colorAccent);

        ApiEndpointInterface apiService =
                ApiClient.getClient(new AuthInterceptor(MyApplication.getPrefManager(getContext()).getUser().getToken())).create(ApiEndpointInterface.class);


        Call<ArrayList<RoomResponce>> call = apiService.getRooms();

        call.enqueue(new Callback<ArrayList<RoomResponce>>() {
            @Override
            public void onResponse(Call<ArrayList<RoomResponce>> call, Response<ArrayList<RoomResponce>> response) {
                Utilities.dismissLoadingDialog();
                if (response.isSuccessful()) {
                    roomResponces = new ArrayList<>();
                    roomResponces = response.body();

                    roomsAdapter = new RoomsAdapter(roomResponces,
                            R.layout.room_row_layout, getContext(), new RoomsAdapter.onRoomClickListner() {

                        @Override
                        public void onRoomClickListner(RoomResponce roomResponce) {
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.cointaner, RoomDetailsFragment.newInstance(roomResponce))
                                    .addToBackStack(null)
                                    .commit();
                        }
                    });
                    roomsRV.setAdapter(roomsAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RoomResponce>> call, Throwable t) {
                Utilities.dismissLoadingDialog();
            }
        });

        return view;
    }


    public static Fragment newInstance() {
        Fragment fragment = new RoomsFragment();
        return fragment;
    }

}
