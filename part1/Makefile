all:
	jflex src/LexicalAnalyzer.flex
	javac -d bin -cp src/ src/Main.java
	jar cfe dist/part1.jar Main -C bin .

testing:
		java -jar dist/part1.jar test/euclid.co
		java -jar dist/part1.jar test/test_tab.co
		java -jar dist/part1.jar test/test_newline.co
		java -jar dist/part1.jar test/test.co
		java -jar dist/part1.jar test/not_in_euclid.co
		java -jar dist/part1.jar test/test_long.co
