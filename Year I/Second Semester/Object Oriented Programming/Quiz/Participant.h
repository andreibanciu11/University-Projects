#pragma once
#include <vector>
#include <string>
#include <sstream>

using namespace std;

class Participant {
private:
	string name;
	int score;
public:
	Participant(string name = "", int score = 0);

	string getName();
	int getScore();

	void setScore(int question_score);

	friend ostream& operator<<(ostream& out, Participant& p);
	friend istream& operator>>(istream& in, Participant& p);
};