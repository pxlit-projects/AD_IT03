﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Finah_DomainClasses;
using Finah_Repository;

namespace Finah_BackendServer.Controllers
{
    public class QuestionBackendController : Controller
    {

        private QuestionRepository _questionRepos;

        public QuestionBackendController()
        {
            _questionRepos = new QuestionRepository();
        }

        // GET: QuestionBackend
        public ActionResult Index()
        {
            var questions = _questionRepos.GetQuestions();
            return View(questions);
        }

        // GET: QuestionBackend/Details/5
        public ActionResult Details(int id)
        {
            var questions = _questionRepos.GetQuestionById(id);
            return View(questions);
        }

        // GET: QuestionBackend/DetailsWithThemeAndQuestionlists/5
        public ActionResult DetailsWithThemeAndQuestionlists(int id)
        {
            QuestionThemeQuestionListViewModel qtqlvm = new QuestionThemeQuestionListViewModel();
            var question = _questionRepos.GetQuestionWithThemeAndQuestionlists(id);
            qtqlvm.Question = question;
            qtqlvm.Theme = question.Themes;
            qtqlvm.Questionslists = question.Questionslists;
            return View(qtqlvm);
        }


        // GET: QuestionBackend/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: QuestionBackend/Create
        [HttpPost]
        public ActionResult Create(question newQuestion)
        {
            try
            {
                // TODO: Add insert logic here
                _questionRepos.AddQuestion(newQuestion);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: QuestionBackend/Edit/5
        public ActionResult Edit(int id)
        {
            var questions = _questionRepos.GetQuestionById(id);
            return View(questions);
        }

        // POST: QuestionBackend/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, question updatedQuestion)
        {
            try
            {
                // TODO: Add update logic here
                _questionRepos.UpdateQuestion(id, updatedQuestion);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: QuestionBackend/Delete/5
        public ActionResult Delete(int id)
        {
            var questions = _questionRepos.GetQuestionById(id);
            return View(questions);
        }

        // POST: QuestionBackend/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, question delQuestion)
        {
            try
            {
                // TODO: Add delete logic here
                _questionRepos.DeleteQuestion(id);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
