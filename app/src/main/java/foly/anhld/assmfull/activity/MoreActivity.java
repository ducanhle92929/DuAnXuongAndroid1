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

public class MoreActivity extends AppCompatActivity {

    private Button btnAdd;
    private EditText edtMaNV, edtTenNV, edtPhongBan;
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

        edtMaNV = findViewById(R.id.edtMaNV);
        edtTenNV = findViewById(R.id.edtTenNV);
        edtPhongBan = findViewById(R.id.edtPhongBan);
        btnAdd = findViewById(R.id.btnAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String manv = edtMaNV.getText().toString();
                String tennv = edtTenNV.getText().toString();
                String phongBan = edtPhongBan.getText().toString();

                if(manv.equals("")||tennv.equals("")||phongBan.equals("")) {
                    Toast.makeText(MoreActivity.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    NhanVIen addNV = new NhanVIen(manv, tennv, phongBan);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("NVMoi", addNV);
                    intent.putExtras(bundle);
                    setResult(1,intent);
                    finish();
                }
            }
        });


    }





}