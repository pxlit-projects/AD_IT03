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

namespace WebAPI.Controllers
{

    public class QuestionListController : ApiController
    {

        private QuestionListRepository _questionListRepos;

        public QuestionListController()
        {
            _questionListRepos = new QuestionListRepository();
        }

        // GET: api/QuestionList
        /// <summary>
        /// Get all questionlists
        /// </summary>
        /// <returns>Returns an IEnumerable of questionlist objects, 404 Not Found or 503 Service Unavailable</returns>
        public IEnumerable<questionlist> Get()
        {
            try
            {
                var questionLists = _questionListRepos.GetQuestionLists();
                if (questionLists != null)
                {
                    return questionLists;
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

        // GET: api/QuestionList/5
        /// <summary>
        /// Get a questionlist by it's id
        /// </summary>
        /// <param name="id">The id of a questionlist</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage GetQuestionlistById(int id)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    var questionlist = _questionListRepos.GetQuestionListById(id);

                    if (questionlist == null)
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    var response = Request.CreateResponse<questionlist>(HttpStatusCode.OK, questionlist);
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

        // POST: api/QuestionList
        /// <summary>
        /// Add a new questionlist
        /// </summary>
        /// <param name="newQuestionlist">The new questionlist object</param>
        /// <returns>Http response 201 Created, 400 Bad Request or 503 Service Unavailable</returns>
        public HttpResponseMessage Post([FromBody]questionlist newQuestionlist)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    newQuestionlist = _questionListRepos.AddQuestionlist(newQuestionlist);
                    if (newQuestionlist != null)
                    {
                        var response = Request.CreateResponse<questionlist>(HttpStatusCode.Created, newQuestionlist);
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

        // PUT: api/QuestionList/5
        /// <summary>
        /// Update an existing questionlist
        /// </summary>
        /// <param name="id">The id of a questionlist</param>
        /// <param name="updatedQuestionlist">The updated questionlist object</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage Put(int id, [FromBody]questionlist updatedQuestionlist)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    if (!_questionListRepos.UpdateQuestionList(id, updatedQuestionlist))
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    else
                    {
                        var response = Request.CreateResponse<questionlist>(HttpStatusCode.OK, updatedQuestionlist);
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

        // DELETE: api/QuestionList/5
        /// <summary>
        /// Delete a questionlist
        /// </summary>
        /// <param name="id">The id of a questionlist</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage Delete(int id)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    questionlist delQuestionlist = _questionListRepos.GetQuestionListById(id);
                    if (delQuestionlist != null)
                    {
                        if (!_questionListRepos.DeleteQuestionlist(id))
                        {
                            throw new HttpResponseException(HttpStatusCode.NotFound);
                        }
                        else
                        {
                            var response = Request.CreateResponse<questionlist>(HttpStatusCode.OK, delQuestionlist);
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
