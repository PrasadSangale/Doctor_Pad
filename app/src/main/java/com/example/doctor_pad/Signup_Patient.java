package com.example.doctor_pad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class Signup_Patient extends AppCompatActivity {
    String TAG="Signup_Patient";

    FirebaseAuth fAuth;
    FirebaseFirestore db;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView7;
    TextView textView8;
    EditText editTextTextPersonName;
    EditText editTextNumber;
    EditText editTextNumber2;
    EditText editTextTextMultiLine;
    Spinner spinner;
    Button button3;

    String p_Name;
    String p_Age;
    String p_Phone_no;
    String p_Address;
    String p_Bloodgroup;
    private AwesomeValidation awesomeValidation;

    ArrayAdapter<String> arrayAdapter;


    String[] bloodgroup={"A+","A-","B+","B-","O+","O-","AB+","AB-"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_patient);

        fAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView7=findViewById(R.id.textView7);
        textView8=findViewById(R.id.textView8);
        editTextTextPersonName=findViewById(R.id.editTextTextPersonName);
        editTextNumber=findViewById(R.id.editTextNumber);
        editTextNumber2=findViewById(R.id.editTextNumber2);
        editTextTextMultiLine=findViewById(R.id.editTextTextMultiLine);
        spinner=findViewById(R.id.spinner);
        button3=findViewById(R.id.button3);

        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,bloodgroup);
        spinner.setAdapter(arrayAdapter);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p_Name=editTextTextPersonName.getText().toString();
                p_Age=editTextNumber.getText().toString();
                p_Phone_no=editTextNumber2.getText().toString();
                p_Address=editTextTextMultiLine.getText().toString();
                int inputInt = Integer.parseInt(p_Age);



                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Object selected_item=adapterView.getItemAtPosition(i);
                        p_Bloodgroup=(String) selected_item;
                        Toast.makeText(Signup_Patient.this, p_Bloodgroup, Toast.LENGTH_SHORT).show();

                    }


                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        Toast.makeText(Signup_Patient.this, "Select your Blood group", Toast.LENGTH_SHORT).show();
                    }
                });
                if (TextUtils.isEmpty(p_Name)) {
                    Toast.makeText(Signup_Patient.this, "Please enter name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(p_Age)) {
                    Toast.makeText(Signup_Patient.this, "Please enter age", Toast.LENGTH_SHORT).show();
                }

                else if (Integer.parseInt(p_Age)> 100  || Integer.parseInt(p_Age)< 1) {
                    Toast.makeText(Signup_Patient.this, "enter valid age", Toast.LENGTH_SHORT).show();
                }

                else if (TextUtils.isEmpty(p_Phone_no)) {
                    Toast.makeText(Signup_Patient.this, "Please enter Phone number", Toast.LENGTH_SHORT).show();
                }

                else if (editTextNumber2.length()!=10) {
                    Toast.makeText(Signup_Patient.this, "Please enter valid Phone number", Toast.LENGTH_SHORT).show();
                }

                else if (TextUtils.isEmpty(p_Address)) {
                    Toast.makeText(Signup_Patient.this, "Please enter address", Toast.LENGTH_SHORT).show();
                }else {

                    addDataToFirestore(p_Name, p_Age, p_Phone_no,p_Address,p_Bloodgroup);
                }
            }
        });
    }

    private void addDataToFirestore(String p_name, String p_age, String p_phone_no, String p_address, String p_bloodgroup) {

        CollectionReference dbPatient = db.collection("Patient");

        Patient patient=new Patient(p_name,p_age,p_phone_no,p_address,p_bloodgroup);

        dbPatient.document(p_phone_no).set(patient);
        //Log.d(TAG, "addDataToFirestore: "+"=============================================================");

        Intent intent = new Intent(getApplicationContext(), PatientHomePage.class);
        intent.putExtra("message_key", p_Phone_no);

        startActivity(intent);
        //Log.d(TAG, "onClick: "+p_Phone_no+"**********************************************************");




    }

}