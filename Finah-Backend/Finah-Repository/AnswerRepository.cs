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
            try
            {
                var context = new db_projectEntities();
                var answers = context.answer.ToList();
                return answers;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public answer GetAnswerById(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var answerWithId = context.answer.First(a => a.id == id);
                return answerWithId;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public answer AddAnswer(answer newAnswer)
        {
            try
            {
                var context = new db_projectEntities();
                if (newAnswer == null)
                {
                    throw new ArgumentNullException("newAnswer");
                }
                context.answer.Add(newAnswer);
                context.SaveChanges();
                return newAnswer;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public Boolean UpdateAnswer(int id, answer answer)
        {
            try
            {
                if (answer == null || id == null)
                {
                    return false;
                }
                else
                {
                    var context = new db_projectEntities();

                    var updatedAnswer = context.answer.First(a => a.id == id);
                    updatedAnswer.title = answer.title;
                    updatedAnswer.number = answer.number;
                    updatedAnswer.choice = answer.choice;
                    context.SaveChanges();
                    return true;
                }
            }
            catch (Exception)
            {
                return false;
            }
        }

        public Boolean DeleteAnswer(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var answer = context.answer.First(a => a.id == id);
                context.answer.Remove(answer);
                context.SaveChanges();
                return true;
            }
            catch (Exception)
            {
                return false;
            }
        }
    }
}
