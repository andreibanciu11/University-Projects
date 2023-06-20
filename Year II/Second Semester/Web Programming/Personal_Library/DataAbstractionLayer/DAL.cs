using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.EnterpriseServices.CompensatingResourceManager;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using MySql.Data.MySqlClient;
using Personal_Library.Models;

namespace Personal_Library.DataAbstractionLayer
{
    public class DAL
    {
        public MySqlConnection getConnection()
        {
            string myConnectionString;
            myConnectionString = "server=localhost;uid=root;pwd=A10N10D10;database=personal_library;";
            return new MySqlConnection(myConnectionString);

        }

        public List<User> GetAllUsers()
        {
            var conn = getConnection();
            conn.Open();
            var sql = "SELECT * FROM users";

            List<User> users = new List<User>();
            var cmd = new MySqlCommand(sql, conn);

            MySqlDataReader dataReader = cmd.ExecuteReader();
            while (dataReader.Read())
            {
                users.Add(new User(dataReader.GetString(1), dataReader.GetString(2)));
            }

            return users;
        }

        public void AddBook(Book book, string username)
        {
            using (MySqlConnection connection = getConnection())
            {
                int userID;
                string queryUserID = "SELECT id FROM users WHERE username = @Username";
                MySqlCommand commandUserID = new MySqlCommand(queryUserID, connection);
                commandUserID.Parameters.AddWithValue("@Username", username);
                connection.Open();
                object resultUserID = commandUserID.ExecuteScalar();
                if (resultUserID != null && int.TryParse(resultUserID.ToString(), out userID))
                {
                    string queryInsert = "INSERT INTO books (author, title, number_of_pages, genre, userID) VALUES (@Author, @Title, @NumberOfPages, @Genre, @UserID)";
                    MySqlCommand commandInsert = new MySqlCommand(queryInsert, connection);
                    commandInsert.Parameters.AddWithValue("@Author", book.Author);
                    commandInsert.Parameters.AddWithValue("@Title", book.Title);
                    commandInsert.Parameters.AddWithValue("@NumberOfPages", book.NumberOfPages);
                    commandInsert.Parameters.AddWithValue("@Genre", book.Genre);
                    commandInsert.Parameters.AddWithValue("@UserID", userID);
                    commandInsert.ExecuteNonQuery();
                }
            }
        }

        public void RemoveBook(string author, string title, string username)
        {
            using (MySqlConnection connection = getConnection())
            {
                int userID;
                string queryUserID = "SELECT id FROM users WHERE username = @Username";
                MySqlCommand commandUserID = new MySqlCommand(queryUserID, connection);
                commandUserID.Parameters.AddWithValue("@Username", username);
                connection.Open();
                object resultUserID = commandUserID.ExecuteScalar();

                if (resultUserID != null && int.TryParse(resultUserID.ToString(), out userID))
                {
                    string query = "DELETE FROM books WHERE author = @Author AND title = @Title AND userID = @UserID";
                    MySqlCommand command = new MySqlCommand(query, connection);
                    command.Parameters.AddWithValue("@Author", author);
                    command.Parameters.AddWithValue("@Title", title);
                    command.Parameters.AddWithValue("@UserID", userID);
                    command.ExecuteNonQuery();

                    string resetQuery1 = "SELECT MAX(id) FROM books";
                    MySqlCommand commandReset1 = new MySqlCommand(resetQuery1, connection);
                    object resultReset1 = commandReset1.ExecuteScalar();
                    int maxi;
                    int.TryParse(resultReset1.ToString(), out maxi);

                    string resetQuery2 = $"ALTER TABLE books AUTO_INCREMENT = {maxi + 1}";
                    MySqlCommand commandReset2 = new MySqlCommand(resetQuery2, connection);
                    commandReset2.ExecuteNonQuery();
                }
            }
        }

        public void UpdateBook(string author, string title, int number_of_pages, string genre, string username)
        {
            using (MySqlConnection connection = getConnection())
            {
                int userID;
                string queryUserID = "SELECT id FROM users WHERE username = @Username";
                MySqlCommand commandUserID = new MySqlCommand(queryUserID, connection);
                commandUserID.Parameters.AddWithValue("@Username", username);
                connection.Open();
                object resultUserID = commandUserID.ExecuteScalar();
                if (resultUserID != null && int.TryParse(resultUserID.ToString(), out userID))
                {
                    string queryInsert = "UPDATE books SET number_of_pages = @NumberOfPages, genre = @Genre WHERE author = @Author AND title = @Title AND userID = @UserID";
                    MySqlCommand commandInsert = new MySqlCommand(queryInsert, connection);
                    commandInsert.Parameters.AddWithValue("@Author", author);
                    commandInsert.Parameters.AddWithValue("@Title", title);
                    commandInsert.Parameters.AddWithValue("@NumberOfPages", number_of_pages);
                    commandInsert.Parameters.AddWithValue("@Genre", genre);
                    commandInsert.Parameters.AddWithValue("@UserID", userID);
                    commandInsert.ExecuteNonQuery();
                }
            }
        }

