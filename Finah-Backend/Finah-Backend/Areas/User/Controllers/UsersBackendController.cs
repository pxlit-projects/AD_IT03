using Finah_Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Mvc;

namespace WebAPI.Areas.User.Controllers
{
    public class UsersBackendController : Controller
    {

        private UserRepository _userRepos;

        public UsersBackendController()
        {
            _userRepos = new UserRepository();
        }

        // GET: api/Users
        public ActionResult Index()
        {
            var users = _userRepos.GetUsers();
            return View(users);
        }

        // GET: api/Users/5
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/Users
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/Users/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Users/5
        public void Delete(int id)
        {
        }
    }
}
