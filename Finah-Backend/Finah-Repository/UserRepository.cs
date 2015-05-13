using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Finah_DomainClasses;
using System.Data.Entity.Validation;

namespace Finah_Repository
{
    public class UserRepository
    {
        public IEnumerable<user> GetUsers()
        {
            var context = new db_projectEntities();
            var userList = context.user.ToList();
            return userList;
        }

        public user GetUserById(int id)
        {
            var context = new db_projectEntities();
            var userWithId = context.user.First(u => u.id == id);
            return userWithId;
        }

        public user AddUser(user newUser)
        {
            using (var context = new db_projectEntities())
            {
                if (newUser == null)
                {
                    throw new ArgumentNullException("newUser");
                }
                context.user.Add(newUser);
                context.SaveChanges();
                return newUser;
            }
        }

        public Boolean UpdateUser(int id, user user)
        {
            if (user == null || id == null)
            {
                return false;
            }
            else
            {
                using (var context = new db_projectEntities())
                {
                    var newUser = context.user.FirstOrDefault(c => c.id == id);
                    newUser.login = user.login;
                    newUser.firstname = user.firstname;
                    newUser.lastname = user.lastname;
                    newUser.password = user.password;
                    newUser.email = user.email;
                    newUser.type = user.type;
                    newUser.street = user.street;
                    newUser.town = user.town;
                    newUser.zipcode = user.zipcode;
                    newUser.birthdate = user.birthdate;
                    context.SaveChanges();
                    return true;
                }
            }
        }

        public void DeleteUser(int id)
        {
            using (var context = new db_projectEntities())
            {
                var users = context.user.Where(u => u.id == id).ToList();
                foreach (var user in users)
                {
                    context.user.Remove(user);
                }
                context.SaveChanges();
            }
        }
    }
}
