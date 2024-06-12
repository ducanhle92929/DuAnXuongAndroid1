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

public class SuaActivity extends AppCompatActivity {

    private Button btnUpdate;
    private EditText edtMaNV, edtTenNV, edtPhongBan;
    private ArrayList<NhanVIen> usersArrayList = new ArrayList<>();
    private DatabaseNhanVien databaseNhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUpdate = findViewById(R.id.btnUpdate);
        edtMaNV = findViewById(R.id.edtMaNV);
        edtTenNV = findViewById(R.id.edtTenNV);
        edtPhongBan = findViewById(R.id.edtPhongBan);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        NhanVIen nhanVien = (NhanVIen) bundle.getSerializable("nhanVienSua");
        int viTriSua = bundle.getInt("viTriSua");
        edtTenNV.setText(String.valueOf(nhanVien.getHoTen()));
        edtMaNV.setText(String.valueOf(nhanVien.getMaNV()));
        edtPhongBan.setText(String.valueOf(nhanVien.getPhongBan()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maNV = edtMaNV.getText().toString();
                String tenNV = edtTenNV.getText().toString();
                String phongBan = edtPhongBan.getText().toString();

                if(maNV.equals("")||tenNV.equals("")||phongBan.equals("")) {
                    Toast.makeText(SuaActivity.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    NhanVIen nhanVienSua = new NhanVIen(maNV, tenNV, phongBan);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("nhanVienSua", nhanVienSua);
                    bundle.putInt("viTriSua", viTriSua);
                    intent.putExtras(bundle);
                    setResult(2, intent);
                    finish();
                }
            }
        });

    }
}