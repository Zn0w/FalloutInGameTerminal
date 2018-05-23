@echo off
g++ src/data_access.cpp -o obj/data_access.o
g++ src/main.cpp -o obj/main.o
g++ obj/main.o obj/data_access.o -o bin/terminal
pause