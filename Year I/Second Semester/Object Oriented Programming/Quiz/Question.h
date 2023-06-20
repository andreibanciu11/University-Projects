#pragma once
#include <string>
#include <sstream>
#include <vector>

using namespace std;

class Question {
private:
	int id;
	string text;
	string answer;
	int score;
public:
	Question(int id = 0, string text = "", string answer = "", int score = 0);
	
	int getID();
	string getText();
	string getAnswer();
	int getScore();

	string toString();

	friend ostream& operator<<(ostream& out, Question& q);
	friend istream& operator>>(istream& in, Question& q);
};