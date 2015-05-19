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
    public class QuestionlistBackendController : Controller
    {
        private QuestionListRepository _questionlistRepos;

        public QuestionlistBackendController()
        {
            _questionlistRepos = new QuestionListRepository();
        }

        // GET: QuestionlistBackend
        public ActionResult Index()
        {
            var questionlists = _questionlistRepos.GetQuestionLists();
            return View(questionlists);
        }

        // GET: QuestionlistBackend/Details/5
        public ActionResult Details(int id)
        {
            var questionlists = _questionlistRepos.GetQuestionListById(id);
            return View(questionlists);
        }

        // GET: QuestionlistBackend/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: QuestionlistBackend/Create
        [HttpPost]
        public ActionResult Create(questionlist newQuestionlist)
        {
            try
            {
                // TODO: Add insert logic here
                _questionlistRepos.AddQuestionlist(newQuestionlist);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: QuestionlistBackend/Edit/5
        public ActionResult Edit(int id)
        {
            var questionlists = _questionlistRepos.GetQuestionListById(id);
            return View(questionlists);
        }

        // POST: QuestionlistBackend/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, questionlist updatedQuestionlist)
        {
            try
            {
                // TODO: Add update logic here
                _questionlistRepos.UpdateQuestionList(id, updatedQuestionlist);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: QuestionlistBackend/Delete/5
        public ActionResult Delete(int id)
        {
            var questionlists = _questionlistRepos.GetQuestionListById(id);
            return View(questionlists);
        }

        // POST: QuestionlistBackend/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, questionlist delQuestionlist)
        {
            try
            {
                // TODO: Add delete logic here
                _questionlistRepos.DeleteQuestionlist(id);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
