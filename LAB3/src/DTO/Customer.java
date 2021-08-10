package DTO;

import DAO.OrderList;

public class Customer {
    private String cusID;
    private String phone;
    private String name;
    private OrderList oList; //Nhan ca cai OrderList vao roi in ra

    public Customer(String cusID, String phone, String name) {
        this.cusID = cusID;
        this.phone = phone;
        this.name = name;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}