#include "Service.h"


Service::Service(RepoTasks& repoT, RepoProgrammers& repoP)
	: repoT(repoT), repoP(repoP)
{
}

vector<Task> Service::getTasks()
{
	return this->repoT.getTasks();
}

vector<Programmer> Service::getProgrammers()
{
	return this->repoP.getProgrammers();
}

void Service::addObserver(Observer* o)
{
	this->repoT.addObserver(o);
}

void Service::removeTask(Task t)
{
	this->repoT.removeTask(t);
}

void Service::addTask(Task t)
{
	this->repoT.addTask(t);
}

void Service::updateTaskStatus(Task t, string newStatus, int newId)
{
	this->repoT.updateTaskStatus(t, newStatus, newId);
}

Programmer Service::getProgrammerById(int id)
{
	return this->repoP.getProgrammerById(id);
}