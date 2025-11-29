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

@WebServlet("/InsertDetails")
public class InsertDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{

        try{
            Class.forName(DbUtil.driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }

        try{
            Connection conn = DriverManager.getConnection(DbUtil.url,DbUtil.user,DbUtil.password);
            System.out.println("Connection successful");

            //Query/statement to insert the values
            PreparedStatement st = conn.prepareStatement("insert into studentdetails values(? , ? , ? , ?)");

            //set values into the qeury
            st.setInt(1,Integer.valueOf(request.getParameter("id")));
            st.setString(2,request.getParameter("name"));
            st.setString(3,request.getParameter("email"));
            st.setString(4,request.getParameter("phnum"));

            //Execute the query
            st.executeUpdate();

            st.close();
            conn.close();

            //Redirect the response to success page
            response.sendRedirect("Success.jsp?msg=Insert");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
