#include "Participant.h"
#include "Utils.h"

Participant::Participant(string name, int score)
{
	this->name = name;
	this->score = 0;
}

string Participant::getName()
{
	return this->name;
}

int Participant::getScore()
{
	return this->score;
}

void Participant::setScore(int question_score)
{
	this->score += question_score;
}

ostream& operator<<(ostream& out, Participant& p)
{
	out << p.name << ";" << p.score << "\n";
	return out;
}

istream& operator>>(istream& in, Participant& p)
{
	string line;
	getline(in, line);
	vector<string> tokens = tokenize(line, ';');
	if (tokens.size() != 2)
	{
		return in;
	}

	p.name = tokens[0];
	p.score = stoi(tokens[1]);

	return in;
}
