class Main {
   public static void main (String[] args){
   	FileReader source=new FileReader(args [0]);
   	final LexicalAnalyzer analyzer = new LexicalAnalyzer(source);
   	while(true){
   		Symbol s = analyzer.nextToken();
   		if (s.getType().toString().equals('VARNAME'){
   			System.out.println(s.toString());
   		}	
   }
}
