using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Finah_DomainClasses;
using Finah_Repository;
using System.Web.Http.Description;
using System.Threading.Tasks;

namespace WebAPI.Controllers
{
    public class HashesController : ApiController
    {

        private HashesRepository _hashRepos;

        public HashesController()
        {
            _hashRepos = new HashesRepository();
        }

        // GET: api/Hashes
        public IEnumerable<hashes> Get()
        {
            var hashes = _hashRepos.GetHashes();
            return hashes;
        }

        // GET: api/Hashes/5
        [ResponseType(typeof(hashes))]
        public async Task<IHttpActionResult> GetHashesById(int id)
        {
            var hash = _hashRepos.GetHashesById(id);

            if (hash == null)
            {
                return NotFound();
            }

            return Ok(hash);
        }

        // POST: api/Hashes
        public HttpResponseMessage Post([FromBody]hashes newHash)
        {

            if (ModelState.IsValid)
            {
                newHash = _hashRepos.AddHashes(newHash);
                var response = Request.CreateResponse<hashes>(HttpStatusCode.Created, newHash);

                string uri = Url.Link("DefaultApi", new { id = newHash.id });
                response.Headers.Location = new Uri(uri);
                return response;
            }
            else
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
            }
        }

        // PUT: api/Hashes/5
        public HttpResponseMessage Put(int id, [FromBody]hashes updatedHash)
        {
            if (!_hashRepos.UpdateHashes(id, updatedHash))
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            else
            {
                var response = Request.CreateResponse<hashes>(HttpStatusCode.OK, updatedHash);
                return response;
            }
        }

        // DELETE: api/Hashes/5
        public HttpResponseMessage Delete(int id)
        {
            hashes delHash = _hashRepos.GetHashesById(id);
            if (delHash == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            _hashRepos.Deletehashes(id);
            var response = Request.CreateResponse<hashes>(HttpStatusCode.OK, delHash);
            return response;
        }
    }
}
