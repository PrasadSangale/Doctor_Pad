package com.example.doctor_pad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DoctorHomePage extends AppCompatActivity {

    ImageButton homebutton,finddoctor,fprofile,flogout;
    ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);


        homebutton=findViewById(R.id.home_button);
        finddoctor=findViewById(R.id.doctor_button);
        fprofile=findViewById(R.id.profile_button);
        flogout=findViewById(R.id.logout_button);
        imageView3=findViewById(R.id.imageView3);

        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                intent.putExtra("message_key1", str);
                startActivity(intent);
            }
        });


        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hoo=new Intent(DoctorHomePage.this,DoctorHomePage.class);
                startActivity(hoo);
            }
        });

        finddoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foo=new Intent(DoctorHomePage.this,AvailableDoctors.class);
                startActivity(foo);
            }
        });

        fprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent poo=new Intent(DoctorHomePage.this,EditProfile.class);
                startActivity(poo);
            }
        });

        flogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DoctorHomePage.this);
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