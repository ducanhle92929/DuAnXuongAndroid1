package foly.anhld.assmfull.Database;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import foly.anhld.assmfull.model.NhanVIen;

public class DatabaseNhanVien {
    private Context mContext;

    public DatabaseNhanVien(Context mContext) {
        this.mContext = mContext;
    }
    public void WriteToFile(ArrayList<NhanVIen> arrayList, String fileName){
        File filedDir = mContext.getFilesDir();
        File file = new File(filedDir,fileName);
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<NhanVIen> readFromFile(String fileName){
        ArrayList<NhanVIen> arrayList=null;
        try {
            File fileDir = mContext.getFilesDir();
            File file=new File(fileDir,fileName);
            if (file.exists()){
                ObjectInputStream objectInputStream =new ObjectInputStream(new FileInputStream(file));
                arrayList=(ArrayList<NhanVIen>) objectInputStream.readObject();
                objectInputStream.close();
                return arrayList;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }


}
