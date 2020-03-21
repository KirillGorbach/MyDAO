package com.company;

//https://bitbucket.org/xerial/sqlite-jdbc/downloads/
import org.sqlite.JDBC;

import java.sql.*;
import java.util.logging.Logger;

public class UserDAO {
    private static Logger logger = Logger.getAnonymousLogger();
    private String table = "usr2";
    //таблица может быть разной, но поля имени, пароля и т.п. должны присутствовать
    //поэтому условные названия столбцов записаны в строки
    private String user_name = "user_name";
    private String user_password = "user_password";
    private String user_money = "user_money";
    private String user_level = "user_level";
    private String user_id = "user_id";

    private int startMoneySumm = 100;
    private UserLevel startLevel = UserLevel.BEGINNER;

    private String DB_addr = "jdbc:sqlite:/run/media/masterkk/Data/JavaAndAndroid/IDEAPROJECTS/MyDAO/sql_db/mydb.db";
    private static UserDAO instance = null;

    private Connection connection;

    public static synchronized UserDAO getInstance() throws SQLException{
        if (instance == null)
            instance = new UserDAO();
        return instance;
    }

    private UserDAO() throws SQLException{
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(DB_addr);
        logger.info("Connected to the db successfully!");
    }


    //получает логин и пароль, сам довставляет деньги, уровень и номер
    // и возвращает выданный пользователю id
    public User addUser(String name, String password) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM "+table+" WHERE "+user_id+" = (SELECT MAX("+user_id+") FROM "+table+" )");
        //Вот тут костыль. При простом запросе не наибольший id выдаётся простое число, которое я не могу обработать
        // известными мне методами. Я нахожу ЗАПИСЬ с МАКСИМАЛНЫМ id и отдельно получаю её id.
        int new_id = resultSet.getInt(user_id)+1;

        statement.executeUpdate("insert into "+table+" ("+user_id+","+user_name+","+user_password+","+user_money+","+user_level+")" +
                " values ( "+new_id+"  , \""+name+"\", \""+password+"\","+startMoneySumm+",\""+startLevel.getLevelRank()+"\")");

        return new User(new_id,
                name,
                password,
                startMoneySumm,
                startLevel);
    }


    //Возвращает true или false в зависимости от того, есть юзер в базе или нет
    public boolean inspectPassword(String name, String password, int id) throws SQLException{
        Statement statement = this.connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT ("+user_id+") FROM "+table+" WHERE "
                +user_name +" = \""+name+"\" and user_password=\""+password+"\"");

        try{
            //перебираем все совпаышие пары логин-пароль
            while(resultSet.next())
                //если при этом ещё и id совпал
                if(resultSet.getInt(user_id)==id)
                    return true;
        } catch (SQLException e) {
            //если ошибка, то вернём false
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public User getUser(int id) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM "+table+" WHERE "+user_id+" = "+id);

        while (resultSet.next()){
            return new User(resultSet.getInt(user_id),
                    resultSet.getString(user_name),
                    resultSet.getString(user_password),
                    resultSet.getInt(user_money),
                    UserLevel.getLevel(resultSet.getInt(user_level)));
        }
        return null;
    }

    public void setUserMoney(int id, int new_money){
        try{
            Statement statement = this.connection.createStatement();
            statement.executeUpdate("update "+table+" set "+user_money+" = "+new_money+" where "+user_id+" = "+id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUserLevel(int id, UserLevel level){
        try{
            Statement statement = this.connection.createStatement();
            statement.executeUpdate("update "+table+" set "+user_level+"=\""+level+"\" where "+user_id+"="+id);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getStartMoneySum() {
        return startMoneySumm;
    }

    public void setStartMoneySum(int startMoneySumm) {
        this.startMoneySumm = startMoneySumm;
    }
    
}