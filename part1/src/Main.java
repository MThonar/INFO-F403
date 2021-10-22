
import java.io.*;
import java.util.*;

class Main {

   private static final TreeMap<String,Integer> ls = new TreeMap<String,Integer>();

   private static void varL(String var,int line){
			if(!ls.containsKey(var))
				ls.put(var,line);
			}
				
				
   public static void main (String[] args){
   if(args.length >= 1){
   	try{
	   	final FileReader source=new FileReader(args [0]);
	   	final LexicalAnalyzer lexAn = new LexicalAnalyzer(source);

	   	while(true){
	   		Symbol s = lexAn.nextToken();
	   		System.out.println(s.toString());
	   		if (s.getType().toString().equals("VARNAME")){
	   			varL(s.getValue().toString(), s.getLine());
	   		}	
	   		else if(s.getType().toString() == "END"){
	   			System.out.println("Variables");
	   			for (String i : ls.keySet()) {
					System.out.println(i + " " + ls.get(i));
					}break;}
			
			}
  
  			
  	}catch(Exception e) {}
    }
    else 
  	System.out.println("The file .co is empty");
    } 
}
 
  
 


