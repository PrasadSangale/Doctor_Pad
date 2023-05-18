package com.example.doctor_pad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class PatientHomePage extends AppCompatActivity {
    String TAG="PatientHomePage";

    Button seeavailabledoctors,logout,seeall;
    ImageView heart,lungs,brain;
    ImageView profile;
    ImageButton homebutton,finddoctor,fprofile,flogout;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_page);

        seeavailabledoctors=findViewById(R.id.button3);
        seeall=findViewById(R.id.see_all);
        logout=findViewById(R.id.button2);
        heart=findViewById(R.id.cardiologist);
        brain=findViewById(R.id.neurologist);
        lungs= findViewById(R.id.pulmonologist);
        profile=findViewById(R.id.imageView);

        homebutton=findViewById(R.id.home_button);
        finddoctor=findViewById(R.id.doctor_button);
        fprofile=findViewById(R.id.profile_button);
        flogout=findViewById(R.id.logout_button);

        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");


        Log.d(TAG, "onCreate: "+str+"********************************************************************************");
        String[] doctors={"DR. ARJAV VAJIR","DR. ASMI SHAH","DR. FARHAN SHEIKH","DR. DIKSHA JAIN","DR. MISHRI VAJIR","DR. SMITA DAVE","DR. RAHUL SHAH","DR. SMIT VASH","DR. SALONI MEHTA"};

        AutoCompleteTextView autoCompleteTextView=findViewById(R.id.autocompleteTextView);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,doctors);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);



        seeavailabledoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PatientHomePage.this,AvailableDoctors.class);
                startActivity(i);
            }
        });


        seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seea=new Intent(PatientHomePage.this,AvailableDoctors.class);
                startActivity(seea);
            }
        });


//        lungs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent pul=new Intent(PatientHomePage.this,pulmonologist.class);
//                startActivity(pul);
//            }
//        });

        lungs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PatientHomePage.this,pulmonologist.class);
                startActivity(i);
            }
        });

        brain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent neu=new Intent(PatientHomePage.this,neurologist.class);
                startActivity(neu);
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(PatientHomePage.this,cardiologist.class);
                startActivity(i);
            }
        });



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                intent.putExtra("message_key", str);
                startActivity(intent);
            }
        });



        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hoo=new Intent(PatientHomePage.this,PatientHomePage.class);
                startActivity(hoo);
            }
        });

        finddoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foo=new Intent(PatientHomePage.this,AvailableDoctors.class);
                startActivity(foo);
            }
        });

        fprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iii=new Intent(PatientHomePage.this,EditProfile.class);
                startActivity(iii);
            }
        });

        flogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PatientHomePage.this);
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