package entity;

//TID,HID,charge
public class Record {
    private Integer RID;
    private Integer TID;
    private Integer HID;

    public void setRID(Integer RID) {this.RID = RID;}
    public void setTID(Integer TID) {this.TID = TID;}
    public void setHID(Integer HID) {this.HID = HID;}

    public Integer getRID() {return RID;}
    public Integer getTID() {return TID;}
    public Integer getHID() {return HID;}
}
