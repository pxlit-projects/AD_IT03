using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class Question
    {
        public Question()
        {

        }

        public int Id { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public Theme Theme { get; set; }
        public Boolean Choice { get; set; }

        public void SetQuestion(int id, string title, string description, Theme theme, Boolean choice)
        {
            this.Id = id;
            this.Title = title;
            this.Description = description;
            this.Theme = theme;
            this.Choice = choice;
        }
    }
}
