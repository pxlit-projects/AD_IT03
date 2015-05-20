using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_DomainClasses
{
    public class CreateUserWithUsertypesViewModel
    {
        public user User { get; set; }
        public virtual List<usertype> Usertypes { get; set; }
    }
}
