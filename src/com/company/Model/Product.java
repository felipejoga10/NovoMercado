package com.company.Model;

import com.company.utils.Utils;

public class Product {
    private static int count = 1;

    private int id;
    private String name;
    private String type;
    private int quantity;
    private String position;
    private double price;

    public Product(String name, String type, int quantity, String position, double price) {
        this.id = count;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.position = position;
        this.price = price;
        Product.count += 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }


    public int getQuantity() {
        return quantity;
    }

    public String getPosition() {
        return position;
    }

    public double getPrice() {
        return price;
    }

    public String toString(){
        return "Lista de produtos cadastrados:" +
                "\nID: "+this.getId()+
                "\nNOME: "+this.getName()+
                "\nTIPO: "+this.getType()+
                "\nQUANTIDADE: "+this.getQuantity()+
                "\nLOCALIZACAO: "+this.getPosition()+
                "\nPRECO: "+ Utils.doubleToString(this.getPrice());

    }
}
