package com.sly.demo.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMain {  
    public static void main(String[] args) {  
        String[] str = { "计算机", "网络", "回收站", "Fire", "驱动精灵", "百度", "白色", "无恐",  
                "lxz", "A酱", "芙兰", "鱼鱼", "妹妹", "你好", "林小姐", "联盟", "L",  
                "xdsfsdggsdsf", "星星", "靴刀誓死", "Java", "倒塌", "黑人", "a妹", "aYa",  
                "Admin", "ali", "阿san", };  
        List<NameBean> arrList = new ArrayList<NameBean>();  
        for (String s : str) {  
            NameBean bean = new NameBean();  
            bean.setName(s);  
            arrList.add(bean);  
        }  
  
        MyComparator compare = new MyComparator();  
        Collections.sort(arrList, compare);  
  
        for (NameBean b : arrList) {  
            System.out.println(b.getNameFirstChar() + ";" + b.getName());  
        }  
    }  
}  