using Finah_DomainClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class QuestionRepository
    {
        public List<question> GetQuestions()
        {
            var context = new db_projectEntities();
            var questions = context.question.ToList();
            return questions;
        }

        public question GetQuestionById(int id)
        {
            var context = new db_projectEntities();
            //var customer = context.Customers.First(c => c.CustomerId == id); is hetzelfde als eronder
            //var question = context.question.Find(id);
            var questionWithId = context.question.First(q => q.id == id);
            return questionWithId;
        }

        public void UpdateQuestion(int id, question question)
        {
            using (var context = new db_projectEntities())
            {
                var updatedQuestion = context.question.FirstOrDefault(c => c.id == id);
                //Hier komen de velden die geupdate worden in de stijl als hieronder
                updatedQuestion.choice = question.choice;
                context.SaveChanges();
            }
        }
    }
}
