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
        /// <returns>Returns an IEnumerable of theme objects</returns>
        public IEnumerable<theme> Get()
        {
            var themes = _themeRepos.GetThemes();
            return themes;
        }

        // GET: api/Theme/5
        /// <summary>
        /// Get a theme by it's id
        /// </summary>
        /// <param name="id">The id of a theme</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        [ResponseType(typeof(theme))]
        public async Task<IHttpActionResult> GetThemeById(int id)
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
        /// <param name="newTheme">The new theme object</param>
        /// <returns>Http response 201 Created or 400 Bad Request</returns>
        public HttpResponseMessage Post([FromBody]theme newTheme)
        {

            if (ModelState.IsValid)
            {
                newTheme = _themeRepos.AddTheme(newTheme);
                var response = Request.CreateResponse<theme>(HttpStatusCode.Created, newTheme);

                string uri = Url.Link("DefaultApi", new { id = newTheme.id });
                response.Headers.Location = new Uri(uri);
                return response;
            }
            else
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
            }
        }

        // PUT: api/Theme/5
        /// <summary>
        /// Update an existing theme
        /// </summary>
        /// <param name="id">The id of a theme</param>
        /// <param name="updatedTheme">The updated theme object</param>
        /// <returns>Http response 201 Created or 404 Not found</returns>
        public HttpResponseMessage Put(int id, [FromBody]theme updatedTheme)
        {
            if (!_themeRepos.UpdateTheme(id, updatedTheme))
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            else
            {
                var response = Request.CreateResponse<theme>(HttpStatusCode.OK, updatedTheme);
                return response;
            }  
        }

        // DELETE: api/Theme/5
        /// <summary>
        /// Delete a theme
        /// </summary>
        /// <param name="id">The id of a theme</param>
        /// <returns>Http response 200 Ok or 404 Not found</returns>
        public HttpResponseMessage Delete(int id)
        {
            theme delTheme = _themeRepos.GetThemeById(id);
            if (delTheme == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            _themeRepos.DeleteTheme(id);
            var response = Request.CreateResponse<theme>(HttpStatusCode.OK, delTheme);
            return response;
        }
    }
}
