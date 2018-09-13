package solutions.hamza.hotelorders.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import solutions.hamza.hotelorders.R;
import solutions.hamza.hotelorders.adapter.ServicesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelServicesFragment extends Fragment {


    @BindView(R.id.servicesRV)
    RecyclerView servicesRecycler;

    private ServicesAdapter servicesAdapter;


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

        servicesAdapter = new ServicesAdapter(
                R.layout.service_row_layout, getContext(), new ServicesAdapter.onServiceClickListner() {


            @Override
            public void onServiceClickListner() {

                FragmentManager fm = getFragmentManager();
                OrderServiceFragment orderDialog = new OrderServiceFragment();
                orderDialog.show(fm, "Show fragment");

            }
        });
        servicesRecycler.setAdapter(servicesAdapter);

        return view;
    }

    public static Fragment newInstance() {
        Fragment fragment = new HotelServicesFragment();
        return fragment;
    }
}
