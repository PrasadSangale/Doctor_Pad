package com.example.doctor_pad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    String TAG="EditProfile";
    String str;
    TextView profileName,profileAge,profilePhone_no,profileAddress;
    TextView phonenocamefromfirestore;

    ImageButton homebutton,finddoctor,fprofile,flogout;
    String p_Phone_no;

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String phone_no;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileName=(TextView) findViewById(R.id.namecamefromfirestore);
        profileAge=(TextView) findViewById(R.id.agecamefromfirestore);
        profilePhone_no=(TextView) findViewById(R.id.phonenocamefromfirestore);
        profileAddress=(TextView) findViewById(R.id.addresscamefromfirestore);

        phonenocamefromfirestore=findViewById(R.id.phonenocamefromfirestore);

        homebutton=findViewById(R.id.home_button);
        finddoctor=findViewById(R.id.doctor_button);
        fprofile=findViewById(R.id.profile_button);
        flogout=findViewById(R.id.logout_button);


        fAuth=FirebaseAuth.getInstance();
        fAuth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);
        fstore=FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        str = intent.getStringExtra("message_key");
        phonenocamefromfirestore.setText(str);
        Log.d(TAG, "onCreate: "+str+"*************************************************");
        Log.d(TAG, "onCreate: "+phonenocamefromfirestore+"*************************************************");

        p_Phone_no=fAuth.getCurrentUser().getUid();
        Log.d(TAG, "onCreate: "+p_Phone_no+"-------------------------------------------------------------========");





        DocumentReference documentReference=fstore.collection("Patient").document(str);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                profileName.setText(documentSnapshot.getString("p_Name"));
                profileAge.setText(documentSnapshot.getString("p_Age"));
                profilePhone_no.setText(documentSnapshot.getString("p_Phone_no"));
                profileAddress.setText(documentSnapshot.getString("p_Address"));

            }
        });


        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hoo=new Intent(EditProfile.this,PatientHomePage.class);
                startActivity(hoo);
            }
        });

        finddoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent foo=new Intent(EditProfile.this,AvailableDoctors.class);
                startActivity(foo);
            }
        });

        fprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent poo=new Intent(EditProfile.this,EditProfile.class);
                startActivity(poo);
            }
        });

        flogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
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
