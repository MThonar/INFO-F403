import java.io.*;
import java.util.*;

class Main {

	private static final TreeMap<String,Integer> ls = new TreeMap<String,Integer>();
	
	/**
	 * This is the main function: it calls the lexical analyzer with args[0] as argument.
	 * Each token encountered is printed using the toString() method of the Symbol class.
	 * If the token is of type "VARNAME", the method varL() is called.
	 * If the token is the end of the file, the word "Variables" is printed.
	 * Then, each element of the TreeMap are printed.
	 * @param args is the alCOl file given as argument during the execution.
	 */
	public static void main (String[] args){
		if(args.length >= 1){
			try {
				final FileReader source=new FileReader(args [0]);
				final LexicalAnalyzer lexAn = new LexicalAnalyzer(source);		
				while(true) {
					Symbol s = lexAn.nextToken();
					System.out.println(s.toString());
					if (s.getType().toString().equals("VARNAME")) {
						varL(s.getValue().toString(), s.getLine());
					}	
					else if(s.getType().toString() == "END") {
						System.out.println("Variables");
						for (String i : ls.keySet()) {
							System.out.println(i + " " + ls.get(i));
						}
					break;
					}
				}
			}
			catch(Exception e) {
			}
		}
		else {
			System.out.println("The file .co is empty");
		}
	}

	/**
	 * This function allows to add a String and an int to a TreeMap.
	 * @param var is the name of the variable.
	 * @param line is the number of the line where this variable has been encountered for the first time.
	 */
	private static void varL(String var,int line) {
		if(!ls.containsKey(var)) {
			ls.put(var,line);
		}
	}	
}
