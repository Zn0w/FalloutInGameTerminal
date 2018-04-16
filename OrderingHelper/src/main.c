#pragma warning(disable : 4996) // Disable annoying visual studio warnings

#include <stdio.h>
#include <stdlib.h>

#include "domain/item.h"

void show_neworder_menu();
void show_adminmode_menu();

int option;

//struct Item* items;
//items = malloc(struct[] Item);

int main()
{
    printf("Welcome to the Ordering Helper! Press enter to continue.");
    getchar();

    while (1)
    {
        printf("____________________\n____________________\n\n");

        printf("1 - New order\n");
        printf("2 - Go to administrator mode\n");
        printf("3 - Quit\n\n");

        printf("Your input: ");
        scanf("%d", &option);

        switch (option)
        {
            case 1 :
                show_neworder_menu();
                break;

            case 2 :
                show_adminmode_menu();
                break;

            case 3 :
                return 0;
                break;

            default :
                printf("This input is invalid.\n");
        }
    }

    return 0;
}

void show_neworder_menu()
{
    int order_completed = 0;

    while (!order_completed)
    {
        printf("____________________\n____________________\n\n");

        printf("1 - Add new item to order\n");
        printf("2 - Remove item from order\n");
        printf("3 - Confirm order\n");
        printf("4 - Cancel order\n\n");

        printf("Your input: ");
        scanf("%d", &option);

        switch (option)
        {
            case 1 :
                // Add new item
                break;

            case 2 :
                // Remove item
                break;

            case 3 :
                order_completed = 1;
                break;

            case 4 :
                order_completed = 1;
                break;

            default :
                printf("This input is invalid.\n");
        }
    }
}

void show_adminmode_menu()
{
    int admin_mode = 1;

    while (admin_mode)
    {
        printf("____________________\n____________________\n\n");

        printf("1 - Manage storage\n");
        printf("2 - Manage employees\n");
        printf("3 - Quit administrator mode\n");
        printf("4 - Quit\n\n");

        printf("Your input: ");
        scanf("%d", &option);

        switch (option)
        {
            case 1 :
                // Manage storage
                break;

            case 2 :
                // Manage employees
                break;

            case 3 :
                admin_mode = 0;
                break;

            case 4 :
                admin_mode = 0;
                break;

            default :
                printf("This input is invalid.\n");
        }
    }
}