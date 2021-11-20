import java.io.*;
import java.util.*;

class Main {
	
	private static ArrayList<LexicalUnit> list = new ArrayList<>();
	
	/**
	 */
	public static void main (String[] args){
		if(args.length >= 1){
			try {
				final FileReader source=new FileReader(args [0]);
				final LexicalAnalyzer lexAn = new LexicalAnalyzer(source);	
				Symbol s = lexAn.nextToken();
				while(s.getType().toString() != "END") {
					list.add(s.getType());
					s = lexAn.nextToken();
				}
				/*for(LexicalUnit i : list) {
					System.out.println(i);
				}*/
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("The file .co is empty");
		}
	}	
}

