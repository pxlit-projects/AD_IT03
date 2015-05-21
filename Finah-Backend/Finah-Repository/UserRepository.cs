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
            try
            {
                var context = new db_projectEntities();
                var userList = context.user.ToList();
                return userList;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public user GetUserById(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var userWithId = context.user.First(u => u.id == id);
                return userWithId;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public user GetUserWithUserTypes(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var userWithUsertype = context.user.Include("Usertypes").First(u => u.id == id);
                return userWithUsertype;
            }
            catch (Exception)
            {

                return null;
            }

        }

        public user AddUser(user newUser)
        {
            try
            {
                var context = new db_projectEntities();
                if (newUser == null)
                {
                    throw new ArgumentNullException("newUser");
                }
                else if (context.user.Any(u => u.login == newUser.login))
                {
                    throw new ArgumentException("That login already exists");
                }
                context.user.Add(newUser);
                context.SaveChanges();
                return newUser;
            }
            catch (ArgumentException)
            {
            }   
            catch (Exception)
            {
                return null;
            }

        }

        public Boolean UpdateUser(int id, user user)
        {
            try
            {
                if (user == null || id == null)
                {
                    return false;
                }
                else
                {
                    var context = new db_projectEntities();
                    var updatedUser = context.user.First(u => u.id == id);
                    updatedUser.login = user.login;
                    updatedUser.firstname = user.firstname;
                    updatedUser.lastname = user.lastname;
                    updatedUser.email = user.email;
                    updatedUser.type = user.type;
                    updatedUser.street = user.street;
                    updatedUser.town = user.town;
                    updatedUser.zipcode = user.zipcode;
                    updatedUser.birthdate = user.birthdate;
                    context.SaveChanges();
                    return true;
                }
            }
            catch (Exception)
            {
                return false;
            }
        }

        public Boolean DeleteUser(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var user = context.user.First(u => u.id == id);
                context.user.Remove(user);
                context.SaveChanges();
                return true;
            }
            catch (Exception)
            {
                return false;
            }

        }
    }
}
