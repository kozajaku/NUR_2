package com.nur.griesmic.grizllym_asus.nur_2;

import java.util.ArrayList;

public class Bill {
    public ArrayList<ItemInBill> list;
    public static String currency = "€";

    public Bill(){
        list = new ArrayList<>();
    }

    public void add(Item item){
        for(ItemInBill i: list)
            if(item.equals(i.item) && !i.state) {
                i.setN(i.n + 1);
                return;
            }
        list.add(new ItemInBill(item));
    }

    public void modify(int position, int amount) {
        ItemInBill item = list.get(position);
        if(amount > 0)
            item.setN(item.n+1);
        if(amount < 0){
            item.setN(item.n-1);
            if(item.n < 1)
                list.remove(position);
        }
    }

    public void remove(int position){
        list.remove(position);
    }

    public void makeOrder() {
        ArrayList<ItemInBill> unordered = new ArrayList<>();
        for(int c = 0; c < list.size(); c++)
            if(!list.get(c).state){
                unordered.add(list.get(c));
                list.remove(c);
                c--;
            }

        for(ItemInBill item: unordered)
            if(!list.contains(item)){
                item.setState(true);
                list.add(item);
            }
            else {
                int index = list.indexOf(item);
                list.get(index).setN(list.get(index).n+item.n);
            }
    }

    public String getTotalPrice(){
        double tot = 0.0;
        for (ItemInBill item:list) {
            tot += item.n*item.item.price;
        }
        return String.valueOf(String.format("%.2f",tot)) + " " + currency;
    }

    public String getNewPrice() {
        double res = 0.0;
        for (ItemInBill item:list){
            if(!item.state)
                res += item.n*item.item.price;
        }
        return String.valueOf(String.format("%.2f",res)) + " " + currency;
    }

    public String getOldPrice() {
        double res = 0.0;
        for (ItemInBill item:list){
            if(item.state)
                res += item.n*item.item.price;
        }
        return String.valueOf(String.format("%.2f",res)) + " " + currency;
    }

    public boolean isNew() {
        for (ItemInBill item:list) {
            if(!item.state)
                return true;
        }
        return false;
    }

    public boolean isOld(){
        for (ItemInBill item:list) {
            if(item.state)
                return true;
        }
        return false;
    }
}

