package PresentationLayer;

import BusinessLogic.BusinessLogic;
import DataAccessLayer.RealCustomer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by Yasi on 11/15/2016.
 */
public class SearchRealCustomerPresentation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        request.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("firstName").replaceAll("\\s+","");
        String lastName = request.getParameter("lastName").replaceAll("\\s+","");
        String customerNumber = request.getParameter("customerNumber").replaceAll("\\s+","");
        String nationalId = request.getParameter("NationalId").replaceAll("\\s+","");

        RealCustomer realCustomer = new RealCustomer(firstName, lastName, "", "", nationalId);
        if (!customerNumber.equals(""))
            realCustomer.setCustomerNumber(parseInt(customerNumber));

        List<RealCustomer> realCustomers = new ArrayList<RealCustomer>();
        String html;
        try {
            realCustomers = BusinessLogic.searchRealCustomerBiz(realCustomer);
            html = createHTMLString(realCustomers);
            if (realCustomers.size() == 0){
                String body = "رکوردی با این مشخصات ثبت نشده است." ;
                html=createHTMLString(body);
            }else
                html = createHTMLString(realCustomers);
        } catch (Exception e) {
            System.out.println("Erorr: " + e.getMessage());
            html = e.getMessage();  //TO DO: Error Page
            showExceptionMessage(html);
        }
        out.println(html);

    }

    public String createHTMLString(List<RealCustomer> realCustomers) {
        StringBuilder table = new StringBuilder(" ");
        for (RealCustomer result : realCustomers) {
            String row = "<TR ALIGN='CENTER'onmouseover=\"this.style.backgroundColor='#ffff66';\" onmouseout=\"this.style.backgroundColor='#d4e3e5';\">\n" +
                    "<td class=\"id\" style=\"display:none;\">" + result.getCustomerNumber() + "</td>" +
                    "<TD>" + result.getFirstName().toString() + " </TD>" +
                    "<TD>" + result.getLastName().toString() + "</TD>\n" +
                    "<TD>" + result.getFatherName().toString() + "</TD>\n" +
                    "<TD>" + result.getBirthDate().toString() + "</TD>\n" +
                    "<TD>" + result.getNationalId().toString() + "</TD>\n" +
                    "<td class=\"edit\"><a href=\"/editRealCustomer?id=" + result.getCustomerNumber() + "\" > <button  \">ویرایش</button></a></td>" +
                    //"<td><a type=\"text\" href=\"editRealCustomer.html?id="+result.getCustomerNumber()+" \"> ویرایش </a></td>\n"+
                    "<td class=\"remove\"><a href=\"/deleteRealCustomer?id=" + result.getCustomerNumber() + "\"><button \">حذف</button></a></td>" +
                    "</TR>\n";
            table.append(row);
        }
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html charset=UTF-8\" lang=\"fa\" dir=\"rtl\"> \n" +
                "<style type=\"text/css\">\n" +
                "    body {\n" +
                "        background-image:\n" +
                "                url('images/background.png');\n" +
                "}\n" +
                "table.hovertable {\n font-family: verdana,arial,sans-serif;\n font-size:11px;\n color:#333333; \n border-width: 1px; \n border-color: #999999; \n border-collapse: collapse;}\n"+
                "table.hovertable th {\n background-color:#c3dde0; \n border-width: 1px; \n padding: 8px; \n border-style: solid; \n border-color: #a9c6c9;}\n"+
                "table.hovertable tr { \n background-color:#d4e3e5; \n }\n" +
                "table.hovertable td {\n border-width: 1px; \n padding: 8px; \n border-style: solid; \n border-color: #a9c6c9;\n}"+
                "</style>" +
                "<head> \n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "<title>Search Result</title> \n" +
                "</head> \n" +
                "<body> <div align='center'> \n" +
                "<style" +
                "\"font-size=\"12px\" color='black'\"" + "\"</font>" +
                "<br><br><br><br> " +
                "<TABLE  class=\"hovertable\" BORDER='5' WIDTH='50%' CELLPADDING='4' CELLSPACING='3'>\n" +
                "<TR>\n" +
                "<TH COLSPAN='7'><BR><H3>نتایج جستجو مشتریان حقیقی</H3>\n" +
                "</TH>\n" +
                "</TR>\n" +
                "<TR>\n" +
                "<TH>نام</TH>\n" + "<TH>نام خانوادگی</TH>\n" + "<TH>نام پدر</TH>\n" + "<TH>تاریخ تولد</TH>\n" + "<TH>کد ملی</TH>\n" +"<TH></TH>\n<TH></TH>\n"+
                "</TR>\n" +
                table.toString() +
                "</TABLE>\n" +
                " <br><br>\n" +
                "<a type=\"text\" href=\"index.html\"> صفحه ی اول </a><br>\n" +
                "</body> \n" +
                "</html>";
    }

    public String createHTMLString(String body) {
        return
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                        "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                        "<html charset=UTF-8\" lang=\"fa\" dir=\"rtl\"> \n" +
                        "<style type=\"text/css\">\n" +
                        "    body {\n" +
                        "        background-image:\n" +
                        "                url('images/background.png');\n" +
                        "}\n" +
                        "</style>" +
                        "<head> \n" +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                        "<title>Edit Legal DataAccessCustomer</title> \n" +
                        "</head> \n" +
                        "<body align='center'>\n" +
                        "<br><br><br><br> " +
                        body+
                        " <br><br>\n" +
                        "<a type=\"text\" href=\"index.html\"> صفحه ی اول </a><br>\n"+
                        "</body> \n" +
                        "</html>";
    }

    public String showExceptionMessage(String message){
        return
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                        "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                        "<html charset=UTF-8\" lang=\"fa\" dir=\"rtl\"> \n" +
                        "<style type=\"text/css\">\n" +
                        "    body {\n" +
                        "        background-image:\n" +
                        "                url('images/background.png')};\n"+
                        "</style>"+
                        "<head> \n" +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"+
                        "<title>DataAccessCustomer Manager</title> \n" +
                        "</head> \n" +
                        "<body> <div align='center'> \n" +
                        "<style" +
                        "\"font-size=\"12px\" color='black'\"" + "\">" +
                        "<br><br><br><br> " +
                        "خطا :  <br>"+
                        "\t " + message + " <br> " +
                        "</font> <br><br>\n" +
                        "<a type=\"text\" href=\"index.html\"> صفحه ی اول </a><br>\n"+
                        "</div>\n"+
                        "</body> \n" +
                        "</html>";
    }
}
