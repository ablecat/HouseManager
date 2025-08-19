package entity;

public class Charge {
    private Integer CID;
    private double amount;
    private Integer RID;

    public Integer getCID() {
        return CID;
    }

    public void setCID(Integer CID) {
        this.CID = CID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getRID() {
        return RID;
    }

    public void setRID(Integer RID) {
        this.RID = RID;
    }
}
