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
    public class AnswerListController : ApiController
    {
        private AnswerListRepository _answerListRepos;

        public AnswerListController()
        {
            _answerListRepos = new AnswerListRepository();
        }

        // GET: api/AnswerList
        public IEnumerable<answerlist> Get()
        {
            var answerlists = _answerListRepos.GetAnswerLists();
            return answerlists;
        }

        // GET: api/AnswerList/5
        public answerlist Get(int id)
        {
            var answerlist = _answerListRepos.GetAnswerListById(id);
            return answerlist;
        }

        // POST: api/AnswerList
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/AnswerList/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/AnswerList/5
        public void Delete(int id)
        {
        }
    }
}
