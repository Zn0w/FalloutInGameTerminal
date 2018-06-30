@echo off
g++ -c src/get_data.cpp -o obj/get_data.o
g++ -c src/main.cpp -o obj/main.o
g++ obj/main.o obj/get_data.o -o bin/terminal -std=c++11 -static-libgcc -static-libstdc++
pause