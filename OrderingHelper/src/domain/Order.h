#pragma once

#include <string>
#include <vector>

using namespace std;

struct Order
{
	string cashier;
	string date;
	vector<pair<Item, int>> ordered_items;
};