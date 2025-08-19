package entity;

//ID,userName,password,TID
public class Tenant_users {
    private Integer TID;
    private String userName;
    private String password;
    private String TName;
    private String TAddress;
    private String TTelephone;
    private String TSex;

    public void setTID(Integer TID) {this.TID = TID;}
    public void setUserName(String userName) {this.userName = userName;}
    public void setPassword(String password) {this.password = password;}
    public void setTName(String TName) {this.TName = TName;}
    public void setTAddress(String TAddress) {this.TAddress = TAddress;}
    public void setTTelephone(String TTelephone) {this.TTelephone = TTelephone;}
    public void setTSex(String TSex) {this.TSex = TSex;}

    public Integer getTID() {return TID;}
    public String getUserName() {return userName;}
    public String getPassword() {return password;}
    public String getTName() {return TName;}
    public String getTAddress() {return TAddress;}
    public String getTTelephone() {return TTelephone;}
    public String getTSex() {return TSex;}
}
