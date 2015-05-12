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
    public class ThemeController : ApiController
    {

        private ThemeRepository _themeRepos;

        public ThemeController()
        {
            _themeRepos = new ThemeRepository();
        }

        // GET: api/Theme
        /// <summary>
        /// Get all themes
        /// </summary>
        /// <returns></returns>
        public IEnumerable<theme> Get()
        {
            var themes = _themeRepos.GetThemes();
            return themes;
        }

        // GET: api/Theme/5
        /// <summary>
        /// Get a theme by it's id
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [ResponseType(typeof(theme))]
        public async Task<IHttpActionResult> GetQuestionById(int id)
        {
            var theme = _themeRepos.GetThemeById(id);

            if (theme == null)
            {
                return NotFound();
            }

            return Ok(theme);
        }

        // POST: api/Theme
        /// <summary>
        /// Add a new theme
        /// </summary>
        /// <param name="value"></param>
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/Theme/5
        /// <summary>
        /// Update an existing theme
        /// </summary>
        /// <param name="id"></param>
        /// <param name="value"></param>
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Theme/5
        /// <summary>
        /// Delete a theme
        /// </summary>
        /// <param name="id"></param>
        public void Delete(int id)
        {
        }
    }
}
