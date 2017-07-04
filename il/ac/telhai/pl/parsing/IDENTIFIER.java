package il.ac.telhai.pl.parsing;

public class IDENTIFIER extends PascalToken{
	
	static String str;

	
	IDENTIFIER(String str){
		IDENTIFIER.str=str;
	}

	public String toString() {
        return getClass().getSimpleName().toUpperCase() + ": " +str;
    }
}
