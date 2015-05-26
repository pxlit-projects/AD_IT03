using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class HashObj
    {
        public HashObj()
        {

        }

        public int Id { get; set; }
        public string Hash { get; set; }
        public int Status { get; set; }
        public int User { get; set; }
        public DateTime Date { get; set; }

        public void SetTheme(int id, string hash, int status, int user, DateTime date)
        {
            this.Id = id;
            this.Hash = hash;
            this.Status = status;
            this.User = user;
            this.Date = date;
        }
    }
}
