using CityLinks.DataAbstractionLayer;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using CityLinks.Models;

namespace CityLinks.Controllers
{
    public class RouteStartController : Controller
    {
        private DAL dal = new DAL();
        
        public ActionResult Index()
        {
            List<City> cities = dal.GetAllCities();
            return View("RouteStartView", cities);
        }

        public ActionResult RouteStart(string name, string county)
        {
            List<City> cities = dal.GetAllCities();
            if (dal.CheckCityExists(name, county))
            {
                HttpContext.Session["name"] = name;
                HttpContext.Session["county"] = county;
                HttpContext.Session["route"] = name;
                HttpContext.Session["duration"] = "0";
                HttpContext.Session["distance"] = "0";
                return Redirect("/RouteContinue");
            }
            else
            {
                return View("RouteStartView", cities);
            }
        }
    }
}