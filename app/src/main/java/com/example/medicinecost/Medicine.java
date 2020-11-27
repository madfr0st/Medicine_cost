package com.example.medicinecost;

public class Medicine {
    private String name;
    private int code;
    private String dis;
    private int each;
    private String cost;

    public Medicine(){

    }

    public Medicine(String name, int code, String dis, int each, String cost) {
        this.name = name;
        this.code = code;
        this.dis = dis;
        this.each = each;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public int getEach() {
        return each;
    }

    public void setEach(int each) {
        this.each = each;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", dis='" + dis + '\'' +
                ", each=" + each +
                ", cost='" + cost + '\'' +
                '}';
    }
}
