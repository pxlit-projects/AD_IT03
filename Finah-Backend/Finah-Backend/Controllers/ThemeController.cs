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
    public class ThemeController : ApiController
    {

        private ThemeRepository _themeRepos;

        public ThemeController()
        {
            _themeRepos = new ThemeRepository();
        }

        // GET: api/Theme
        public IEnumerable<theme> Get()
        {
            var themes = _themeRepos.GetThemes();
            return themes;
        }

        // GET: api/Theme/5
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/Theme
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/Theme/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Theme/5
        public void Delete(int id)
        {
        }
    }
}
