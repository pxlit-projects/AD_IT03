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
            var context = new db_projectEntities();
            var questionLists = context.questionlist.ToList();
            return questionLists;
        }

        public questionlist GetQuestionListById(int id)
        {
            var context = new db_projectEntities();
            var questionListWithId = context.questionlist.First(ql => ql.id == id);
            return questionListWithId;
        }

        public questionlist AddQuestionlist(questionlist newQuestionlist)
        {
            using (var context = new db_projectEntities())
            {
                if (newQuestionlist == null)
                {
                    throw new ArgumentNullException("newQuestionlist");
                }
                context.questionlist.Add(newQuestionlist);
                context.SaveChanges();
                return newQuestionlist;
            }
        }

        public void UpdateQuestionList(int id, questionlist questionList)
        {
            using (var context = new db_projectEntities())
            {
                var updatedQuestionList = context.questionlist.FirstOrDefault(c => c.id == id);
                updatedQuestionList.list = questionList.list;
                updatedQuestionList.question = questionList.question;
                updatedQuestionList.user = questionList.user;
                context.SaveChanges();
            }
        }

        public void DeleteUser(int id)
        {
            using (var context = new db_projectEntities())
            {
                var questionlists = context.questionlist.Where(ql => ql.id == id).ToList();
                foreach (var delQuestionlist in questionlists)
                {
                    context.questionlist.Remove(delQuestionlist);
                }
                context.SaveChanges();
            }
        }
    }
}