        public List<Book> SearchBook(string genre, string username)
        {
            List<Book> books = new List<Book>();

            using (MySqlConnection connection = getConnection())
            {
                int userID;
                string queryUserID = "SELECT id FROM users WHERE username = @Username";
                MySqlCommand commandUserID = new MySqlCommand(queryUserID, connection);
                commandUserID.Parameters.AddWithValue("@Username", username);
                connection.Open();
                object resultUserID = commandUserID.ExecuteScalar();
                if (resultUserID != null && int.TryParse(resultUserID.ToString(), out userID))
                {
                    string querySearch = "SELECT * FROM books WHERE genre = @Genre AND userID = @UserID";
                    MySqlCommand commandSearch = new MySqlCommand(querySearch, connection);
                    commandSearch.Parameters.AddWithValue("@Genre", genre);
                    commandSearch.Parameters.AddWithValue("@UserID", userID);

                    using (MySqlDataReader reader = commandSearch.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            Book book = new Book
                            {
                                Author = reader["author"].ToString(),
                                Title = reader["title"].ToString(),
                                NumberOfPages = int.Parse(reader["number_of_pages"].ToString()),
                                Genre = reader["genre"].ToString()
                            };
                            books.Add(book);
                        }
                    }
                }
            }
            return books;
        }

