using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CityLinks.Models
{
    public class CityLinkInfo
    {
        private string _name1;
        private string _name2;
        private int _duration;
        private int _distance;

        public CityLinkInfo(string name1, string name2, int duration, int destination)
        {
            _name1 = name1;
            _name2 = name2;
            _duration = duration;
            _distance = destination;
        }

        public string Name1 { get => _name1; set => _name1 = value; }
        public string Name2 { get => _name2; set => _name2 = value; }
        public int Duration { get => _duration; set => _duration = value;}
        public int Distance { get => _distance; set => _distance = value;}
    }
}