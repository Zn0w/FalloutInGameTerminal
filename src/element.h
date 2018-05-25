#pragma once

// The structure of elements data file:
// 	page_id;type;content;special_data; (the next page id, text, or the path to the script)

enum Type
{
	Menu,
	Button,
	Text
};

struct Element
{
	Type type;
	bool selected;
	//int current_page;
	int previous_page;
	//int next_page;
	const char* content;
	const char* special_data;
};

static void create_element(const char* element_data)
{
	
}