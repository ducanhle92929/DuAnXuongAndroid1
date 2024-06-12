package foly.anhld.assmfull.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import foly.anhld.assmfull.Database.DatabaseNhanVien;
import foly.anhld.assmfull.R;
import foly.anhld.assmfull.adapter.AllAdapter;
import foly.anhld.assmfull.adapter.PhongBanAdapter;
import foly.anhld.assmfull.model.PhongBan;
import foly.anhld.assmfull.model.NhanVIen;

public class PhongBanActivity extends AppCompatActivity {

    private ListView splist;
    private ImageView imgMore, imgChek;
    private EditText searchEditText;

    private AllAdapter allAdapter;
    private ArrayList<PhongBan> originalList;
    private ArrayList<PhongBan> filteredList;
    private DatabaseNhanVien databaseNhanVien;
    private PhongBanAdapter phongBanAdapter;
    private ArrayList<NhanVIen> nhanVIenList =new ArrayList<>();
    private ArrayList<NhanVIen> usersArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        splist = findViewById(R.id.splist);
        splist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent=new Intent(PhongBanActivity.this, MorePActivity.class);

                startActivity(mIntent);
            }
        });

        imgMore = findViewById(R.id.imgMore);
        imgChek = findViewById(R.id.imgChek);
        searchEditText = findViewById(R.id.searchEditText);
        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        originalList = new ArrayList<>();
        originalList.add(new PhongBan(R.drawable.baseline_add_home_work_24,"Nhân Sự"));
        originalList.add(new PhongBan(R.drawable.baseline_add_home_work_24,"Đào Tạo"));
        originalList.add(new PhongBan(R.drawable.baseline_add_home_work_24,"Hành Chính"));


        filteredList = new ArrayList<>(originalList);

        allAdapter = new AllAdapter(PhongBanActivity.this, filteredList);
        splist.setAdapter(allAdapter);


        imgChek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });


        splist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }


    private void filter(String text) {
        filteredList.clear();
        if (text.isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            text = text.toLowerCase();
            for (PhongBan item : originalList) {
                if (item.getText().toLowerCase().contains(text)) {
                    filteredList.add(item);
                }
            }
        }
        allAdapter.notifyDataSetChanged();
    }
}
