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
import java.sql.ResultSet;

@WebServlet("/SelectDetails")
public class SelectDetails extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request , HttpServletResponse response)  throws
            ServletException , IOException {

        try {
            Class.forName(DbUtil.driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found "  + e);
        }

        try{
            int id = 0;
            String name = "" , email = "" , ph = "";

            Connection conn = DriverManager.getConnection(DbUtil.url , DbUtil.user , DbUtil.password);
            System.out.println("connection successful");

            PreparedStatement st = conn.prepareStatement("select * from studentdetails where stuid = ?");

            st.setInt(1,Integer.valueOf(request.getParameter("id")));

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                email = rs.getString(3);
                ph = rs.getString(4);
            }

            rs.close();
            st.close();
            conn.close();

            response.sendRedirect("Result.jsp?id=" + id + "&name=" + name + "&email=" + email + "&phone=" + ph);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
