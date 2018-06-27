package com.iastate.yummyames.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.iastate.yummyames.R;
import com.iastate.yummyames.connection.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class RegisterActivity extends AppCompatActivity{

    private Button regBtn;
    private TextView register_to_login;

    private EditText et_name, et_username, et_password, et_email, et_address, et_phone;
    private String name, username, password, email, address, phone;

    private static String reg_url = "http://proj-309-sd-b-4.cs.iastate.edu/android/register.php";

    private AlertDialog.Builder builder;

    private Spinner user_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regBtn = (Button)findViewById(R.id.register_button);
        register_to_login = (TextView)findViewById(R.id.register_to_login);
        et_name = (EditText)findViewById(R.id.name);
        et_username = (EditText)findViewById(R.id.username);
        et_password = (EditText)findViewById(R.id.password);
        et_email = (EditText)findViewById(R.id.email);
        et_address = (EditText)findViewById(R.id.address);
        et_phone = (EditText)findViewById(R.id.phone);
        builder = new AlertDialog.Builder(RegisterActivity.this);
        user_type = (Spinner)findViewById(R.id.register_spinner);



        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        register_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });


    }


    private void register(){

        name = et_name.getText().toString();
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        email = et_email.getText().toString();
        address = et_address.getText().toString();
        phone = et_phone.getText().toString();


        if(name.equals("") || username.equals("") || password.equals("") || email.equals("") || address.equals("") || phone.equals("")){
            builder.setTitle("Something went wrong...");
            builder.setMessage("Please fill all the fields...");
            displayAlert("input_error");
        }
        else{
            //Toast.makeText(getApplicationContext(), , Toast.LENGTH_LONG).show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, reg_url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            try{
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("message");
                                builder.setTitle("Server Response...");
                                builder.setMessage(message);
                                displayAlert(code);
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
                    parms.put("c_id", "24");
                    parms.put("c_name", name);
                    parms.put("c_address", address);
                    parms.put("c_phone", phone);
                    parms.put("c_email", email);
                    parms.put("c_username", username);
                    parms.put("c_pwd", password);
                    parms.put("c_date", "2017/11/3");//Calendar.getInstance().getTime().toString());
                    return parms;
                }
            };

            MySingleton.getInstance(RegisterActivity.this).addToRequestqueue(stringRequest);
        }



    }

    public void displayAlert(final String code){

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(code.equals("input_error")){
                    et_password.setText("");
                }
                else if(code.equals("reg_success")){
                    finish();
                }
                else if(code.equals("reg_failed")){
                    et_name.setText("");
                    et_username.setText("");
                    et_password.setText("");
                    et_email.setText("");
                    et_address.setText("");
                    et_phone.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



}
