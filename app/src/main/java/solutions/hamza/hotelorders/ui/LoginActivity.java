package solutions.hamza.hotelorders.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import solutions.hamza.hotelorders.R;
import solutions.hamza.hotelorders.model.User;
import solutions.hamza.hotelorders.model.UserResponce;
import solutions.hamza.hotelorders.service.ApiClient;
import solutions.hamza.hotelorders.service.ApiEndpointInterface;
import solutions.hamza.hotelorders.service.AuthInterceptor;
import solutions.hamza.hotelorders.utils.MyApplication;
import solutions.hamza.hotelorders.utils.Utilities;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.LoginemailET)
    EditText emailET;
    @BindView(R.id.LoginPasswordET)
    EditText passwordET;
    @BindView(R.id.loginB)
    Button loginB;
    @BindView(R.id.gmailloginTV)
    TextView gmailloginTV;
    @BindView(R.id.RegisterTV)
    TextView registerTV;
    String password, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });
    }

    private void get_EnteredData() {
        password = passwordET.getText().toString().trim();
        phone = emailET.getText().toString().trim();
    }


    @OnClick(R.id.loginB)
    void signIN() {
        get_EnteredData();

        if (phone.isEmpty()) {
            emailET.setError("Enter phone");
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            emailET.setError(getString(R.string.phone_format));
        } else if (password.isEmpty()) {
            passwordET.setError(getString(R.string.enter_password));
        } else {

            User user = new User(phone, password);
            Utilities.showLoadingDialog(LoginActivity.this, R.color.colorAccent);

            ApiEndpointInterface apiService =
                    ApiClient.getClient(new AuthInterceptor(null)).create(ApiEndpointInterface.class);

            Call<UserResponce> call = apiService.signIn(user);

            call.enqueue(new Callback<UserResponce>() {
                @Override
                public void onResponse(Call<UserResponce> call, Response<UserResponce> response) {
                    Utilities.dismissLoadingDialog();
                    if (response.isSuccessful()) {
                        MyApplication.getPrefManager(LoginActivity.this).storeUser(response.body());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        emailET.setError(getString(R.string.error_mail));
                    }
                }

                @Override
                public void onFailure(Call<UserResponce> call, Throwable t) {
                    Utilities.dismissLoadingDialog();

                }
            });
        }
    }
}
