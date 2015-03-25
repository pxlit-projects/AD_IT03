using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using WebAPI;

namespace WebAPI.Controllers
{
    public class QuestionlistsController : ApiController
    {
        private db_projectEntities db = new db_projectEntities();

        // GET: api/Questionlists
        public IEnumerable<Questionlist> GetQuestionlists1()
        {
            return db.Questionlists1;
        }

        // GET: api/Questionlists/5
        [ResponseType(typeof(Questionlist))]
        public IHttpActionResult GetQuestionlist(int id)
        {
            Questionlist questionlist = db.Questionlists1.Find(id);
            if (questionlist == null)
            {
                return NotFound();
            }

            return Ok(questionlist);
        }

        // PUT: api/Questionlists/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutQuestionlist(int id, Questionlist questionlist)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != questionlist.id)
            {
                return BadRequest();
            }

            db.Entry(questionlist).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!QuestionlistExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Questionlists
        [ResponseType(typeof(Questionlist))]
        public IHttpActionResult PostQuestionlist(Questionlist questionlist)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Questionlists1.Add(questionlist);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = questionlist.id }, questionlist);
        }

        // DELETE: api/Questionlists/5
        [ResponseType(typeof(Questionlist))]
        public IHttpActionResult DeleteQuestionlist(int id)
        {
            Questionlist questionlist = db.Questionlists1.Find(id);
            if (questionlist == null)
            {
                return NotFound();
            }

            db.Questionlists1.Remove(questionlist);
            db.SaveChanges();

            return Ok(questionlist);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool QuestionlistExists(int id)
        {
            return db.Questionlists1.Count(e => e.id == id) > 0;
        }
    }
}