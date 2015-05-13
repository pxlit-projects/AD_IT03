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
    public class QuestionListController : ApiController
    {

        private QuestionListRepository _questionListRepos;

        public QuestionListController() 
        {
            _questionListRepos = new QuestionListRepository();
        }

        // GET: api/QuestionList
        public IEnumerable<questionlist> Get()
        {
            var questionLists = _questionListRepos.GetQuestionLists();
            return questionLists;
        }

        // GET: api/QuestionList/5
        public questionlist Get(int id)
        {
            var questionList = _questionListRepos.GetQuestionListById(id);
            return questionList;
        }

        // POST: api/QuestionList
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
        public void Put(int id, [FromBody]questionlist updatedQuestionlist)
        {
            _questionListRepos.UpdateQuestionList(id, updatedQuestionlist);
        }

        // DELETE: api/QuestionList/5
        public void Delete(int id)
        {
            _questionListRepos.DeleteUser(id);
        }
    }
}
