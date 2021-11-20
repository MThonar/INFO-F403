all:
	jflex src/LexicalAnalyzer.flex
	javac -d bin -cp src/ src/part1.Main.java
	jar cfe dist/part1.jar part1.Main -C bin .

testing:
		java -jar dist/part1.jar test/euclid.co
