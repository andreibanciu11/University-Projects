#include "PresenterWindow.h"

using namespace std;

PresenterWindow::PresenterWindow(Service serv, QWidget *parent)
	: QMainWindow(parent), serv(serv)
{
	ui.setupUi(this);
	this->setWindowTitle(QString::fromStdString("Presenter"));
	this->connectSignalsAndSlots();
	this->update();
}

void PresenterWindow::update()
{
	this->ui.questionListWidget->clear();
	for (auto q : this->serv.getQuestions())
	{
		string s = q.toString();
		auto item = new QListWidgetItem(s.c_str());
		this->ui.questionListWidget->addItem(item);
	}
}

void PresenterWindow::connectSignalsAndSlots()
{
	QObject::connect(ui.addButton, &QPushButton::clicked, this, &PresenterWindow::handleAdd);
}

void PresenterWindow::handleAdd()
{
	int id = stoi(ui.idLineEdit->text().toStdString());
	string text = ui.textLineEdit->text().toStdString();
	string answer = ui.answerLineEdit->text().toStdString();
	int score = stoi(ui.scoreLineEdit->text().toStdString());
	//if (text != "" && answer != "")
	//

	Question q(id, text, answer, score);
		//check id already exists
		//if id doesnt already exist then ->>>>
	this->serv.addQuestion(q);
	this->update();
	QMessageBox::information(this, "Success", "Success", QMessageBox::Ok);
		//else display error message
	//}
	//else
		//QMessageBox::information(this, "Error", "Error text size", QMessageBox::Ok);
}
