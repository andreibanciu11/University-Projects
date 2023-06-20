#pragma once
#include "ParticipantRepository.h"
#include "QuestionRepository.h"

class Service {
private:
	QuestionRepository& repoQ;
	ParticipantRepository& repoP;
public:
	Service(QuestionRepository& repoQ, ParticipantRepository& repoP);

	vector<Question> getQuestions();
	vector<Participant> getParticipants();
	void addObserver(Observer* o);
	void addQuestion(Question q);
};