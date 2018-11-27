package solutions.hamza.hotelorders.service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import solutions.hamza.hotelorders.model.AllRoomsResponce;
import solutions.hamza.hotelorders.model.BookRoom;
import solutions.hamza.hotelorders.model.BookingRoomResponce;
import solutions.hamza.hotelorders.model.CancelResponce;
import solutions.hamza.hotelorders.model.ExtendBook;
import solutions.hamza.hotelorders.model.ExtendResponse;
import solutions.hamza.hotelorders.model.OrderResponce;
import solutions.hamza.hotelorders.model.RoomResponce;
import solutions.hamza.hotelorders.model.ServicesResponce;
import solutions.hamza.hotelorders.model.User;
import solutions.hamza.hotelorders.model.UserResponce;

public interface ApiEndpointInterface {

    @POST("signup")
    Call<UserResponce> signUp(@Body User user);

    @POST("login")
    Call<UserResponce> signIn(@Body User user);

    @GET("service")
    Call<ArrayList<ServicesResponce>> getServices();

    @POST("service/{id}/customer-order")
    Call<OrderResponce> addOrder(@Body OrderResponce orderResponce, @Path("id") String id);

    @GET("room")
    Call<ArrayList<RoomResponce>> getRooms();

    @POST("room/{id}/customer-order")
    Call<BookingRoomResponce> bookRoom(@Body BookRoom bookRoom, @Path("id") String id);

    @GET("room/{id}/customer-order")
    Call<ArrayList<AllRoomsResponce>> getAllRooms(@Path("id") String id);

    @PUT("room/{id}/customer-order/{room_id}")
    Call<ExtendResponse> extendRoom(@Body ExtendBook bookRoom, @Path("id") String id, @Path("room_id") String room_id);

    @PUT("room/{id}/admin/{room_id}/cancle")
    Call<CancelResponce> cancleRoom(@Path("id") String id, @Path("room_id") String room_id);



}


