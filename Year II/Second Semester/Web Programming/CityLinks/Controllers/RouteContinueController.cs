using CityLinks.DataAbstractionLayer;
using CityLinks.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace CityLinks.Controllers
{
    public class RouteContinueController : Controller
    {
        private DAL dal = new DAL();

        public ActionResult Index()
        {
            string name = HttpContext.Session["name"].ToString();
            List<CityLinkInfo> cities = dal.GetLinkCitiesForCurrentCity(name);
            return View("RouteContinueView", cities);
        }

        public ActionResult RouteContinue(string name)
        {
            string name_current = HttpContext.Session["name"].ToString();
            string county_current = HttpContext.Session["county"].ToString();
            List<CityLinkInfo> cities = dal.GetLinkCitiesForCurrentCity(name_current);

            List<CityLinkInfo> cities_check1 = cities.Where(link => link.Name1.Equals(name) || link.Name2.Equals(name)).ToList();

            if (cities_check1.Count.Equals(0))
            {
                return View("RouteContinueView", cities);
            }
            else
            {
                List<CityLinkInfo> cities_session = cities.Where(link => link.Name1.Equals(name_current) && link.Name2.Equals(name)).ToList();

                CityLinkInfo link_info = cities.ElementAt(0);

                HttpContext.Session["name"] = name;
                HttpContext.Session["county"] = dal.GetCountyForCityName(name);
                HttpContext.Session["route"] += " " + name;
                int current_duration;
                string duration_string = HttpContext.Session["duration"].ToString();
                int.TryParse(duration_string, out current_duration);
                int new_duration = current_duration + link_info.Duration;
                HttpContext.Session["duration"] = new_duration.ToString();

                int current_distance;
                string distance_string = HttpContext.Session["distance"].ToString();
                int.TryParse(distance_string, out current_distance);
                int new_distance = current_distance + link_info.Distance;
                HttpContext.Session["distance"] = new_distance.ToString();

                return Redirect("/RouteContinue");
            }
        }
    }
}