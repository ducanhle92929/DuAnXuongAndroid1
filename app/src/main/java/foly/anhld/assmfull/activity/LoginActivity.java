package foly.anhld.assmfull.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import foly.anhld.assmfull.Database.DatabaseUser;
import foly.anhld.assmfull.R;
import foly.anhld.assmfull.model.User;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText Inputname, Inputpas;
    private CheckBox checkBoxRemember;
    private Button btlLogin, btlRegister;
    private String useName = "";
    private String passWord = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Inputname = findViewById(R.id.Inputname);
        Inputpas = findViewById(R.id.Inputpas);
        btlLogin = findViewById(R.id.btlLogin);
        btlRegister = findViewById(R.id.btlRegister);
        checkBoxRemember = findViewById(R.id.checkBoxRemember);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        checkBoxRemember.setChecked(sharedPreferences.getBoolean("isChecked", false));
        checkBoxRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("isChecked", isChecked);
                editor.apply();
            }
        });


        if (checkBoxRemember.isChecked()) {
            String savedUsername = sharedPreferences.getString("username", "");
            String savedPassword = sharedPreferences.getString("password", "");
            Inputname.setText(savedUsername);
            Inputpas.setText(savedPassword);
        }

        btlRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(mIntent);
            }
        });

        btlLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Inputname.getText().toString();
                String password = Inputpas.getText().toString();


                if (checkBoxRemember.isChecked()) {
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();
                }

                DatabaseUser fileHelper = new DatabaseUser(LoginActivity.this);
                ArrayList<User> list = fileHelper.readFromFile("user.txt");

                if (list.size() > 0) {
                    if (list.get(0).getName().equals(username) && list.get(0).getPass().equals(password)) {
                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(LoginActivity.this, QLNSActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}


//public class LoginActivity extends AppCompatActivity {
//
//    private TextInputEditText Inputname,Inputpas;
//    private CheckBox checkBoxRemember;
//    private Button btlLogin,btlRegister;
//    private  String useName ="";
//    private  String passWord ="";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_login);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        Inputname=findViewById(R.id.Inputname);
//        Inputpas=findViewById(R.id.Inputpas);
//        btlLogin=findViewById(R.id.btlLogin);
//        btlRegister=findViewById(R.id.btlRegister);
//        checkBoxRemember=findViewById(R.id.checkBoxRemember);
////        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
////        boolean remember = preferences.getBoolean("remember", false);
////        checkBoxRemember.setChecked(remember);
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        checkBoxRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                editor.putBoolean("isChecked", isChecked);
//                editor.apply();
//            }
//        });
//        boolean isChecked = sharedPreferences.getBoolean("isChecked", false);
//        checkBoxRemember.setChecked(isChecked);
//
//        btlRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mIntent=new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(mIntent);
//            }
//        });
//        btlLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CheckData();
//                CheckDataa();
//
//                DatabaseUser fileHeLper =  new DatabaseUser(LoginActivity.this);
//                ArrayList<User> list= fileHeLper.readFromFile("user.txt");
//                if (list.size()>0){
//                    if (list.get(0).getName().equals(Inputname.getText().toString())&&
//                            list.get(0).getPass().equals(Inputpas.getText().toString()) ){
//                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(LoginActivity.this, QLNSActivity.class) );
//                    }else {
//                        Toast.makeText(LoginActivity.this, "login fail", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }
//        });
//    }
//    public void CheckDataa(){
//        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putBoolean("remember", checkBoxRemember.isChecked());
//        editor.apply();
//    }
//
// public void CheckData(){
//      String enteredUse = Inputname.getText().toString();
//       String enteredPass = Inputpas.getText().toString();
//       Bundle mBundle=getIntent().getExtras();
//       if(mBundle != null){
//         useName =mBundle.getString("name");
//           passWord=mBundle.getString("pass");
//      }
//    boolean x = useName.equals(enteredUse);
//      boolean y = passWord.equals(enteredPass);
//
//      if(x&&y){
//          Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
//        startActivity(new Intent(LoginActivity.this,WelcomeActivity.class) );
//       }else{
//         Toast.makeText(LoginActivity.this,"Đăng nhập thất bại",Toast.LENGTH_LONG).show();
//       }
//  }
//}


