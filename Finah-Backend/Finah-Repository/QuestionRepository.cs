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
            var questionWithId = context.question.First(q => q.id == id);
            return questionWithId;
        }

        public question GetQuestionWithThemeAndQuestionlists(int id)
        {
            var context = new db_projectEntities();
            var questionWithThemeAndQuestionlists = context.question
                .Include("Themes")
                .Include("Questionslists")
                .First(u => u.id == id);
            return questionWithThemeAndQuestionlists;
        }

        public question AddQuestion(question newQuestion)
        {
            using (var context = new db_projectEntities())
            {
                if (newQuestion == null)
                {
                    throw new ArgumentNullException("newQuestion");
                }
                context.question.Add(newQuestion);
                context.SaveChanges();
                return newQuestion;
            }
        }

        public Boolean UpdateQuestion(int id, question question)
        {
            if (question == null || id == null)
            {
                return false;
            }
            else
            {
                using (var context = new db_projectEntities())
                {
                    var updatedQuestion = context.question.FirstOrDefault(q => q.id == id);
                    updatedQuestion.title = question.title;
                    updatedQuestion.description = question.description;
                    updatedQuestion.theme = question.theme;
                    updatedQuestion.choice = question.choice;
                    context.SaveChanges();
                    return true;
                }
            }
        }

        public void DeleteQuestion(int id)
        {
            using (var context = new db_projectEntities())
            {
                var questions = context.question.Where(q => q.id == id).ToList();
                foreach (var question in questions)
                {
                    context.question.Remove(question);
                }
                context.SaveChanges();
            }
        }
    }
}
