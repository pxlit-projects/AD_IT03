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
        /// <returns>Returns an IEnumerable of question objects</returns>
        public IEnumerable<question> Get()
        {
            var questions = _questionRepos.GetQuestions();
            return questions;
        }

        // GET: api/Question/5
        /// <summary>
        /// Get a question by it's id
        /// </summary>
        /// <param name="id">The id of a question</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        [ResponseType(typeof(question))]
        public async Task<IHttpActionResult> GetQuestionById(int id)
        {
            var question = _questionRepos.GetQuestionById(id);

            if (question == null)
            {
                return NotFound();
            }

            return Ok(question);
        }

        // POST: api/Question
        /// <summary>
        /// Add a new question
        /// </summary>
        /// <param name="newQuestion">The new question object</param>
        /// <returns>Http response 201 Created or 400 Bad Request</returns>
        public HttpResponseMessage Post([FromBody]question newQuestion)
        {
            if (ModelState.IsValid)
            {
                newQuestion = _questionRepos.AddQuestion(newQuestion);
                var response = Request.CreateResponse<question>(HttpStatusCode.Created, newQuestion);

                string uri = Url.Link("DefaultApi", new { id = newQuestion.id });
                response.Headers.Location = new Uri(uri);
                return response;
            }
            else
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
            }
        }

        // PUT: api/Question/5
        /// <summary>
        /// Update an existing question
        /// </summary>
        /// <param name="id">The id of a question</param>
        /// <param name="updatedQuestion">The updated question object</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        public HttpResponseMessage Put(int id, [FromBody]question updatedQuestion)
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

        // DELETE: api/Question/5
        /// <summary>
        /// Delete a question
        /// </summary>
        /// <param name="id">The id of a question</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        public HttpResponseMessage Delete(int id)
        {
            question delQuestion = _questionRepos.GetQuestionById(id);
            if (delQuestion == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            _questionRepos.DeleteQuestion(id);
            var response = Request.CreateResponse<question>(HttpStatusCode.OK, delQuestion);
            return response;
        }
    }
}
