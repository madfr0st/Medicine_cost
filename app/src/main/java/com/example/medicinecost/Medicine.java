package com.example.medicinecost;

import com.example.medicinecost.DBMS.DBMS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    public static ArrayList<Medicine> searchItem(ArrayList<Medicine> all,String string){
        ArrayList<Medicine> list = new ArrayList<>();
        for(int i=0;i<all.size();i++) {
            if (isSubSequence(string, all.get(i).name,string.length(),all.get(i).name.length())) {
                list.add(all.get(i));
            }
        }
        System.out.println(list);
        if(list.size()==0){
            list.add(new Medicine("No such medicine in database",000000,"nil",0,"nil"));
        }
        return list;
    }

    static boolean isSubSequence(String str1,
                                 String str2, int m, int n) {

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        int j = 0;

        // Traverse str2 and str1, and compare
        // current character of str2 with first
        // unmatched char of str1, if matched
        // then move ahead in str1
        for (int i = 0; i < n && j < m; i++)
            if (str1.charAt(j) == str2.charAt(i))
                j++;

        // If all characters of str1 were found
        // in str2
        return (j == m);
    }
}
