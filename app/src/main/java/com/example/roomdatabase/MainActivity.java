package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AppDatabase mDb;
    private TextView txt_list;
    private Button button;
    private EditText etName;
    private EditText etDesignation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_list = (TextView) findViewById(R.id.txt_List);
        etName = (EditText) findViewById(R.id.Name);
        etDesignation = (EditText) findViewById(R.id.Designation);
        button = (Button) findViewById(R.id.btnSave);
        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        populateEmployList();
        button.setOnClickListener(this);
    }
    private void populateEmployList() {
        txt_list.setText("");
        List<EmployeeEntity> employeeList = mDb.employDao().findAllEmploySync();
        for (EmployeeEntity employee : employeeList) {
            txt_list.append(employee.name + " : " + employee.designation);
            txt_list.append("\n");
        }
    }

    @Override
    public void onClick(View view) {
        String name = etName.getText().toString().trim();
        String designation = etDesignation.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(designation)) {
            Toast.makeText(this, "Name/Designation should not be empty", Toast.LENGTH_SHORT).show();
        } else {
            EmployeeEntity employee = new EmployeeEntity();
            employee.name = name;
            employee.designation = designation;
            mDb.employDao().insertEmploy(employee);
            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etDesignation.setText("");
            etName.requestFocus();
            populateEmployList();
        }
    }
}