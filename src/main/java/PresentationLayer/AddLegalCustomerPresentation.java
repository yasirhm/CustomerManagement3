package PresentationLayer;

import BusinessLogic.BusinessLogic;
import DataAccessLayer.LegalCustomer;
import BusinessLogic.ConflictInDataException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Yasi on 11/19/2016.
 */
public class AddLegalCustomerPresentation extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String registrationDate = request.getParameter("registrationDate");
        String economicalCode = request.getParameter("economicalCode").replaceAll("\\s+","");

        String html="";
        if (economicalCode.equals("") || name.equals("")){
            html = showExceptionMessage("اطالاعات ورودی کافی نیست.");
            out.println(html);
        }
        else{
            LegalCustomer legalCustomer = new LegalCustomer(name,registrationDate,economicalCode);

            try {
                //check if null returnedd
                legalCustomer = BusinessLogic.addLegalCustomerBiz(legalCustomer);
                html = createHTMLString(legalCustomer);
            }catch (ConflictInDataException e){
                System.out.println(" خطا : "+e.getMessage());
                html = showExceptionMessage(e.getMessage());
            }
            out.println(html);
        }
    }

    public String createHTMLString(LegalCustomer legalCustomer){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
                "http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html charset=UTF-8\" lang=\"fa\" dir=\"rtl\"> \n" +
                "<style type=\"text/css\">\n" +
                "    body {\n" +
                "        background-image:\n" +
                "                url('images/background.png');\n" +
                "}\n"+
                "</style>"+
                "<head> \n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"+
                "<title>DataAccessCustomer Manager</title> \n" +
                "</head> \n" +
                "<body> <div align='center'> \n" +
                "<style" +
                "\"font-size=\"12px\" color='black'\"" + "\">" +
                "<br><br><br><br> " +
                "اطلاعات مشتری جدید ثبت شد. <br>"+
                "<br><br>" +
                "\t  نام شرکت  : " +  legalCustomer.getName() + " <br> " +
                "\t کد اقتصادی : " +  legalCustomer.getEconomicalCode()+ " <br> " +
                "\t شماره مشتری : " +  legalCustomer.getCustomerNumber() + " <br> " +
                "</font> <br><br>\n" +
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
                        "خطا: <br>"+
                        "\t " + message + " <br> " +
                        "</font> <br><br>\n" +
                        "<a type=\"text\" href=\"index.html\"> صفحه ی اول </a><br>\n"+
                        "</div>\n"+
                        "</body> \n" +
                        "</html>";
    }

}
