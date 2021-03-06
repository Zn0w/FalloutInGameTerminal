#include <iostream>
#include <windows.h>
#include <stdlib.h>

#include "page.h"
#include "get_data.h"
#include "set_data.h"

Page current_page;

std::vector<Page> pages;

bool running = true;

void close_terminal();

void display_welcomeScreen();

Page setPage(int id);

HANDLE hConsole; // For changing console text color

int main()
{
	current_page.id = 0;
	current_page.previous = 0;

	Element start_element = {Link, true, "Go", "1"};

	current_page.elements.push_back(start_element);

	hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
	// Set console color to the light green (default)
	SetConsoleTextAttribute(hConsole, 2);

	// Load initial page
	
	while (running)
	{
		// clear the console screen
		system("cls");

		// Render
		if (current_page.id == 0)
			display_welcomeScreen();

		for (Element element : current_page.elements)
		{
			if (element.selected)
			{
				SetConsoleTextAttribute(hConsole, 10);
				std::cout << element.title << std::endl;
				SetConsoleTextAttribute(hConsole, 2);
			}
			else
				std::cout << element.title << std::endl;
		}

		// Pauses the execution until any key is pressed (">nul" means not to display 'Press any key to continue')
		system("pause>nul");

		// Process pressed key

		if (GetAsyncKeyState(VK_ESCAPE))
		{
			running = false;
		}

		else if (GetAsyncKeyState(VK_UP))
		{
			for (int i = 0; i < current_page.elements.size(); i++)
			{
				if (current_page.elements.at(i).selected && i > 0)
				{
					current_page.elements.at(i).selected = false;
					current_page.elements.at(i - 1).selected = true;
					break;
				}
			}
		}

		else if (GetAsyncKeyState(VK_DOWN))
		{
			for (int i = 0; i < current_page.elements.size(); i++)
			{
				if (current_page.elements.at(i).selected && i < current_page.elements.size() - 1)
				{
					current_page.elements.at(i).selected = false;
					current_page.elements.at(i + 1).selected = true;
					break;
				}
			}
		}

		else if (GetAsyncKeyState(VK_LEFT))
		{
			current_page = setPage(current_page.previous);
		}

		else if (GetAsyncKeyState(VK_RIGHT))
		{
			// Find selected element of current page and activate its function
			for (Element element : current_page.elements)
			{
				if (element.selected)
				{
					if (element.type == Link)
					{
						int prev_id = current_page.id;
						current_page = setPage(stoi(element.special_data));
						current_page.previous = prev_id;
					}

					else if (element.type == Text)
					{
						system("cls");
						std::cout << "Reading now: * " << element.title << " *" << std::endl;
						std::cout << element.special_data << std::endl;
						system("pause");
					}

					else if (element.type == Button)
					{
						// Handle running scipt output
					}
				}
			}
		}

		else if (GetAsyncKeyState(0x41)) // A key
		{
			// Go to "Create new element" page (form)
			std::string answer;
			std::cout << "Do you want to create new element on this page (y / n) ?";
			std::getline(std::cin, answer);

			if (answer == "y")
			{
				bool valid;
				
				Type elem_type;
				std::string s_type, title, special_data;
				
				std::cout << std::endl << "Type: ";
				std::getline(std::cin, s_type);
				std::cout << std::endl << "Title: ";
				std::getline(std::cin, title);
				std::cout << std::endl << "Special info: ";
				std::getline(std::cin, special_data);

				valid = title != "" && special_data != "";

				if (s_type == "link")
					elem_type = Link;
				else if (s_type == "text")
					elem_type = Text;
				else if (s_type == "button")
					elem_type = Button;
				else
					valid = false;

				if (valid)
				{
					Element new_element = {elem_type, false, title, special_data};
					createElement(current_page, new_element);
				}
				else
				{
					std::cout << "Failed to create an element" << std::endl;
					system("pause");
				}
			}
		}

		else if (GetAsyncKeyState(0x53)) // S key
		{
			// Go to "Delete selected element" page (yes / no)
			char answer;
			std::cout << "Do you want to delete selected element on this page (y / n) ?";
			std::cin >> answer;

			if (answer == 'y')
			{
				for (int i = 0; i < current_page.elements.size(); i++)
				{
					if (current_page.elements.at(i).selected)
					{
						deleteElement(&current_page, i);
						break;
					}
				}
			}
		}

		else if (GetAsyncKeyState(0x44)) // D key
		{
			// Go to "Create new page" page (yes / or)
			std::string answer;
			std::cout << "Do you want to create new page? You will need to create a link on the current page to the page you want yo create. Proceed (y / n) ?";
			std::getline(std::cin, answer);

			if (answer == "y")
			{
				bool valid;
				
				Element page_link;
				page_link.type = Link;
				page_link.selected = false;
				
				std::cout << std::endl << "Link's Title: ";
				std::getline(std::cin, page_link.title);

				valid = link.title != "";

				if (valid)
				{
					createPage(&page_link, current_page.id);
				}
				else
				{
					std::cout << "Failed to create a page" << std::endl;
					system("pause");
				}
			}
		}

		else if (GetAsyncKeyState(0x46)) // F key
		{
			// Go to "Delete current page" page (yes / no)
		}
	}

	close_terminal();
	
	return 0;
}

void close_terminal()
{
	std::cout << "Terminal has been closed." << std::endl;
}

void display_welcomeScreen()
{
	std::cout << "Welcome to the ZOS!" << std::endl;
	std::cout << std::endl << "***" << std::endl << std::endl;
	std::cout << "ZOS - Znow Operating System is an alternative to the RobCo Industries' Unified Operating System (UOS)" << std::endl;
	std::cout << std::endl << "***" << std::endl << std::endl;
	std::cout << "ZOS is officially intended to work on a hardware supplied by RobCo Industries, namely:" << std::endl;
	std::cout << "RobCo NX-12 terminal, RobCo E-330 terminal, RobCo E-601 terminal, RobCo RX-6550 terminal, RobCo RX-9000 terminal" << std::endl;
	std::cout << std::endl << "***" << std::endl << std::endl;
	std::cout << "Press RIGHT arrow in order to proceed to the startup menu." << std::endl;
}

Page setPage(int id)
{
	for (Page page : pages)
	{
		if (page.id == id)
		{
			return page;
		}
	}
	
	return getPage(id);
}