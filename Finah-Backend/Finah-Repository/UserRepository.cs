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

        public user GetUserById(int id)
        {
            var context = new db_projectEntities();
            //var customer = context.Customers.First(c => c.CustomerId == id); is hetzelfde als eronder
            var user = context.user.Find(id);
            return user;
        }

        public void UpdateUser(int id, user user)
        {
            using (var context = new db_projectEntities())
            {
                var newUser = context.user.FirstOrDefault(c => c.id == id);
                newUser.firstname = user.firstname;
                newUser.lastname = user.lastname;
                newUser.birthdate = user.birthdate;
                context.SaveChanges();
            }
        }
    }
}
