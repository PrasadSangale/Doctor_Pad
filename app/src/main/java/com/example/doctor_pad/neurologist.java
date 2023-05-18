package com.example.doctor_pad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class neurologist extends AppCompatActivity {

    RecyclerView rv;
    String doctor_name[],description[],location[];
    RecyclerView.LayoutManager mLayoutManager;
    int imgs[] = { R.drawable.karan,R.drawable.smit,R.drawable.gaurangi,R.drawable.sanjouy,
            R.drawable.rushabh,R.drawable.prasad,R.drawable.madhuri,R.drawable.zenith,R.drawable.madhuri,
            R.drawable.rajesh
    };
    ImageButton homebutton,finddoctor,fprofile,flogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neurologist);
        rv=findViewById(R.id.rr10);
        doctor_name = getResources().getStringArray(R.array.neuro_names);
        description = getResources().getStringArray(R.array.neurologist);
        location = getResources().getStringArray(R.array.location);

        homebutton=findViewById(R.id.home_button);
        finddoctor=findViewById(R.id.doctor_button);
        fprofile=findViewById(R.id.profile_button);
        flogout=findViewById(R.id.logout_button);

        MyAdapter adapter;
        adapter = new MyAdapter(this,doctor_name,description,location,imgs);
        rv.setAdapter(adapter);

        mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hoo=new Intent(neurologist.this,PatientHomePage.class);
                startActivity(hoo);
            }
        });

        finddoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foo=new Intent(neurologist.this,AvailableDoctors.class);
                startActivity(foo);
            }
        });

        fprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent poo=new Intent(neurologist.this,EditProfile.class);
                startActivity(poo);
            }
        });

        flogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(neurologist.this);
                builder.setTitle("Confirmation PopUp!").
                        setMessage("You sure, that you want to logout?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(getApplicationContext(),
                                        MainActivity.class);
                                startActivity(i);
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });

    }
}