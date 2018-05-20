#include <iostream>

#include <windows.h>

#include "element.h"

const char* current_page;

// current_page = w is terminal's welcome screen
// current_page = 0 is terminal's initial screen
// current_page starts with u is user mode
// current_page starts with e is edit mode
// current_page = g is guide page

bool running = true;

// Initial screen elements
Element init_scr_elems[3];

void close_terminal();

void display_welcomeScreen();
void display_initialScreen();
void display_guideScreen();

HANDLE hConsole; // For changing console text color

int main()
{
	current_page = "w";

	init_scr_elems[0].type = Menu;
	init_scr_elems[0].selected = true;
	init_scr_elems[0].content = "Go user mode";

	init_scr_elems[1].type = Menu;
	init_scr_elems[1].selected = false;
	init_scr_elems[1].content = "Go edit mode";

	init_scr_elems[2].type = Text;
	init_scr_elems[2].selected = false;
	init_scr_elems[2].content = "See guide";

	hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
	// Set console color to the light green (default)
	SetConsoleTextAttribute(hConsole, 2);
	
	while (running)
	{
		// clear the console screen
		system("cls");

		if (current_page == "w")
		{
			display_welcomeScreen();
		}
		else if (current_page == "0")
		{
			display_initialScreen();
		}
		else if (current_page == "g")
		{
			display_guideScreen();
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
			if (current_page == "0")
			{
				for (int i = 0; i < sizeof(init_scr_elems) / sizeof(Element); i++)
				{
					if (init_scr_elems[i].selected && i > 0)
					{
						init_scr_elems[i].selected = false;
						init_scr_elems[i - 1].selected = true;
						break;
					}
				}
			}
		}

		else if (GetAsyncKeyState(VK_DOWN))
		{
			if (current_page == "0")
			{
				for (int i = 0; i < sizeof(init_scr_elems) / sizeof(Element); i++)
				{
					if (init_scr_elems[i].selected && i < sizeof(init_scr_elems) / sizeof(Element) - 1)
					{
						init_scr_elems[i].selected = false;
						init_scr_elems[i + 1].selected = true;
						break;
					}
				}
			}
		}

		else if (GetAsyncKeyState(VK_LEFT))
		{
			//if (current_page > 1)
			//{
				// Get the elements of this page and then go back
			//}
		}

		else if (GetAsyncKeyState(VK_RIGHT))
		{
			if (current_page == "w")
				current_page = "0";
			else if (current_page == "0")
			{
				if (init_scr_elems[0].selected)
					current_page = "u";
				else if (init_scr_elems[1].selected)
					current_page = "e";
				else if (init_scr_elems[2].selected)
					current_page = "g";
			}
			else if (current_page == "g")
				current_page = "0";
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
	std::cout << "***" << std::endl;
	std::cout << "ZOS - Znow Operating System is an alternative to the RobCo Industries' Unified Operating System (UOS)" << std::endl;
	std::cout << "***" << std::endl;
	std::cout << "ZOS is officially intended to work on a hardware supplied by RobCo Industries, namely:" << std::endl;
	std::cout << "RobCo NX-12 terminal, RobCo E-330 terminal, RobCo E-601 terminal, RobCo RX-6550 terminal, RobCo RX-9000 terminal" << std::endl;
	std::cout << "***" << std::endl;
	std::cout << "Press RIGHT arrow in order to proceed to the startup menu." << std::endl;
}

void display_initialScreen()
{
	for (int i = 0; i < sizeof(init_scr_elems) / sizeof(Element); i++)
	{
		if (init_scr_elems[i].selected)
		{
			SetConsoleTextAttribute(hConsole, 3);
			std::cout << init_scr_elems[i].content << std::endl;
			SetConsoleTextAttribute(hConsole, 2);
		}
		else
			std::cout << init_scr_elems[i].content << std::endl;
	}
}

void display_guideScreen()
{
	std::cout << "This is a ZOS usage guide." << std::endl;
	std::cout << "***" << std::endl;
	std::cout << "Navigation:" << std::endl;
	std::cout << "UP and DOWN arrows - go through the elements" << std::endl;
	std::cout << "RIGHT arrow - select the element, verify" << std::endl;
	std::cout << "LEFT arrow - go back, discard" << std::endl;
	std::cout << "***" << std::endl;
	std::cout << "Special commands:" << std::endl;
	std::cout << "ESCAPE - shut down the terminal" << std::endl;
	std::cout << "***" << std::endl;
	std::cout << "Press RIGHT arrow in order to proceed to the startup menu." << std::endl;
}