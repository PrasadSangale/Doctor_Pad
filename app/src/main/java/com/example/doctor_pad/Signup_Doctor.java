package com.example.doctor_pad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class Signup_Doctor extends AppCompatActivity {
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    EditText editTextTextPersonName;
    EditText editTextNumber;
    EditText editTextNumber2;
    Button button3;
    Button button4;
    int SELECT_IMAGE_CODE=1;
    ImageView imageView;
    String d_Name;
    String d_Age;
    String d_Phone_no;
    String d_filename;
    Uri imageUri;
    FirebaseFirestore db;
    StorageReference storageReference;
    String fileName;

    Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_doctor);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        textView6=findViewById(R.id.textView6);
        editTextTextPersonName=findViewById(R.id.editTextTextPersonName);
        editTextNumber=findViewById(R.id.editTextNumber);
        editTextNumber2=findViewById(R.id.editTextNumber2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        imageView=findViewById(R.id.imageView);
        db=FirebaseFirestore.getInstance();
        fileName="";
        logger = Logger.getLogger(Signup_Doctor.class.getName());


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectImage();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d_Name=editTextTextPersonName.getText().toString();
                d_Age=editTextNumber.getText().toString();
                d_Phone_no=editTextNumber2.getText().toString();

                if (TextUtils.isEmpty(d_Name)) {
                    Toast.makeText(Signup_Doctor.this, "Please enter  name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(d_Age)) {
                    Toast.makeText(Signup_Doctor.this, "Please enter age", Toast.LENGTH_SHORT).show();
                }else if (Integer.parseInt(d_Age)> 60  || Integer.parseInt(d_Age)< 25) {
                    Toast.makeText(Signup_Doctor.this, "enter valid age", Toast.LENGTH_SHORT).show();
                }
                else if (editTextNumber2.length()!=10) {
                    Toast.makeText(Signup_Doctor.this, "Please enter valid phone number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(d_Phone_no)) {
                    Toast.makeText(Signup_Doctor.this, "Please enter Phone number", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat formatter=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
                    Date now=new Date();
                    fileName = formatter.format(now);

                    addDataToFirestore(d_Name, d_Age, d_Phone_no,fileName);
                }

                uploadImage();

            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && data != null && data.getData()!=null){

            imageUri=data.getData();
            imageView.setImageURI(imageUri);
        }
//        else {
//            assert data != null;
//            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(bitmap);
//
//        }
    }

    private void uploadImage() {


        storageReference=FirebaseStorage.getInstance().getReference("images/"+ fileName);


        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imageView.setImageURI(null);
                        Toast.makeText(Signup_Doctor.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Signup_Doctor.this, "Failed to Upload", Toast.LENGTH_SHORT).show();

                    }
                });



    }

    private void selectImage() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }



    private void addDataToFirestore(String d_name, String d_age, String d_phone_no,String d_filename) {
        CollectionReference dbDoctor=db.collection("Doctor");


        Doctor doctor=new Doctor(d_name,d_age,d_phone_no,d_filename);
        dbDoctor.document(d_phone_no).set(doctor);

        dbDoctor.add(doctor).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(Signup_Doctor.this, "Succesfully Doctor Register", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Signup_Doctor.this, "Failed to Register Patient" + e, Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = new Intent(getApplicationContext(), PatientHomePage.class);
        intent.putExtra("message_key1", d_Phone_no);


        startActivity(new Intent(Signup_Doctor.this,DoctorHomePage.class));

    }


}