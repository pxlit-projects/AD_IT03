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
    }
}
