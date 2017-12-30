#pragma once

#include <string>

using namespace std;

struct Item
{
	int id;
	string name;
	int price; // for 1
	string measure; // piece, kilograms, liters, meters, pounds, foots, etc

	Item(int i, string n, int p, string m)
        : id(i), name(n), price(p), measure(m)
	{

	}
};
