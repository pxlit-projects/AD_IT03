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
    public class UsertypesController : ApiController
    {
        private db_projectEntities db = new db_projectEntities();

        // GET: api/Usertypes
        public IEnumerable<Usertype> GetUsertypes1()
        {
            return db.Usertypes1;
        }

        // GET: api/Usertypes/5
        [ResponseType(typeof(Usertype))]
        public IHttpActionResult GetUsertype(int id)
        {
            Usertype usertype = db.Usertypes1.Find(id);
            if (usertype == null)
            {
                return NotFound();
            }

            return Ok(usertype);
        }

        // PUT: api/Usertypes/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutUsertype(int id, Usertype usertype)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != usertype.id)
            {
                return BadRequest();
            }

            db.Entry(usertype).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!UsertypeExists(id))
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

        // POST: api/Usertypes
        [ResponseType(typeof(Usertype))]
        public IHttpActionResult PostUsertype(Usertype usertype)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Usertypes1.Add(usertype);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = usertype.id }, usertype);
        }

        // DELETE: api/Usertypes/5
        [ResponseType(typeof(Usertype))]
        public IHttpActionResult DeleteUsertype(int id)
        {
            Usertype usertype = db.Usertypes1.Find(id);
            if (usertype == null)
            {
                return NotFound();
            }

            db.Usertypes1.Remove(usertype);
            db.SaveChanges();

            return Ok(usertype);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool UsertypeExists(int id)
        {
            return db.Usertypes1.Count(e => e.id == id) > 0;
        }
    }
}