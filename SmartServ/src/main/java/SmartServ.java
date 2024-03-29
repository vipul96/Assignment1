import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SmartServ {


    public static void main(String args[])  {



        /**
         * Give the input
         * */


        /**
         * Test Cases:   1. samsung galaxy grand
         *              2. apple ipad
         *              3.apple iphone
         *              4.samsung galaxy
         *              5.samsung note
         *              6.samsung duos
         */
        Scanner sc = new Scanner(System.in);
        String title=sc.nextLine ();
        title=title.replaceAll ( "\\s",".*" ).toLowerCase ();

        Response res= (Response) given().
                when().request().
                get("https://s3.amazonaws.com/open-to-cors/assignment.json");

        Map <?,?>json =  res.jsonPath ().getMap ( "products" );
        for (Map.Entry<?,?> entry : json.entrySet()){
            String string = entry.getKey ().toString ();
            Pattern pattern=Pattern.compile ( title.toLowerCase ());
            Matcher matcher = pattern.matcher ( res.jsonPath ().getString ( "products."+string +".title" ).toLowerCase ());

           while(matcher.find ()){
               System.out.println (res.jsonPath ().getString ( "products."+string +""));
           }

        }

    }

}