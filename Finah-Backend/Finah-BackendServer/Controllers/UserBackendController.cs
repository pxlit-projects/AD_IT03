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
        private UserTypeRepository _usertypeRepos;

        public UserBackendController()
        {
            _userRepos = new UserRepository();
            _usertypeRepos = new UserTypeRepository();
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

        // GET: UserBackend/DetailsWithUsertype/5
        public ActionResult DetailsWithUsertype(int id)
        {
            UserUsertypesViewModel uutvm = new UserUsertypesViewModel();

            var user = _userRepos.GetUserWithUserTypes(id);
            uutvm.User = user;
            uutvm.Usertypes = user.Usertypes;
            return View(uutvm);
        }

        // GET: UserBackend/Create
        public ActionResult Create()
        {
            CreateUserWithUsertypesViewModel cuwut = new CreateUserWithUsertypesViewModel();
            var user = new user();
            var usertypes = _usertypeRepos.GetUserTypes();
            cuwut.User = user;
            cuwut.Usertypes = usertypes;
            return View(cuwut);
        }

        // POST: UserBackend/Create
        [HttpPost]
        public ActionResult Create(CreateUserWithUsertypesViewModel newUserWithUsertypes)
        {
            try
            {
                // TODO: Add insert logic here
                user addedUser = newUserWithUsertypes.User;
                addedUser.password = CalculateMd5Hash(addedUser.password);
                addedUser = _userRepos.AddUser(addedUser);
                if (addedUser.login == "duplicateUserFound")
                {
                    throw new ArgumentException("That login already exists");
                }
                return RedirectToAction("Index");
            }
            catch (ArgumentException ex)
            {
                TempData["alertMessage"] = "<script>alert('" + ex.Message + "');</script>";
                return RedirectToAction("Create");
            }
            catch (Exception ex)
            {
                TempData["alertMessage"] = "<script>alert('" + ex.Message + "');</script>";
                return RedirectToAction("Create");
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
        public ActionResult Delete(int id, user delUser)
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
                sb.Append(hash[i].ToString("x2"));
            }
            return sb.ToString();
        }

    }
}
