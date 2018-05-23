#include "data_access.h"

void get_elements(int page)
{
	std::ifstream file_reader("resources/elements.txt");
	std::string line;
	while (std::getline(file_reader, line))
	{
		std::cout << line << std::endl;
	}
}