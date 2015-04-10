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
            //var customer = context.Customers.First(c => c.CustomerId == id); is hetzelfde als eronder
            var questionList = context.questionlist.Find(id);
            return questionList;
        }

        public void UpdateQuestionList(int id, questionlist questionList)
        {
            using (var context = new db_projectEntities())
            {
                var updatedQuestionList = context.questionlist.FirstOrDefault(c => c.id == id);
                //Hier komen de velden die geupdate worden in de stijl als hieronder
                updatedQuestionList.question = questionList.question;
                context.SaveChanges();
            }
        }
    }
}
