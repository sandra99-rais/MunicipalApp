package sample.connexion;

import java.sql.*;

public class CnxDB {
    private Connection c;
    public static Statement inst;
    private ResultSet re;

    public CnxDB() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        c= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","Sandra");
        inst=c.createStatement();
    }

    public static Statement getInst() {
        if (inst==null)
            try {
                new CnxDB();
            }
            catch (Exception e) {
                e.getMessage();
            }
        return inst;
    }
}
