#include "fileManager.h"

void getFileContents() {
	ifstream fileReader("resources/itemsData.txt");
	string line;

	if (fileReader.is_open()) {
		while (getline(fileReader, line)) {
			cout << line << endl;
		}
	}
	else
		cout << "Failed to open an items data file";

	fileReader.close();
}
