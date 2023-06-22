using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CityLinks.Models
{
    public class Link
    {
        private int _id_city1;
        private int _id_city2;
        private int _duration;
        private int _distance;

        public Link(int id_city1, int id_city2, int duration, int distance)
        {
            _id_city1 = id_city1;
            _id_city2 = id_city2;
            _duration = duration;
            _distance = distance;
        }

        public int IDcity1 { get => _id_city1; set => _id_city1 = value; }
        public int IDcity2 { get => _id_city2; set => _id_city2 = value;}
        public int Duration { get => _duration; set => _duration = value; }
        public int Distance { get => _distance; set => _distance = value; }
    }
}