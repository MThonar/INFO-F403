import java.io.*;
import java.util.*;

class Main {

    private static final ArrayList<Symbol> list = new ArrayList<>();

    /**
     */
    public static void main (String[] args){
        if(args.length >= 1){
            try {
                final FileReader source=new FileReader(args [0]);
                final LexicalAnalyzer lexAn = new LexicalAnalyzer(source);
                Symbol s = lexAn.nextToken();
                while(s.getType().toString() != "END") {
			        list.add(s);
			        s = lexAn.nextToken();
                }
                if(s.getType().toString() == "END") {
                	list.add(s);
                }
                for (Symbol i : list){
		        System.out.println(i);
		 }
                Parser parser = new Parser(list);
                parser.startParsing();
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
