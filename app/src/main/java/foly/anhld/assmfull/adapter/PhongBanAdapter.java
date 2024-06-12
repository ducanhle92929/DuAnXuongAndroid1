package foly.anhld.assmfull.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;

import java.util.ArrayList;

import foly.anhld.assmfull.Database.DatabaseNhanVien;
import foly.anhld.assmfull.R;
import foly.anhld.assmfull.activity.SuaActivity;
import foly.anhld.assmfull.model.NhanVIen;

public class PhongBanAdapter extends BaseAdapter  {
    private Context mContext;
    private ArrayList<NhanVIen> listNhanVIen;
    private ActivityResultLauncher<Intent> myLauncher;

    public PhongBanAdapter(Context mContext, ArrayList<NhanVIen> listNhanVIen, ActivityResultLauncher<Intent> myLauncher) {
        this.mContext = mContext;
        this.listNhanVIen = listNhanVIen;
        this.myLauncher = myLauncher;
    }

    @Override
    public int getCount() {
        return listNhanVIen.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_phongban, parent,  false);

        TextView txtMa = convertView.findViewById(R.id.txtMa);
        TextView txtHo = convertView.findViewById(R.id.txtHo);
        TextView txtPhong = convertView.findViewById(R.id.txtPhong);
        ImageView imvpen = convertView.findViewById(R.id.imvpen);
        ImageView imvtrash = convertView.findViewById(R.id.imvtrash);

        NhanVIen nhanVIen = listNhanVIen.get(i);
        txtMa.setText("Ma NV :" + nhanVIen.getMaNV());
        txtHo.setText("Họ Tên :" + nhanVIen.getHoTen());
        txtPhong.setText("Phòng Ban :" + nhanVIen.getPhongBan());
//        imvpen.setImageResource(phongBan.getImvpen());
//        imvtrash.setImageResource(phongBan.getImvtrash());
        imvpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SuaActivity.class );
                Bundle bundle = new Bundle();
                bundle.putSerializable("nhanVienSua", listNhanVIen.get(i));
                bundle.putInt("viTriSua", i);
                intent.putExtras(bundle);
                myLauncher.launch(intent);

            }
        });

        imvtrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Deller(i);
            }
        });
        return convertView;

    }
    public void Deller(int index){
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure delete this item");
        builder.setPositiveButton("yes",(dialog, which) -> {
            listNhanVIen.remove(index);
            writetofile("ListNV.txt", listNhanVIen);
            notifyDataSetChanged();
            Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("No",null);
        builder.show();
    }

    public void writetofile(String filename, ArrayList<NhanVIen> nhanVienArraylist){
        DatabaseNhanVien filehelper = new DatabaseNhanVien(mContext);
        filehelper.WriteToFile(nhanVienArraylist, filename);
    }
}
