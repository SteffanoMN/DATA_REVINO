package com.example.data_revino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.os.Bundle;

public class DeleteActivity extends AppCompatActivity {
    private  String name,phone,id,time,order,payment,key;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        TextInputEditText txt_name = findViewById(R.id.txt_name_edit);
        TextInputEditText txt_phone = findViewById(R.id.txt_number_edit);
        TextInputEditText txt_id = findViewById(R.id.txt_id_edit);
        TextInputEditText txt_time = findViewById(R.id.txt_time_edit);
        TextInputEditText txt_order = findViewById(R.id.txt_order_edit);
        TextInputEditText txt_payment = findViewById(R.id.txt_payment_edit);
        Button btn_delete = findViewById(R.id.btn_delete);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            phone = bundle.getString("phone");
            id = bundle.getString("id");
            time = bundle.getString("time");
            order = bundle.getString("order");
            payment = bundle.getString("payment");
            key = bundle.getString("key");

            txt_name.setText(name);
            txt_phone.setText(phone);
            txt_id.setText(id);
            txt_time.setText(time);
            txt_order.setText(order);
            txt_payment.setText(payment);
        }


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference().child("contacts").child(key);

                ProgressDialog progressDialog = new ProgressDialog(DeleteActivity.this);
                progressDialog.setMessage("Menghapus data anda...");
                progressDialog.show();
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            dataSnapshot.getRef().removeValue();
                        }
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Data has been deleted !", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                    }

                });
            }
        });
    }
}
