#pragma once
#include <qwidget.h>
#include "Observer.h"
#include "Service.h"
#include <qlistwidget.h>
#include <qpushbutton.h>
#include <qlayout.h>
#include <qformlayout.h>
#include <qlineedit.h>
#include <qlabel.h>
#include <qmessagebox.h>

class ProgrammerWindow : public QWidget, public Observer {
private:
	Service serv;
	Programmer p;
public:
	ProgrammerWindow(Service serv, Programmer& p, QWidget* parent);
private:
	QListWidget* tasksListWidget;
	QPushButton* addButton;
	QPushButton* deleteButton;
	QPushButton* startButton;
	QPushButton* doneButton;
	QFormLayout* inputFormLayout;
	QLineEdit* descriptionLineEdit;
	QLabel* descriptionLabel;
	void initGUI();
	void connectSignals();

private slots:
	void handleAdd();
	void handleDelete();
	void handleStart();
	void handleDone();
	void handleDoneButton();
public:
	void update() override;
private:
	void updateTaskList();
};