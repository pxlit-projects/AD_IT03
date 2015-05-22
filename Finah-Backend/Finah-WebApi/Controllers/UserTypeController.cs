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
         /// <returns>Returns an IEnumerable of usertype objects, 404 Not Found or 503 Service Unavailable</returns>
         public IEnumerable<usertype> Get()
        {
            try
            {
                var usertypes = _userTypeRepos.GetUserTypes();
                if (usertypes != null)
                {
                    return usertypes;
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

        // GET: api/UserType/5
        /// <summary>
        /// Get a usertype by it's id
        /// </summary>
        /// <param name="id">The id of the usertype</param>
         /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
         public HttpResponseMessage GetUsertypeById(int id)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    var usertype = _userTypeRepos.GetUserTypeById(id);

                    if (usertype == null)
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    var response = Request.CreateResponse<usertype>(HttpStatusCode.OK, usertype);
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

        // POST: api/UserType
        /// <summary>
        /// Add a new usertype
        /// </summary>
        /// <param name="newUsertype">The new usertype object</param>
         /// <returns>Http response 201 Created, 400 Bad Request or 503 Service Unavailable</returns>
         public HttpResponseMessage Post([FromBody]usertype newUsertype)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    newUsertype = _userTypeRepos.AddUsertype(newUsertype);
                    if (newUsertype != null)
                    {
                        var response = Request.CreateResponse<usertype>(HttpStatusCode.Created, newUsertype);
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

        // PUT: api/UserType/5
        /// <summary>
        /// Update an existing usertype
        /// </summary>
        /// <param name="id">The id of a usertype</param>
        /// <param name="updatedUsertype">The updated usertype object</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage Put(int id, [FromBody]usertype updatedUsertype)
        {
            try
            {
                if (Validator.IsPositive(id))
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

        // DELETE: api/UserType/5
        /// <summary>
        /// Delete a usertype
        /// </summary>
        /// <param name="id">The id of a usertype</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage Delete(int id)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    usertype delUsertype = _userTypeRepos.GetUserTypeById(id);
                    if (delUsertype != null)
                    {
                        if (!_userTypeRepos.DeleteUsertype(id))
                        {
                            throw new HttpResponseException(HttpStatusCode.NotFound);
                        }
                        else
                        {
                            var response = Request.CreateResponse<usertype>(HttpStatusCode.OK, delUsertype);
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
