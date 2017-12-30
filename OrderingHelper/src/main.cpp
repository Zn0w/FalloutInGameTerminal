#include <iostream>
#include <string>
#include <vector>

#include "dao/itemsDataManaging.h"
#include "domain/Item.h"

using namespace std;

int option;
bool inputEntered = false;

void showMainMenu();

int main()
{
    showMainMenu();

	vector<Item> items = getItems();

	cin.ignore();
	cin.get();
}

void showMainMenu()
{
    while (!inputEntered)
    {
        cout << "-------------------------------------------------\n";
        cout << "1. New order\n";
        cout << "2. Log in as administrator\n";
        cout << "3. Exit\n";
        cout << "-------------------------------------------------\n";

        cout << "Input: ";
        cin >> option;

        inputEntered = option >= 1 && option <= 3;

        if (cin.fail())
        {
            cin.clear();
            cin.ignore(INT_MAX, '\n');
            cout << "Your input is invalid. (you only can use integer values for input)\n";
        }
        else if (!inputEntered)
            cout << "Your input is invalid. (input must be either 1, 2 or 3)\n";
    }
}
