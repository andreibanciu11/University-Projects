using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CityLinks.Models
{
    public class City
    {
        private string _name;
        private string _county;

        public City(string name, string county)
        {
            _name = name;
            _county = county;
        }

        public string Name { get => _name; set => _name = value; }
        public string County { get => _county; set => _county = value; }
    }
}