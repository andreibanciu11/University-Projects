/********************************************************************************
** Form generated from reading UI file 'PresenterWindow.ui'
**
** Created by: Qt User Interface Compiler version 6.5.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_PRESENTERWINDOW_H
#define UI_PRESENTERWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_PresenterWindowClass
{
public:
    QWidget *centralWidget;
    QListWidget *questionListWidget;
    QLabel *label;
    QLabel *label_2;
    QLabel *label_3;
    QLabel *label_4;
    QLineEdit *idLineEdit;
    QLineEdit *textLineEdit;
    QLineEdit *answerLineEdit;
    QLineEdit *scoreLineEdit;
    QPushButton *addButton;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *PresenterWindowClass)
    {
        if (PresenterWindowClass->objectName().isEmpty())
            PresenterWindowClass->setObjectName("PresenterWindowClass");
        PresenterWindowClass->resize(896, 597);
        centralWidget = new QWidget(PresenterWindowClass);
        centralWidget->setObjectName("centralWidget");
        questionListWidget = new QListWidget(centralWidget);
        questionListWidget->setObjectName("questionListWidget");
        questionListWidget->setGeometry(QRect(10, 10, 521, 391));
        label = new QLabel(centralWidget);
        label->setObjectName("label");
        label->setGeometry(QRect(590, 50, 49, 16));
        label_2 = new QLabel(centralWidget);
        label_2->setObjectName("label_2");
        label_2->setGeometry(QRect(590, 90, 49, 16));
        label_3 = new QLabel(centralWidget);
        label_3->setObjectName("label_3");
        label_3->setGeometry(QRect(590, 130, 49, 16));
        label_4 = new QLabel(centralWidget);
        label_4->setObjectName("label_4");
        label_4->setGeometry(QRect(590, 170, 49, 16));
        idLineEdit = new QLineEdit(centralWidget);
        idLineEdit->setObjectName("idLineEdit");
        idLineEdit->setGeometry(QRect(650, 50, 191, 21));
        textLineEdit = new QLineEdit(centralWidget);
        textLineEdit->setObjectName("textLineEdit");
        textLineEdit->setGeometry(QRect(650, 90, 191, 21));
        answerLineEdit = new QLineEdit(centralWidget);
        answerLineEdit->setObjectName("answerLineEdit");
        answerLineEdit->setGeometry(QRect(650, 130, 191, 21));
        scoreLineEdit = new QLineEdit(centralWidget);
        scoreLineEdit->setObjectName("scoreLineEdit");
        scoreLineEdit->setGeometry(QRect(650, 170, 191, 21));
        addButton = new QPushButton(centralWidget);
        addButton->setObjectName("addButton");
        addButton->setGeometry(QRect(580, 220, 91, 24));
        PresenterWindowClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(PresenterWindowClass);
        menuBar->setObjectName("menuBar");
        menuBar->setGeometry(QRect(0, 0, 896, 22));
        PresenterWindowClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(PresenterWindowClass);
        mainToolBar->setObjectName("mainToolBar");
        PresenterWindowClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(PresenterWindowClass);
        statusBar->setObjectName("statusBar");
        PresenterWindowClass->setStatusBar(statusBar);

        retranslateUi(PresenterWindowClass);

        QMetaObject::connectSlotsByName(PresenterWindowClass);
    } // setupUi

    void retranslateUi(QMainWindow *PresenterWindowClass)
    {
        PresenterWindowClass->setWindowTitle(QCoreApplication::translate("PresenterWindowClass", "PresenterWindow", nullptr));
        label->setText(QCoreApplication::translate("PresenterWindowClass", "ID", nullptr));
        label_2->setText(QCoreApplication::translate("PresenterWindowClass", "Text", nullptr));
        label_3->setText(QCoreApplication::translate("PresenterWindowClass", "Answer", nullptr));
        label_4->setText(QCoreApplication::translate("PresenterWindowClass", "Score", nullptr));
        addButton->setText(QCoreApplication::translate("PresenterWindowClass", "Add question", nullptr));
    } // retranslateUi

};

namespace Ui {
    class PresenterWindowClass: public Ui_PresenterWindowClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_PRESENTERWINDOW_H
