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
    public class UserTypeController : ApiController
    {
         private UserTypeRepository _userTypeRepos;

         public UserTypeController()
        {
            _userTypeRepos = new UserTypeRepository();
        }

        // GET: api/UserType
        /// <summary>
        /// Get all usertypes
        /// </summary>
        /// <returns>Returns an IEnumerable of all usertypes</returns>
        public IEnumerable<usertype> Get()
        {
            var usertypes = _userTypeRepos.GetUserTypes();
            return usertypes;
        }

        // GET: api/UserType/5
        /// <summary>
        /// Get a usertype by it's id
        /// </summary>
        /// <param name="id">The id of the usertype</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        [ResponseType(typeof(usertype))]
        public async Task<IHttpActionResult> GetUsertypeById(int id)
        {
            var usertype = _userTypeRepos.GetUserTypeById(id);

            if (usertype == null)
            {
                return NotFound();
            }

            return Ok(usertype);
        }

        // POST: api/UserType
        /// <summary>
        /// Add a new usertype
        /// </summary>
        /// <param name="newUsertype">The new usertype object</param>
        /// <returns>Http response 201 Created or 400 Bad Request</returns>
        public HttpResponseMessage Post([FromBody]usertype newUsertype)
        {

            if (ModelState.IsValid)
            {
                newUsertype = _userTypeRepos.AddUsertype(newUsertype);
                var response = Request.CreateResponse<usertype>(HttpStatusCode.Created, newUsertype);

                string uri = Url.Link("DefaultApi", new { id = newUsertype.id });
                response.Headers.Location = new Uri(uri);
                return response;
            }
            else
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
            }
        }

        // PUT: api/UserType/5
        /// <summary>
        /// Update an existing usertype
        /// </summary>
        /// <param name="id">The id of a usertype</param>
        /// <param name="updatedUsertype">The updated usertype object</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        public HttpResponseMessage Put(int id, [FromBody]usertype updatedUsertype)
        {
            if (!_userTypeRepos.UpdateUserType(id, updatedUsertype))
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            else
            {
                var response = Request.CreateResponse<usertype>(HttpStatusCode.OK, updatedUsertype);
                return response;
            }
        }

        // DELETE: api/UserType/5
        /// <summary>
        /// Delete a usertype
        /// </summary>
        /// <param name="id">The id of a usertype</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        public HttpResponseMessage Delete(int id)
        {
            usertype delUsertype = _userTypeRepos.GetUserTypeById(id);
            if (delUsertype == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            _userTypeRepos.DeleteUsertype(id);
            var response = Request.CreateResponse<usertype>(HttpStatusCode.OK, delUsertype);
            return response;
        }
    }
}
