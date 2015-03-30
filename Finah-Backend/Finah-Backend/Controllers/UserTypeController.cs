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
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/UserType
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/UserType/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/UserType/5
        public void Delete(int id)
        {
        }
    }
}
