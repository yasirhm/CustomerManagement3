package Sample;

import java.util.Random;
import static java.lang.Integer.parseInt;

/**
 * Created by dotinschool6 on 11/9/2016.
 */
public class Hello {
    public static Integer customerNumbers = 0;
    private static Random random = new Random();
    public static void main(String[] args)  {
        StringBuilder stmt = new StringBuilder("SELECT * FROM realcustomer WHERE ");
        stmt.append("nationalId");

        String x = " 70364 4254".replaceAll("\\s+","");
        Integer v = parseInt(x);
        System.out.println("inttttttt = "+v);

    }
}
