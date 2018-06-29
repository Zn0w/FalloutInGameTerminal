#include "data_access.h"

void get_elements(int page, int current_page, std::map<int, Element[10]>* elements)
{
	Element page_elements[10];
	
	std::ifstream file_reader("resources/elements.txt");
	std::string line;
	int element_counter = 0;
	while (std::getline(file_reader, line))
	{
		Element element;

		int page_id;

		std::string info = "";
		
		int counter = 0;
		for (int i = 0; i < line.size(); i++)
		{
			if (line.at(i) == ';')
			{
				counter++;
				
				if (counter == 1)
				{
					page_id = std::stoi(info);

					if (page_id != page)
					break;
				}
				else if (counter == 2)
				{
					if (info == "m")
						element.type = Menu;
					else if (info == "t")
						element.type = Text;
					else if (info == "b")
						element.type = Button;
					// else, handle error
				}
				else if (counter == 3)
					element.content = info.c_str();
				else if (counter == 4)
					element.special_data = info.c_str();

				info = "";
			}

			else
				info += line.at(i);
		}

		if (page_id == page && element_counter < 10)
		{
			page_elements[element_counter] = element;
			element_counter++;
		}
	}

	elements[page] = *page_elements;
}