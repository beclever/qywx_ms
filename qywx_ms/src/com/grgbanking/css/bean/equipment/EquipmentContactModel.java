package com.grgbanking.css.bean.equipment;

public class EquipmentContactModel extends EquipmentContact {
 
    private static final long serialVersionUID = 1L;

    private String isManagerStr;// 是否当前管理员
    private String isRefuseSmsStr;// 是否拒收短信
    private String sexStr;
    public String getIsManagerStr() {
        return isManagerStr;
    }
    public void setIsManagerStr(String isManagerStr) {
        this.isManagerStr = isManagerStr;
    }
    public String getIsRefuseSmsStr() {
        return isRefuseSmsStr;
    }
    public void setIsRefuseSmsStr(String isRefuseSmsStr) {
        this.isRefuseSmsStr = isRefuseSmsStr;
    }
    public String getSexStr() {
        return sexStr;
    }
    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }
    /*public String getIsManagerStr() {
    return this.getIsManager() == "Y" ? "是":"否";
}
public String getIsRefuseSmsStr() {
    return this.getIsRefuseSms() == "Y" ? "是":"否";
}
public String getSexStr() {
    return this.getSex() == "Y" ? "是":"否";
}
public void setIsManagerStr(String isManagerStr) {
    this.isManagerStr = this.getIsManager();
}
public void setIsRefuseSmsStr(String isRefuseSmsStr) {
    this.isRefuseSmsStr = this.getIsRefuseSmsStr();
}
public void setSexStr(String sexStr) {
    this.sexStr = this.getSex();
}*/
}
