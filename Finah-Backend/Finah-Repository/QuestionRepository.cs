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
            try
            {
                var context = new db_projectEntities();
                var questions = context.question.ToList();
                return questions;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public question GetQuestionById(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var questionWithId = context.question.First(q => q.id == id);
                return questionWithId;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public question GetQuestionWithThemeAndQuestionlists(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var questionWithThemeAndQuestionlists = context.question
                    .Include("Themes")
                    .Include("Questionslists")
                    .First(u => u.id == id);
                return questionWithThemeAndQuestionlists;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public question AddQuestion(question newQuestion)
        {
            try
            {
                var context = new db_projectEntities();
                if (newQuestion == null)
                {
                    throw new ArgumentNullException("newQuestion");
                }
                context.question.Add(newQuestion);
                context.SaveChanges();
                return newQuestion;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public Boolean UpdateQuestion(int id, question question)
        {
            try
            {
                if (question == null || id == null)
                {
                    return false;
                }
                else
                {
                    var context = new db_projectEntities();
                    var updatedQuestion = context.question.First(q => q.id == id);
                    updatedQuestion.title = question.title;
                    updatedQuestion.description = question.description;
                    updatedQuestion.theme = question.theme;
                    updatedQuestion.choice = question.choice;
                    context.SaveChanges();
                    return true;
                }
            }
            catch (Exception)
            {
                return false;
            }

        }

        public Boolean DeleteQuestion(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var question = context.question.First(q => q.id == id);
                var answerlists = context.answerlist.ToList();
                var questionlists = context.questionlist.ToList();
                for (int i = 0; i < answerlists.Count; i++)
                {
                    if (answerlists[i].question == question.id)
                    {
                        context.answerlist.Remove(answerlists[i]);
                    }
                }
                for (int i = 0; i < questionlists.Count; i++)
                {
                    if (questionlists[i].question == question.id)
                    {
                        context.questionlist.Remove(questionlists[i]);
                    }
                }
                context.question.Remove(question);
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
