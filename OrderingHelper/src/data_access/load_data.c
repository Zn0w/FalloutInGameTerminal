#include "load_data.h"

Item* get_items()
{
	static Item* items; // Static in order for function to actually return an array ic C

	return items;
}