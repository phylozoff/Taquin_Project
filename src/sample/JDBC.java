package sample;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Iterator;

public class JDBC {
    private static Boolean connected = false;
    private static Connection connexion;

    public static void Connexion(){
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


            // interroger la base et récupérer tous les films

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        JDBC.Connexion();
    }
}
