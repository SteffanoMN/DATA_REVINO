package com.example.data_revino;


import java.io.Serializable;


public class Client_Data implements Serializable{

    private String name, phone ,id , time , order , payment;

    public Client_Data(String name, String phone, String id, String time, String order, String payment) {
        this.name = name;
        this.phone = phone;
        this.id = id;
        this.time = time;
        this.order = order;
        this.payment = payment;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getOrder() { return order; }
    public void setOrder(String order) { this.order = order; }

    public String getPayment() { return payment; }
    public void setPayment(String payment) { this.payment = payment; }
}
