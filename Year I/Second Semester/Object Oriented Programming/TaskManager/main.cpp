#include "TaskManager.h"
#include <QtWidgets/QApplication>
#include "ProgrammerRepository.h"
#include "TaskRepository.h"
#include "Service.h"
#include "ProgrammerWindow.h"
#include <vector>

int main(int argc, char* argv[])
{
    QApplication a(argc, argv);
    RepoProgrammers repoP;
    RepoTasks repoT;
    Service serv(repoT, repoP);
    vector<ProgrammerWindow*> windows;
    for (Programmer p : repoP.getProgrammers())
    {
        auto window = new ProgrammerWindow(serv, p, nullptr);
        windows.push_back(window);
        window->show();

    }
    return a.exec();
}