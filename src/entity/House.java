package entity;

import java.io.*;
import java.text.Format;
//HID,HAddress,layout,capacity,rent,con,OID
public class House {
    private Integer HID;
    private String HAddress;
    private String layout;
    private Integer capacity;
    private Integer rent;
    private Boolean con;
    private Integer OID;

    public House() {super();}
    public void setHID(Integer HID) {this.HID = HID;}
    public void setLayout(String layout) {this.layout = layout;}
    public void setHAddress(String HAddress) {this.HAddress = HAddress;}
    public void setCapacity(Integer capacity) {this.capacity = capacity;}
    public void setRent(Integer rent) {this.rent = rent;}
    public void setCon(Boolean con) {this.con = con;}
    public void setOID(Integer OID) {this.OID = OID;}

    public Integer getHID() {return HID;}
    public String getHAddress() {return HAddress;}
    public String getLayout() {return layout;}
    public Integer getCapacity() {return capacity;}
    public Integer getRent() {return rent;}
    public Boolean getCon() {return con;}
    public Integer getOID() {return OID;}
}
