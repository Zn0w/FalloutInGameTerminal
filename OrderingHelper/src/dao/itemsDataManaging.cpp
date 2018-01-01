#include "itemsDataManaging.h"

vector<string> split(string str, char itr);
vector<string> getFileContents();

vector<Item> getItems()
{
    vector<string> itemlines = getFileContents();
    vector<Item> items;

    vector<string> item_properties;
    for (string item_info : itemlines)
    {
        item_properties = split(item_info, ';');
        Item item(
                  stoi(item_properties.at(0)),
                  item_properties.at(1),
                  stoi(item_properties.at(2)),
                  item_properties.at(3)
                  );
        items.push_back(item);
    }

    return items;
}

vector<string> getFileContents()
{
	ifstream fileReader("resources/itemsData.txt");
	vector<string> itemlines;

	if (fileReader.is_open())
        {
		string line;
		while (getline(fileReader, line))
		{
			itemlines.push_back(line);

			cout << line << endl;
		}
	}
	else
		cout << "Failed to open an items data file";

	fileReader.close();
	return itemlines;
}

vector<string> split(string str, char itr)
{
    vector<string> str_divided;
    string currentStr = "";

    for (int i = 0; i < str.size(); i++)
    {
        if (str.at(i) == itr) 
		{
            str_divided.push_back(currentStr);
            currentStr = "";
        }
        else
            currentStr += str.at(i);
    }

    str_divided.push_back(currentStr);

    return str_divided;
}
