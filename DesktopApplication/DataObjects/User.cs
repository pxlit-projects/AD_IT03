using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class User
    {
        public User()
        {

        }
        
        public int Id { get; set; }
        public String Firstname { get; set; }
        public String Lastname { get; set; }
        public String Screenname { get; set; }
        public String Password { get; set; }
        public String Email { get; set; }
        public UserType Type { get; set; }


        public void SetUser(int id, String firstname, String lastname, String screenname, String password, String email, UserType type)
        {
            this.Id = id;
            this.Firstname = firstname;
            this.Lastname = lastname;
            this.Screenname = screenname;
            this.Password = password;
            this.Email = email;
            this.Type = type;
        }

    }
}
