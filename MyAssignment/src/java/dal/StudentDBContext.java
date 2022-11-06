/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author MANH
 */
public class StudentDBContext extends DBContext<Student> {

    @Override
    public void insert(Student model) {
        try {
            String sql = "INSERT INTO Student(stdid, stdname, stdgender, stddob)\n"
                    + "VALUES (?, ?, ?, ?)"
                    + "INSERT into Student_Group values (?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());
            stm.setString(2, model.getName());
            stm.setBoolean(3, model.isGender());
            stm.setDate(4, model.getDob());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Student model) {
        try {
            connection.setAutoCommit(false);
            String sql = "Update Student set stdname = ?, stdgender = ?, stddob = ? where stdid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());
            stm.setString(1, model.getName());
            stm.setBoolean(2, model.isGender());
            stm.setDate(3, model.getDob());
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Student model) {
        try {
            String sql = "Delete Student_Group where stdid = ?"
                    + "Delete Student where stdid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Student get(int id) {
//        try {
//            String sql = "select s.stdid, s.stdname, s.stdgender, s.stddob, sg.gid, g.gname, g.lid from Student s\n"
//                    + "left join Student_Group sg on s.stdid = sg.stdid\n"
//                    + "left join [Group] g on g.gid = sg.gid"
//                    + "where s.stdid = ?";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setInt(1, id);
//            ResultSet rs = stm.executeQuery();
//            Student s = null;
//            while (rs.next()) {
//                if (s == null) {
//                    s = new Student();
//                    int stdid = rs.getInt("stdid");
//                    String stdname = rs.getString("stdname");
//                    boolean stdgender = rs.getBoolean("stdgender");
//                    Date stddob = rs.getDate("stddob");
//                    s.setId(stdid);
//                    s.setName(stdname);
//                    s.setGender(stdgender);
//                    s.setDob(stddob);
//                    
//                }
//                
//            } 
//            return s;
//        } catch (SQLException ex) {
//            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
        Student s = new Student();
        try {
            String statement = "select stdname from student where stdid = ?";
            PreparedStatement stm = connection.prepareStatement(statement);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            String name = rs.getString(1);
            s.setId(id);
            s.setName(name);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    @Override
    public ArrayList<Student> list() {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "Select stdid, stdname, stdgender, stddob from Student";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                int stdid = rs.getInt("stdid");
                String stdname = rs.getString("stdname");
                boolean stdgender = rs.getBoolean("stdgender");
                Date stddob = rs.getDate("stddob");
                s.setId(stdid);
                s.setName(stdname);
                s.setGender(stdgender);
                s.setDob(stddob);
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

}
