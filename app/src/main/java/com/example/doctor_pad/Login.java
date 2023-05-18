package com.example.doctor_pad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
    TextView textView9;
    TextView textView10;
    EditText editTextNumber3;
    EditText editTextNumber4;
    Button button6;
    Button button7;
    FirebaseAuth mAuth;
    String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        textView9=findViewById(R.id.textView9);
        textView10=findViewById(R.id.textView10);
        editTextNumber3=findViewById(R.id.editTextNumber3);
        editTextNumber4=findViewById(R.id.editTextNumber4);
        button6=findViewById(R.id.button6);
        button7=findViewById(R.id.button7);



        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(textView9.getText().toString())){
                    Toast.makeText(Login.this, "Please Enter a valid phone number", Toast.LENGTH_SHORT).show();
                }
                else {
                    String phone="+91" + editTextNumber3.getText().toString();
                    sendVerificationCode(phone);
                }
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editTextNumber4.getText().toString())){
                    Toast.makeText(Login.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                }
                else {
                    verifyCode(editTextNumber4.getText().toString());
                }
            }
        });

    }
    public void signInWithCredential(PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent i=new Intent(Login.this,PatientHomePage.class);
                            startActivity(i);
                            finish();
                        }
                        else {
                            Toast.makeText(Login.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    private void sendVerificationCode(String phone) {

        String _number = phone;

        System.out.println(_number);

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(_number)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationId=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            final String code=phoneAuthCredential.getSmsCode();

            if (code!=null){
                editTextNumber4.setText(code);

                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    };

    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }
}