#pragma once

#include <fstream>
#include <string>
#include <iostream>
#include <map>

#include "element.h"

void get_elements(int page, int current_page, std::map<int, Element[10]>* elements);