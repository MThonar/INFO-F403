all:
	jflex src/LexicalAnalyzer.flex
	javac -d bin -cp src/ src/Main.java
	jar cfe dist/part3.jar Main -C bin .

testing:
	java -jar dist/part3.jar test/testReadPrint.co

args:
	java -jar dist/part3.jar test/testReadPrint.co llCode.ll
