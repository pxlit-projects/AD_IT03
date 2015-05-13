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
        [ResponseType(typeof(answerlist))]
        public async Task<IHttpActionResult> GetQuestionById(int id)
        {
            var answerlist = _answerListRepos.GetAnswerListById(id);
            if (answerlist == null)
            {
                return NotFound();
            }

            return Ok(answerlist);
        }

        // POST: api/AnswerList
        public HttpResponseMessage Post([FromBody]answerlist newAnswerlist)
        {
            if (ModelState.IsValid)
            {
                newAnswerlist = _answerListRepos.AddAnswerlist(newAnswerlist);
                var response = Request.CreateResponse<answerlist>(HttpStatusCode.Created, newAnswerlist);

                string uri = Url.Link("DefaultApi", new { id = newAnswerlist.id });
                response.Headers.Location = new Uri(uri);
                return response;
            }
            else
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
            }
        }

        // PUT: api/AnswerList/5
        public void Put(int id, [FromBody]answerlist updatedAnswerlist)
        {
            _answerListRepos.UpdateAnswerList(id, updatedAnswerlist);
        }

        // DELETE: api/AnswerList/5
        public void Delete(int id)
        {
            _answerListRepos.DeleteAnswerlist(id);
        }
    }
}
