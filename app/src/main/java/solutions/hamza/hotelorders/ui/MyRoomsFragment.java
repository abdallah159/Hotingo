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
import solutions.hamza.hotelorders.adapter.MyRoomsAdapter;
import solutions.hamza.hotelorders.model.AllRoomsResponce;
import solutions.hamza.hotelorders.model.UserResponce;
import solutions.hamza.hotelorders.service.ApiClient;
import solutions.hamza.hotelorders.service.ApiEndpointInterface;
import solutions.hamza.hotelorders.service.AuthInterceptor;
import solutions.hamza.hotelorders.utils.MyApplication;
import solutions.hamza.hotelorders.utils.Utilities;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyRoomsFragment extends Fragment {

    @BindView(R.id.allRoomsRV)
    RecyclerView allRoomsRV;

    private MyRoomsAdapter roomsAdapter;
    ArrayList<AllRoomsResponce> roomResponces;

//    String USER_PREF_ID = MyApplication.getPrefManager(getContext()).getUser().getUser().getUser_id();

    UserResponce user = MyApplication.getPrefManager(getContext()).getUser();


    public MyRoomsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_rooms, container, false);
        ButterKnife.bind(this, view);
        allRoomsRV.setLayoutManager
                (new GridLayoutManager(getContext()
                        , 1));


        Utilities.showLoadingDialog(getContext(), R.color.colorAccent);

        ApiEndpointInterface apiService =
                ApiClient.getClient(new AuthInterceptor(MyApplication.getPrefManager(getContext()).getUser().getToken())).create(ApiEndpointInterface.class);

        Call<ArrayList<AllRoomsResponce>> call = apiService.getAllRooms(user.getUser().getUser_id());

        call.enqueue(new Callback<ArrayList<AllRoomsResponce>>() {
            @Override
            public void onResponse(Call<ArrayList<AllRoomsResponce>> call, Response<ArrayList<AllRoomsResponce>> response) {
                Utilities.dismissLoadingDialog();
                if (response.isSuccessful()) {

                    roomResponces = new ArrayList<>();
                    roomResponces = response.body();
                    Timber.d(response.body().size()+"");

                    roomsAdapter = new MyRoomsAdapter(roomResponces,
                            R.layout.room_row_layout, getContext(), new MyRoomsAdapter.onMyRoomClickListner() {

                        @Override
                        public void onMyRoomClickListner(AllRoomsResponce roomResponce) {
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.cointaner, MyRoomFragment.newInstance(roomResponce))
                                    .addToBackStack(null)
                                    .commit();
                        }
                    });
                    allRoomsRV.setAdapter(roomsAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AllRoomsResponce>> call, Throwable t) {
                Utilities.dismissLoadingDialog();
            }
        });

        return view;
    }


    public static Fragment newInstance() {
        Fragment fragment = new MyRoomsFragment();
        return fragment;
    }
}
