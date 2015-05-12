using Finah_DomainClasses;
using Finah_Repository;
using System;
using System.Collections.Generic;
using System.Data.Entity.Validation;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
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
        public async Task<IHttpActionResult> GetUserById(int id)
        {
            var user = _userRepos.GetUserById(id);

            if (user == null)
            {
                return NotFound();
            }

            return Ok(user);
        }

        // POST: api/User
        public HttpResponseMessage Post(user newUser)
        {

            if (ModelState.IsValid)
            {
                newUser = _userRepos.AddUser(newUser);
                var response = Request.CreateResponse<user>(HttpStatusCode.Created, newUser);

                string uri = Url.Link("DefaultApi", new { id = newUser.id });
                response.Headers.Location = new Uri(uri);
                return response;
            }
            else
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
            }
        }

        // PUT: api/User/5
        public void Put(int id, user updatedUser)
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