package com.androidbook.sax;

public class Information {  
    String name;  
    String age;  

    public String getName() {  
        return name;  
    }  

    public void setName(String name) {  
        this.name = name;  
    }  

    public String getAge() {  
        return age;  
    }  

    public void setAge(String age) {  
        this.age = age;  
    }  

    @Override  
    public String toString() {  
        return "个人资料 [年龄=" + getAge() + ", 姓名=" + getName() + "]";  
    }  

}  