package foly.anhld.assmfull.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import foly.anhld.assmfull.Database.FoodDataSource;
import foly.anhld.assmfull.R;
import foly.anhld.assmfull.model.PhongBan;
public class AllAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PhongBan> list;
    private FoodDataSource dataSource;


    public AllAdapter(Context mcoContext, ArrayList<PhongBan> arrayList) {
        this.context = mcoContext;
        this.list = arrayList;
        dataSource = new FoodDataSource(context); // Khởi tạo dataSource
        dataSource.open();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.item_department, parent,  false);

        ImageView imvIcon = convertView.findViewById(R.id.imvIcon);
//        ImageView imvpen = convertView.findViewById(R.id.imvpen);
//        ImageView imvtrash = convertView.findViewById(R.id.imvtrash);
        TextView textView = convertView.findViewById(R.id.txNS);

      PhongBan food = list.get(i);
      textView.setText(food.getText());
      imvIcon.setImageResource(food.getImageResId());
//        imvpen.setImageResource(food.getImvpen());
//        imvtrash.setImageResource(food.getImvtrash());

//        imvtrash.setOnClickListener(v -> CheckTrash(i));



        return convertView;
    }

    public void CheckTrash(int index) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure you want to delete this item?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            if (list != null && index >= 0 && index < list.size()) {
                list.remove(index);
                notifyDataSetChanged();
                // Xóa dữ liệu từ cơ sở dữ liệu khi xóa từ adapter
                dataSource.clearAllFoods();
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

                // Xóa cache từ SharedPreferences
                clearCache();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();

    }
    // Trong adapter của bạn
    private void clearCache() {
        SharedPreferences preferences = context.getSharedPreferences("my_cache", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

}


