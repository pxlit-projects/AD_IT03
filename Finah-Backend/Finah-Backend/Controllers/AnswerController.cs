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
    public class AnswerController : ApiController
    {

        private AnswerRepository _answerRepos;

        public AnswerController()
        {
            _answerRepos = new AnswerRepository();
        }
        // GET: api/Answer
        public IEnumerable<answer> Get()
        {
            var answers = _answerRepos.GetAnswers();
            return answers;
        }

        // GET: api/Answer/5
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/Answer
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/Answer/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Answer/5
        public void Delete(int id)
        {
        }
    }
}
