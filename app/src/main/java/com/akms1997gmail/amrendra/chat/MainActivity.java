package com.akms1997gmail.amrendra.chat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance().getReference("Message");
        final TextView mytext = findViewById(R.id.text);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String[] Message = dataSnapshot.getValue().toString().split(",");
                mytext.setText(" ");
                for (int i=0;i<Message.length;i++){
                    String[] fMassage =Message[i].split("=");
                    mytext.append(fMassage[1] + "\n");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mytext.setText("Cancelled");
            }
        });
    }
    public void sendMessage(View view){
        EditText editText = findViewById(R.id.editText);
        db.child(Long.toString(System.currentTimeMillis())).setValue(editText.getText().toString());
        editText.setText(" ");
    }
}
