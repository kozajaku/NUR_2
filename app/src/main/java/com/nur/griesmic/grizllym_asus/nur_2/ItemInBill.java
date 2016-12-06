package com.nur.griesmic.grizllym_asus.nur_2;

public class ItemInBill {
    public Item item;
    public int n;
    public boolean state;

    public ItemInBill(Item item){
        this.item = item;
        this.n = 1;
        this.state = false;
    }

    public void setN(int newN){
        this.n = newN;
    }
    public void setOrdered(){
        this.state = true;
    }

    public String getTotalPrice()
    {
        return String.valueOf(n) + "x : " + String.format("%.2f",n*item.price) + item.currency;
    }

    public void setState(boolean b) {
        this.state = b;
    }

    @Override
    public boolean equals(Object obj) {
        ItemInBill o = (ItemInBill)obj;
        if(this.item.name == o.item.name){
            return true;
        }
        return false;
    }
}
