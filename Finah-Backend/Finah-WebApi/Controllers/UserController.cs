﻿using Finah_DomainClasses;
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
            if (users != null)
            {
                return users;
            }
            else
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            
        }

        // GET: api/User/5
        /// <summary>
        /// Get a user by it's id
        /// </summary>
        /// <param name="id"></param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        public HttpResponseMessage GetUserById(int id)
        {
            if (Validator.IsPositive(id))
            {
                var user = _userRepos.GetUserById(id);

                if (user == null)
                {
                    throw new HttpResponseException(HttpStatusCode.NotFound);
                }
                var response = Request.CreateResponse<user>(HttpStatusCode.OK, user);
                return response;
            }
            else
            {
                throw new HttpResponseException(HttpStatusCode.Forbidden);
            }
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
                if (newUser != null)
                {
                    var response = Request.CreateResponse<user>(HttpStatusCode.Created, newUser);
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

        // PUT: api/User/5
        /// <summary>
        /// Update an existing user
        /// </summary>
        /// <param name="id">The id of a user</param>
        /// <param name="updatedUser">The updated user object</param>
        /// <returns>Http response 200 OK or 404 Not found or 403 Forbidden</returns>
        public HttpResponseMessage Put(int id, [FromBody]user updatedUser)
        {
            if (Validator.IsPositive(id))
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
            else
            {
                throw new HttpResponseException(HttpStatusCode.Forbidden);
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
            if (Validator.IsPositive(id))
            {
                user delUser = _userRepos.GetUserById(id);
                if (delUser != null)
                {
                    if (!_userRepos.DeleteUser(id))
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    else
                    {
                        var response = Request.CreateResponse<user>(HttpStatusCode.OK, delUser);
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
    }
}