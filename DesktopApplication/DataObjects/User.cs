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
        public String Login { get; set; }
        public String Lastname { get; set; }
        public String Password { get; set; }
        public String Email { get; set; }
        public String Straat { get; set; }
        public String Gemeente { get; set; }
        public String Postcode { get; set; }
        public String Geboortedatum { get; set; }
        public UserType Type { get; set; }
        


        public void SetUser(int id, String firstname, String Login, String lastname, String password, String email, String Straat, String Gemeente, String Postcode, String Geboortedatum, UserType type)
        {
            this.Id = id;
            this.Firstname = firstname;
            this.Login = Login;
            this.Lastname = lastname;
            this.Password = password;
            this.Email = email;
            this.Type = type;
            this.Straat = Straat;
            this.Gemeente = Gemeente;
            this.Postcode = Postcode;
            this.Geboortedatum = Geboortedatum;
        }

    }
}
