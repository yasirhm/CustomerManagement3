package PresentationLayer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yasi on 11/8/2016.
 */
public class Presentation extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String type = request.getParameter("type");
        PrintWriter out = response.getWriter();
        String newManager;
        if(type.equals("DataAccessLayer.RealCustomer")){
            newManager = responseString("realCustomerManager",type);
        } else{
            newManager = responseString("legalCustomerManager",type);
        }
        out.println (newManager);

/*
        RequestDispatcher rs = request.getRequestDispatcher("/customerManger.html");
        rs.include(request,response);
        */
    }

    public String responseString(String action,String type){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html> \n" +
                "<style type=\"text/css\">\n" +
                "    body {\n" +
                "        background-image:\n" +
                "                url('../background.png');\n" +
                "}\n"+
                "</style>"+
                "<head> \n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
                "charset=ISO-8859-1\"> \n" +
                "<title>DataAccessCustomer Manager</title> \n" +
                "</head> \n" +
                "<body> <div align='center'> \n" +
                "<style" +
                "\"font-size=\"12px\" color='black'\"" + "\">" +
                "DataAccessCustomer type: " + type + " <br> " +
                "</font>" +
                "<form action="+action+">"+
                "        <br><br>\n" +
                "        <input type=\"submit\" name=\"action\" value=\"Creat New DataAccessCustomer\"<br><br>\n" +
                "<br>\n"+
                "        <input type=\"submit\" name=\"action\" value=\"Search DataAccessCustomer\"><br><br>\n" +
                "        <br><br>\n" +
                "</form>"+
                "</body> \n" +
                "</html>";

    }
}
