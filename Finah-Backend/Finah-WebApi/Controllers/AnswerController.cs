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
    public class AnswerController : ApiController
    {

        private AnswerRepository _answerRepos;

        public AnswerController()
        {
            _answerRepos = new AnswerRepository();
        }
        // GET: api/Answer
        /// <summary>
        /// Get all answers
        /// </summary>
        /// <returns>Returns an IEnumerable of answer objects, 404 Not Found or 503 Service Unavailable</returns>
        public IEnumerable<answer> Get()
        {
            try
            {
                var answers = _answerRepos.GetAnswers();
                if (answers != null)
                {
                    return answers;
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

        // GET: api/Answer/5
        /// <summary>
        /// Get an answer by it's id
        /// </summary>
        /// <param name="id">The id of an answer</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage GetQuestionById(int id)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    var answer = _answerRepos.GetAnswerById(id);

                    if (answer == null)
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    var response = Request.CreateResponse<answer>(HttpStatusCode.OK, answer);
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

        // POST: api/Answer
        /// <summary>
        /// Add a new answer
        /// </summary>
        /// <param name="newAnswer">The new answer object</param>
        /// <returns>Http response 201 Created, 400 Bad Request or 503 Service Unavailable</returns>
        public HttpResponseMessage Post([FromBody]answer newAnswer)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    newAnswer = _answerRepos.AddAnswer(newAnswer);
                    if (newAnswer != null)
                    {
                        var response = Request.CreateResponse<answer>(HttpStatusCode.Created, newAnswer);
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

        // PUT: api/Answer/5
        /// <summary>
        /// Update an existing answer
        /// </summary>
        /// <param name="id">The id of an answer</param>
        /// <param name="updatedAnswer">The updated answer object</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage Put(int id, [FromBody]answer updatedAnswer)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    if (!_answerRepos.UpdateAnswer(id, updatedAnswer))
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    else
                    {
                        var response = Request.CreateResponse<answer>(HttpStatusCode.OK, updatedAnswer);
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

        // DELETE: api/Answer/5
        /// <summary>
        /// Delete an answer
        /// </summary>
        /// <param name="id">The id of an Answer</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage Delete(int id)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    answer delAnswer = _answerRepos.GetAnswerById(id);
                    if (delAnswer != null)
                    {
                        if (!_answerRepos.DeleteAnswer(id))
                        {
                            throw new HttpResponseException(HttpStatusCode.NotFound);
                        }
                        else
                        {
                            var response = Request.CreateResponse<answer>(HttpStatusCode.OK, delAnswer);
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
