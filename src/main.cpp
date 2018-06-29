#include <iostream>
#include <map>

#include <windows.h>

#include "element.h"
#include "data_access.h"

int current_page;

bool running = true;

void close_terminal();

void display_welcomeScreen();

HANDLE hConsole; // For changing console text color

int main()
{
	current_page = 0;

	hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
	// Set console color to the light green (default)
	SetConsoleTextAttribute(hConsole, 2);

	// Load initial page
	
	while (running)
	{
		// clear the console screen
		system("cls");

		// Render

		// Pauses the execution until any key is pressed (">nul" means not to display 'Press any key to continue')
		system("pause>nul");

		// Process pressed key

		if (GetAsyncKeyState(VK_ESCAPE))
		{
			running = false;
		}

		else if (GetAsyncKeyState(VK_UP))
		{
			
		}

		else if (GetAsyncKeyState(VK_DOWN))
		{
			
		}

		else if (GetAsyncKeyState(VK_LEFT))
		{
			
		}

		else if (GetAsyncKeyState(VK_RIGHT))
		{
			
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