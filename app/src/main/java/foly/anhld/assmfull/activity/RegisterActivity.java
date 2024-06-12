package foly.anhld.assmfull.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import foly.anhld.assmfull.Database.DatabaseUser;
import foly.anhld.assmfull.R;
import foly.anhld.assmfull.model.User;

public class RegisterActivity extends AppCompatActivity {
    private  TextInputEditText Inputname,Inputpas,Inputcomfrim;
    private Button btlRegister,btlReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Inputname=findViewById(R.id.Inputname);
        Inputpas=findViewById(R.id.Inputpas);
        Inputcomfrim=findViewById(R.id.Inputcomfrim);
        btlRegister=findViewById(R.id.btlRegister);
        btlReturn=findViewById(R.id.btlReturn);
        btlReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(mIntent);
            }
        });
        btlRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent mIntent=new Intent(RegisterActivity.this,LoginActivity.class);
//                startActivity(mIntent);
//                Bundle mBundle=new Bundle();
//                mBundle.putString("name",Inputname.getText().toString());
//                mBundle.putString("pass",Inputpas.getText().toString());
//                mBundle.putString("comfirmpass",Inputcomfrim.getText().toString());
//                mIntent.putExtras(mBundle);

                String maNV = Inputname.getText().toString();
                String hoTen = Inputpas.getText().toString();
                String phongBan = Inputcomfrim.getText().toString();
                if (Inputname.getText().toString()==null || Inputname.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "can nhap ten", Toast.LENGTH_SHORT).show();

                    return;
        }
        if (Inputpas.getText().toString()==null || Inputpas.getText().toString().isEmpty()){

            Toast.makeText(RegisterActivity.this, "can nhap pass", Toast.LENGTH_SHORT).show();

            return;
        }
        if (Inputcomfrim.getText().toString()==null || Inputcomfrim.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "can nhap xac nhan", Toast.LENGTH_SHORT).show();

            return;
        }

        if (!Inputcomfrim.getText().toString().equals(Inputpas.getText().toString())){
            Toast.makeText(RegisterActivity.this, "mat khau va xac nhan khong trung nhau", Toast.LENGTH_SHORT).show();

            return;
        }
                Intent mIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(mIntent);
                Bundle mBundle=new Bundle();
                mBundle.putString("name",Inputname.getText().toString());
                mBundle.putString("pass",Inputpas.getText().toString());
                mBundle.putString("comfirmpass",Inputcomfrim.getText().toString());
                mIntent.putExtras(mBundle);
                ChekFile();
            }
        });
    }
    public void ChekFile(){
        Intent mIntent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(mIntent);

        User user= new User(Inputname.getText().toString(),Inputpas.getText().toString(),Inputcomfrim.getText().toString());
        ArrayList<User> list =new ArrayList<>();
        list.add(user);
        DatabaseUser databaseUser =new DatabaseUser(RegisterActivity.this);
        databaseUser.WriteToFile(list,"user.txt");
    }
}