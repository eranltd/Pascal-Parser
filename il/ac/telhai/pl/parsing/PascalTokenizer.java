package il.ac.telhai.pl.parsing;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author eran Peled
 * 
 * This Class will interpret a Pascal File 
 * and output the tokens
 *
 */
public class PascalTokenizer {
	
	InputStreamReader src;
	StreamTokenizer tokens;
	HashMap<String,PascalToken> mapOfKeyWords;
	
	public PascalTokenizer(InputStreamReader in)
	{
		tokens = new StreamTokenizer(in);
		mapOfKeyWords = new HashMap<String,PascalToken>();
		initTokens();
	}
	
	/**
	 * Initialize the HashMap with the KeyWords of pascal
	 * 
	 * */
	public void initTokens()
	{
		mapOfKeyWords.put("AND", new AND());
		mapOfKeyWords.put("BEGIN", new BEGIN());
		mapOfKeyWords.put("ELSE", new ELSE());
		mapOfKeyWords.put("END", new END());
		mapOfKeyWords.put("FUNCTION", new FUNCTION());
		mapOfKeyWords.put("IF", new IF());
		mapOfKeyWords.put("NIL", new NIL());
		mapOfKeyWords.put("NOT", new NOT());
		mapOfKeyWords.put("PROGRAM", new PROGRAM());
		mapOfKeyWords.put("RECORD", new RECORD());
		mapOfKeyWords.put("THEN", new THEN());
		mapOfKeyWords.put("TYPE", new TYPE());
		mapOfKeyWords.put("VAR", new VAR());
		mapOfKeyWords.put("WHILE", new WHILE());	
		mapOfKeyWords.put("NEW", new NEW());
		
		mapOfKeyWords.put("INTEGER", new INTEGER());
		mapOfKeyWords.put("REAL", new REAL());
		
		
		tokens.ordinaryChars(',', '.');  // set out to be returned in ttype
	}
		
    	
	
	
	/**
	 * nextPascalToken will parse the input word by word \ sign by sign\
	 * and return to the caller a PascalToken
	 * */
	public PascalToken nextPascalToken() throws IOException{
	
		String s = new String();		
		PascalToken token = new EMPTY();
		 
	
		 try {
			while(tokens.nextToken() != StreamTokenizer.TT_EOF){
	
		        if(tokens.ttype == StreamTokenizer.TT_WORD) {	
		        	
		        	s = tokens.sval.toUpperCase();
		        	PascalToken value = mapOfKeyWords.get(s);
		        	if (value != null) {
		        	return value;
		        	
		        	}
		        		String parseME = tokens.toString();
						String betweenBRK = parseME.substring(parseME.indexOf("[") + 1, parseME.indexOf("]"));
						return new IDENTIFIER(betweenBRK.toLowerCase());	
		    
		        } else if(tokens.ttype == StreamTokenizer.TT_NUMBER) {
		        	
		        	String parseME = tokens.toString();
					String betweenBRK = parseME.substring(parseME.indexOf("[") + 3, parseME.indexOf("]"));
					betweenBRK = betweenBRK.indexOf(".") < 0 ? betweenBRK : betweenBRK.replaceAll("0*$", "").replaceAll("\\.$", "");
					return new LITERAL(betweenBRK);					
		        } else if(tokens.ttype == StreamTokenizer.TT_EOL) {
		        	//end-of-line
		        }
		        else{
		        	
		        	String parseME = tokens.toString();
					String betweenBRK = parseME.substring(parseME.indexOf("[") + 1, parseME.indexOf("]"));
		        	Pattern p = Pattern.compile("['^']", Pattern.CASE_INSENSITIVE);
		        	Matcher m = p.matcher(parseME);
		        	boolean b = m.find();
		        	
		        	if (b){
		        		if(betweenBRK.equalsIgnoreCase("'('"))
						{
		        			int t1 = tokens.nextToken();
		        			if(t1 == '*')
		        			{
		        				//skip the comment
		        				while(tokens.nextToken()!='*');
		        				if(tokens.nextToken()==')')
		        				continue;
		        			}
		        			tokens.pushBack();
						}
		        		
		        		//return the operator token
		        		if(betweenBRK.equalsIgnoreCase("':'"))
							{
		        	       
							int t1 = tokens.nextToken();
							tokens.pushBack();
							
							if(t1 == '=')
							{
								tokens.nextToken();
								return new SPECIALOPER();
							}
						
							
							return new OPER(betweenBRK);					
							}

						return new OPER(betweenBRK);	
		        	}
	
		        	else{
		        		//return the literal words
						return new LITERAL(betweenBRK);
		        	}
		        }
			}
			return new PascalEOF();
		} catch (IOException e) {
			System.out.println(e.getMessage());
}
		return token;
	}
	
	}

