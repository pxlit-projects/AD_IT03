using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataObjects
{
    public class Theme
    {
        public Theme()
        {

        }

        public int Id { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }

        public void SetTheme(int id, string title, string description)
        {
            this.Id = id;
            this.Title = title;
            this.Description = description;
        }
    }
}
