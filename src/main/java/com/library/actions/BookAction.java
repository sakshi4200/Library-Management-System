package com.library.actions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport{


//    public static Connection conn() throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "sakshi");
//    }

   public String BookSearch ()
    {
        getBorrowers();
        return "success";
    }
        public static void getBorrowers( ) {
//            int flag = 0;
//            try {
//                PreparedStatement ps = conn().prepareStatement("select * from borrower");
//             ResultSet rs=   ps.executeQuery();
//                System.out.println(rs);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//          //  return flag;
        }


}
