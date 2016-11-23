package PresentationLayer;

import BusinessLogic.BusinessLogic;
import DataAccessLayer.LegalCustomer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

/**
 * Created by Yasi on 11/22/2016.
 */
public class EditLegalCustomerPresentation extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String customerNumber = request.getParameter("id");
        LegalCustomer legalCustomer = BusinessLogic.getLegalCustomerBiz(customerNumber);
        legalCustomer.setCustomerNumber(parseInt(customerNumber));
        String body = createHTMLBodyEditPageString(legalCustomer);
        String html = createHTMLString(body);
        out.println(html);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String registrationDate = request.getParameter("registrationDate").replaceAll("\\s+","");
        String customerNumber = request.getParameter("customerNumber").replaceAll("\\s+","");
        LegalCustomer legalCustomer = new LegalCustomer(name,registrationDate,"");
        legalCustomer.setCustomerNumber(parseInt(customerNumber));

        String html;
        try {
            LegalCustomer updated = BusinessLogic.updateLegalCustomerBiz(legalCustomer);
            String body = createHTMLBodyResultPageString(updated);
            html = createHTMLString(body);
        }catch (Exception exp){
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
                        "<title>Edit Legal DataAccessCustomer</title> \n" +
                        "</head> \n" +
                        "<body align='center'>\n" +
                        body+
                        "</body> \n" +
                        "</html>";
    }

    public String createHTMLBodyEditPageString(LegalCustomer legalCustomer){
        return
                "<br><br><div align='center' >\n" +
                        "<form action = \"/editLegalCustomer\" method = \"post\" >" +
                        "<table >\n"+
                        "<tr >\n"+
                        "<td >نام شرکت </td >\n"+
                        "<td > تاریخ ثبت</td >\n"+
                        "<td > شماره مشتری</td >\n"+
                        "</tr >\n"+
                        "<tr >\n"+
                        "<td >\n"+
                        "<input name = \"name\" type = \"text\" value='"+legalCustomer.getName()+
                        "'placeholder='"+legalCustomer.getName()+"'>"+
                        "</td >\n"+
                        "<td >\n"+
                        "<input name = \"registrationDate\" type = \"text\" value='"+legalCustomer.getRegistrationDate()+
                        "' placeholder="+legalCustomer.getRegistrationDate()+">"+
                        "</td >\n"+
                        "<td >\n"+
                        "<div>"+legalCustomer.getCustomerNumber()+
                        "</div>"+
                        "</td >\n"+
                        "<td >\n <input value = \"ثبت\" type = \"submit\"  / > </td >\n"+
                        "<td style=\"display:none;\">\n <input name='customerNumber' value='"+legalCustomer.getCustomerNumber()+
                        "' ></td >\n"+
                        "</tr>\n"+
                        "</table >\n"+
                        "<br><br>" +
                        "<a type=\"text\" href=\"index.html\"> صفحه ی اول </a><br>\n" +
                        "</form >\n</div>\n";
    }

    public String createHTMLBodyResultPageString(LegalCustomer legalCustomer){
        return
                "<br><br>\n" +
                        "<table >\n"+
                        "<tr >\n"+
                        "<td >نام شرکت </td >\n"+
                        "<td > تاریخ ثبت</td >\n"+
                        "<td > شماره مشتری</td >\n"+
                        "</tr >\n"+
                        "<tr >\n"+
                        "<td >\n"+
                        legalCustomer.getName()+
                        "</td >\n"+
                        "<td >\n"+
                        legalCustomer.getRegistrationDate()+
                        "</td >\n"+
                        "<td >\n"+
                        legalCustomer.getCustomerNumber()+
                        "</td >\n"+
                        "</tr>\n"+
                        "</table >\n"+
                        "<br><br>" +
                        "<a type=\"text\" href=\"index.html\"> صفحه ی اول </a><br>\n";
    }
}
