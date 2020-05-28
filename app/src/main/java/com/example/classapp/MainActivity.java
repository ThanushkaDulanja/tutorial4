package com.example.classapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classapp.Database.DBHandler;

import java.util.List;

// Name:Td Balasooriya
// Registration no : IT18212150

public class MainActivity extends AppCompatActivity {

    EditText txtUserName, txtPassword;
    Button btnSelectAll, btnDelete, btnUpdate, btnAdd, btnSignIn;
    DBHandler userDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDb = new DBHandler(this);
        txtUserName = findViewById(R.id.unField);
        txtPassword = findViewById(R.id.pwField);
        btnSelectAll = findViewById(R.id.SelectAllbutton);
        btnAdd = findViewById(R.id.Addbutton);
        btnDelete = findViewById(R.id.Deletebutton);
        btnSignIn = findViewById(R.id.SignInbutton);
        btnUpdate = findViewById(R.id.Updatebutton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDb.addInfo(txtUserName.getText().toString(), txtPassword.getText().toString())) {
                    Toast.makeText(MainActivity.this, "New record has been added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Cannot added record", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List result = userDb.readAllInfo();
                if (result != null) {
                    for (int i = 0; i < result.size(); i++) {
                        Log.d("Record", "new record" + result.get(i));
                    }
                } else
                    showMsg();
            }

            private void showMsg() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setMessage("No Student Found");
                builder.show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDb.updateInfo(txtUserName.getText().toString(), txtPassword.getText().toString());
                Toast.makeText(MainActivity.this, "Updated Successfully", Toast.LENGTH_LONG).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDb.deleteInfo(txtUserName.getText().toString());
                Toast.makeText(MainActivity.this, "Deleted Successfully", Toast.LENGTH_LONG).show();
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDb.readInfo(txtUserName.getText().toString(), txtPassword.getText().toString()))
                    Toast.makeText(MainActivity.this, "UserName and password already exist", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "UserName and password not exist", Toast.LENGTH_LONG).show();
            }
        });
    }

}
