using Finah_DomainClasses;
using Finah_Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Finah_BackendServer.Controllers
{
    public class UsertypeBackendController : Controller
    {

        private UserTypeRepository _usertypeRepos;

        public UsertypeBackendController()
        {
            _usertypeRepos = new UserTypeRepository();
        }

        // GET: UsertypeBackend
        public ActionResult Index()
        {
            var usertypes = _usertypeRepos.GetUserTypes();
            return View(usertypes);
        }

        // GET: UsertypeBackend/Details/5
        public ActionResult Details(int id)
        {
            var usertypes = _usertypeRepos.GetUserTypeById(id);
            return View(usertypes);
        }

        // GET: UsertypeBackend/DetailsWithUsers/5
        public ActionResult DetailsWithUsers(int id)
        {
            UsertypeUsersViewModel utuvm = new UsertypeUsersViewModel();

            var usertype = _usertypeRepos.GetUsertypeWithUsers(id);
            utuvm.UserType = usertype;
            utuvm.Users = usertype.Users;
            return View(utuvm);
        }


        // GET: UsertypeBackend/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: UsertypeBackend/Create
        [HttpPost]
        public ActionResult Create(usertype newUsertype)
        {
            try
            {
                // TODO: Add insert logic here
                _usertypeRepos.AddUsertype(newUsertype);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: UsertypeBackend/Edit/5
        public ActionResult Edit(int id)
        {
            var usertypes = _usertypeRepos.GetUserTypeById(id);
            return View(usertypes);
        }

        // POST: UsertypeBackend/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, usertype updatedUsertype)
        {
            try
            {
                // TODO: Add update logic here
                _usertypeRepos.UpdateUserType(id, updatedUsertype);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: UsertypeBackend/Delete/5
        public ActionResult Delete(int id)
        {
            var usertypes = _usertypeRepos.GetUserTypeById(id);
            return View(usertypes);
        }

        // POST: UsertypeBackend/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, usertype delUsertype)
        {
            try
            {
                // TODO: Add delete logic here
                _usertypeRepos.DeleteUsertype(id);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
