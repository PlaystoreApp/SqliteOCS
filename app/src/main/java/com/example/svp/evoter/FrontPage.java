package com.example.svp.evoter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Krishnan on 21-06-2016.
 */
public class FrontPage extends AppCompatActivity {





        ArrayList<String> u_name,u_age,u_id,u_place;
        ListView list1;

        Map<String,String> jsdata;
        Button refresh,BtnAdd,BtnJson;
        DataBase db;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            db = new DataBase(getApplicationContext());
            super.onCreate(savedInstanceState);
            setContentView(R.layout.new_layout);


            list1 = (ListView) findViewById(R.id.list_view1);




            refresh = (Button) findViewById(R.id.btn_refresh);
            BtnAdd = (Button) findViewById(R.id.btn_add);
            BtnJson  = (Button) findViewById(R.id.btn_json);



            u_name = new ArrayList();
            u_age = new ArrayList();
            u_id = new ArrayList();
            u_place = new ArrayList();



            BtnJson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{
                        JSONArray jsonArray = db.getAll();
//                  //  for (int i = 0; i < jsonArray.length(); i++) {
//
                        Toast.makeText(getApplicationContext(), "" + jsonArray, Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){

                    }


                }
            });




                    BtnAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(FrontPage.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });


                    refresh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                u_name.clear();
                                u_age.clear();
                                u_id.clear();
                                u_place.clear();


                                JSONArray jsonArray = db.getAll();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = new JSONObject((jsonArray.get(i)).toString());
                                    u_name.add(jsonObject.getString("name"));
                                    u_age.add(jsonObject.getString("age"));
                                    u_id.add(jsonObject.getString("id"));
                                    u_place.add(jsonObject.getString("place"));

//                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("name"), Toast.LENGTH_SHORT).show();

                                }

                            } catch (Exception e) {

                            }
                            CustomList customList = new CustomList(FrontPage.this, u_name, u_age, u_id, u_place);
                            list1.setAdapter(customList);
                           // Log.d("submit", u_name.toString());

                            //  Log.d("JSON DATA IS",jsonObject.toString());

                        }
                    });



                }


    // ..........................................alert exit................................................

    public void onBackPressed() {

        new AlertDialog.Builder(this)

                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Are you sure you want to close this App?")

                .setPositiveButton("Yes", new DialogInterface.OnClickListener()

                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                })
                .setNegativeButton("No", null).show();


    }


            }
