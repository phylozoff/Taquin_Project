package sample;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class JDBC {
    private static Boolean connected = false;
    private static Connection connexion= null;
    private static final String TOP= "select pseudo, score from joueur inner join aJouer aJ on joueur.id = aJ.idJ inner join partie p on aJ.idP = p.id order by score DESC limit 3;";
    private static final String ADD_PARTIE = "insert into partie(score, date) values (?,?);";
    private static final String ADD_JOUEUR = "insert into joueur(pseudo) values  (?)";
    private static final String JOUEUR = "select * from joueur where pseudo=?";
    private static final String ADD_AJOUER= "insert into ajouer values(?,?)";

    private JDBC() {
    }

    public static TreeMap<String, Integer> classement() throws SQLException {
        PreparedStatement s =connexion.prepareStatement(TOP);
        ResultSet rs= s.executeQuery();
        TreeMap<String, Integer> res = new TreeMap<String, Integer>();

        while (rs.next()){
            res.put(rs.getString("pseudo"), rs.getInt("score"));
        }
        return res;
    }

    public static boolean addPartie(String pseudo, int score) throws SQLException {
        int idP=-1;
        int idJ=-1;
        PreparedStatement s1 =connexion.prepareStatement(JOUEUR);
        s1.setString(1, pseudo);
        ResultSet rs1 = s1.executeQuery();
        if (rs1.next()) {
            idJ = rs1.getInt(1);
        }
        else{
            PreparedStatement s2 =connexion.prepareStatement(ADD_JOUEUR,Statement.RETURN_GENERATED_KEYS);
            s2.setString(1, pseudo);
            try{
                s2.executeUpdate();
                ResultSet rs2= s2.getGeneratedKeys();
                if(rs2.next()) {
                    idJ=rs2.getInt(1);
                }
                System.out.println(idJ);
            }
            catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }

        PreparedStatement s =connexion.prepareStatement(ADD_PARTIE, Statement.RETURN_GENERATED_KEYS);
        s.setDate(2, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        s.setInt(1, score);
        try{
            s.execute();
            ResultSet rs= s.getGeneratedKeys();
            if (rs.next()) {
                idP=rs.getInt(1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        System.out.println("test");
        PreparedStatement s3 =connexion.prepareStatement(ADD_AJOUER);
        s3.setInt(1,idJ);
        s3.setInt(2,idP);
        try{
            s3.execute();
            System.out.println("test");
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public static Connection getConnexion()
    {
        if (connexion == null)
        {
            try {
                FileReader reader = new FileReader("src/sample/JDBCconfig.json");
                System.out.println(reader.toString());
                JSONObject config = (JSONObject) new JSONParser().parse(reader);

                System.out.println(config.toString());
                Class.forName("com.mysql.jdbc.Driver");
                String lien = "jdbc:mysql://"+config.get("host").toString()+":"+config.get("port").toString()+"/"+config.get("bdd").toString();
                System.out.println(lien);
                connexion= DriverManager.getConnection(lien,config.get("user").toString(),config.get("pass").toString());
                connected = true;


            }catch (Exception e){
                System.out.println(e);
            }
        }
        return connexion;
    }
    public static void main(String[] args) throws SQLException {
        JDBC.getConnexion();
        JDBC.addPartie("test", 99);
    }
}
