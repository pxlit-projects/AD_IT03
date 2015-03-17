using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class Answer
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public int Number { get; set; }
        public int Choice { get; set; }

        public Answer(int id, string title, int number, int choice)
        {
            this.Id = id;
            this.Title = title;
            this.Number = number;
            this.Choice = choice;
        }
    }
}
