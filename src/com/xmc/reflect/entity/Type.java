package com.xmc.reflect.entity;

/**
 * Created by Administrator on 2015/8/26.
 */
public class Type {
    private Integer id;
    private String name;
    private String description;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private void whisper(String name){
        System.out.println("Hello"+""+name+"! "+"I'm a private method!");
    }

    private void noPara(){
        System.out.println("我一个参数都没有！");
    }
}
