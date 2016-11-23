package PresentationLayer;

import BusinessLogic.BusinessLogic;
import DataAccessLayer.RealCustomer;
import BusinessLogic.ConflictInDataException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;


/**
 * Created by Yasi on 11/19/2016.
 */
public class EditRealCustomerPresentation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Edit Real DataAccessCustomer ");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        //request.getAttribute("id");
        String customerNumber = request.getParameter("id");
        RealCustomer realCustomer = BusinessLogic.getRealCustomerBiz(customerNumber);
        realCustomer.setCustomerNumber(parseInt(customerNumber));
        String body = createHTMLBodyEditPageString(realCustomer);
        String html = createHTMLString(body);
        out.println(html);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        System.out.println("Edit Real DataAccessCustomer ");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String fatherName = request.getParameter("fatherName");
        String birthDay = request.getParameter("birthDate").replaceAll("\\s+","");
        String customerNumber = request.getParameter("customerNumber").replaceAll("\\s+","");
        String nationalId = request.getParameter("NationalId").replaceAll("\\s+","");

        RealCustomer realCustomer = new RealCustomer(firstName,lastName,fatherName,birthDay,nationalId);
        realCustomer.setCustomerNumber(parseInt(customerNumber));

        String html;
        try {
            RealCustomer updated = BusinessLogic.updateRealCustomerBiz(realCustomer);
            String body = createHTMLBodyResultPageString(updated);
            html = createHTMLString(body);
        }catch (ConflictInDataException exp){
            String body = exp.getMessage();
            html = createHTMLString(body);
        }
            out.println(html);
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
                        "<title>Edit RealCustomer</title> \n" +
                        "</head> \n" +
                        "<body>\n" +
                        "<div align='center' >\n"+
                        body+
                        "</div>\n </body> \n" +
                        "</html>";

    }

    public String createHTMLBodyEditPageString(RealCustomer realCustomer){
        return
                "<br><br>\n" +
                        "<form action = \"/editRealCustomer\" method = \"post\" >" +
                        "<table >\n"+
                        "<tr >\n"+
                        "<td > نام </td >\n"+
                        "<td > نام خانوادگی</td >\n"+
                        "<td > نام پدر</td >\n"+
                        "<td > تاریخ تولد</td >\n"+
                        //"<td > کد ملی</td >\n"+
                        "</tr >\n"+
                        "<tr >\n"+
                        "<td >\n"+
                        "<input name = \"firstName\" type = \"text\" value='"+realCustomer.getFirstName()+
                        "'placeholder='"+realCustomer.getFirstName()+"'>"+
                        "</td >\n"+
                        "<td >\n"+
                        "<input name = \"lastName\" type = \"text\" value='"+realCustomer.getLastName()+
                        "' placeholder="+realCustomer.getLastName()+">"+
                        "</td >\n"+
                        "<td >\n"+
                        "<input name = \"fatherName\" type = \"text\" value='"+realCustomer.getFatherName()+
                        "' placeholder="+realCustomer.getFatherName()+">"+
                        "</td >\n"+
                        "<td >\n"+
                        "<input name = \"birthDate\" type = \"text\" value='"+realCustomer.getBirthDate()+
                        "' placeholder="+realCustomer.getBirthDate()+">"+
                        "</td >\n"+
                        "<td >\n <input value = \"ثبت\" type = \"submit\"  / > </td >\n"+
                        "<td style=\"display:none;\">\n <input name='customerNumber' value='"+realCustomer.getCustomerNumber()+"' ></td >\n"+
                        "<td style=\"display:none;\">\n <input name='NationalId' value='"+realCustomer.getNationalId()+"'></td >\n"+
                        "</tr>\n"+
                        "</table >\n"+
                        "<br><br>" +
                        "<a type=\"text\" href=\"index.html\"> صفحه ی اول </a><br>\n" +
                        "</form >\n";
    }

    public String createHTMLBodyResultPageString(RealCustomer realCustomer){
        return
                "<br><br>\n" +
                        "<table >\n"+
                        "<tr >\n"+
                        "<td > نام </td >\n"+
                        "<td > نام خانوادگی</td >\n"+
                        "<td > نام پدر</td >\n"+
                        "<td > تاریخ تولد</td >\n"+
                        "<td > کد ملی</td >\n"+
                        "</tr >\n"+
                        "<tr >\n"+
                        "<td >\n"+
                        realCustomer.getFirstName()+
                        "</td >\n"+
                        "<td >\n"+
                        realCustomer.getLastName()+
                        "</td >\n"+
                        "<td >\n"+
                        realCustomer.getFatherName()+
                        "</td >\n"+
                        "<td >\n"+
                        realCustomer.getBirthDate()+
                        "</td >\n"+
                        "<td >\n"+
                        realCustomer.getNationalId()+
                        "</td >\n"+
                        "</tr>\n"+
                        "</table >\n"+
                        "<br><br>" +
                        "<a type=\"text\" href=\"index.html\"> صفحه ی اول </a><br>\n"
                        ;
    }
}
