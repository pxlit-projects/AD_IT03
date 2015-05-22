using Finah_DomainClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class QuestionListRepository
    {
        public List<questionlist> GetQuestionLists()
        {
            try
            {
                var context = new db_projectEntities();
                var questionLists = context.questionlist.ToList();
                return questionLists;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }

        }

        public questionlist GetQuestionListById(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var questionListWithId = context.questionlist.First(ql => ql.id == id);
                return questionListWithId;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public questionlist AddQuestionlist(questionlist newQuestionlist)
        {
            try
            {
                var context = new db_projectEntities();
                if (newQuestionlist == null)
                {
                    throw new ArgumentNullException("newQuestionlist");
                }
                context.questionlist.Add(newQuestionlist);
                context.SaveChanges();
                return newQuestionlist;
            }
            catch (Exception)
            {
                return null;
            }
           
        }

        public Boolean UpdateQuestionList(int id, questionlist questionList)
        {
            try
            {
                if (questionList == null || id == null)
                {
                    return false;
                }
                else
                {
                    var context = new db_projectEntities();

                    var updatedQuestionList = context.questionlist.First(ql => ql.id == id);
                    updatedQuestionList.list = questionList.list;
                    updatedQuestionList.question = questionList.question;
                    updatedQuestionList.user = questionList.user;
                    context.SaveChanges();
                    return true;
                }
            }
            catch (Exception)
            {
                return false;
            }
            
        }

        public Boolean DeleteQuestionlist(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var questionlist = context.questionlist.First(ql => ql.id == id);
                context.questionlist.Remove(questionlist);
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
