using Finah_DomainClasses;
using Finah_Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Description;

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
        [ResponseType(typeof(user))]
        public async Task<IHttpActionResult> GetUser(int id)
        {
            var user = _userRepos.GetUserById(id);

            if (user == null)
            {
                return NotFound();
            }

            return Ok(user);
        }

        // POST: api/User
        public void Post([FromBody]user newUser)
        {
            _userRepos.AddUser(newUser);
        }

        // PUT: api/User/5
        public void Put(int id, [FromBody]user updatedUser)
        {
            _userRepos.UpdateUser(id, updatedUser);
        }

        // DELETE: api/User/5
        public void Delete(int id)
        {
            _userRepos.DeleteUser(id);
        }
    }
}
