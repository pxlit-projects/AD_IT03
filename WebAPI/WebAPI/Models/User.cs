using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebAPI.Models
{
    public class User
    {
        [Key]
        public int UserId { get; set; }
        public String Firstname { get; set; }
        public String Login { get; set; }
        public String Lastname { get; set; }
        public String Screenname { get; set; }
        public String Password { get; set; }
        public String Email { get; set; }
        public String Straat { get; set; }
        public String Gemeente { get; set; }
        public String Postcode { get; set; }
        public String Geboortedatum { get; set; }
        //public UserType Type { get; set; }
    }
}