package solutions.hamza.hotelorders.ui;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solutions.hamza.hotelorders.R;
import solutions.hamza.hotelorders.model.OrderResponce;
import solutions.hamza.hotelorders.service.ApiClient;
import solutions.hamza.hotelorders.service.ApiEndpointInterface;
import solutions.hamza.hotelorders.service.AuthInterceptor;
import solutions.hamza.hotelorders.utils.MyApplication;
import solutions.hamza.hotelorders.utils.Utilities;


public class OrderServiceFragment extends DialogFragment {
    @BindView(R.id.orderRoomET)
    EditText orderRoomET;
    @BindView(R.id.orderNotesET)
    EditText orderNotesET;
    @BindView(R.id.orderBTN)
    Button orderBTN;

    static String serviceId;
    static OrderResponce orderResponce;

    public OrderServiceFragment() {
        // Required empty public constructor
    }

    public static OrderServiceFragment newInstance(String id) {
        OrderServiceFragment fragment = new OrderServiceFragment();
        serviceId = id;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_service, container, false);
        ButterKnife.bind(this, view);

        orderBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReq();

            }
        });

        return view;
    }

    public void sendReq() {
        ApiEndpointInterface apiService =
                ApiClient.getClient(new AuthInterceptor(MyApplication.getPrefManager(getContext()).getUser().getToken())).create(ApiEndpointInterface.class);

        orderResponce = new OrderResponce(orderNotesET.getText().toString(), Integer.valueOf(orderRoomET.getText().toString()));
        Call<OrderResponce> call = apiService.addOrder(orderResponce, serviceId);
        call.enqueue(new Callback<OrderResponce>() {
            @Override
            public void onResponse(Call<OrderResponce> call, Response<OrderResponce> response) {
                Utilities.dismissLoadingDialog();
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Order Sent Successfully ... ", Toast.LENGTH_LONG).show();
                    dismiss();
                }
            }

            @Override
            public void onFailure(Call<OrderResponce> call, Throwable t) {
                Utilities.dismissLoadingDialog();

            }
        });

    }
}
