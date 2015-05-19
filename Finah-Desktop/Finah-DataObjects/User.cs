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
        public String Street { get; set; }
        public String Town { get; set; }
        public String Zipcode { get; set; }
        public String Birthdate { get; set; }
        public int Type { get; set; }



        public void SetUser(int id, String firstname, String login, String lastname, String password, String email,
            String street, String town, String zipcode, String birthdate, int type)
        {
            this.Id = id;
            this.Firstname = firstname;
            this.Login = login;
            this.Lastname = lastname;
            this.Password = password;
            this.Email = email;
            this.Type = type;
            this.Street = street;
            this.Town = town;
            this.Zipcode = zipcode;
            this.Birthdate = birthdate;
        }

    }
}
