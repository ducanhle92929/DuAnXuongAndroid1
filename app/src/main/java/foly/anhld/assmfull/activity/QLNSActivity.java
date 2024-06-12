package foly.anhld.assmfull.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import foly.anhld.assmfull.R;

public class QLNSActivity extends AppCompatActivity {

    private LinearLayout txtPhong,txtNV,txtThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtPhong=findViewById(R.id.txtPhong);
        txtNV=findViewById(R.id.txtNV);
        txtThoat=findViewById(R.id.txtThoat);
        txtPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(QLNSActivity.this, PhongBanActivity.class);

                startActivity(mIntent);
            }
        });
        txtNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(QLNSActivity.this, NhanVienActivity.class);
                startActivity(mIntent);
            }
        });
        txtThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        txtThoat.setOnClickListener(v -> {
//            Intent mIntent=new Intent(QLNSActivity.this, LoginActivity.class);
//            startActivity(mIntent);
////            finish();
////            Intent intent = new Intent(Intent.ACTION_MAIN);
////            intent.addCategory(Intent.CATEGORY_HOME);
////            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            startActivity(intent);
//        });
    }
}