import java.io.*;
import java.util.*;

class Main {

    private static final ArrayList<Symbol> list = new ArrayList<>();

    /**
     */
    public static void main (String[] args){
        if(args.length >= 3 && Objects.equals(args[0], "-wt")){
            try {
                System.out.println("PREMIERE CONDITION");
            	System.out.println(args.length);
            	System.out.println(args[0]);
                final FileReader source=new FileReader(args [2]);
                final FileWriter writer = new FileWriter(args[1]);
                final LexicalAnalyzer lexAn = new LexicalAnalyzer(source);
                Symbol s = lexAn.nextToken();
                while(s.getType().toString() != "END") {
			        list.add(s);
			        s = lexAn.nextToken();
                }
                if(s.getType().toString() == "END") {
                	list.add(s);
                }
                Parser parser = new Parser(list);
                String str = parser.startParsing();
                writer.write(str);
                writer.close();
                
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else if(args.length ==1) {
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
                Parser parser = new Parser(list);
                parser.startParsing();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("The file .co is empty");
            for (String i:args){
            		System.out.println(i);}
        }
        
    }
}
