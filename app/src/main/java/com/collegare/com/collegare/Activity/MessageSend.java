package com.collegare.com.collegare.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.collegare.com.collegare.Managers.AppManager;
import com.collegare.com.collegare.Managers.App_Config;
import com.collegare.com.collegare.Managers.DatabaseManager;
import com.collegare.com.collegare.Managers.InternetManager;
import com.collegare.com.collegare.Models.CollegareUser;
import com.collegare.com.collegare.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MessageSend extends AppCompatActivity implements View.OnClickListener{

    EditText rec;
    EditText msg;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_send);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        rec= (EditText) findViewById(R.id.rec);
        msg= (EditText) findViewById(R.id.msgContent);
        btn= (Button) findViewById(R.id.send);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        if(id==R.id.send){
            String post=msg.getText().toString();
            String receiver=rec.getText().toString();
            if(post.length()>5){
                if (InternetManager.getInstance(this).isConnectedToNet()) {
                    sendMsg(post,receiver);
                }
                else{
                    Snackbar.make(msg,"No Internet Connectivity",Snackbar.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void sendMsg(final String msg,final String receiver) {
        String TAG = "postReqSEND";


        CollegareUser user= DatabaseManager.getInstance(this).getUser();
        final String UserId= user.id;
        final String UserToken=user.token;

        StringRequest request = new StringRequest(Request.Method.POST, App_Config.Post_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object= new JSONObject(response);

                            if(object.getString("status").equals("0")){
                                Log.e("post sent", "");
                            }
                            else{

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("" + volleyError.toString(), "[error reported]");

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", "set");
                params.put("id", UserId);
                params.put("content",msg);
                params.put("rec",receiver);
                params.put("token",UserToken);
                return params;
            }

        };

        AppManager.getInstance().addToRequestQueue(request, "postSendReq", this);


    }
}
