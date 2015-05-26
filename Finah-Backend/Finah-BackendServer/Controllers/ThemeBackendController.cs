using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Finah_DomainClasses;
using Finah_Repository;

namespace Finah_BackendServer.Controllers
{
    public class ThemeBackendController : Controller
    {
        private ThemeRepository _themeRepos;

        public ThemeBackendController()
        {
            _themeRepos = new ThemeRepository();
        }
        // GET: ThemeBackend
        public ActionResult Index()
        {
            var themes = _themeRepos.GetThemes();
            return View(themes);
        }

        // GET: ThemeBackend/Details/5
        public ActionResult Details(int id)
        {
            var themes = _themeRepos.GetThemeById(id);
            return View(themes);
        }

        // GET: ThemeBackend/DetailsWithQuestions/5
        public ActionResult DetailsWithQuestions(int id)
        {
            ThemeQuestionsViewModel tqvm = new ThemeQuestionsViewModel();

            var theme = _themeRepos.GetThemeWithQuestions(id);
            tqvm.Theme = theme;
            tqvm.Questions = theme.Questions;
            return View(tqvm);
        }

        // GET: ThemeBackend/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: ThemeBackend/Create
        [HttpPost]
        public ActionResult Create(theme newTheme)
        {
            try
            {
                // TODO: Add insert logic here
                _themeRepos.AddTheme(newTheme);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: ThemeBackend/Edit/5
        public ActionResult Edit(int id)
        {
            var themes = _themeRepos.GetThemeById(id);
            return View(themes);
        }

        // POST: ThemeBackend/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, theme updatedTheme)
        {
            try
            {
                // TODO: Add update logic here
                _themeRepos.UpdateTheme(id, updatedTheme);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: ThemeBackend/Delete/5
        public ActionResult Delete(int id)
        {
            var themes = _themeRepos.GetThemeById(id);
            return View(themes);
        }

        // POST: ThemeBackend/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, theme delTheme)
        {
            try
            {
                // TODO: Add delete logic here
                _themeRepos.DeleteTheme(id);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
