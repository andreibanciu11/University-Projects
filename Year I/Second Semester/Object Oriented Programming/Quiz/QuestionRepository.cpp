#include "QuestionRepository.h"

void QuestionRepository::loadData()
{
	ifstream file;
	file.open("questions.txt");
	if (file.is_open())
	{
		Question q;
		while (file >> q)
		{
			this->questions.push_back(q);
		}
		file.close();
	}
}

void QuestionRepository::saveData()
{
	ofstream file;
	file.open("questions.txt");
	if (file.is_open())
	{
		for (Question q : this->questions)
		{
			file << q;
		}
		file.close();
	}
}

void QuestionRepository::sortByID()
{
	sort(this->questions.begin(), this->questions.end(), [](Question q1, Question q2) {
		return q1.getID() < q2.getID();
		});
}

void QuestionRepository::sortByScore()
{
	sort(this->questions.begin(), this->questions.end(), [](Question q1, Question q2) {
		return q1.getScore() > q2.getScore();
		});
}

QuestionRepository::QuestionRepository()
{
	this->loadData();
}

vector<Question> QuestionRepository::getQuestions()
{
	return this->questions;
}

void QuestionRepository::addQuestion(Question q)
{
	this->questions.push_back(q);
	this->notify();
	this->saveData();
}
