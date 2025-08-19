package entity;

import javax.xml.crypto.Data;
import java.sql.Timestamp;

public class Message {
    private Integer MID;
    private String content;
    private Timestamp createDate;
    private Integer OID;
    private Integer TID;
    private Integer HT;

    public Integer getHT() {return HT;}

    public void setHT(Integer HT) {this.HT = HT;}

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Integer getTID() {
        return TID;
    }

    public void setTID(Integer TID) {
        this.TID = TID;
    }

    public Integer getMID() {
        return MID;
    }

    public void setMID(Integer MID) {
        this.MID = MID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOID() {
        return OID;
    }

    public void setOID(Integer OID) {
        this.OID = OID;
    }
}
