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
            var usertypes = context.usertype.ToList();
            return usertypes;
        }
    }
}
