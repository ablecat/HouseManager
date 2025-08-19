package entity;

import java.io.*;
import java.text.Format;

public class VacantHouse {
    private Integer HID;
    private String HAddress;
    private String layout;
    private Integer capacity;
    private Integer rent;
    private Integer OID;
    private String OName;
    private String OTelephone;

    public void setHID(Integer HID) {this.HID = HID;}
    public void setLayout(String layout) {this.layout = layout;}
    public void setHAddress(String HAddress) {this.HAddress = HAddress;}
    public void setCapacity(Integer capacity) {this.capacity = capacity;}
    public void setRent(Integer rent) {this.rent = rent;}
    public void setOID(Integer OID) {this.OID = OID;}
    public void setOName(String OName) {this.OName = OName;}
    public void setOTelephone(String OTelephone) {this.OTelephone = OTelephone;}

    public Integer getHID() {return HID;}
    public String getHAddress() {return HAddress;}
    public String getLayout() {return layout;}
    public Integer getCapacity() {return capacity;}
    public Integer getRent() {return rent;}
    public Integer getOID() {return OID;}
    public String getOName() {return OName;}
    public String getOTelephone() {return OTelephone;}
}
