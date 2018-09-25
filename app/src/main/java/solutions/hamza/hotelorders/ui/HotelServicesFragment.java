package solutions.hamza.hotelorders.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
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
import solutions.hamza.hotelorders.adapter.ServicesAdapter;
import solutions.hamza.hotelorders.model.ServicesResponce;
import solutions.hamza.hotelorders.service.ApiClient;
import solutions.hamza.hotelorders.service.ApiEndpointInterface;
import solutions.hamza.hotelorders.service.AuthInterceptor;
import solutions.hamza.hotelorders.utils.MyApplication;
import solutions.hamza.hotelorders.utils.Utilities;

public class HotelServicesFragment extends Fragment {

    @BindView(R.id.servicesRV)
    RecyclerView servicesRecycler;

    private ServicesAdapter servicesAdapter;
    ArrayList<ServicesResponce> services;


    public HotelServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotel_services, container, false);
        ButterKnife.bind(this, view);

        servicesRecycler.setLayoutManager
                (new LinearLayoutManager(getContext()
                        , LinearLayoutManager.VERTICAL, false));

        Utilities.showLoadingDialog(getContext(), R.color.colorAccent);

        ApiEndpointInterface apiService =
                ApiClient.getClient(new AuthInterceptor(MyApplication.getPrefManager(getContext()).getUser().getToken())).create(ApiEndpointInterface.class);


        Call<ArrayList<ServicesResponce>> call = apiService.getServices();

        call.enqueue(new Callback<ArrayList<ServicesResponce>>() {
            @Override
            public void onResponse(Call<ArrayList<ServicesResponce>> call, Response<ArrayList<ServicesResponce>> response) {
                Utilities.dismissLoadingDialog();
                if (response.isSuccessful()) {
                    services = new ArrayList<>();
                    services = response.body();

                    servicesAdapter = new ServicesAdapter(services,
                            R.layout.service_row_layout, getContext(), new ServicesAdapter.onServiceClickListner() {

                        @Override
                        public void onServiceClickListner(String id) {

                            FragmentManager fm = getFragmentManager();
//                            Toast.makeText(getContext(), id , Toast.LENGTH_LONG).show();
                            OrderServiceFragment orderDialog = OrderServiceFragment.newInstance(id);
                            orderDialog.show(fm, "Show fragment");


                        }
                    });
                    servicesRecycler.setAdapter(servicesAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ServicesResponce>> call, Throwable t) {
                Utilities.dismissLoadingDialog();
            }
        });

        return view;
    }

    public static Fragment newInstance() {
        Fragment fragment = new HotelServicesFragment();
        return fragment;
    }
}
