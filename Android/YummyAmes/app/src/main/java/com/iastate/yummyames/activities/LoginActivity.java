package com.iastate.yummyames.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.iastate.yummyames.R;
import com.iastate.yummyames.connection.MySingleton;
import com.iastate.yummyames.fragments.UserFragment;
import com.iastate.yummyames.objects.Customer;
import com.iastate.yummyames.singletons.CustomerSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{

    private static String lgoin_url = "http://proj-309-sd-b-4.cs.iastate.edu/android/login.php";

    private Button loginBtn, guestBtn;
    private EditText et_username, et_password;
    private TextView login_to_register;
    private String username, password;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        builder = new AlertDialog.Builder(LoginActivity.this);

        loginBtn = (Button) findViewById(R.id.login_button);
        guestBtn = (Button) findViewById(R.id.guest);

        et_username = (EditText) findViewById(R.id.login_username);
        et_password = (EditText) findViewById(R.id.login_password);

        login_to_register = (TextView) findViewById(R.id.login_to_register);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


        login_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        guestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void login(){
        username = et_username.getText().toString();
        password = et_password.getText().toString();

        if(username.equals("") || password.equals("")) {
            builder.setTitle("Something went wrong...");
            displayAlert("Please Enter Both Fields");
        }
        else{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, lgoin_url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");

                                if(code.equals("login_failed"))
                                {
                                    builder.setTitle("Login Error");
                                    displayAlert(jsonObject.getString("message"));
                                }
                                else
                                {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    String name = jsonObject.getString("c_name");
                                    String address = jsonObject.getString("c_address");
                                    String phone = jsonObject.getString("c_phone");
                                    String email = jsonObject.getString("c_email");
                                    Customer customer = new Customer(name, address, phone, email);
                                    CustomerSingleton.getInstance().setCustomer(customer);
                                    startActivity(intent);
                                }

                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parms = new HashMap<String, String>();
                    parms.put("c_username", username);
                    parms.put("c_pwd", password);
                    return parms;
                }
            };

            MySingleton.getInstance(LoginActivity.this).addToRequestqueue(stringRequest);
        }





    }

    public void displayAlert(final String message){

        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                et_username.setText("");
                et_password.setText("");
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



}
