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
    public class AnswerlistsController : ApiController
    {
        private db_projectEntities db = new db_projectEntities();

        // GET: api/Answerlists
        public IEnumerable<Answerlist> GetAnswerlists1()
        {
            return db.Answerlists1;
        }

        // GET: api/Answerlists/5
        [ResponseType(typeof(Answerlist))]
        public IHttpActionResult GetAnswerlist(int id)
        {
            Answerlist answerlist = db.Answerlists1.Find(id);
            if (answerlist == null)
            {
                return NotFound();
            }

            return Ok(answerlist);
        }

        // PUT: api/Answerlists/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutAnswerlist(int id, Answerlist answerlist)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != answerlist.id)
            {
                return BadRequest();
            }

            db.Entry(answerlist).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AnswerlistExists(id))
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

        // POST: api/Answerlists
        [ResponseType(typeof(Answerlist))]
        public IHttpActionResult PostAnswerlist(Answerlist answerlist)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Answerlists1.Add(answerlist);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = answerlist.id }, answerlist);
        }

        // DELETE: api/Answerlists/5
        [ResponseType(typeof(Answerlist))]
        public IHttpActionResult DeleteAnswerlist(int id)
        {
            Answerlist answerlist = db.Answerlists1.Find(id);
            if (answerlist == null)
            {
                return NotFound();
            }

            db.Answerlists1.Remove(answerlist);
            db.SaveChanges();

            return Ok(answerlist);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool AnswerlistExists(int id)
        {
            return db.Answerlists1.Count(e => e.id == id) > 0;
        }
    }
}