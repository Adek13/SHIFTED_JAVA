package com.day09.Tugas03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Menu {
    //variable untuk scanner java
    static InputStreamReader r = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(r);
    //variable con dan statement
    static Connection con;
    static Statement stmt;
    // main method
    public static void main(String[] args) {
        Connect();//set koneksi DB
        PrintMenu();//print menu program
    }
    //method print menu program
    public static void PrintMenu(){
        String menu = "";
        while (!menu.equals("99")){
            System.out.println("Menu\n");
            System.out.println("1. Input Data");
            System.out.println("2. Edit Data");
            System.out.println("3. Delete Data");
            System.out.println();
            System.out.print("Input Pilihan : ");
            try {
                menu = br.readLine();
                switch (Integer.parseInt(menu)){
                    case 1:
                        InputData();
                        break;
                    case 2:
                        EditData();
                        break;
                    case 3:
                        DeleteData();
                        break;
                    default:
                        break;
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
        try {
            con.close();//close koneksi
            System.out.println("Disconnected!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //method koneksi ke DB, db = db_siswa, user = root, pass = root1234
    public static void Connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_siswa?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC","root","root1234");
            stmt = con.createStatement();
            System.out.println("Connected to Database");
        }catch(Exception e){ System.out.println(e);}
    }
    //method untuk input data ke DB
    public static void InputData(){
        try {
            System.out.print("Input Data Baru\n\n");
            System.out.print("Fullname : ");
            String Fullname = br.readLine();
            System.out.print("Address : ");
            String Address = br.readLine();
            System.out.print("Status : ");
            String Status = br.readLine();
            System.out.println("Grades...");
            System.out.print("Physics : ");
            String Physics = br.readLine();
            System.out.print("Calculus : ");
            String Calculus = br.readLine();
            System.out.print("Biologi : ");
            String Biologi = br.readLine();
            String query = "INSERT INTO tbl_data(fullname, address, status, physics, calculus, biologi) VALUES('" +
                    Fullname + "','" +
                    Address + "','" +
                    Status + "','" +
                    Physics + "','" +
                    Calculus + "','" +
                    Biologi + "')";
            int res = stmt.executeUpdate(query);
            if(res==1){
                System.out.println("Sukses Input Data!");
            }else{
                System.out.println("Gagal Input Data!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //Method untuk edit data berdasarkan id
    public static void EditData(){
        ShowAll();// tampilkan data dalam DB
        try {
            System.out.print("Update Data\n\n");
            System.out.print("Input ID : ");
            String id = br.readLine();
            System.out.print("Fullname : ");
            String Fullname = br.readLine();
            System.out.print("Address : ");
            String Address = br.readLine();
            System.out.print("Status : ");
            String Status = br.readLine();
            System.out.println("Grades...");
            System.out.print("Physics : ");
            String Physics = br.readLine();
            System.out.print("Calculus : ");
            String Calculus = br.readLine();
            System.out.print("Biologi : ");
            String Biologi = br.readLine();
            String query = "UPDATE tbl_data SET " +
                    "fullname = '" + Fullname + "'," +
                    "address = '" + Address + "'," +
                    "status = '" + Status + "'," +
                    "physics = '" + Physics + "'," +
                    "calculus = '" + Calculus + "'," +
                    "biologi = '" + Biologi + "' " +
                    "WHERE id = '"+ id +"'";
            int res = stmt.executeUpdate(query);
            if(res==1){
                System.out.println("Sukses Update Data!");
            }else{
                System.out.println("Gagal Update Data!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //delete data berdasarkan id
    public static void DeleteData(){
        ShowAll();// tampilkan data dalam DB
        try {
            System.out.print("Delete Data\n\n");
            System.out.print("Input ID : ");
            String id = br.readLine();
            String query = "DELETE FROM tbl_data WHERE id= '" + id + "'";
            int res = stmt.executeUpdate(query);
            if(res==1){
                System.out.println("Sukses Delete Data!");
            }else{
                System.out.println("Gagal Delete Data!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //Method untuk menampilkan semua data dalam DB
    public static void ShowAll(){
        try {
            String query = "SELECT * FROM tbl_data";
            ResultSet rs = stmt.executeQuery(query);
            String format = "| %-2d | %-15s | %-10s | %-10s | %-2d      | %-2d       | %-2d      |%n";
            System.out.printf("+----+-----------------+------------+------------+---------+----------+---------+%n");
            System.out.printf("| ID |  Fullname       |   Adress   |   Status   | Physics | Calculus | Biologi |%n");
            System.out.printf("+----+-----------------+------------+------------+---------+----------+---------+%n");
            while (rs.next()){
                System.out.printf(format,
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7));
            }
            System.out.printf("+----+-----------------+------------+------------+---------+----------+---------+%n");
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
