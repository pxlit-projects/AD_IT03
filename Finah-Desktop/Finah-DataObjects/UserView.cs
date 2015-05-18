using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class UserView
    {
        public UserView()
        {

        }
        
        public int Id { get; set; }
        public String Firstname { get; set; }
        public String Login { get; set; }
        public String Lastname { get; set; }
        public String Type { get; set; }



        public void SetUser(int id, String firstname, String Login, String lastname, String type)
        {
            this.Id = id;
            this.Firstname = firstname;
            this.Login = Login;
            this.Lastname = lastname;
            this.Type = type;
        }
    }
}
