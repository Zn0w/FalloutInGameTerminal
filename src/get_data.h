#pragma once

#include <fstream>
#include <string>
#include <stdlib.h>

#include <iostream> // For debugging purposes std::cout << "" << std::endl;

#include "page.h"

//static bool pageExists(int id);

Page getPage(int id);