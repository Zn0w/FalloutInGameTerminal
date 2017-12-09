bin/OrderingHelper: obj/main.o obj/fileManager.o
	g++ obj/main.o obj/fileManager.o -o bin/OrderingHelper

obj/main.o: src/main.cpp
	g++ -c src/main.cpp -o obj/main.o

obj/fileManager.o: src/fileManager/fileManager.cpp src/fileManager/fileManager.h
	g++ -c src/fileManager/fileManager.cpp -o obj/fileManager.o