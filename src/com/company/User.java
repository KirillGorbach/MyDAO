package com.company;

public class User {
    private String name;
    private String password;
    private int id;
    private int money;
    private UserLevel level;

    public User( int id, String name, String password, int money, UserLevel level){
        this.id = id;
        this.name = name;
        this.password = password;
        this.money = money;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }
}
