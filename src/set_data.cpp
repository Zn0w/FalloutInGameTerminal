#include "set_data.h"

void createElement(Page page, Element element)
{
	std::string path = "resources/pages/page" + std::to_string(page.id) + ".txt";
	std::ofstream file_writer(path, std::ios_base::app);

	if (!file_writer)
	{
		std::cout << "Failed to create an element" << std::endl;
		std::cout << "Title: " << element.title << std::endl;
		std::cout << "Special info: " << element.special_data << std::endl;
		system("pause");

		return;
	}

	// If the element is the first to be added to the page, then element.selected = true
	char selected_c;
	if (page.elements.size() > 0)
		selected_c = '0';
	else
		selected_c = '1';

	std::string elem_type_s;
	if (element.type == Link)
		elem_type_s = "link";
	else if (element.type == Text)
		elem_type_s = "text";
	else if (element.type == Button)
		elem_type_s = "button";

	file_writer << elem_type_s << ";" << selected_c << ";" << element.title << ";" << element.special_data << ";\n";
	file_writer.close();
}