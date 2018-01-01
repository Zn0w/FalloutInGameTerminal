#include <iostream>
#include <string>
#include <vector>

#include "dao/itemsDataManaging.h"
#include "domain/Item.h"
#include "domain/Order.h"

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
		//else if (command == 2)
			//enterAdminMode();
		else
			run = false;
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

		cout << "-------------------------------------------------\n\n";
    }

	return option;
}

void createOrder()
{
	bool order_completed = false;
	int input;

	vector<Item> items = getItems();

	Order new_order;

	int total = 0;

	while (!order_completed)
	{
		cout << "-------------------------------------------------\n";
		cout << "New order\n";
		cout << "Commands:\n";
		cout << "1. Add new item\n";
		cout << "2. Remove item\n";
		cout << "3. Complete order\n";
		cout << "4. Cancel order\n";
		cout << endl;
		cout << "Ordered items:\n";
		for (int i = 0; i < new_order.ordered_items.size(); i++)
		{
			pair<Item, int> element = new_order.ordered_items.at(i);
			cout << i + 1 << ". " << element.first.name << "(" << element.first.id << ")  ";
			cout << element.second << " " << element.first.measure;
			cout << " - $" << element.first.price * element.second << endl;
		}
		cout << "Total: $" << total << endl;

		cout << "Input: ";
		cin >> input;

		if (input == 1)
		{
			int item_id;
			int amount;

			cout << "Enter items id and preffered amount\n";
			cin >> item_id;
			cin >> amount;
			for (Item item : items)
			{
				if (item.id == item_id)
				{
					new_order.ordered_items.push_back(make_pair(item, amount));
					total += item.price * amount;
					break;
				}
			}

			cout << amount << " item with id " << item_id << " has been added\n";
		}
		else if (input == 2)
		{
			int element_number;

			cout << "Which item to delete (put a number of order, not items id)\n";
			cin >> element_number;

			pair<Item, int> element = new_order.ordered_items.at(element_number - 1);
			total -= element.first.price * element.second;

			new_order.ordered_items.erase(new_order.ordered_items.begin() + (element_number - 1));
		}
		else if (input == 3)
			order_completed = true;
		else if (input == 4)
			break;


		cout << "-------------------------------------------------\n\n";
	}

	if (order_completed)
		// Save order info to file
}