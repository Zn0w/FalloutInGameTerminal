enum Type
{
	Button, // To be implemented later
	Text,
	Menu
};

struct Element
{
	Type type;
	bool selected;
	int previous_page;
	void* special_info;
	const char* content;
};