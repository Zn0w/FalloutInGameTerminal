// The structure of elements data file:
// 	page_id;type;selected;nextpage_id;content;special_data;

enum Type
{
	Menu,
	Read,
	Button,
	Text,
	Action
};

struct Element
{
	Type type;
	bool selected;
	int current_page;
	int previous_page;
	int next_page;
	const char* content;
	const char* special_data;
};

void create_element(const char* element_data)
{
	//for ()
	std::cout << "Strlen: " << strlen(element_data);
}