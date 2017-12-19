#include "itemsDataManager.h"

string* split(string str, char itr);
vector<string> getFileContents();

Item* getItems() {
    vector<string> itemlines = getFileContents();
    Item items[itemlines.size()];

    for (string itemline : itemlines) {
        string* components = split(itemline, ';');
    }

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

string* split(string str, char itr) {
    string std_divided[str.size()];

    for (int i; i < str.size(); i++) {

    }
}
