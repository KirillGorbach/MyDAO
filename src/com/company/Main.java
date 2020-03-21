package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserDAO database = UserDAO.getInstance();

        String new_login = "newComer", new_password = "dimassic_64";

        //database.setUserLevel(5, "Intermediate");

        System.out.println(UserLevel.getLevel(2));


        //User id = database.addUser(new_login, new_password);
        //System.out.println(id.getName());


    }
}
