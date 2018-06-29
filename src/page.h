#pragma once

#include <vector>

#include "element.h"

struct Page
{
	int id;
	int previous;
	std::vector<Element> elements;
};