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
            return questionLists;
        }

        // GET: api/QuestionList/5
        /// <summary>
        /// Get a questionlist by it's id
        /// </summary>
        /// <param name="id">The id of a questionlist</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        [ResponseType(typeof(questionlist))]
        public async Task<IHttpActionResult> GetQuestionlistById(int id)
        {
            var questionlist = _questionListRepos.GetQuestionListById(id);

            if (questionlist == null)
            {
                return NotFound();
            }

            return Ok(questionlist);
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
                var response = Request.CreateResponse<questionlist>(HttpStatusCode.Created, newQuestionlist);

                string uri = Url.Link("DefaultApi", new { id = newQuestionlist.id });
                response.Headers.Location = new Uri(uri);
                return response;
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

        // DELETE: api/QuestionList/5
        /// <summary>
        /// Delete a questionlist
        /// </summary>
        /// <param name="id">The id of a questionlist</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        public HttpResponseMessage Delete(int id)
        {
            questionlist delQuestionlist = _questionListRepos.GetQuestionListById(id);
            if (delQuestionlist == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            _questionListRepos.DeleteQuestionlist(id);
            var response = Request.CreateResponse<questionlist>(HttpStatusCode.OK, delQuestionlist);
            return response;
        }
    }
}
