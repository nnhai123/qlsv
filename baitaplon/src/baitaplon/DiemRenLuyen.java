/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package baitaplon;

public class DiemRenLuyen {
    private String name, maSo, loai;
    private int drl;

    public DiemRenLuyen(String name, String maSo, int drl, String loai) {
        this.name = name;
        this.maSo = maSo;
        this.drl = drl;
        this.loai = loai;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMaSo() {
        return maSo;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public int getDrl() {
        return drl;
    }

    public void setDrl(int drl) {
        this.drl = drl;
    }
    
    public int congDiem(){
        return drl += 10;
    }
    
    public String xepLoai(){
        if(drl >=  90){
            loai = "Xuất Sắc";
            return "Xuất Sắc";
        }
        else if(drl >=  80 && drl < 90){
            loai = "Tốt";
            return "Tốt";
        }
        else if(drl >=  65 && drl < 80){
            loai = "Khá";
            return "Khá";
        }
        else if(drl >=  50 && drl < 65){
            loai = "Trung Bình";
            return "Trung Bình";
        }
        else if(drl >=  35 && drl < 50){
            loai = "Yếu";
            return "Yếu";
        }
        else
            return "Kém";
    }
}
