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
                ArrayList<Symbol> AST = parser.startParsing();
                System.out.println("AST in form of a list:");
                for(Symbol node : AST){
                    System.out.println(node.getValue().toString());
                }
                System.out.println("end AST");
                LLVMprinter llvm = new LLVMprinter(AST);
                String llvmCode = llvm.getLLVMcode();
                System.out.println(llvmCode);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else if (args.length == 2){
            try {
                final FileReader source = new FileReader(args[0]);
                final FileWriter writer = new FileWriter(args[1]);
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
                ArrayList<Symbol> AST = parser.startParsing();
                System.out.println("AST in form of a list:");
                for(Symbol node : AST){
                    System.out.println(node.getValue().toString());
                }
                System.out.println("end AST");
                LLVMprinter llvm = new LLVMprinter(AST);
                String llvmCode = llvm.getLLVMcode();
                System.out.println(llvmCode);
                writer.write(llvmCode);
                writer.close();
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