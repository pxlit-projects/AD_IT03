using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class UserType
    {

        public UserType()
        {

        }

        public int Id { get; set; }
        public String Screenname { get; set; }
        public String Description { get; set; }

        public void SetUserType(int id, String screenname, String description)
        {
            this.Id = id;
            this.Screenname = screenname;
            this.Description = description;
        }


    }
}
