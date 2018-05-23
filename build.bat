@echo off
g++ -c src/data_access.cpp -o obj/data_access.o
g++ -c src/main.cpp -o obj/main.o
g++ obj/main.o obj/data_access.o -o bin/terminal -std=c++11 -static-libgcc -static-libstdc++
pause