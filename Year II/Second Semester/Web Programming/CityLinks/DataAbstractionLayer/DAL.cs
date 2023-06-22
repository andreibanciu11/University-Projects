using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Xml.Linq;
using CityLinks.Models;
using MySql.Data.MySqlClient;

namespace CityLinks.DataAbstractionLayer
{
    public class DAL
    {
        public MySqlConnection getConnection()
        {
            string myConnectionString;
            myConnectionString = "server=localhost;uid=root;pwd=A10N10D10;database=city_links;";
            return new MySqlConnection(myConnectionString);
        }

        public List<City> GetAllCities()
        {
            var conn = getConnection();
            conn.Open();
            var query = "SELECT * FROM city";
            var cmd = new MySqlCommand(query, conn);

            MySqlDataReader dataReader = cmd.ExecuteReader();
            List<City> cities = new List<City>();

            while (dataReader.Read())
            {
                cities.Add(new City(dataReader.GetString(1), dataReader.GetString(2)));
            }

            conn.Close();

            return cities;
        }

        public bool CheckCityExists(string name, string county)
        {
            var conn = getConnection();
            conn.Open();
            var query = "SELECT name, county FROM city WHERE name = @Name AND county = @County LIMIT 1";
            var cmd = new MySqlCommand(query, conn);
            cmd.Parameters.AddWithValue("@Name", name);
            cmd.Parameters.AddWithValue("@County", county);

            object query_result = cmd.ExecuteScalar();

            if(query_result != null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public int GetIdByCityName(string name)
        {
            var conn = getConnection();
            conn.Open();
            var query = "SELECT id FROM city WHERE name = @Name";
            var cmd = new MySqlCommand(query, conn);
            cmd.Parameters.AddWithValue("@Name", name);

            object query_result = cmd.ExecuteScalar();

            if (query_result != null)
            {
                int cityID;
                int.TryParse(query_result.ToString(), out cityID);
                conn.Close();
                return cityID;
            }
            else
            {
                conn.Close();
                return 0;
            }
        }

        public string GetCityNameByID(int id)
        {
            var conn = getConnection();
            conn.Open();
            var query = "SELECT name FROM city WHERE id = @ID";
            var cmd = new MySqlCommand(query, conn);
            cmd.Parameters.AddWithValue("@ID", id);

            object query_result = cmd.ExecuteScalar();

            if (query_result != null)
            {
                string name = query_result.ToString();
                conn.Close();
                return name;
            }
            else
            {
                conn.Close();
                return "";
            }
        }

        public string GetCountyForCityName(string name)
        {
            var conn = getConnection();
            conn.Open();
            var query = "SELECT county FROM city WHERE name = @Name LIMIT 1";
            var cmd = new MySqlCommand(query, conn);
            cmd.Parameters.AddWithValue("@Name", name);

            object query_result = cmd.ExecuteScalar();

            if (query_result != null)
            {
                string county = query_result.ToString();
                conn.Close();
                return county;
            }
            else
            {
                conn.Close();
                return "";
            }
        }

        public List<CityLinkInfo> GetLinkCitiesForCurrentCity(string name)
        {
            var conn = getConnection();
            conn.Open();

            int current_cityID = GetIdByCityName(name);
            var query = "SELECT * FROM link WHERE id_city1 = @CityID OR id_city2 = @CityID";
            var cmd = new MySqlCommand(query, conn);
            cmd.Parameters.AddWithValue("@CityID", current_cityID);

            MySqlDataReader dataReader = cmd.ExecuteReader();
            List<CityLinkInfo> cities = new List<CityLinkInfo>();

            while (dataReader.Read())
            {
                int id1;
                int.TryParse(dataReader.GetString(1), out id1);
                string name1 = GetCityNameByID(id1);

                int id2;
                int.TryParse(dataReader.GetString(2), out id2);
                string name2 = GetCityNameByID(id2);

                int duration;
                int.TryParse(dataReader.GetString(3), out duration);

                int distance;
                int.TryParse(dataReader.GetString(4), out distance);

                cities.Add(new CityLinkInfo(name1, name2, duration, distance));
            }

            conn.Close();

            SortLinkCitiesForCurrentCity(cities);
            return cities;
        }

        public int CompareByAverage(CityLinkInfo city1, CityLinkInfo city2)
        {
            double city1_duration = (double)city1.Duration;
            double city1_distance = (double)city1.Distance;
            double city2_duration = (double)city2.Duration;
            double city2_distance = (double)city2.Distance;
            double average1 = city1_duration * 0.6 + city1_distance * 0.4;
            double average2 = city2_duration * 0.6 + city2_distance * 0.4;

            if(average1 < average2)
            {
                return -1;
            }
            else
            {
                if (average1 == average2)
                    return 0;
                else
                    return 1;
            }
        }

        public void SortLinkCitiesForCurrentCity(List<CityLinkInfo> cities)
        {
            cities.Sort(CompareByAverage);
        }
    }
}