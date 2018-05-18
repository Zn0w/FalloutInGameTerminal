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
	const char* content;
};