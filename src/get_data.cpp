#include "get_data.h"

Page getPage(int id)
{
	std::string path = "resources/pages/page" + std::to_string(id) + ".txt";
	std::ifstream file_reader(path);
	
	if (!file_reader)
	{
		Page error;
		error.id = id;
		
		Element err = {Notify, true, "[This page does not exist.]", ""};

		error.elements.push_back(err);

		return error;
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
		elements.push_back(elem);
	}

	file_reader.close();

	Page page = {id, 0, elements};
	return page;
}