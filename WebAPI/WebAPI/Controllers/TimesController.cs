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
    public class TimesController : ApiController
    {
        private db_projectEntities db = new db_projectEntities();

        // GET: api/Times
        public IEnumerable<Time> GetTimes1()
        {
            return db.Times1;
        }

        // GET: api/Times/5
        [ResponseType(typeof(Time))]
        public IHttpActionResult GetTime(int id)
        {
            Time time = db.Times1.Find(id);
            if (time == null)
            {
                return NotFound();
            }

            return Ok(time);
        }

        // PUT: api/Times/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutTime(int id, Time time)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != time.id)
            {
                return BadRequest();
            }

            db.Entry(time).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TimeExists(id))
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

        // POST: api/Times
        [ResponseType(typeof(Time))]
        public IHttpActionResult PostTime(Time time)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Times1.Add(time);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = time.id }, time);
        }

        // DELETE: api/Times/5
        [ResponseType(typeof(Time))]
        public IHttpActionResult DeleteTime(int id)
        {
            Time time = db.Times1.Find(id);
            if (time == null)
            {
                return NotFound();
            }

            db.Times1.Remove(time);
            db.SaveChanges();

            return Ok(time);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool TimeExists(int id)
        {
            return db.Times1.Count(e => e.id == id) > 0;
        }
    }
}