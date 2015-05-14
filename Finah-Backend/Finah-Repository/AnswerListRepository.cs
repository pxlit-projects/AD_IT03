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
            var answerlistWithId = context.answerlist.First(al => al.id == id);
            return answerlistWithId;
        }

        public answerlist AddAnswerlist(answerlist newAnswerlist)
        {
            using (var context = new db_projectEntities())
            {
                if (newAnswerlist == null)
                {
                    throw new ArgumentNullException("newAnswerlist");
                }
                context.answerlist.Add(newAnswerlist);
                context.SaveChanges();
                return newAnswerlist;
            }
        }

        public Boolean UpdateAnswerList(int id, answerlist answerList)
        {
            if (answerList == null || id == null)
            {
                return false;
            }
            else
            {
                using (var context = new db_projectEntities())
                {
                    var updatedAnswerList = context.answerlist.FirstOrDefault(al => al.id == id);
                    updatedAnswerList.list = answerList.list;
                    updatedAnswerList.answer = answerList.answer;
                    updatedAnswerList.question = answerList.answer;
                    updatedAnswerList.workpoint = answerList.workpoint;
                    updatedAnswerList.hash = answerList.hash;
                    updatedAnswerList.date = answerList.date;
                    updatedAnswerList.usertype = answerList.usertype;
                    updatedAnswerList.time = answerList.time;
                    context.SaveChanges();
                    return true;
                }
            }
        }

        public void DeleteAnswerlist(int id)
        {
            using (var context = new db_projectEntities())
            {
                var answerlists = context.answerlist.Where(a => a.id == id).ToList();
                foreach (var delAnswerlist in answerlists)
                {
                    context.answerlist.Remove(delAnswerlist);
                }
                context.SaveChanges();
            }
        }
    }
}
