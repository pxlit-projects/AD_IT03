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
        /// <returns>Returns an IEnumerable of questionlist objects</returns>
        public IEnumerable<questionlist> Get()
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

        // GET: api/QuestionList/5
        /// <summary>
        /// Get a questionlist by it's id
        /// </summary>
        /// <param name="id">The id of a questionlist</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        public HttpResponseMessage GetQuestionlistById(int id)
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

        // POST: api/QuestionList
        /// <summary>
        /// Add a new questionlist
        /// </summary>
        /// <param name="newQuestionlist">The new questionlist object</param>
        /// <returns>Http response 201 Created or 400 Bad Request</returns>
        public HttpResponseMessage Post([FromBody]questionlist newQuestionlist)
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

        // PUT: api/QuestionList/5
        /// <summary>
        /// Update an existing questionlist
        /// </summary>
        /// <param name="id">The id of a questionlist</param>
        /// <param name="updatedQuestionlist">The updated questionlist object</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        public HttpResponseMessage Put(int id, [FromBody]questionlist updatedQuestionlist)
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

        // DELETE: api/QuestionList/5
        /// <summary>
        /// Delete a questionlist
        /// </summary>
        /// <param name="id">The id of a questionlist</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        public HttpResponseMessage Delete(int id)
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
    }
}
