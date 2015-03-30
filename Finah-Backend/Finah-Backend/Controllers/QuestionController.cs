using Finah_DomainClasses;
using Finah_Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

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
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/Question
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/Question/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Question/5
        public void Delete(int id)
        {
        }
    }
}
