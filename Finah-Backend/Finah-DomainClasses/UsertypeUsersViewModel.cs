using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_DomainClasses
{
    public class UsertypeUsersViewModel
    {
        public usertype UserType { get; set; }
        public virtual ICollection<user> Users { get; set; }
    }
}
