package com.example.btktck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class thongtincanhan extends AppCompatActivity {

    DatabaseReference db = FirebaseDatabase.getInstance().getReferenceFromUrl("https://btktck-f6b44-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtincanhan);

        final EditText nhom = findViewById(R.id.txtnhom);
        final EditText tensv = findViewById(R.id.txtsv);
        final EditText masv = findViewById(R.id.txtmsv);

        final Button dangki = findViewById(R.id.Dangki);
        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nhomtxt = nhom.getText().toString();
                final String tentxt = tensv.getText().toString();
                final String masvtxt = masv.getText().toString();


                db.child("nhom").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        db.child("nhom").child(nhomtxt).child("ten").setValue(tentxt);
                        db.child("nhom").child(nhomtxt).child("masv").setValue(masvtxt);

                        Toast.makeText(thongtincanhan.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}