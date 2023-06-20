#include "Question.h"
#include "Utils.h"

Question::Question(int id, string text, string answer, int score)
{
	this->id = id;
	this->text = text;
	this->answer = answer;
	this->score = score;
}

int Question::getID()
{
	return this->id;
}

string Question::getText()
{
	return this->text;
}

string Question::getAnswer()
{
	return this->answer;
}

int Question::getScore()
{
	return this->score;
}

string Question::toString()
{
	stringstream out;
	out << this->id << ";" << this->text << ";" << this->answer << ";" << this->score << '\n';
	return out.str();
}

ostream& operator<<(ostream& out, Question& q)
{
	out << q.toString();
	return out;
}

istream& operator>>(istream& in, Question& q)
{
	string line;
	getline(in, line);
	vector<string> tokens = tokenize(line, ';');
	if (tokens.size() != 4)
	{
		return in;
	}

	q.id = stoi(tokens[0]);
	q.text = tokens[1];
	q.answer = tokens[2];
	q.score = stoi(tokens[3]);

	return in;
}
