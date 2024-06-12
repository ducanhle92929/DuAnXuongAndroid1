package foly.anhld.assmfull.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import foly.anhld.assmfull.Database.DatabaseNhanVien;
import foly.anhld.assmfull.R;
import foly.anhld.assmfull.model.NhanVIen;

public class MorePActivity extends AppCompatActivity {
    private Button btnAdd;
    private EditText edtName, edtPassword, edtConfirm;
    private ArrayList<NhanVIen> usersArrayList = new ArrayList<>();
    private DatabaseNhanVien databaseNhanVien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_more);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtName = findViewById(R.id.edtMaNV);
        edtPassword = findViewById(R.id.edtTenNV);
        edtConfirm = findViewById(R.id.edtPhongBan);
        btnAdd = findViewById(R.id.btnAdd);
        databaseNhanVien = new DatabaseNhanVien(this);
        usersArrayList = databaseNhanVien.readFromFile("nhan_vien.txt");
        if (usersArrayList == null) {
            usersArrayList = new ArrayList<>();
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
            }
        });


    }

    private void setData() {

        if (edtName.getText().toString()==null || edtName.getText().toString().isEmpty()){
            Toast.makeText(this, "Nhap mã", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtPassword.getText().toString()==null || edtPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Nhập Họ Tên", Toast.LENGTH_SHORT).show();
            return;
        }
        if ( edtConfirm.getText().toString().isEmpty()){
            Toast.makeText(this, "Nhập phòng ban", Toast.LENGTH_SHORT).show();
            return;
        }

//        if (!edtConfirm.getText().toString().equals(edtPassword.getText().toString())){
//            Toast.makeText(this, "mat khau va xac nhan khong trung nhau", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        fileMore = new FileMore(MoreActivity.this);
//        usersArrayList = fileMore.readFromFile("user.txt");
//        PhongBan PhongBan = new PhongBan(edtName.getText().toString(), edtPassword.getText().toString(),
//                edtConfirm.getText().toString(), R.drawable.pen_solid, R.drawable.trash);
//


        NhanVIen nhanVIen = new NhanVIen(
                edtName.getText().toString(),
                edtPassword.getText().toString(),
                edtConfirm.getText().toString()
        );

        usersArrayList.add(nhanVIen);
        databaseNhanVien.WriteToFile(usersArrayList, "nhan_vien.txt");
        nextScreen();

    }

    public void nextScreen() {
        Intent mIntent = new Intent(MorePActivity.this, PhongBanActivity.class);
        startActivity(mIntent);
    }
}