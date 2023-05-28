/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class ModelHotel {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/hotel";
    static final String USER = "root";
    static final String PASS = ""; 
    public Connection koneksi;
    public Statement statement;
    int jmlData;
    public ModelHotel() {
        try{
            Class.forName(JDBC_DRIVER);
            koneksi = (java.sql.Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Koneksi Berhasil");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }   
    }  
    public void inputdatakamar( String tipe_kamar, int harga){
        try {
            String query = "INSERT INTO `datakamar`(`id_kamar`, `tipe_kamar`,`harga_kamar`) VALUES (NULL,'"+tipe_kamar+"','"+harga+"')";
            
            statement = koneksi.createStatement();
            statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null,"Input Berhasil !!");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
     }
    public void inputpesankamar( String nama, int id_kamar, String bed, String breakfast, int hari, String checkin, int harga){
        try {
            System.out.println(checkin);
            String query = "INSERT INTO `history`(`id_booking`,`nama_tamu`, `id_kamar`,`tipe_bed`,`breakfast`,`total_hari`,`check_in`,`check_out`,`total_harga`,`status`) VALUES (NULL,'"+nama+"','"+id_kamar+"','"+bed+"','"+breakfast+"','"+hari+"','"+checkin+"',DATE_ADD('"+checkin+"',INTERVAL "+hari+" DAY),'"+harga+"','Check In')";
          
            statement = koneksi.createStatement();
            statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null,"Input Berhasil !!");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
     }
    public String[][] readDataKamar(){
         String[][] data=new String[jumlahDataKamar()][3];
        try{
          int totaldata = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya 0
          String query = "Select * from `datakamar`"; //proses pengambilan data
          statement = koneksi.createStatement();
          ResultSet resultSet = statement.executeQuery(query); //result isinya tabel belum berupa string
          while(resultSet.next()){ //konversi tabel ke string
              data[totaldata][0] = resultSet.getString("id_kamar"); 
              data[totaldata][1] = resultSet.getString("tipe_kamar"); 
              data[totaldata][2] = resultSet.getString("harga_kamar");
              totaldata++; 
          }
      }catch(SQLException e){
          System.out.println(e.getMessage());
          System.out.println("SQL Error");
      }
      return data;
    }
    public String[][] readDataPesan(){
         String[][] data=new String[jumlahDataPesan()][10];
        try{
          int totaldata = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya 0
          String query = "Select id_booking,nama_tamu,tipe_kamar,tipe_bed,breakfast,total_hari,check_in,check_out,total_harga,status from history INNER JOIN datakamar on datakamar.id_kamar=history.id_kamar"; //proses pengambilan data
          statement = koneksi.createStatement();
          ResultSet resultSet = statement.executeQuery(query); //result isinya tabel belum berupa string
          while(resultSet.next()){ //konversi tabel ke string
              data[totaldata][0] = resultSet.getString("id_booking"); 
              data[totaldata][1] = resultSet.getString("nama_tamu"); 
              data[totaldata][2] = resultSet.getString("tipe_kamar"); 
              data[totaldata][3] = resultSet.getString("tipe_bed");
              data[totaldata][4] = resultSet.getString("breakfast");
              data[totaldata][5] = resultSet.getString("total_hari"); 
              data[totaldata][6] = resultSet.getString("check_in");
              data[totaldata][7] = resultSet.getString("check_out");
              data[totaldata][8] = resultSet.getString("total_harga");
              data[totaldata][9] = resultSet.getString("status");
              totaldata++; 
          }
      }catch(SQLException e){
          System.out.println(e.getMessage());
          System.out.println("SQL Error");
      }
      return data;
    }
    public int jumlahDataKamar()
    {
        try{
            jmlData = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya 0
            String query = "Select * from `datakamar`"; //proses pengambilan data
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query); //result isinya tabel belum berupa string
            while(resultSet.next()){ //konversi tabel ke string
                jmlData++; 
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
        return jmlData;
      }
    public int jumlahDataPesan()
    {
        try{
            jmlData = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya 0
            String query = "Select * from `history`"; //proses pengambilan data
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query); //result isinya tabel belum berupa string
            while(resultSet.next()){ //konversi tabel ke string
                jmlData++; 
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
        return jmlData;
      }
    public boolean login(String username, String password){
        try{
            String query = "SELECT COUNT(*) AS total FROM admin WHERE username = '"+username+"' AND password = BINARY '"+password+"'";
            statement = koneksi.createStatement();
            ResultSet rs=statement.executeQuery(query);
            while(rs.next()){
                if(rs.getString("total").equals("1")){
                    return true;
                }
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }
        return false;
    }
    public void hapusdatakamar(int id_kamar){
         try{
            String query = "DELETE from datakamar WHERE id_kamar='"+id_kamar+"'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Hapus Berhasil !!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        } 
    }
    public void editdatakamar(int id_kamar, String tipe_kamar, int harga){
         try {
            String query = "UPDATE datakamar SET tipe_kamar='"+tipe_kamar+"', harga_kamar='"+harga+"' WHERE id_kamar='"+id_kamar+"'";
            
        
            statement = koneksi.createStatement();
            statement.executeUpdate(query);

            System.out.println("Edit Berhasil");
            JOptionPane.showMessageDialog(null,"Edit Berhasil !!");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } 
        
    }
    public void editpesankamar(int id_booking){
         try {
            String query = "UPDATE history SET status='Check Out' WHERE id_booking='"+id_booking+"'";
            
        
            statement = koneksi.createStatement();
            statement.executeUpdate(query);

            System.out.println("Edit Berhasil");
            JOptionPane.showMessageDialog(null,"Edit Berhasil !!");
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        } 
        
    }
    
  
}
