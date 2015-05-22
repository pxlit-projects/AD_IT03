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
            try
            {
                var themes = _themeRepos.GetThemes();
                if (themes != null)
                {
                    return themes;
                }
                else
                {
                    throw new HttpResponseException(HttpStatusCode.NotFound);
                }
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }
        }

        // GET: api/Theme/5
        /// <summary>
        /// Get a theme by it's id
        /// </summary>
        /// <param name="id">The id of a theme</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        public HttpResponseMessage GetThemeById(int id)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    var theme = _themeRepos.GetThemeById(id);

                    if (theme == null)
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    var response = Request.CreateResponse<theme>(HttpStatusCode.OK, theme);
                    return response;
                }
                else
                {
                    throw new HttpResponseException(HttpStatusCode.Forbidden);
                }
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }
        }

        // POST: api/Theme
        /// <summary>
        /// Add a new theme
        /// </summary>
        /// <param name="newTheme">The new theme object</param>
        /// <returns>Http response 201 Created or 400 Bad Request</returns>
        public HttpResponseMessage Post([FromBody]theme newTheme)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    newTheme = _themeRepos.AddTheme(newTheme);
                    if (newTheme != null)
                    {
                        var response = Request.CreateResponse<theme>(HttpStatusCode.Created, newTheme);
                        return response;
                    }
                    else
                    {
                        throw new HttpResponseException(HttpStatusCode.BadRequest);
                    }
                }
                else
                {
                    return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
                }
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
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
            try
            {
                if (Validator.IsPositive(id))
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
                else
                {
                    throw new HttpResponseException(HttpStatusCode.Forbidden);
                }
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
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
            try
            {
                if (Validator.IsPositive(id))
                {
                    theme delTheme = _themeRepos.GetThemeById(id);
                    if (delTheme != null)
                    {
                        if (!_themeRepos.DeleteTheme(id))
                        {
                            throw new HttpResponseException(HttpStatusCode.NotFound);
                        }
                        else
                        {
                            var response = Request.CreateResponse<theme>(HttpStatusCode.OK, delTheme);
                            return response;
                        }
                    }
                    else
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                }
                else
                {
                    throw new HttpResponseException(HttpStatusCode.Forbidden);
                }
            }
            catch (Exception)
            {
                throw new HttpResponseException(HttpStatusCode.ServiceUnavailable);
            }
        }
    }
}
