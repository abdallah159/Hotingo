package solutions.hamza.hotelorders.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solutions.hamza.hotelorders.R;
import solutions.hamza.hotelorders.model.AllRoomsResponce;
import solutions.hamza.hotelorders.model.CancelResponce;
import solutions.hamza.hotelorders.model.ExtendBook;
import solutions.hamza.hotelorders.model.ExtendResponse;
import solutions.hamza.hotelorders.model.RoomResponce;
import solutions.hamza.hotelorders.model.UserResponce;
import solutions.hamza.hotelorders.service.ApiClient;
import solutions.hamza.hotelorders.service.ApiEndpointInterface;
import solutions.hamza.hotelorders.service.AuthInterceptor;
import solutions.hamza.hotelorders.utils.MyApplication;
import solutions.hamza.hotelorders.utils.Utilities;


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
    @BindView(R.id.extendBTN)
    Button extendBTN;

    @BindView(R.id.extendET)
    EditText extendET;

    @BindView(R.id.extendCard)
    CardView extendCard;

    static AllRoomsResponce rooms;
    UserResponce user = MyApplication.getPrefManager(getContext()).getUser();


    ExtendBook bookRoom;

    public MyRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_room, container, false);
        unbinder = ButterKnife.bind(this, view);

        Glide.with(getContext()).load(rooms.getRoom().getImgs().get(0)).into(roomIV);
        roomPriceTV.setText("Room Price per night: " + rooms.getRoom().getPrice());
        roomNumberTV.setText("Room Number : " + rooms.getRoom().getNumber());
        bookingDurationTV.setText("Duration of booking : " + rooms.getDuration());

        extendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReq();
            }
        });

        return view;
    }

    public void sendReq() {
        Utilities.showLoadingDialog(getContext(), R.color.colorAccent);

        bookRoom = new ExtendBook(extendET.getText().toString());
        ApiEndpointInterface apiService =
                ApiClient.getClient(new AuthInterceptor(MyApplication.getPrefManager(getContext()).getUser().getToken())).create(ApiEndpointInterface.class);

        Call<ExtendResponse> call = apiService.extendRoom(bookRoom, user.getUser().getUser_id(), rooms.getId());
        call.enqueue(new Callback<ExtendResponse>() {
            @Override
            public void onResponse(Call<ExtendResponse> call, Response<ExtendResponse> response) {

                Utilities.dismissLoadingDialog();

                if (response.isSuccessful()) {
                Toast.makeText(getContext(), "Extend Successfully ... ", Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(getContext(), "Cannot Extend Order Canceled  ... ", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ExtendResponse> call, Throwable t) {
                Utilities.dismissLoadingDialog();
                Toast.makeText(getContext(), "Cannot extend room already accepted  ... ", Toast.LENGTH_LONG).show();


            }
        });

    }

    public void sendReqCancle() {

        Utilities.showLoadingDialog(getContext(), R.color.colorAccent);

        ApiEndpointInterface apiService =
                ApiClient.getClient(new AuthInterceptor(MyApplication.getPrefManager(getContext()).getUser().getToken())).create(ApiEndpointInterface.class);

        Call<CancelResponce> call = apiService.cancleRoom( user.getUser().getUser_id(), rooms.getId());
        call.enqueue(new Callback<CancelResponce>() {
            @Override
            public void onResponse(Call<CancelResponce> call, Response<CancelResponce> response) {

                Utilities.dismissLoadingDialog();

                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Cancle Successfully ... ", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CancelResponce> call, Throwable t) {
                Utilities.dismissLoadingDialog();
                Toast.makeText(getContext(), "Cannot cancle  room order  ... ", Toast.LENGTH_LONG).show();


            }
        });

    }



    public static Fragment newInstance(AllRoomsResponce allRoomsResponce) {
        Fragment fragment = new MyRoomFragment();
        rooms = allRoomsResponce;
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.roomMoreDetailsTV)
    public void onViewClicked() {
        RoomResponce roomsResponce = new RoomResponce(rooms.getRoom().getImgs(), rooms.getRoom().getCreationDate()
                , rooms.getRoom().getId(), rooms.getRoom().getNumber(), rooms.getRoom().getPrice(), rooms.getRoom().getDesc(), rooms.getRoom().getV());

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.cointaner, RoomDetailsFragment.newInstance(roomsResponce))
                .addToBackStack(null)
                .commit();
    }

    @OnClick({R.id.cancleBookingBTN, R.id.extendBookingBTN})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancleBookingBTN:
                sendReqCancle();
                break;
            case R.id.extendBookingBTN:
                extendCard.setVisibility(View.VISIBLE);
                break;
        }
    }
}
