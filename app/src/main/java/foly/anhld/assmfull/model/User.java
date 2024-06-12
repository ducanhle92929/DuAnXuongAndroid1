package foly.anhld.assmfull.model;

import java.io.Serializable;

public class User implements Serializable {

    private  String name,pass,pascofrim;

    public User(String name, String pass, String pascofrim) {
        this.name = name;
        this.pass = pass;
        this.pascofrim = pascofrim;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPascofrim() {
        return pascofrim;
    }

    public void setPascofrim(String pascofrim) {
        this.pascofrim = pascofrim;
    }
}
