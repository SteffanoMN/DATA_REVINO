package com.example.data_revino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.os.Bundle;
import java.util.ArrayList;

public class RV_Activity extends AppCompatActivity {

    private ArrayList<Client_Data> List;
    private RecyclerView recyclerView;
    private Adapter adapter;
    EditText search;
    CharSequence searchresult = "";
    private final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_v_);
        recyclerView = findViewById(R.id.rv_main);
        addData();
    }
    private void addData() {
        TextView tv_item = findViewById(R.id.tv_item);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("contacts");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Client_Data profile = dataSnapshot.getValue(Client_Data.class);
                    List.add(profile);
                }
                if (List != null) {
                    if (List.isEmpty()) tv_item.setVisibility(View.VISIBLE);
                    else tv_item.setVisibility(View.INVISIBLE);
                }

                adapter = new Adapter(getApplicationContext(), List);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        myRef.orderByChild("number").equalTo(List.get(position).getPhone()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String key = "";
                                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                    key = childSnapshot.getKey();
                                    Toast.makeText(getApplicationContext(), key, Toast.LENGTH_SHORT).show();
                                }

                                String name, phone,id,time,order,payment;

                                name = List.get(position).getName();
                                phone = List.get(position).getPhone();
                                id = List.get(position).getId();
                                time = List.get(position).getTime();
                                order = List.get(position).getOrder();
                                payment = List.get(position).getOrder();

                                Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("phone", phone);
                                intent.putExtra("id", id);
                                intent.putExtra("time",time);
                                intent.putExtra("order ", order);
                                intent.putExtra("paymeent",payment);
                                intent.putExtra("key", key);
                                startActivityForResult(intent,REQUEST_CODE);
                                Log.d(name, "onDataChange: ");

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK)
                addData();
        }
    }
}

