#include "Quiz.h"
#include <QtWidgets/QApplication>
#include "ParticipantRepository.h"
#include "QuestionRepository.h"
#include "ParticipantWindow.h"
#include "PresenterWindow.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    Quiz w;
    w.show();
    QuestionRepository repoQ;
    ParticipantRepository repoP;
    Service serv(repoQ, repoP);
    vector<ParticipantWindow*> windows;
    //for (auto p : repoP.getParticipants())
    //{
        //windows.push_back(new ParticipantWindow(repoQ, repoP, p));
        // windows.push_back(window);
        // window->show();
    //}
    ParticipantWindow participantWindow(nullptr);
    participantWindow.show();

    PresenterWindow presenterWindow(serv, nullptr);
    presenterWindow.show();
    return a.exec();
}
