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

	element.selected = selected_c == '1';
	page.elements.push_back(element);
}

void deleteElement(Page* page, int element_id)
{
	std::string path = "resources/pages/page" + std::to_string(page->id) + ".txt";
	std::ofstream file_clearer(path);
	std::ofstream file_writer(path, std::ios_base::app);
	
	if (!file_clearer || !file_writer)
	{
		std::cout << "Failed to delete the element (no such page found)" << std::endl;
	}

	// Delete file's contents
	file_clearer << "";

	file_clearer.close();

	//vec.erase(vec.begin() + 1);
	page->elements.erase(page->elements.begin() + element_id);

	if (element_id > 0)
		page->elements.at(element_id - 1).selected = true;
	else
		page->elements.at(element_id + 1).selected = true;

	for (Element element : page->elements)
	{
		char selected_c;
		if (element.selected)
			selected_c = '1';
		else
			selected_c = '0';

		std::string elem_type_s;
		if (element.type == Link)
			elem_type_s = "link";
		else if (element.type == Text)
			elem_type_s = "text";
		else if (element.type == Button)
			elem_type_s = "button";
		
		file_writer << elem_type_s << ";" << selected_c << ";" << element.title << ";" << element.special_data << ";\n";
	}

	file_writer.close();
}

void createPage(Element* link, int previous)
{
	int page_count = getPageCounter();
	if (page_count < 0)
	{
		std::cout << "Failed to create a new page" << std::endl;
		system("pause");
	}
	else
	{
		std::string path = "resources/pages/page" + std::to_string(page_count++) + ".txt";
		std::ofstream file_writer(path);

		if (!file_writer)
		{
			std::cout << "Failed to create a new page" << std::endl;
			system("pause");

			return;
		}

		
	}
}