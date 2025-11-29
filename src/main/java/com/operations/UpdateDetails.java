package com.operations;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/UpdateDetails")
public class UpdateDetails extends HttpServlet {

    private  static  final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {

        try {
            Class.forName(DbUtil.driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }


        try{
            Connection conn = DriverManager.getConnection(DbUtil.url,DbUtil.user,DbUtil.password);
            System.out.println("connection successful");

            PreparedStatement st = conn.prepareStatement("update studentdetails set stuname=?, email=? , phonenum = ?  where stuid = ?");
            st.setString(1,request.getParameter("name"));
            st.setString(2,request.getParameter("email"));
            st.setString(3,request.getParameter("phnum"));
            st.setInt(4,Integer.valueOf(request.getParameter("id")));
            st.executeUpdate();

            st.close();
            conn.close();

            response.sendRedirect("Success.jsp?msg=Update");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
