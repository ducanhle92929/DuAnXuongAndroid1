package foly.anhld.assmfull.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import foly.anhld.assmfull.Database.DatabaseNhanVien;
import foly.anhld.assmfull.R;
import foly.anhld.assmfull.adapter.PhongBanAdapter;
import foly.anhld.assmfull.model.NhanVIen;

public class NhanVienActivity extends AppCompatActivity {
    private ListView splist;
    private TextView btnThem;
    private ImageView imgBack;
    private EditText searchEditText;
    private DatabaseNhanVien databaseNhanVien;
    private PhongBanAdapter phongBanAdapter;
    private ArrayList<NhanVIen> nhanVIenList =new ArrayList<>();

//    ActivityResultLauncher<Intent>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        splist =findViewById(R.id.splist);
        btnThem =findViewById(R.id.btnThem);
        imgBack =findViewById(R.id.imgBack);
        searchEditText =findViewById(R.id.searchEditText);

        loadNhanVien(nhanVIenList);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NhanVienActivity.this, MoreActivity.class);
                myLauncher.launch(intent);
            }
        });

//        PhongBan.add(new PhongBan("NV001","Nguyễn Văn A","Hành chính",R.drawable.pen_solid,R.drawable.trash));
//        PhongBan.add(new PhongBan("NV001","Nguyễn Văn A","Hành chính",R.drawable.pen_solid,R.drawable.trash));
//        PhongBan.add(new PhongBan("NV001","Nguyễn Văn A","Hành chính",R.drawable.pen_solid,R.drawable.trash));
//        PhongBan.add(new PhongBan("NV001","Nguyễn Văn A","Hành chính",R.drawable.pen_solid,R.drawable.trash));
//        PhongBan.add(new PhongBan("NV001","Nguyễn Văn A","Hành chính",R.drawable.pen_solid,R.drawable.trash));
//        PhongBanAdapter phongBanAdapter1=new PhongBanAdapter(MainActivity2.this,PhongBan);
//        splist.setAdapter(phongBanAdapter1);




    }


    private ActivityResultLauncher<Intent> myLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {


                            //thêm trả về
                            if(result.getResultCode() == 1) {
                                Intent intent = result.getData();
                                Bundle bundle = intent.getExtras();
                                NhanVIen nhanVienMoi = (NhanVIen) bundle.getSerializable("NVMoi");
                                readFile(nhanVienMoi, -5);
                                databaseNhanVien.WriteToFile(nhanVIenList, "ListNV.txt");
                                loadNhanVien(nhanVIenList);
                                Toast.makeText(NhanVienActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            }
                            //sửa trả về
                            if(result.getResultCode() == 2) {
                                Intent intent = result.getData();
                                Bundle bundle = intent.getExtras();
                                NhanVIen nhanVienSua = (NhanVIen) bundle.getSerializable("nhanVienSua");
                                int viTriSua = bundle.getInt("viTriSua");
                                readFile(nhanVienSua, viTriSua);
                                databaseNhanVien.WriteToFile(nhanVIenList, "ListNV.txt");
                                loadNhanVien(nhanVIenList);
                                Toast.makeText(NhanVienActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
    private void loadNhanVien(ArrayList<NhanVIen> list) {
        databaseNhanVien = new DatabaseNhanVien(NhanVienActivity.this);
        list = databaseNhanVien.readFromFile("ListNV.txt");
        PhongBanAdapter adapter = new PhongBanAdapter(this, list, myLauncher);
        splist.setAdapter(adapter);
    }

    private void readFile(NhanVIen nhanVien, int viTri) {
        databaseNhanVien = new DatabaseNhanVien(NhanVienActivity.this);
        nhanVIenList = databaseNhanVien.readFromFile("ListNV.txt");
        if(nhanVIenList != null) {
            if(viTri == -5) {
                nhanVIenList.add(nhanVien);
            } else {
                nhanVIenList.set(viTri, nhanVien);
            }
        } else {
            if(viTri == -5) {
                nhanVIenList = new ArrayList<>();
                nhanVIenList.add(nhanVien);
            } else {
                nhanVIenList = new ArrayList<>();
                nhanVIenList.set(viTri, nhanVien);
            }
        }
    }

}