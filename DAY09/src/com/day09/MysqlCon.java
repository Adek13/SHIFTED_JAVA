package com.day09;

import java.sql.*;
class MysqlCon{
    public static void main(String args[]){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sonoo?useJDBCCompliantTimezoneShift=true&serverTimezone=UTC","root","root1234");
//here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            int result = stmt.executeUpdate("update  emp values('2','2','3')");
            ResultSet rs=stmt.executeQuery("select * from emp");
            System.out.println(result);
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}


