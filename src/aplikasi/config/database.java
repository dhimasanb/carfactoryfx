package aplikasi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class database {
	public static Connection koneksi;
    public static Connection getConnection() throws SQLException {
        if(koneksi==null){
            new Driver();
            koneksi =DriverManager.getConnection("jdbc:mysql://localhost:3306/pabrikmobil","root","root");
        }
        return koneksi;
    }
    public static void main(String[] args){
        try{
            getConnection();
            System.out.println("Sukses");
         }
        catch (SQLException ex){
            System.err.println("Koneksi GAGAL");

        }
    }
}
