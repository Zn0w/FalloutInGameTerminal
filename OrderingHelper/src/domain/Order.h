#pragma once

#include <string>
#include <vector>

using namespace std;

struct Order
{
	string cashier;
	string date;
	vector<string, int> ordered_items;
};