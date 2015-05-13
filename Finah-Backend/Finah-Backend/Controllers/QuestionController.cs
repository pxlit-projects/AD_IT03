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
        public IEnumerable<question> Get()
        {
            var questions = _questionRepos.GetQuestions();
            return questions;
        }

        // GET: api/Question/5
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
        public void Put(int id, [FromBody]question updatedQuestion)
        {
            _questionRepos.UpdateQuestion(id, updatedQuestion);
        }

        // DELETE: api/Question/5
        public void Delete(int id)
        {
            _questionRepos.DeleteQuestion(id);
        }
    }
}
