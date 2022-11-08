/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Feature;
import model.Role;

/**
 *
 * @author MANH
 */
public class AccountDBContext extends DBContext<Account> {

    public Account get(String username, String password) {
        Account acc = new Account();
        Role role;
        Feature feature;
        try {
            String statement = "select acc.displayname, r.rid, r.rname, f.fid, f.fname, f.url\n"
                    + "from Account acc left join Role_Account ra on acc.username = ra.username\n"
                    + "						  left join Role r on ra.rid = r.rid\n"
                    + "						  left join Role_Feature rf on r.rid = rf.rid\n"
                    + "						  left join Feature f on rf.fid = f.fid\n"
                    + "						  where acc.username = ? and acc.[password] = ?";
            PreparedStatement pstm = connection.prepareStatement(statement);
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            acc.setUsername(username);
            acc.setDisplayname(rs.getString("displayname"));

            role = new Role();
            role.setId(rs.getInt("rid"));
            role.setName(rs.getString("rname"));

            feature = new Feature();
            feature.setId(rs.getInt("fid"));
            feature.setName(rs.getString("fname"));
            feature.setUrl(rs.getString("url"));
            role.getFeatures().add(feature);

            acc.getRoles().add(role);
            while (rs.next()) {
                int rid = rs.getInt("rid");
                if (rid != role.getId()) {
                    role = new Role();
                    role.setId(rs.getInt("rid"));
                    role.setName(rs.getString("rname"));

                    feature = new Feature();
                    feature.setId(rs.getInt("fid"));
                    feature.setName(rs.getString("fname"));
                    feature.setUrl(rs.getString("url"));
                    role.getFeatures().add(feature);

                    acc.getRoles().add(role);
                } else {
                    feature = new Feature();
                    feature.setId(rs.getInt("fid"));
                    feature.setName(rs.getString("fname"));
                    feature.setUrl(rs.getString("url"));
                    role.getFeatures().add(feature);
                }
            }
            return acc;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
