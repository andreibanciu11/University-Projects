#pragma once

#include <QMainWindow>
#include "ui_PresenterWindow.h"
#include "Service.h"
#include "qmessagebox.h"

class PresenterWindow : public QMainWindow, public Observer
{
	Q_OBJECT

private:
	Ui::PresenterWindowClass ui;
	Service serv;

public:
	PresenterWindow(Service serv, QWidget *parent = nullptr);

	void update() override;

private:
	void connectSignalsAndSlots();

private slots:
	void handleAdd();

};
