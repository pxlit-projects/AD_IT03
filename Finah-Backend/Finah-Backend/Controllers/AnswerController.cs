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
        /// <returns></returns>
        public IEnumerable<answer> Get()
        {
            var answers = _answerRepos.GetAnswers();
            return answers;
        }

        // GET: api/Answer/5
        /// <summary>
        /// Get an answer by it's id
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [ResponseType(typeof(answer))]
        public async Task<IHttpActionResult> GetQuestionById(int id)
        {
            var answer = _answerRepos.GetAnswerById(id);
            if (answer == null)
            {
                return NotFound();
            }

            return Ok(answer);
        }

        // POST: api/Answer
        /// <summary>
        /// Add a new answer
        /// </summary>
        /// <param name="newAnswer">The new answer object</param>
        /// <returns>Http response 201 Created or 400 Bad Request</returns>
        public HttpResponseMessage Post([FromBody]answer newAnswer)
        {
            if (ModelState.IsValid)
            {
                newAnswer = _answerRepos.AddAnswer(newAnswer);
                var response = Request.CreateResponse<answer>(HttpStatusCode.Created, newAnswer);

                string uri = Url.Link("DefaultApi", new { id = newAnswer.id });
                response.Headers.Location = new Uri(uri);
                return response;
            }
            else
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
            }
        }

        // PUT: api/Answer/5
        /// <summary>
        /// Update an existing answer
        /// </summary>
        /// <param name="id">The id of an answer</param>
        /// <param name="updatedAnswer">The answer by id</param>
        public void Put(int id, [FromBody]answer updatedAnswer)
        {
            _answerRepos.UpdateAnswer(id, updatedAnswer);
        }

        // DELETE: api/Answer/5
        /// <summary>
        /// Delete an answer
        /// </summary>
        /// <param name="id">The id of a theme</param>
        public void Delete(int id)
        {
            _answerRepos.DeleteAnswer(id);
        }
    }
}
