package com.example.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data_model.user;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_dangky extends AppCompatActivity {

    TextView login;
    EditText username,password,name,address,phone;
    Button dangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        login=(TextView)findViewById(R.id.login);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        name=(EditText)findViewById(R.id.name);
        address=(EditText)findViewById(R.id.address);
        phone=(EditText)findViewById(R.id.phone);
        dangky=(Button) findViewById(R.id.button_dangky);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity_dangky.this,activity_login.class);
                startActivity(intent);
            }
        });
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().replaceAll("\\s+", "").isEmpty() || password.getText().toString().replaceAll("\\s+", "").isEmpty() || name.getText().toString().replaceAll("\\s+", "").isEmpty() || address.getText().toString().replaceAll("\\s+", "").isEmpty() || phone.getText().toString().replaceAll("\\s+", "").isEmpty()) {
                    Toast.makeText(activity_dangky.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_LONG).show();
                } else {
                    if (username.getText().toString().contains(" ")) {
                        Toast.makeText(activity_dangky.this, "Tên đăng nhập không hợp lệ", Toast.LENGTH_LONG).show();
                    } else {
                        user user = new user(username.getText().toString(), password.getText().toString(), name.getText().toString(), address.getText().toString(), phone.getText().toString());
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                        db.child("account").child(username.getText().toString()).setValue(user);
                        db.child("favorite").child(user.getUsername()).setValue("");
                        Toast.makeText(activity_dangky.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(activity_dangky.this, activity_login.class);
                        startActivity(intent);
                    }
                }
            }
        });

    }
}
