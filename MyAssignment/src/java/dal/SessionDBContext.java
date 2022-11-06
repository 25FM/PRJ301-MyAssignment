/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.Student;
import model.Subject;
import model.TimeSlot;
import util.DateTimeHelper;

/**
 *
 * @author MANH
 */
public class SessionDBContext extends dal.DBContext<Session> {

    public ArrayList<Session> filterByStudent(int stdid, java.util.Date from, java.util.Date to) {
        ArrayList<Session> sessions = new ArrayList<>();

        try {
            String statement = "SELECT ses.sesid, gr.gname, r.rname, at.present, ts.tid, ts.description, ses.date  \n"
                    + "								   FROM Session ses join [Group] gr on ses.gid = gr.gid\n"
                    + "								   join Room r on ses.rid = r.rid\n"
                    + "								   join TimeSlot ts on ses.tid = ts.tid\n"
                    + "								   join Attandance at on ses.sesid = at.sesid\n"
                    + "								   where at.stdid = ? and ses.date between ? and ?";
            PreparedStatement pstm = connection.prepareStatement(statement);
            pstm.setInt(1, stdid);
            pstm.setDate(2, new java.sql.Date(DateTimeHelper.removeTime(from).getTime()));
            pstm.setDate(3, new java.sql.Date(DateTimeHelper.removeTime(to).getTime()));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {

                Session ses = new Session();
                Room r = new Room();
                TimeSlot ts = new TimeSlot();
                Group gr = new Group();
                Attendance att = new Attendance();

                ses.setId(rs.getInt("sesid"));
                ses.setDate(new java.util.Date(DateTimeHelper.removeTime(rs.getDate("date")).getTime()));

                gr.setName(rs.getString("gname"));
                ses.setGroup(gr);

                r.setName(rs.getString("rname"));
                ses.setRoom(r);

                ts.setId(rs.getInt("tid"));
                ts.setDescription(rs.getString("description"));
                ses.setTimeslot(ts);

                att.setPresent(rs.getBoolean("present"));
                ses.getAttandances().add(att);

                sessions.add(ses);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sessions;
    }

    public ArrayList<Session> filterByLecturer(int lid, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String statement = "select ses.sesid, ses.[index], lec.lname, gr.gid, gr.gname, r.rname, ses.attanded, ts.tid, ts.description, ses.date ,gr.class, sub.subname\n"
                    + "					from Session ses \n"
                    + "					join Lecturer lec on ses.lid = lec.lid\n"
                    + "					join [Group] gr on ses.gid = gr.gid\n"
                    + "					 join Room r on ses.rid = r.rid\n"
                    + "					   join TimeSlot ts on ses.tid = ts.tid\n"
                    + "					   join Subject sub on gr.subid = sub.subid\n"
                    + "					where ses.lid = ? and ses.date between ? and ?";
            PreparedStatement pstm = connection.prepareStatement(statement);
            pstm.setInt(1, lid);
            pstm.setDate(2, new java.sql.Date(DateTimeHelper.removeTime(from).getTime()));
            pstm.setDate(3, new java.sql.Date(DateTimeHelper.removeTime(to).getTime()));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Session ses = new Session();
                Room r = new Room();
                TimeSlot ts = new TimeSlot();
                Group gr = new Group();
                Lecturer lec = new Lecturer();
                Subject sub = new Subject();
                ses.setId(rs.getInt("sesid"));
                ses.setDate(new java.util.Date(DateTimeHelper.removeTime(rs.getDate("date")).getTime()));
                ses.setIndex(rs.getInt("index"));
                
                lec.setId(lid);
                lec.setName(rs.getString("lname"));
                ses.setLecturer(lec);
                
                gr.setId(rs.getInt("gid"));
                gr.setName(rs.getString("gname"));
                gr.setClassname(rs.getString("class"));
                ses.setGroup(gr);

                r.setName(rs.getString("rname"));
                ses.setRoom(r);

                ts.setId(rs.getInt("tid"));
                ts.setDescription(rs.getString("description"));
                ses.setTimeslot(ts);

                ses.setAttandated(rs.getBoolean("attanded"));

                sub.setName(rs.getString("subname"));
                gr.setSubject(sub);
                sessions.add(ses);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [Session] SET attanded = 1 WHERE sesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());
            stm.executeUpdate();

            //remove old attandances
            sql = "DELETE Attandance WHERE sesid = ?";
            PreparedStatement stm_delete = connection.prepareStatement(sql);
            stm_delete.setInt(1, model.getId());
            stm_delete.executeUpdate();

            //insert new attandances
            for (Attendance att : model.getAttandances()) {
                sql = "INSERT INTO [Attandance]\n"
                        + "           ([sesid]\n"
                        + "           ,[stdid]\n"
                        + "           ,[present]\n"
                        + "           ,[description]\n"
                        + "           ,[record_time])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,GETDATE())";
                PreparedStatement stm_insert = connection.prepareStatement(sql);
                stm_insert.setInt(1, model.getId());
                stm_insert.setInt(2, att.getStudent().getId());
                stm_insert.setBoolean(3, att.isPresent());
                stm_insert.setString(4, att.getDescription());
                stm_insert.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        try {
            String sql = "SELECT ses.sesid,ses.[index],ses.date,ses.attanded\n"
                    + "	,g.gid,g.gname\n"
                    + "	,r.rid,r.rname\n"
                    + "	,t.tid,t.[description] tdescription\n"
                    + "	,l.lid,l.lname\n"
                    + "	,sub.subid,sub.subname\n"
                    + "	,s.stdid,s.stdname\n"
                    + "	,ISNULL(a.present,0) present, ISNULL(a.[description],'') [description]\n"
                    + "		FROM [Session] ses\n"
                    + "		INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "		INNER JOIN TimeSlot t ON t.tid = ses.tid\n"
                    + "		INNER JOIN Lecturer l ON l.lid = ses.lid\n"
                    + "		INNER JOIN [Group] g ON g.gid = ses.gid\n"
                    + "		INNER JOIN [Subject] sub ON sub.subid = g.subid\n"
                    + "		INNER JOIN [Student_Group] sg ON sg.gid = g.gid\n"
                    + "		INNER JOIN [Student] s ON s.stdid = sg.stdid\n"
                    + "		LEFT JOIN Attandance a ON s.stdid = a.stdid AND ses.sesid = a.sesid\n"
                    + "WHERE ses.sesid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Session ses = null;
            while (rs.next()) {
                if (ses == null) {
                    ses = new Session();
                    Room r = new Room();
                    r.setId(rs.getInt("rid"));
                    r.setName(rs.getString("rname"));
                    ses.setRoom(r);

                    TimeSlot t = new TimeSlot();
                    t.setId(rs.getInt("tid"));
                    t.setDescription(rs.getString("tdescription"));
                    ses.setTimeslot(t);

                    Lecturer l = new Lecturer();
                    l.setId(rs.getInt("lid"));
                    l.setName(rs.getString("lname"));
                    ses.setLecturer(l);

                    Group g = new Group();
                    g.setId(rs.getInt("gid"));
                    g.setName(rs.getString("gname"));
                    ses.setGroup(g);

                    Subject sub = new Subject();
                    sub.setId(rs.getInt("subid"));
                    sub.setName(rs.getString("subname"));
                    g.setSubject(sub);

                    ses.setDate(rs.getDate("date"));
                    ses.setIndex(rs.getInt("index"));
                    ses.setAttandated(rs.getBoolean("attanded"));
                }
                //read student
                Student s = new Student();
                s.setId(rs.getInt("stdid"));
                s.setName(rs.getString("stdname"));
                //read attandance
                Attendance a = new Attendance();
                a.setStudent(s);
                a.setSession(ses);
                a.setPresent(rs.getBoolean("present"));
                a.setDescription(rs.getString("description"));
                ses.getAttandances().add(a);
            }
            return ses;
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) throws ParseException{
        SessionDBContext ssdb = new SessionDBContext();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Session> sessions = ssdb.filterByStudent(1, sdf.parse("17/10/2022"), sdf.parse("23/10/2022"));
        
        for(Session ses : sessions){
            System.out.println(ses);
        }
    }
}
