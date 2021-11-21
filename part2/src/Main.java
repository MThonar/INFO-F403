import java.io.*;
import java.util.*;

class Main {

    private static final ArrayList<Symbol> list = new ArrayList<>();

    /**
     */
    public static void main (String[] args){
        if(args.length >= 1){
            try {
            	System.out.println("coucou");
                final FileReader source=new FileReader(args [0]);
                final LexicalAnalyzer lexAn = new LexicalAnalyzer(source);
                Symbol s = lexAn.nextToken();
                while(s.getType().toString() != "END") {
			list.add(s);
			Parser parser = new Parser(list);
			s = lexAn.nextToken();
                }
                if(s.getType().toString() == "END") {
                	list.add(s);
                }
		System.out.println(list);
                //parser.startParsing();
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
