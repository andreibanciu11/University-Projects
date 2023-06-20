#pragma once
#include "Participant.h"
#include <sstream>
#include <fstream>
#include <algorithm>

using namespace std;

class ParticipantRepository{
private:
	vector<Participant> participants;
	void loadData();
public:
	ParticipantRepository();

	vector<Participant> getParticipants();
};