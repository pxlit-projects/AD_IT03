using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class QuestionList
    {
        private IList<Question> questionsList;


        public QuestionList()
        {

        }

        public int Id { get; set; }
        public User User { get; set; }
        public IList<Question> Questions { get; set; }
        public String Description { get; set; }

        public IList<Question> GetQuestionList()
        {
            return questionsList;
        }

        public void AddQuestion(Question question)
        {
            questionsList.Add(question);
        }

        public void AddQuestions(IList<Question> questions)
        {
            foreach (Question quest in questions)
            {
                questionsList.Add(quest);
            }
        }

        public void SetQuestionList(int id, IList<Question> questions, User user, string description)
        {
            this.Id = id;
            this.Questions = questions;
            this.User = user;
            this.Description = description;
        }
    }
}
