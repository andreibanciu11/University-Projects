#include "ParticipantRepository.h"

void ParticipantRepository::loadData()
{
	ifstream file;
	file.open("participants.txt");
	if (file.is_open())
	{
		Participant p;
		while (file >> p)
		{
			this->participants.push_back(p);
		}
		file.close();
	}
}

ParticipantRepository::ParticipantRepository()
{
	this->loadData();
}

vector<Participant> ParticipantRepository::getParticipants()
{
	return this->participants;
}
