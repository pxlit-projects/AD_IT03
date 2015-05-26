using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class AnswerList
    {
        public AnswerList()
        {

        }

        public int Id { get; set; }
        public int List { get; set; }
        public int Answer { get; set; }
        public int Question { get; set; }
        public int Workpoint { get; set; }
        public string Hash { get; set; }
        public DateTime Date { get; set; }
        public int UserType { get; set; }
        public int Time { get; set; }


        public void SetAnswerList(int id, int list, int answer, int question, int workpoint, string hash, DateTime date, int userType, int Time)
        {
            this.Id = id;
            this.List = list;
            this.Answer = answer;
            this.Question = question;
            this.Workpoint = workpoint;
            this.Hash = hash;
            this.Date = date;
            this.UserType = userType;
            this.Time = Time;


        }
    }
}
