﻿using Finah_DomainClasses;
using Finah_Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Description;
using System.Web.Mvc;

namespace WebAPI.Controllers
{
    public class AnswerListController : ApiController
    {
        private AnswerListRepository _answerListRepos;

        public AnswerListController()
        {
            _answerListRepos = new AnswerListRepository();
        }

        // GET: api/AnswerList
        /// <summary>
        /// Get all answerlists
        /// </summary>
        /// <returns>Returns an IEnumerable of answerlist objects, 404 Not Found or 503 Service Unavailable</returns>
        public IEnumerable<answerlist> Get()
        {
            try
            {
                var answerlists = _answerListRepos.GetAnswerLists();
                if (answerlists != null)
                {
                    return answerlists;
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

        // GET: api/AnswerList/5
        /// <summary>
        /// Get an answerlist by it's id
        /// </summary>
        /// <param name="id">The id of an answerlist</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage GetAnswerlistById(int id)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    var answerlist = _answerListRepos.GetAnswerListById(id);

                    if (answerlist == null)
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    var response = Request.CreateResponse<answerlist>(HttpStatusCode.OK, answerlist);
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
        // GET: api/AnswerList/GetAnswerlistByHash/{hash}
        /// <summary>
        /// Get all answerlists by hash
        /// </summary>
        /// <param name="hash">The hash of certain answerlists</param>
        /// <returns>Returns an IEnumerable of answer objects, 404 Not Found or 503 Service Unavailable</returns>
        public IEnumerable<answerlist> GetAnswerlistByHash(string hash)
        {
            try
            {
                var answerlists = _answerListRepos.GetAnswerListByHash(hash);
                if (answerlists != null)
                {
                    return answerlists;
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

        // POST: api/AnswerList
        /// <summary>
        /// Add a new answerlist
        /// </summary>
        /// <param name="newAnswerlist">The new answerlist object</param>
        /// <returns>Http response 201 Created, 400 Bad Request or 503 Service Unavailable</returns>
        public HttpResponseMessage Post([FromBody]answerlist newAnswerlist)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    newAnswerlist = _answerListRepos.AddAnswerlist(newAnswerlist);
                    if (newAnswerlist != null)
                    {
                        var response = Request.CreateResponse<answerlist>(HttpStatusCode.Created, newAnswerlist);
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

        // PUT: api/AnswerList/5
        /// <summary>
        /// Update an existing answerlist
        /// </summary>
        /// <param name="id">The id of an answerlist</param>
        /// <param name="updatedAnswerlist">The updated answerlist object</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage Put(int id, [FromBody]answerlist updatedAnswerlist)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    if (!_answerListRepos.UpdateAnswerList(id, updatedAnswerlist))
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    else
                    {
                        var response = Request.CreateResponse<answerlist>(HttpStatusCode.OK, updatedAnswerlist);
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

        // DELETE: api/AnswerList/5
        /// <summary>
        /// Delete an answerlist
        /// </summary>
        /// <param name="id">The id of an answerlist</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage Delete(int id)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    answerlist delAnswerlist = _answerListRepos.GetAnswerListById(id);
                    if (delAnswerlist != null)
                    {
                        if (!_answerListRepos.DeleteAnswerlist(id))
                        {
                            throw new HttpResponseException(HttpStatusCode.NotFound);
                        }
                        else
                        {
                            var response = Request.CreateResponse<answerlist>(HttpStatusCode.OK, delAnswerlist);
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
