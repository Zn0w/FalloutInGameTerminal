#ifndef DYNAMIC_ARRAY_H
#define DYNAMIC_ARRAY_H

// Add the element to the array
void add_element(void* array, void* element);

// Removes from array elements, which equals to the specified element
void remove_element(void* array, void* element);

// Returns a number of elements present in the array
int size(void* array);

// Removes all elements of the array
void clear(void* array);

#endif