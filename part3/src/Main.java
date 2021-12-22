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
        if (args.length == 1){
            try {
                final FileReader source = new FileReader(args[0]);
                final LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(source);
                Symbol symbol = lexicalAnalyzer.nextToken();
                while (!symbol.getType().toString().equals("END")){
                    list.add(symbol);
                    symbol = lexicalAnalyzer.nextToken();
                }
                if(symbol.getType().toString().equals("END")){
                    list.add(symbol);
                }
                Parser parser = new Parser(list);
                parser.startParsing();
            }
            catch (Exception e){
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