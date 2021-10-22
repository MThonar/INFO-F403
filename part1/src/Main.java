import java.util.ArrayList;
import java.io.*;


class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException {
   		FileReader source = new FileReader(args [0]);
   		final LexicalAnalyzer analyzer = new LexicalAnalyzer(source);
   		ArrayList<String> ls = new ArrayList<String>();
   		while(true) {
	   		Symbol s = analyzer.nextToken();
	   		if (s.getType().toString().equals("VARNAME")) {
	   			System.out.println(s.toString());
	   			ls.add(s + " " + s.getValue());
	   		}	
	   		else if(s.getType().toString() == "END") {
	   			System.out.println("Variables");
	   			for(int i = 0; i < ls.size(); i++) {
	   			
		    			System.out.println(ls.get(i));
	   			}
	   			break;
   			}
	 	}
	}
}
