package il.ac.telhai.pl.parsing;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
    	final String filename = "/Users/eran/Documents/workspace/EX1_Pascal/src/TaxiCab.p";
    	System.setIn(new FileInputStream(filename));
    	
        PascalTokenizer pt = new
                PascalTokenizer(new InputStreamReader(System.in));

        PascalToken token;
        do  {
            System.out.println(token = pt.nextPascalToken());
        } while (!(token instanceof PascalEOF));
    }
}
