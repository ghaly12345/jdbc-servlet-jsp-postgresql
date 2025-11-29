<%@ page language = "java" contentType = "text/html; charset=ISO-8859-1"
    pageEncoding ="ISO-8859-1"%>

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Delete Details</title>
        </head>

        <body>

            <form action="./DeleteDetails" method="post">
                <table>
                    <tr>
                        <td>Enter ID:</td>
                        <td><input type="text" name="id" maxlength = "6" size="7" /> </td>
                    </tr>
                </table>

                <br /> <input type="submit" value= "Delete Data" />
            </form>

            <br />

            <input type = "button" value = "Return to Home"
            onclick="window.location.href='Home.jsp'" />
        </body>
    </html>