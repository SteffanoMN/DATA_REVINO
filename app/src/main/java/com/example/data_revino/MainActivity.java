     package com.example.data_revino;

import androidx.appcompat.app.AppCompatActivity;
import  androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private String name, phone, id, time, order, payment;
    private ArrayList<Client_Data> List;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AL FIREBASE
        List = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataclient = database.getReference("contacts");

        TextInputEditText txt_name = findViewById(R.id.txt_name);
        TextInputEditText txt_phone = findViewById(R.id.txt_number);
        TextInputEditText txt_id = findViewById(R.id.txt_id);
        TextInputEditText txt_time = findViewById(R.id.txt_time);
        TextInputEditText txt_order = findViewById(R.id.txt_order);
        TextInputEditText txt_payment = findViewById(R.id.txt_payment);
        Button btn_add = findViewById(R.id.btn_add);
        Button btn_intent = findViewById(R.id.btn_intent);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txt_name.getText().toString();
                phone = txt_phone.getText().toString();
                id = txt_id.getText().toString();
                time = txt_time.getText().toString();
                order = txt_order.getText().toString();
                payment = txt_payment.getText().toString();


                if (name.trim().isEmpty() || phone.trim().isEmpty() || id.trim().isEmpty() || time.trim().isEmpty() || order.trim().isEmpty() || payment.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill the empty bar !", Toast.LENGTH_SHORT).show();
                } else {

                    String key = dataclient.push().getKey();
                    Client_Data profile = new Client_Data(name, phone, id, time, order, payment);
                    dataclient.child(key).setValue(profile);


                    txt_name.setText("");
                    txt_phone.setText("");
                    txt_id.setText("");
                    txt_time.setText("");
                    txt_order.setText("");
                    txt_payment.setText("");

                    Toast.makeText(getApplicationContext(), "Data has been added !", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RV_Activity.class);
                startActivity(intent);
            }
        });
    }
}