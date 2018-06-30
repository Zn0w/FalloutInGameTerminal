#pragma once

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
	const char* title;
	const char* special_data;
};