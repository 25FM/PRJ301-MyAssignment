/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MANH
 */
public abstract class DBContext<T> {
    protected Connection connection;

    public DBContext() {
        try {
            String user = "manhdd";
            String password = "6266445";
            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=PRJ301_FALL2022_Assignment";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public abstract void insert(T model); //INSERT INTO
    public abstract void update(T model); //UPDATE SET
    public abstract void delete(T model); //DELETE FROM
    public abstract T get(int id); //SELECT * FROM WHERE id =
    public abstract ArrayList<T> list();//SELECT * FROM
    
}
