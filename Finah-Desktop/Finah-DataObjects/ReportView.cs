using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class ReportView
    {

        public ReportView()
        {

        }

        public int Id { get; set; }
        public string Theme { get; set; }
        public string Question { get; set; }
        public string AnswerPatient { get; set; }
        public string AnswerCaretaker { get; set; }
        public string HelpNeeded { get; set; }

        public void SetQuestion(int id, string theme, string question, string answerPatient, string answerCaretaker, string helpNeeded)
        {
            this.Id = id;
            this.Theme = theme;
            this.Question = question;
            this.AnswerPatient = answerPatient;
            this.AnswerCaretaker = answerCaretaker;
            this.HelpNeeded = helpNeeded;
        }
    }
}
