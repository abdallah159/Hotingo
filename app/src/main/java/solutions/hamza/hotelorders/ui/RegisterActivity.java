package solutions.hamza.hotelorders.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.UsernameET)
    EditText UsernameET;
    @BindView(R.id.emailET)
    EditText emailET;
    @BindView(R.id.PasswordET)
    EditText PasswordET;
    @BindView(R.id.CpasswordET)
    EditText CpasswordET;
    @BindView(R.id.phoneET)
    EditText phoneET;
    @BindView(R.id.registerB)
    Button registerB;

    String userName, password, cPassword, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        get_EnteredData();
    }

    private void get_EnteredData() {
        userName = UsernameET.getText().toString().trim();
        password = PasswordET.getText().toString().trim();
        cPassword = CpasswordET.getText().toString().trim();
        email = emailET.getText().toString().trim();
        phone = phoneET.getText().toString().trim();
    }

    @OnClick(R.id.registerB)
    void signUp() {
        get_EnteredData();

        if (userName.isEmpty()) {
            UsernameET.setError(getString(R.string.enter_name));
        } else if (email.isEmpty()) {
            emailET.setError(getString(R.string.enter_email));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError(getString(R.string.email_not_formatted));
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            phoneET.setError(getString(R.string.phone_format));
        } else if (password.isEmpty()) {
            PasswordET.setError(getString(R.string.enter_password));
        } else if (cPassword.isEmpty()) {
            CpasswordET.setError(getString(R.string.enter_cpassword));
        } else if (password.equals(cPassword) != true) {
            CpasswordET.setError(getString(R.string.confirm_pass_error));
        } else if (phone.length() < 6) {
            phoneET.setError(getString(R.string.phone_format));
        } else {
            //
            Utilities.showLoadingDialog(RegisterActivity.this, R.color.colorAccent);
            final User user = new User(userName, email, password, phone);

            ApiEndpointInterface apiService =
                    ApiClient.getClient(new AuthInterceptor(null)).create(ApiEndpointInterface.class);

            Call<UserResponce> call = apiService.signUp(user);

            call.enqueue(new Callback<UserResponce>() {
                @Override
                public void onResponse(Call<UserResponce> call, Response<UserResponce> response) {

                    Utilities.dismissLoadingDialog();
                    if (response.isSuccessful()) {
                        MyApplication.getPrefManager(RegisterActivity.this).storeUser(response.body());
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Phone Exist please try again", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<UserResponce> call, Throwable t) {
                    Utilities.dismissLoadingDialog();
                    Toast.makeText(RegisterActivity.this, "Please Enter Valid Information", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
