all:
	mkdir -p bin
	javac -d bin $(shell find src -name "*.java")
run: all
	java -cp bin Main
clean:
	rm -rf bin
