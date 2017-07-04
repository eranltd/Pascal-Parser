package il.ac.telhai.pl.parsing;

public class OPER extends PascalToken{
		
		String sign;

		
		OPER(String sign){
			this.sign=sign;
		}

		public String toString() {
	        return sign;
	    }
	

}
