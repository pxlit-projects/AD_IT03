using Finah_DomainClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class UserTypeRepository
    {
        public List<usertype> GetUserTypes()
        {
            try
            {
                var context = new db_projectEntities();
                var usertypeList = context.usertype.ToList();
                return usertypeList;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public usertype GetUserTypeById(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var usertypeWithId = context.usertype.First(ut => ut.id == id);
                return usertypeWithId;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public usertype GetUsertypeWithUsers(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var usertypeWithUsers = context.usertype.Include("Users").First(ut => ut.id == id);
                return usertypeWithUsers;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public usertype AddUsertype(usertype newUsertype)
        {
            try
            {
                var context = new db_projectEntities();
                if (newUsertype == null)
                {
                    throw new ArgumentNullException("newUsertype");
                }
                context.usertype.Add(newUsertype);
                context.SaveChanges();
                return newUsertype;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public Boolean UpdateUserType(int id, usertype usertype)
        {
            try
            {
                if (usertype == null || id == null)
                {
                    return false;
                }
                else
                {
                    var context = new db_projectEntities();
                    var updatedUserType = context.usertype.First(ut => ut.id == id);
                    updatedUserType.screenname = usertype.screenname;
                    updatedUserType.description = usertype.description;
                    context.SaveChanges();
                    return true;
                }
            }
            catch (Exception)
            {
                return false;   
            }
            
        }

        public Boolean DeleteUsertype(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var usertype = context.usertype.First(ut => ut.id == id);
                context.usertype.Remove(usertype);
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
