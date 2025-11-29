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

@WebServlet("/DeleteDetails")
public class DeleteDetails extends HttpServlet {

    private static final long srialVersionUID = 1L;

    protected  void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        try{
            Class.forName(DbUtil.driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found" + e);
        }

        try {
            Connection conn = DriverManager.getConnection(DbUtil.url, DbUtil.user,DbUtil.password);
            System.out.println("connection successful");
            PreparedStatement st = conn.prepareStatement("delete from studentdetails where stuid=?");

            st.setInt(1,Integer.valueOf(request.getParameter("id")));
            st.executeUpdate();

            st.close();
            conn.close();
            response.sendRedirect("Success.jsp?msg=Delete");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
