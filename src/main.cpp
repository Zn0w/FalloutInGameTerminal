#include <iostream>

#include <windows.h>

int current_page = 0;

// current_page = 0 is terminal's welcome screen
// current_page = 1 is terminal's initial screen
// current_page > 1 is the rest

bool running = true;

void close_terminal();

void display_welcomeScreen();

int main()
{
	while (running)
	{
		// clear the console screen
		system("cls");

		if (current_page == 0)
		{
			display_welcomeScreen();
		}

		// Pauses the execution until any key is pressed (">nul" means not to display 'Press any key to continue')
		system("pause>nul");

		// Process pressed key

		if (GetAsyncKeyState(VK_ESCAPE))
		{
			running = false;
		}
		else if (GetAsyncKeyState(VK_RETURN))
		{
			//if (current_page == )
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
	std::cout << "Press enter in order to proceed to the startup menu." << std::endl;
}