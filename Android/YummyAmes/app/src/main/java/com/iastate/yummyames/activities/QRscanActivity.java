package com.iastate.yummyames.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.iastate.yummyames.R;
import com.iastate.yummyames.connection.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class QRscanActivity extends AppCompatActivity {

    private static String complete_url = "http://proj-309-sd-b-4.cs.iastate.edu/android/complete.php";

    private Button scan_btn;
    private String resultString;
    private String order_id = "26";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);

        scan_btn = (Button)findViewById(R.id.scan_btn);
        final Activity activity = this;

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            resultString = result.getContents();
            if(resultString==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else if(resultString.equals(order_id)){
                completeOrder();
                Toast.makeText(this, "Thank Your For Ordering With Us!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Please Use a Valid Code", Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void completeOrder()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, complete_url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
//                        try{
//                            JSONArray jsonArray = new JSONArray(response);
//                            JSONObject jsonObject = jsonArray.getJSONObject(0);
//                            String code = jsonObject.getString("code");
//                            String message = jsonObject.getString("message");
//                            builder.setTitle("Server Response...");
//                            builder.setMessage(message);
//                            displayAlert(code);
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                        }
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
                parms.put("o_id", resultString);
                return parms;
            }
        };

        MySingleton.getInstance(QRscanActivity.this).addToRequestqueue(stringRequest);
    }

}
