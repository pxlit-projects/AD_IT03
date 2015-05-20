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
            try
            {
                var context = new db_projectEntities();
                var answerLists = context.answerlist.ToList();
                return answerLists;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public answerlist GetAnswerListById(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var answerlistWithId = context.answerlist.First(al => al.id == id);
                return answerlistWithId;
            }
            catch (Exception)
            {
                return null;
            }
        }
        public List<answerlist> GetAnswerListByHash(string hash)
        {
            try
            {
                var context = new db_projectEntities();
                var query_whereHash = from al in context.answerlist
                                      where al.hash.Contains(hash)
                                      select al;
                List<answerlist> answerlistsWithHash = new List<answerlist>();
                foreach (var al in query_whereHash)
                {
                    answerlistsWithHash.Add(al);
                }
                return answerlistsWithHash;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public answerlist AddAnswerlist(answerlist newAnswerlist)
        {
            try
            {
                var context = new db_projectEntities();
                if (newAnswerlist == null)
                {
                    throw new ArgumentNullException("newAnswerlist");
                }
                context.answerlist.Add(newAnswerlist);
                context.SaveChanges();
                return newAnswerlist;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public Boolean UpdateAnswerList(int id, answerlist answerList)
        {
            try
            {
                if (answerList == null || id == null)
                {
                    return false;
                }
                else
                {
                    var context = new db_projectEntities();
                    var updatedAnswerList = context.answerlist.First(al => al.id == id);
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
            catch (Exception)
            {
                return false;
            }

        }

        public Boolean DeleteAnswerlist(int id)
        {
            try
            {
                var context = new db_projectEntities();
                var answerlist = context.answerlist.First(al => al.id == id);
                context.answerlist.Remove(answerlist);
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
