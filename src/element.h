#pragma once

#include <string>

// The structure of elements data file:
// 	page_id;type;content;special_data; (the next page id, text, or the path to the script)

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