        public List<Book> GetBooksForUser(string username)
        {
            List<Book> books = new List<Book>();

            using (MySqlConnection connection = getConnection())
            {
                int userID;
                string queryUserID = "SELECT id FROM users WHERE username = @Username";
                MySqlCommand commandUserID = new MySqlCommand(queryUserID, connection);
                commandUserID.Parameters.AddWithValue("@Username", username);
                connection.Open();
                object resultUserID = commandUserID.ExecuteScalar();

                if (resultUserID != null && int.TryParse(resultUserID.ToString(), out userID))
                {
                    string query = "SELECT * FROM books WHERE userID = @UserID";
                    MySqlCommand command = new MySqlCommand(query, connection);
                    command.Parameters.AddWithValue("@UserID", userID);

                    using (MySqlDataReader reader = command.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            Book book = new Book
                            {
                                Author = reader.GetString("author"),
                                Title = reader.GetString("title"),
                                NumberOfPages = reader.GetInt32("number_of_pages"),
                                Genre = reader.GetString("genre")
                            };

                            books.Add(book);
                        }
                    }
                }
            }
            return books;
        }

        /*
        internal List<LogReport> GetReportsOnPage(int pageNo, int pageSize, int totalSize)
        {
            int offset = pageNo * pageSize;
            int totalPages = (int)Math.Ceiling((double)totalSize / pageSize);
            List<LogReport> reports = new List<LogReport>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;

                cmd.CommandText = "select * from reports limit " + offset + ", " + pageSize + ";";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    LogReport report = new LogReport();
                    report.Id = myreader.GetInt32("id");
                    report.User_id = myreader.GetInt32("user_id");
                    report.Type = myreader.GetString("type");
                    report.Severity = myreader.GetString("severity");
                    report.Date = myreader.GetString("date_posted");
                    reports.Add(report);
                }
                myreader.Close();

                conn.Close();
            }
            catch (MySqlException e)
            {

            }
            return reports;
        }

        public int GetUserIdOfUser(string user)
        {
            int report = 0;
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;

                cmd.CommandText = "select id from users where username='" + user + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    report = myreader.GetInt32("id");
                }

                myreader.Close();

                conn.Close();
            }
            catch (MySqlException e)
            {

            }
            return report;
        }

        internal List<string> GetAllSeverities(string user)
        {
            List<string> severities = new List<string>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select distinct r.severity from reports r inner join users u on r.user_id=u.id where u.username='" + user + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    severities.Add(myreader.GetString("severity"));
                    System.Diagnostics.Debug.WriteLine(myreader.GetString("severity"));
                }
                myreader.Close();
                conn.Close();
            }
            catch (MySqlException e)
            {
                System.Diagnostics.Debug.WriteLine(e.Message);
            }
            return severities;
        }

        public List<string> GetAllTypes(string user)
        {
            List<string> types = new List<string>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select distinct r.type from reports r inner join users u on r.user_id=u.id where u.username='" + user + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    types.Add(myreader.GetString("type"));
                    System.Diagnostics.Debug.WriteLine(myreader.GetString("type"));
                }
                myreader.Close();
                conn.Close();
            }
            catch (MySqlException e)
            {
                System.Diagnostics.Debug.WriteLine(e.Message);
            }
            return types;
        }

        internal List<LogReport> GetLogReportsOfUser(string user)
        {
            List<LogReport> slist = new List<LogReport>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;

                cmd.CommandText = "select r.id, r.user_id, r.type, r.severity, r.date_posted from reports r inner join users u on u.id=r.user_id where u.username='" + user + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    LogReport report = new LogReport();
                    report.Id = myreader.GetInt32("id");
                    report.User_id = myreader.GetInt32("user_id");
                    report.Type = myreader.GetString("type");
                    report.Severity = myreader.GetString("severity");
                    report.Date = myreader.GetString("date_posted");
                    slist.Add(report);
                }
                myreader.Close();

                conn.Close();
            }
            catch (MySqlException ex)
            {

                Console.Write(ex.Message);
            }
            return slist;
        }

        public List<LogReport> GetLogReports()
        {
            List<LogReport> slist = new List<LogReport>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from reports";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    LogReport report = new LogReport();
                    report.Id = myreader.GetInt32("id");
                    report.User_id = myreader.GetInt32("user_id");
                    report.Type = myreader.GetString("type");
                    report.Severity = myreader.GetString("severity");
                    report.Date = myreader.GetString("date_posted");
                    slist.Add(report);
                }
                myreader.Close();

                conn.Close();
            }
            catch (MySqlException ex)
            {
                Console.WriteLine("here");
                Console.Write(ex.Message);
            }
            return slist;
        }

        public bool AddLogReport(LogReport log)
        {
            MySqlCommand cmd = new MySqlCommand();
            MySqlConnection conn = getConnection();
            conn.Open();
            cmd.Connection = conn;
            cmd.CommandText = "INSERT INTO reports(user_id, type, severity, date_posted) VALUES (" +
                log.User_id + ", '" + log.Type + "', '" + log.Severity + "', '" + log.Date + "');";
            int cnt = cmd.ExecuteNonQuery();
            conn.Close();
            return cnt == 1;
        }

        public bool DeleteLogReport(int id)
        {
            MySqlCommand cmd = new MySqlCommand();
            MySqlConnection conn = getConnection();
            conn.Open();
            cmd.Connection = conn;
            cmd.CommandText = "DELETE FROM reports WHERE id=" + id;
            int cnt = cmd.ExecuteNonQuery();
            conn.Close();
            return cnt == 1;
        }

        public List<LogReport> GetReportsOfType(string type, string user)
        {
            List<LogReport> slist = new List<LogReport>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select r.id, r.user_id, r.type, r.severity, r.date_posted from reports r inner join users u on u.id=r.user_id where type='" + type + "' and u.username='" + user + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    LogReport report = new LogReport();
                    report.Id = myreader.GetInt32("id");
                    report.User_id = myreader.GetInt32("user_id");
                    report.Type = myreader.GetString("type");
                    report.Severity = myreader.GetString("severity");
                    report.Date = myreader.GetString("date_posted");
                    slist.Add(report);
                }
                myreader.Close();

                conn.Close();
            }
            catch (MySqlException ex)
            {
                System.Diagnostics.Debug.WriteLine(ex.Message);
            }
            return slist;
        }

        public List<LogReport> GetReportsOfSeverity(string severity, string user)
        {
            List<LogReport> slist = new List<LogReport>();
            try
            {
                MySqlConnection conn = getConnection();
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select r.id, r.user_id, r.type, r.severity, r.date_posted from reports r inner join users u on u.id=r.user_id where severity='" + severity + "' and u.username='" + user + "'";
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    LogReport report = new LogReport();
                    report.Id = myreader.GetInt32("id");
                    report.User_id = myreader.GetInt32("user_id");
                    report.Type = myreader.GetString("type");
                    report.Severity = myreader.GetString("severity");
                    report.Date = myreader.GetString("date_posted");
                    slist.Add(report);
                }
                myreader.Close();

                conn.Close();
            }
            catch (MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return slist;
        }*/
    }
}