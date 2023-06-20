/********************************************************************************
** Form generated from reading UI file 'ParticipantWindow.ui'
**
** Created by: Qt User Interface Compiler version 6.5.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_PARTICIPANTWINDOW_H
#define UI_PARTICIPANTWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_ParticipantWindowClass
{
public:
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QWidget *centralWidget;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *ParticipantWindowClass)
    {
        if (ParticipantWindowClass->objectName().isEmpty())
            ParticipantWindowClass->setObjectName("ParticipantWindowClass");
        ParticipantWindowClass->resize(600, 400);
        menuBar = new QMenuBar(ParticipantWindowClass);
        menuBar->setObjectName("menuBar");
        ParticipantWindowClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(ParticipantWindowClass);
        mainToolBar->setObjectName("mainToolBar");
        ParticipantWindowClass->addToolBar(mainToolBar);
        centralWidget = new QWidget(ParticipantWindowClass);
        centralWidget->setObjectName("centralWidget");
        ParticipantWindowClass->setCentralWidget(centralWidget);
        statusBar = new QStatusBar(ParticipantWindowClass);
        statusBar->setObjectName("statusBar");
        ParticipantWindowClass->setStatusBar(statusBar);

        retranslateUi(ParticipantWindowClass);

        QMetaObject::connectSlotsByName(ParticipantWindowClass);
    } // setupUi

    void retranslateUi(QMainWindow *ParticipantWindowClass)
    {
        ParticipantWindowClass->setWindowTitle(QCoreApplication::translate("ParticipantWindowClass", "ParticipantWindow", nullptr));
    } // retranslateUi

};

namespace Ui {
    class ParticipantWindowClass: public Ui_ParticipantWindowClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_PARTICIPANTWINDOW_H
