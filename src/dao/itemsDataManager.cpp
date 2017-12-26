#include "itemsDataManager.h"

vector<string> split(string str, char itr);
vector<string> getFileContents();

Item* getItems() {
    vector<string> itemlines = getFileContents();
    Item items[itemlines.size()];
    vector<string> result = split("xxfdfhellofworld!flove", 'f');
    for (string str : result)
        cout << str << endl;

    //for (string itemline : itemlines) {
        //string* components = split(itemline, ';');
    //}

    return items;
}

vector<string> getFileContents() {
	ifstream fileReader("resources/itemsData.txt");
	vector<string> itemlines;

	if (fileReader.is_open()) {
		string line;
		while (getline(fileReader, line)) {
			itemlines.push_back(line);

			cout << line << endl;
		}
	}
	else
		cout << "Failed to open an items data file";

	fileReader.close();
	return itemlines;
}

vector<string> split(string str, char itr) {
    vector<string> str_divided;
    string currentStr = "";

    for (int i = 0; i < str.size(); i++) {
        if (str.at(i) == itr) {
            str_divided.push_back(currentStr);
            currentStr = "";
        }
        else
            currentStr += str.at(i);
    }

    str_divided.push_back(currentStr);

    return str_divided;
}
