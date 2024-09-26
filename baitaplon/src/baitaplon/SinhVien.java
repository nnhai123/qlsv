/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package baitaplon;

public class SinhVien {
     private String name, phone, address, email, maSoDoanVien;

    public SinhVien(String name,String maSoDoanVien , String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.maSoDoanVien = maSoDoanVien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getMaSoDoanVien() {
        return maSoDoanVien;
    }

    public void setMaSoDoanVien(String maSoDoanVien) {
        this.maSoDoanVien = maSoDoanVien;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     
     
}
