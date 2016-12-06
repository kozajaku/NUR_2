package com.nur.griesmic.grizllym_asus.nur_2;

public class Item {
    public String name;
    public String note;
    public double price;
    public String currency = "€";
    public String detail = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur magna erat, ornare in ligula fringilla, semper elementum dolor. Sed consequat.";

    public Item(String name, String note, double price){
        this.name = name;
        this.note = note;
        this.price = price;
    }

    public Item(String name, String note, double price, String currency){
        this.name = name;
        this.note = note;
        this.price = price;
        this.currency = currency;
    }

    public String getPrice(){
        return String.valueOf(price) + " " + currency;
    }

    public boolean equals(Item o) {
        if(this.name == o.name)
            return true;
        else
            return false;
    }
}
