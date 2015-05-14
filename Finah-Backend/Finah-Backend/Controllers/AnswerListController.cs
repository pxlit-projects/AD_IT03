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
        /// <summary>
        /// Get all answerlists
        /// </summary>
        /// <returns>Returns an IEnumerable of answerlist objects</returns>
        public IEnumerable<answerlist> Get()
        {
            var answerlists = _answerListRepos.GetAnswerLists();
            return answerlists;
        }

        // GET: api/AnswerList/5
        /// <summary>
        /// Get an answerlist by it's id
        /// </summary>
        /// <param name="id">The id of an answerlist</param>
        /// <returns>Http response 200 OK or 404 Not found</returns>
        [ResponseType(typeof(answerlist))]
        public async Task<IHttpActionResult> GetAnswerlistById(int id)
        {
            var answerlist = _answerListRepos.GetAnswerListById(id);
            if (answerlist == null)
            {
                return NotFound();
            }

            return Ok(answerlist);
        }

        // POST: api/AnswerList
        /// <summary>
        /// Add a new answerlist
        /// </summary>
        /// <param name="newAnswerlist">The new answerlist object</param>
        /// <returns>Http response 201 Created or 400 Bad Request</returns>
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
        /// <summary>
        /// Update an existing answerlist
        /// </summary>
        /// <param name="id">The id of an answerlist</param>
        /// <param name="updatedAnswerlist">The updated answerlist object</param>
        /// <returns>Http response 200 Ok or 404 Not found</returns>
        public HttpResponseMessage Put(int id, [FromBody]answerlist updatedAnswerlist)
        {
            if (!_answerListRepos.UpdateAnswerList(id, updatedAnswerlist))
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            else
            {
                var response = Request.CreateResponse<answerlist>(HttpStatusCode.OK, updatedAnswerlist);
                return response;
            }
        }

        // DELETE: api/AnswerList/5
        /// <summary>
        /// Delete an answerlist
        /// </summary>
        /// <param name="id">The id of an answerlist</param>
        /// <returns>Http response 200 Ok or 404 Not found</returns>
        public HttpResponseMessage Delete(int id)
        {
            answerlist delAnswerlist = _answerListRepos.GetAnswerListById(id);
            if (delAnswerlist == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            _answerListRepos.DeleteAnswerlist(id);
            var response = Request.CreateResponse<answerlist>(HttpStatusCode.OK, delAnswerlist);
            return response;
        }
    }
}
