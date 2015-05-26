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

    public class QuestionController : ApiController
    {

        private QuestionRepository _questionRepos;

        public QuestionController()
        {
            _questionRepos = new QuestionRepository();
        }

        // GET: api/Question
        /// <summary>
        /// Get all questions
        /// </summary>
        /// <returns>Returns an IEnumerable of question objects, 404 Not Found or 503 Service Unavailable</returns>
        public IEnumerable<question> Get()
        {
            try
            {
                var questions = _questionRepos.GetQuestions();
                if (questions != null)
                {
                    return questions;
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

        // GET: api/Question/5
        /// <summary>
        /// Get a question by it's id
        /// </summary>
        /// <param name="id">The id of a question</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage GetQuestionById(int id)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    var question = _questionRepos.GetQuestionById(id);

                    if (question == null)
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    var response = Request.CreateResponse<question>(HttpStatusCode.OK, question);
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

        // POST: api/Question
        /// <summary>
        /// Add a new question
        /// </summary>
        /// <param name="newQuestion">The new question object</param>
        /// <returns>Http response 201 Created, 400 Bad Request or 503 Service Unavailable</returns>
        public HttpResponseMessage Post([FromBody]question newQuestion)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    newQuestion = _questionRepos.AddQuestion(newQuestion);
                    if (newQuestion != null)
                    {
                        var response = Request.CreateResponse<question>(HttpStatusCode.Created, newQuestion);
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

        // PUT: api/Question/5
        /// <summary>
        /// Update an existing question
        /// </summary>
        /// <param name="id">The id of a question</param>
        /// <param name="updatedQuestion">The updated question object</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage Put(int id, [FromBody]question updatedQuestion)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    if (!_questionRepos.UpdateQuestion(id, updatedQuestion))
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    else
                    {
                        var response = Request.CreateResponse<question>(HttpStatusCode.OK, updatedQuestion);
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

        // DELETE: api/Question/5
        /// <summary>
        /// Delete a question
        /// </summary>
        /// <param name="id">The id of a question</param>
        /// <returns>Http response 200 OK, 403 Forbidden, 404 Not found or 503 Service Unavailable</returns>
        public HttpResponseMessage Delete(int id)
        {
            try
            {
                if (Validator.IsPositive(id))
                {
                    question delQuestion = _questionRepos.GetQuestionById(id);
                    if (delQuestion != null)
                    {
                        if (!_questionRepos.DeleteQuestion(id))
                        {
                            throw new HttpResponseException(HttpStatusCode.NotFound);
                        }
                        else
                        {
                            var response = Request.CreateResponse<question>(HttpStatusCode.OK, delQuestion);
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
