#pragma once

#include <iostream>
#include <fstream>

#include "../domain/Order.h"

vector<Order> getActiveOrders();

vector<Order> getCompletedOrders();

void saveOrder(Order order);