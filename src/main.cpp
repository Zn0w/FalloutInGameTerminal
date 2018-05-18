#include <iostream>

#include <windows.h>

int current_page = 0;

int main()
{
	/*printf("Welcome to the ZOS!\n");
	printf("***\n");
	printf("ZOS - Znow Operating System is an alternative to the RobCo Industries' Unified Operating System (UOS)\n");
	printf("***\n");
	printf("ZOS is officially intended to work on a hardware supplied by RobCo Industries, namely:\n");
	printf("RobCo NX-12 terminal, RobCo E-330 terminal, RobCo E-601 terminal, RobCo RX-6550 terminal, RobCo RX-9000 terminal\n");
	printf("***\n");

	printf("Press enter in order to proceed to the startup menu.");

	getchar();*/

	while (true)
	{
		// clear the console screen
		system("cls");

		std::cout << "Test" << std::endl;

		// Pauses the execution until any key is pressed (">nul" means not to display 'Press any key to continue')
		system("pause>nul");
	}
	
	return 0;
}