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
    public class TimeController : ApiController
    {

        private TimeRepository _timeRepos;

        public TimeController()
        {
            _timeRepos = new TimeRepository();
        }

        // GET: api/Time
        public IEnumerable<time> Get()
        {
            var times = _timeRepos.GetTimes();
            return times;
        }

        // GET: api/Time/5
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/Time
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/Time/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Time/5
        public void Delete(int id)
        {
        }
    }
}
