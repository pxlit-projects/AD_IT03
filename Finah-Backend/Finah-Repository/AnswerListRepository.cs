using Finah_DomainClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class AnswerListRepository
    {
        public List<answerlist> GetAnswerLists()
        {
            var context = new db_projectEntities();
            var answerLists = context.answerlist.ToList();
            return answerLists;
        }

        public answerlist GetAnswerListById(int id)
        {
            var context = new db_projectEntities();
            //var customer = context.Customers.First(c => c.CustomerId == id); is hetzelfde als eronder
            var answerList = context.answerlist.Find(id);
            return answerList;
        }

        public void UpdateAnswerList(int id, answerlist answerList)
        {
            using (var context = new db_projectEntities())
            {
                var updatedAnswerList = context.answerlist.FirstOrDefault(c => c.id == id);
                //Hier komen de velden die geupdate worden
                //updatedAnswerList.answer = answerList.answer;
                context.SaveChanges();
            }
        }
    }
}
