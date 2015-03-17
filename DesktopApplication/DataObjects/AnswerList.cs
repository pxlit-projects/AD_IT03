using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class AnswerList
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public IList<Answer> Answer { get; set; }
        public QuestionList QuestionList { get; set; }
        public User User { get; set; }
        public string Patient { get; set; }

        public AnswerList(int id, string title, IList<Answer> answer, QuestionList questionList, User user, string patient)
        {
            this.Id = id;
            this.Title = title;
            this.Answer = answer;
            this.QuestionList = questionList;
            this.User = user;
            this.Patient = patient;
        }
    }
}
