#include <iostream>
#include <string>

#include "fileManager/fileManager.h"

int option;
bool inputEntered = false;

void showMainMenu();

int main() {
    showMainMenu();
	getFileContents();
}

void showMainMenu() {
    while (!inputEntered) {
        std::cout << "-------------------------------------------------\n";
        std::cout << "1. New order\n";
        std::cout << "2. Log in as administrator\n";
        std::cout << "3. Exit\n";
        std::cout << "-------------------------------------------------\n";

        std::cout << "Input: ";
        std::cin >> option;

        inputEntered = option >= 1 && option <= 3;

        if (std::cin.fail()) {
            std::cin.clear();
            std::cin.ignore(INT_MAX, '\n');
            std::cout << "Your input is invalid. (you only can use integer values for input)\n";
        }
        else if (!inputEntered)
            std::cout << "Your input is invalid. (input must be either 1, 2 or 3)\n";
    }
}
