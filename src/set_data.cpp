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
	Element element_to_delete = page->elements.at(element_id);

	std::string path = "resources/pages/page" + std::to_string(page->id) + ".txt";
	std::ifstream file_reader(path);
	std::ofstream file_clearer(path);
	std::ofstream file_writer(path, std::ios_base::app);
	
	if (!file_reader || !file_clearer || !file_writer)
	{
		std::cout << "Failed to delete the element (no such page found)" << std::endl;
	}

	std::vector<Element> elements;
	
	std::string line;
	while (std::getline(file_reader, line))
	{
		std::string element[4];
		std::string buffer;
		int counter = 0;
		for (int i = 0; i < line.size(); i++)
		{
			if (line.at(i) == ';')
			{
				element[counter] = buffer;
				buffer = "";
				counter++;
				
				if (counter >= 4)
					break;
				
				continue;
			}
			
			buffer += line.at(i);
		}

		Type element_type;
		if (element[0] == "link")
			element_type = Link;
		else if (element[0] == "button")
			element_type = Button;
		else if (element[0] == "text")
			element_type = Text;
		else if (element[0] == "notify")
			element_type = Notify;
		
		Element elem = {element_type, stoi(element[1]), element[2], element[3]};

		// Check if the element is one that needs to be deleted
		if (elem.title == element_to_delete.title && elem.special_data == element_to_delete.special_data)
			continue;

		elements.push_back(elem);
	}

	file_reader.close();

	// Delete file's contents
	file_clearer << "";

	file_clearer.close();

	for (Element element : elements)
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

	page->elements = elements;
}