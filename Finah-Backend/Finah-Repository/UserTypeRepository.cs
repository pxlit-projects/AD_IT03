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

        public usertype GetUserTypeById(int id)
        {
            var context = new db_projectEntities();
            //var customer = context.Customers.First(c => c.CustomerId == id); is hetzelfde als eronder
            var usertype = context.usertype.Find(id);
            return usertype;
        }

        public void UpdateUserType(int id, usertype usertype)
        {
            using (var context = new db_projectEntities())
            {
                var updatedUserType = context.usertype.FirstOrDefault(c => c.id == id);
                //Hier komen de velden die geupdate worden in de stijl als hieronder
                updatedUserType.description = usertype.description;
                context.SaveChanges();
            }
        }
    }
}
