using Finah_DomainClasses;
using Finah_Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace WebAPI.Controllers
{
    public class UserController : ApiController
    {

         private UserRepository _userRepos;

         public UserController()
        {
            _userRepos = new UserRepository();
        }
        // GET: api/User
        public IEnumerable<user> Get()
        {
            var users = _userRepos.GetUsers();
            return users;
        }

        // GET: api/User/5
        public user Get(int id)
        {
            var user = _userRepos.GetUserById(id);
            return user;
        }

        // POST: api/User
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/User/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/User/5
        public void Delete(int id)
        {
        }
    }
}
