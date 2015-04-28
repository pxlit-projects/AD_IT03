using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Finah_DomainClasses;

namespace Finah_Repository
{
    public class UserRepository
    {
        public List<user> GetUsers()
        {
            var context = new db_projectEntities();
            var userList = context.user.ToList();
            return userList;
        }

        public void AddUser(user newUser)
        {
            using (var context = new db_projectEntities())
            {
                context.user.Add(newUser);
                context.SaveChanges();
            }
        }

        public user GetUserById(int id)
        {
            var context = new db_projectEntities();
            var user = context.user.First(c => c.id == id); //is hetzelfde als eronder
            //var user = context.user.Find(id);
            //var user = context.user.Where(u => u.id == id);
            return user;
        }

        public void UpdateUser(int id, user user)
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
