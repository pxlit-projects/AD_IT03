using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class Rapport
    {
        public Rapport()
        {

        }

        public int Id { get; set; }
        public QuestionList QuestionList { get; set; }
        public AnswerList AnswerList { get; set; }
        public DateTime DateTime { get; set; }
        
        public void SetRapport(int id, QuestionList questionList, AnswerList answerList, DateTime dateTime)
        {
            this.Id = id;
            this.QuestionList = questionList;
            this.AnswerList = answerList;
            this.DateTime = dateTime;
        }
    }
}
