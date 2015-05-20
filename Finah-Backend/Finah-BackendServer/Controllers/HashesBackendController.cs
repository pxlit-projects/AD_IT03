using Finah_DomainClasses;
using Finah_Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Finah_BackendServer.Controllers
{
    public class HashesBackendController : Controller
    {
        private HashesRepository _hashesRepos;

        public HashesBackendController()
        {
            _hashesRepos = new HashesRepository();
        }

        // GET: HashesBackend
        public ActionResult Index()
        {
            var hashes = _hashesRepos.GetHashes();
            return View(hashes);
        }

        // GET: HashesBackend/Details/5
        public ActionResult Details(int id)
        {
            var hashes = _hashesRepos.GetHashesById(id);
            return View(hashes);
        }

        // GET: HashesBackend/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: HashesBackend/Create
        [HttpPost]
        public ActionResult Create(hashes newHashes)
        {
            try
            {
                // TODO: Add insert logic here
                _hashesRepos.AddHashes(newHashes);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: HashesBackend/Edit/5
        public ActionResult Edit(int id)
        {
            var hashes = _hashesRepos.GetHashesById(id);
            return View(hashes);
        }

        // POST: HashesBackend/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, hashes updatedHashes)
        {
            try
            {
                // TODO: Add update logic here
                _hashesRepos.UpdateHashes(id, updatedHashes);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: HashesBackend/Delete/5
        public ActionResult Delete(int id)
        {
            var hashes = _hashesRepos.GetHashesById(id);
            return View(hashes);
        }

        // POST: HashesBackend/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, hashes delHashes)
        {
            try
            {
                // TODO: Add delete logic here
                _hashesRepos.Deletehashes(id);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
