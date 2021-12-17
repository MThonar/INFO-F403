import java.io.*;
import java.util.*;

class Main {

    private static final ArrayList<Symbol> list = new ArrayList<>();

    /**
     * This function allows to extract a list of tokens from the input file.
     * It then creates a new Parser object with the list of tokens as an
     * argument. Lastly, it writes the ParseTree in LaTeX format in the
     * .tex file given as argument.
     */
    public static void main (String[] args){
        if (args.length == 3 && args[0].equals("-wt")) {
            try {
                final FileReader source=new FileReader(args [2]);
                final FileWriter writer = new FileWriter(args[1]);
                final LexicalAnalyzer lexAn = new LexicalAnalyzer(source);
                Symbol s = lexAn.nextToken();
                while (!s.getType().toString().equals("END")) {
			        list.add(s);
			        s = lexAn.nextToken();
                }
                if (s.getType().toString().equals("END")) {
                	list.add(s);
                }
                Parser parser = new Parser(list);
                ParseTree parseTree = parser.startParsing();
                String str = parseTree.toLaTeX();
                writer.write(str);
                writer.close();
                
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else if (args.length == 1) {
        	try {
                final FileReader source=new FileReader(args [0]);
                final LexicalAnalyzer lexAn = new LexicalAnalyzer(source);
                Symbol s = lexAn.nextToken();
                while (!s.getType().toString().equals("END")) {
			        list.add(s);
			        s = lexAn.nextToken();
                }
                if (s.getType().toString().equals("END")) {
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