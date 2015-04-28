using Finah_DomainClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class AnswerRepository
    {
        public List<answer> GetAnswers()
        {
            var context = new db_projectEntities();
            var answers = context.answer.ToList();
            return answers;
        }

        public IQueryable<answer> GetAnswerById(int id)
        {
            var context = new db_projectEntities();
            //var customer = context.Customers.First(c => c.CustomerId == id); is hetzelfde als eronder
            //var answer = context.answer.Find(id);
            var answer = context.answer.Where(a => a.id == id);
            return answer;
        }

        public void UpdateAnswer(int id, answer answer)
        {
            using (var context = new db_projectEntities())
            {
                var updatedAnswer = context.answer.FirstOrDefault(c => c.id == id);
                //Hier komen de velden die geupdate worden
                updatedAnswer.choice = answer.choice;
                context.SaveChanges();
            }
        }
    }
}
