package solutions.hamza.hotelorders.ui;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solutions.hamza.hotelorders.R;
import solutions.hamza.hotelorders.model.BookRoom;
import solutions.hamza.hotelorders.model.BookingRoomResponce;
import solutions.hamza.hotelorders.service.ApiClient;
import solutions.hamza.hotelorders.service.ApiEndpointInterface;
import solutions.hamza.hotelorders.service.AuthInterceptor;
import solutions.hamza.hotelorders.utils.MyApplication;
import solutions.hamza.hotelorders.utils.Utilities;

public class BookRoomFragment extends DialogFragment {

    @BindView(R.id.bookRoomB)
    Button bookRoomB;
    @BindView(R.id.bookRoomPhoneET)
    EditText bookRoomPhoneET;
    @BindView(R.id.bookRoomDurationET)
    EditText bookRoomDurationET;
    @BindView(R.id.bookRoomNotesET)
    EditText bookRoomNotesET;


    static String room_id;
    static BookRoom bookRoom;
    @BindView(R.id.cachIV)
    ImageView cachIV;
    @BindView(R.id.masterCardIV)
    ImageView masterCardIV;
    @BindView(R.id.visaIV)
    ImageView visaIV;
    @BindView(R.id.payingLayout)
    LinearLayout payingLayout;


    public static BookRoomFragment newInstance(String id) {
        BookRoomFragment fragment = new BookRoomFragment();
        room_id = id;
        return fragment;
    }

    public BookRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_room, container, false);
        ButterKnife.bind(this, view);

        bookRoomB.setOnClickListener(new View.OnClickListener() {
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

        bookRoom = new BookRoom(bookRoomPhoneET.getText().toString(), bookRoomDurationET.getText().toString(), bookRoomNotesET.getText().toString());
        Call<BookingRoomResponce> call = apiService.bookRoom(bookRoom, room_id);
        call.enqueue(new Callback<BookingRoomResponce>() {
            @Override
            public void onResponse(Call<BookingRoomResponce> call, Response<BookingRoomResponce> response) {
                Utilities.dismissLoadingDialog();
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Room Booked Successfully ... ", Toast.LENGTH_LONG).show();
                    dismiss();
                }
            }

            @Override
            public void onFailure(Call<BookingRoomResponce> call, Throwable t) {
                Utilities.dismissLoadingDialog();

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.cachIV, R.id.masterCardIV, R.id.visaIV})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cachIV:
                cachIV.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
                masterCardIV.setBackgroundColor(getContext().getResources().getColor(R.color.normal));
                visaIV.setBackgroundColor(getContext().getResources().getColor(R.color.normal));
                payingLayout.setVisibility(View.GONE);
                break;
            case R.id.masterCardIV:
                cachIV.setBackgroundColor(getContext().getResources().getColor(R.color.normal));
                masterCardIV.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
                visaIV.setBackgroundColor(getContext().getResources().getColor(R.color.normal));
                payingLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.visaIV:
                cachIV.setBackgroundColor(getContext().getResources().getColor(R.color.normal));
                visaIV.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
                masterCardIV.setBackgroundColor(getContext().getResources().getColor(R.color.normal));
                payingLayout.setVisibility(View.VISIBLE);
                break;
        }
    }
}
