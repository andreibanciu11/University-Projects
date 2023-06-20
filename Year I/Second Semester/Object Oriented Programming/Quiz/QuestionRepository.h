#pragma once
#include "Question.h"
#include "Observer.h"
#include <sstream>
#include <fstream>
#include <algorithm>

using namespace std;

class QuestionRepository: public Subject {
private:
	vector<Question> questions;
	void loadData();
	void saveData();
	void sortByID();
	void sortByScore();
public:
	QuestionRepository();

	vector<Question> getQuestions();

	void addQuestion(Question q);
};