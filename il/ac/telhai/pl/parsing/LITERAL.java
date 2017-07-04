package il.ac.telhai.pl.parsing;

public class LITERAL extends PascalToken{
	
	String literal;

	
	LITERAL(String lit){
		this.literal=lit;
	}

	public String toString() {
        return "LITERAL: "+literal;
    }

}
