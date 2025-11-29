<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Details</title>
</head>
<body>

    <h2>Fill in your details</h2>

    <form action="./UpdateDetails" method="post">
        <table>
            <tr>
                <td>ID:</td>
                <td><input type = "text" name="id"
                    value= "<%=request.getParameter("id")%>" readonly = "readonly">
                    </td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" maxlength="30" size="25" />
                </td>
            </tr>

            <tr>
                            <td>Email Id:</td>
                            <td><input type="text" name="email" maxlength="40" size="35" />
                            </td>
                        </tr>

                        <tr>
                                        <td>Phone Number:</td>
                                        <td><input type="text" name="phnum" maxlength="10" size="11" />
                                        </td>
                                    </tr>

                                    <br />
                                    </table>
                                    <br / > <input type = "submit" value="Update Data" />
        </form>

        <br />

        <input type="button"  value= "Return to Home"
        onclick="window.location.href='Home.jsp'"/>

       </body>

    </html>


