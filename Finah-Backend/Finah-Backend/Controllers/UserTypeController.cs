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
    public class UserTypeController : ApiController
    {
         private UserTypeRepository _userTypeRepos;

         public UserTypeController()
        {
            _userTypeRepos = new UserTypeRepository();
        }

        // GET: api/UserType
        public IEnumerable<usertype> Get()
        {
            var usertypes = _userTypeRepos.GetUserTypes();
            return usertypes;
        }

        // GET: api/UserType/5
        public usertype Get(int id)
        {
            var usertype = _userTypeRepos.GetUserTypeById(id);
            return usertype;
        }

        // POST: api/UserType
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
        public void Put(int id, [FromBody]usertype updatedUsertype)
        {
            _userTypeRepos.UpdateUserType(id, updatedUsertype);
        }

        // DELETE: api/UserType/5
        public void Delete(int id)
        {
            _userTypeRepos.DeleteUsertype(id);
        }
    }
}
