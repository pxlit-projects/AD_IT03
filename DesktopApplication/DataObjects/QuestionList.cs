using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class QuestionList
    {



        public QuestionList()
        {

        }

        public int Id { get; set; }
        public User CurrentUser { get; set; }
        public String Description { get; set; }
    }
}
