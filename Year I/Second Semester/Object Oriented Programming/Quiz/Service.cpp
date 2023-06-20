#include "Service.h"

Service::Service(QuestionRepository& repoQ, ParticipantRepository& repoP)
	: repoQ(repoQ), repoP(repoP)
{
}

vector<Question> Service::getQuestions()
{
	return this->repoQ.getQuestions();
}

vector<Participant> Service::getParticipants()
{
	return this->repoP.getParticipants();
}

void Service::addObserver(Observer* o)
{
	this->repoQ.addObserver(o);
}

void Service::addQuestion(Question q)
{
	this->repoQ.addQuestion(q);
}
