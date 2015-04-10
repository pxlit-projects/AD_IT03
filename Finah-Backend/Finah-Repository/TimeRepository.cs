using Finah_DomainClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Finah_Repository
{
    public class TimeRepository
    {
        public List<time> GetTimes()
        {
            var context = new db_projectEntities();
            var times = context.time.ToList();
            return times;
        }

        public time GetTimeById(int id)
        {
            var context = new db_projectEntities();
            //var customer = context.Customers.First(c => c.CustomerId == id); is hetzelfde als eronder
            var time = context.time.Find(id);
            return time;
        }

        public void UpdateTime(int id, time time)
        {
            using (var context = new db_projectEntities())
            {
                var updatedTime = context.time.FirstOrDefault(c => c.id == id);
                //Hier komen de velden die geupdate worden in de stijl als hieronder
                updatedTime.datetime = time.datetime;
                context.SaveChanges();
            }
        }
    }
}
