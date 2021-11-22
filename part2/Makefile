all:
	jflex src/LexicalAnalyzer.flex
	javac -d bin -cp src/ src/Main.java
	jar cfe dist/part2.jar Main -C bin .

testing:
	java -jar dist/part2.jar -wt tree.tex test/test1.co
	#pdflatex tree.tex > /dev/null
	#java -jar dist/part2.jar test/euclid.co
