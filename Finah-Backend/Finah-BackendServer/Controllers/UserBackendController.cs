using Finah_DomainClasses;
using Finah_Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace Finah_BackendServer.Controllers
{
    public class UserBackendController : Controller
    {

        private UserRepository _userRepos;

        public UserBackendController()
        {
            _userRepos = new UserRepository();
        }
        // GET: UserBackend
        public ActionResult Index()
        {
            var users = _userRepos.GetUsers();
            return View(users);
        }

        // GET: UserBackend/Details/5
        public ActionResult Details(int id)
        {
            var users = _userRepos.GetUserById(id);
            return View(users);
        }

        // GET: UserBackend/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: UserBackend/Create
        [HttpPost]
        public ActionResult Create(user newUser)
        {
            try
            {
                // TODO: Add insert logic here
                newUser.password = CalculateMd5Hash(newUser.password);
                _userRepos.AddUser(newUser);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: UserBackend/Edit/5
        public ActionResult Edit(int id)
        {
            var users = _userRepos.GetUserById(id);
            return View(users);
        }

        // POST: UserBackend/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, user updatedUser)
        {
            try
            {
                // TODO: Add update logic here
                _userRepos.UpdateUser(id, updatedUser);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: UserBackend/Delete/5
        public ActionResult Delete(int id)
        {
            var users = _userRepos.GetUserById(id);
            return View(users);
        }

        // POST: UserBackend/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here
                _userRepos.DeleteUser(id);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
        private static string CalculateMd5Hash(string input)
        {
            // step 1, calculate MD5 hash from input
            MD5 md5 = System.Security.Cryptography.MD5.Create();
            byte[] inputBytes = System.Text.Encoding.ASCII.GetBytes(input);
            byte[] hash = md5.ComputeHash(inputBytes);

            // step 2, convert byte array to hex string
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.Length; i++)
            {
                sb.Append(hash[i].ToString("X2"));
            }
            return sb.ToString();
        }
    }
}
