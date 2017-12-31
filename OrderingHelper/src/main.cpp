#include <iostream>
#include <string>
#include <vector>

#include "dao/itemsDataManaging.h"
#include "domain/Item.h"

using namespace std;

// Returns command, chosen by user
int getUserCommand();
// Gets information about new order and saves its information
void createOrder();
// Opens administrator tools mode
void enterAdminMode();

int main()
{
	bool run = true;

	while (run)
	{
		int command = getUserCommand();
		
		if (command == 1)
			createOrder();
		else if (command == 2)
			enterAdminMode();
		else
			run = false;
	}

	vector<Item> items = getItems();
	for (Item item : items)
	{
		cout << "Item " << item.id << ":" << endl;
		cout << "Properties: " << item.name << " " << item.price << " " << item.measure << endl;
	}

	cin.ignore();
	cin.get();
}

int getUserCommand()
{
	bool inputEntered = false;
	int option;
	
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
