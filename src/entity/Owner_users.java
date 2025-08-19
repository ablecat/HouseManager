package entity;

//Owner_users(OID,userName,password,OName,OAddress,Otelephone)
public class Owner_users {
    private Integer OID;
    private String userName;
    private String password;
    private String OName;
    private String OAddress;
    private String OTelephone;

    public void setOID(Integer OID) {this.OID = OID;}
    public void setUserName(String userName) {this.userName = userName;}
    public void setPassword(String password) {this.password = password;}
    public void setOName(String OName) {this.OName = OName;}
    public void setOAddress(String OAddress) {this.OAddress = OAddress;}
    public void setOTelephone(String OTelephone) {this.OTelephone = OTelephone;}
    public Integer getOID() {return OID;}
    public String getUserName() {return userName;}
    public String getPassword() {return password;}
    public String getOName() {return OName;}
    public String getOAddress() {return OAddress;}
    public String getOTelephone() {return OTelephone;}
}
