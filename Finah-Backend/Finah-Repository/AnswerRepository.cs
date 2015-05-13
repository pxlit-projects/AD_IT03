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

        public answer GetAnswerById(int id)
        {
            var context = new db_projectEntities();
            var answerWithId = context.answer.First(a => a.id == id);
            return answerWithId;
        }

        public answer AddAnswer(answer newAnswer)
        {
            using (var context = new db_projectEntities())
            {
                if (newAnswer == null)
                {
                    throw new ArgumentNullException("newAnswer");
                }
                context.answer.Add(newAnswer);
                context.SaveChanges();
                return newAnswer;
            }
        }

        public void UpdateAnswer(int id, answer answer)
        {
            using (var context = new db_projectEntities())
            {
                var updatedAnswer = context.answer.FirstOrDefault(a => a.id == id);
                updatedAnswer.title = answer.title;
                updatedAnswer.number = answer.number;
                updatedAnswer.choice = answer.choice;
                context.SaveChanges();
            }
        }

        public void DeleteAnswer(int id)
        {
            using (var context = new db_projectEntities())
            {
                var answers = context.answer.Where(a => a.id == id).ToList();
                foreach (var delAnswer in answers)
                {
                    context.answer.Remove(delAnswer);
                }
                context.SaveChanges();
            }
        }
    }
}
