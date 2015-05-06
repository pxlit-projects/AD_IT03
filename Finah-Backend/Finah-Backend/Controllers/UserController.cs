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
        [HttpGet]
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

            //string output = Newtonsoft.Json.JsonConvert.SerializeObject(user);

            return Ok(user);
        }

        // POST: api/User
        [HttpPost]
        public HttpResponseMessage Post([FromBody]user newUser)
        {
            //_userRepos.AddUser(newUser);
            //var response = Request.CreateResponse<user>(HttpStatusCode.Created, newUser);

            //string uri = Url.Link("DefaultApi", new { id = newUser.id });
            //response.Headers.Location = new Uri(uri);
            //return response;

            try
            {
                if (newUser == null) Request.CreateErrorResponse(HttpStatusCode.BadRequest, "Could not read subject/tutor from body");

                if (_userRepos.AddUser(newUser))
                {
                    return Request.CreateResponse(HttpStatusCode.Created, newUser);
                }
                else
                {
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, "Could not save to the database.");
                }
            }
            catch (Exception ex)
            {

                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ex);
            }
        }

        // PUT: api/User/5
        [HttpPut]
        public void Put(int id, user updatedUser)
        {
            _userRepos.UpdateUser(id, updatedUser);
        }

        // DELETE: api/User/5
        [HttpDelete]
        public void Delete(int id)
        {
            _userRepos.DeleteUser(id);
        }
    }
}