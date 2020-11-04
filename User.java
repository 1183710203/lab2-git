package increment4;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private final String status;
    private String name;
    private String pwd;
    private ArrayList<String> his_lst = new ArrayList<>();
    public User(String status, String name, String pwd){
        this.status = status;
        this.name = name;
        this.pwd = pwd;
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)){
            return false;
        }
        User  user = (User) obj;
        if(user.name.equals(this.name) && user.status.equals(this.status)){
            return true;
        }
        return false;
    }
    public void addHistory(String his){
        this.his_lst.add(his);
    }
    public ArrayList<String> getHistory(){
        ArrayList<String> hst = new ArrayList<String>();
        hst.addAll(this.his_lst);
        return hst;
    }
    public String getPwd() {
        return pwd;
    }
    @Override
    public int hashCode(){
        return this.name.hashCode() + this.status.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
