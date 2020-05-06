package com.example.learnfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DatabaseReference db_ref;
    MainDiary diary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView logout_text = findViewById(R.id.LogoutText);
        final EditText date_entry = findViewById(R.id.dateEntry);
        final EditText add_title = findViewById(R.id.addTitle);
        final EditText data_entry = findViewById(R.id.dataEntry);
        Button data_submit = findViewById(R.id.dataAddBtn);
        TextView view_diary = findViewById(R.id.viewDiary);
        db_ref = FirebaseDatabase.getInstance().getReference();
        diary = new MainDiary();

        data_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = date_entry.getText().toString().trim();
                String title = add_title.getText().toString().trim();
                String data = data_entry.getText().toString().trim();

                diary.setDate(date);
                diary.setTitle(title);
                diary.setData(data);

                db_ref.child(date).setValue(diary);

                Toast.makeText(MainActivity.this, "Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        logout_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logged Out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
            }
        });


    }
}
