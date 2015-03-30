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
    }
}
