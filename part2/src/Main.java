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
                list.add(s);
                while(s.getType().toString() != "END") {
                    Parser parser = new Parser(s);
                    s = lexAn.nextToken();
                }
				for(Symbol i : list) {
					System.out.println(i);
				}
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
