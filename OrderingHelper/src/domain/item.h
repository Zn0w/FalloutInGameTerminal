#ifndef ITEM_H
#define ITEM_H

struct Item
{
    int id;
    const char* name;
    double price;
};

typedef struct Item Item;

#endif