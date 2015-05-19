using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Finah_DomainClasses;
using Finah_Repository;

namespace Finah_BackendServer.Controllers
{
    [Authorize]
    public class AnswerlistBackendController : Controller
    {
        private AnswerListRepository _answerlistRepos;

        public AnswerlistBackendController()
        {
            _answerlistRepos = new AnswerListRepository();
        }

        // GET: AnswerlistBackend
        public ActionResult Index()
        {
            var answerlists = _answerlistRepos.GetAnswerLists();
            return View(answerlists);
        }

        // GET: AnswerlistBackend/Details/5
        public ActionResult Details(int id)
        {
            var answerlists = _answerlistRepos.GetAnswerListById(id);
            return View(answerlists);
        }

        // GET: AnswerlistBackend/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: AnswerlistBackend/Create
        [HttpPost]
        public ActionResult Create(answerlist newAnswerlist)
        {
            try
            {
                // TODO: Add insert logic here
                _answerlistRepos.AddAnswerlist(newAnswerlist);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: AnswerlistBackend/Edit/5
        public ActionResult Edit(int id)
        {
            var answerlists = _answerlistRepos.GetAnswerListById(id);
            return View(answerlists);
        }

        // POST: AnswerlistBackend/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, answerlist updatedAnswerlist)
        {
            try
            {
                // TODO: Add update logic here
                _answerlistRepos.UpdateAnswerList(id, updatedAnswerlist);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: AnswerlistBackend/Delete/5
        public ActionResult Delete(int id)
        {
            var answerlists = _answerlistRepos.GetAnswerListById(id);
            return View(answerlists);
        }

        // POST: AnswerlistBackend/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, answerlist delAnswerlist)
        {
            try
            {
                // TODO: Add delete logic here
                _answerlistRepos.DeleteAnswerlist(id);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
