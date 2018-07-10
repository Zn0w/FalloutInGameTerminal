#pragma once

#include <string>

enum Type
{
	Link,
	Button,
	Text,
	Notify
};

struct Element
{
	Type type;
	bool selected;
	std::string title;
	std::string special_data;
};