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
            var context = new db_projectEntities();
            var usertypeList = context.usertype.ToList();
            return usertypeList;
        }

        public usertype GetUserTypeById(int id)
        {
            var context = new db_projectEntities();
            var usertypeWithId = context.usertype.Find(id);
            return usertypeWithId;
        }

        public usertype GetUsertypeWithUsers(int id)
        {
            var context = new db_projectEntities();
            var usertypeWithUsers = context.usertype.Include("Users").First(u => u.id == id);
            return usertypeWithUsers;
        }

        public usertype AddUsertype(usertype newUsertype)
        {
            using (var context = new db_projectEntities())
            {
                if (newUsertype == null)
                {
                    throw new ArgumentNullException("newUsertype");
                }
                context.usertype.Add(newUsertype);
                context.SaveChanges();
                return newUsertype;
            }
        }

        public Boolean UpdateUserType(int id, usertype usertype)
        {
            if (usertype == null || id == null)
            {
                return false;
            }
            else
            {
                using (var context = new db_projectEntities())
                {
                    var updatedUserType = context.usertype.FirstOrDefault(ut => ut.id == id);
                    updatedUserType.screenname = usertype.screenname;
                    updatedUserType.description = usertype.description;
                    context.SaveChanges();
                    return true;
                }
            }
        }

        public void DeleteUsertype(int id)
        {
            using (var context = new db_projectEntities())
            {
                var usertypes = context.usertype.Where(ut => ut.id == id).ToList();
                foreach (var delUsertype in usertypes)
                {
                    context.usertype.Remove(delUsertype);
                }
                context.SaveChanges();
            }
        }
    }
}
