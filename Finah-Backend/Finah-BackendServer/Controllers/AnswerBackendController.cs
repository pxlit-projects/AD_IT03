using Finah_DomainClasses;
using Finah_Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Finah_BackendServer.Controllers
{
    public class AnswerBackendController : Controller
    {

        private AnswerRepository _answerRepos;

        public AnswerBackendController()
        {
            _answerRepos = new AnswerRepository();
        }


        // GET: AnswerBackend
        public ActionResult Index()
        {
            var answers = _answerRepos.GetAnswers();
            return View(answers);
        }

        // GET: AnswerBackend/Details/5
        public ActionResult Details(int id)
        {
            var answers = _answerRepos.GetAnswerById(id);
            return View(answers);
        }

        // GET: AnswerBackend/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: AnswerBackend/Create
        [HttpPost]
        public ActionResult Create(answer newAnswer)
        {
            try
            {
                // TODO: Add insert logic here
                _answerRepos.AddAnswer(newAnswer);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: AnswerBackend/Edit/5
        public ActionResult Edit(int id)
        {
            var answers = _answerRepos.GetAnswerById(id);
            return View(answers);
        }

        // POST: AnswerBackend/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, answer updatedAnswer)
        {
            try
            {
                // TODO: Add update logic here
                _answerRepos.UpdateAnswer(id, updatedAnswer);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: AnswerBackend/Delete/5
        public ActionResult Delete(int id)
        {
            var answers = _answerRepos.GetAnswerById(id);
            return View(answers);
        }

        // POST: AnswerBackend/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, answer delAnswer)
        {
            try
            {
                // TODO: Add delete logic here
                _answerRepos.DeleteAnswer(id);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
