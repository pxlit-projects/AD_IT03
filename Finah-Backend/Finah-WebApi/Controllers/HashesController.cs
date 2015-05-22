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
            if (hashes != null)
            {
                return hashes;
            }
            else
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
        }

        // GET: api/Hashes/5
        public HttpResponseMessage GetHashesById(int id)
        {
            if (Validator.IsPositive(id))
            {
                var hash = _hashRepos.GetHashesById(id);

                if (hash == null)
                {
                    throw new HttpResponseException(HttpStatusCode.NotFound);
                }
                var response = Request.CreateResponse<hashes>(HttpStatusCode.OK, hash);
                return response;
            }
            else
            {
                throw new HttpResponseException(HttpStatusCode.Forbidden);
            }
        }

        // POST: api/Hashes
        public HttpResponseMessage Post([FromBody]hashes newHash)
        {
            if (ModelState.IsValid)
            {
                newHash.date = DateTime.UtcNow.AddHours(2);
                newHash = _hashRepos.AddHashes(newHash);
                if (newHash != null)
                {
                    var response = Request.CreateResponse<hashes>(HttpStatusCode.Created, newHash);
                    return response;
                }
                else
                {
                    throw new HttpResponseException(HttpStatusCode.BadRequest);
                }
            }
            else
            {
                return Request.CreateErrorResponse(HttpStatusCode.BadRequest, ModelState);
            }
        }

        // PUT: api/Hashes/5
        public HttpResponseMessage Put(int id, [FromBody]hashes updatedHash)
        {
            if (Validator.IsPositive(id))
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
            else
            {
                throw new HttpResponseException(HttpStatusCode.Forbidden);
            }
        }

        // DELETE: api/Hashes/5
        public HttpResponseMessage Delete(int id)
        {
            if (Validator.IsPositive(id))
            {
                hashes delHash = _hashRepos.GetHashesById(id);
                if (delHash != null)
                {
                    if (!_hashRepos.Deletehashes(id))
                    {
                        throw new HttpResponseException(HttpStatusCode.NotFound);
                    }
                    else
                    {
                        var response = Request.CreateResponse<hashes>(HttpStatusCode.OK, delHash);
                        return response;
                    }
                }
                else
                {
                    throw new HttpResponseException(HttpStatusCode.NotFound);
                }
            }
            else
            {
                throw new HttpResponseException(HttpStatusCode.Forbidden);
            }            
        }
    }
}
