#pragma once
#include "TaskRepository.h"
#include "ProgrammerRepository.h"

class Service {
private:
	RepoTasks& repoT;
	RepoProgrammers& repoP;
public:
	Service(RepoTasks& repoT, RepoProgrammers& repoP);

	vector<Task> getTasks();
	vector<Programmer> getProgrammers();
	void addObserver(Observer* o);
	void removeTask(Task t);
	void addTask(Task t);
	void updateTaskStatus(Task t, string newStatus, int newId);
	Programmer getProgrammerById(int id);
};