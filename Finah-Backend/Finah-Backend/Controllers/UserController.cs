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
        /// <summary>
        /// Get all users
        /// </summary>
        /// <returns>Returns an IEnumerable of user objects</returns>
        public IEnumerable<user> Get()
        {
            var users = _userRepos.GetUsers();
            return users;
        }

        // GET: api/User/5
        /// <summary>
        /// Get a user by it's id
        /// </summary>
        /// <param name="id"></param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
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
        /// <summary>
        /// Add a new user
        /// </summary>
        /// <param name="newUser">The new user object</param>
        /// <returns>Http response 201 Created or 400 Bad Request</returns>
        public HttpResponseMessage Post([FromBody]user newUser)
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
        /// <summary>
        /// Update an existing user
        /// </summary>
        /// <param name="id">The id of a user</param>
        /// <param name="updatedUser">The user by id</param>
        /// <returns>Http response 200 Ok or 404 Not found</returns>
        public HttpResponseMessage Put(int id, [FromBody]user updatedUser)
        {
            if (!_userRepos.UpdateUser(id, updatedUser))
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            else
            {
                var response = Request.CreateResponse<user>(HttpStatusCode.OK, updatedUser);
                return response;
            }
        }

        // DELETE: api/User/5
        /// <summary>
        /// Delete an existing user
        /// </summary>
        /// <param name="id">The id of a user</param>
        /// <returns>Http response 200 Ok or 404 Not found</returns>
        public HttpResponseMessage Delete(int id)
        {
            user delUser = _userRepos.GetUserById(id);
            if (delUser == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            _userRepos.DeleteUser(id);
            var response = Request.CreateResponse<user>(HttpStatusCode.OK, delUser);
            return response;  
        }
    }
}