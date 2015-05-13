//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Finah_DomainClasses
{
    using Newtonsoft.Json;
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    
    public partial class question
    {
        public question()
        {
            this.Answerlists = new HashSet<answerlist>();
            this.Questionslists = new HashSet<questionlist>();
        }

        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int id { get; set; }
        public string title { get; set; }
        public string description { get; set; }
        public int theme { get; set; }
        public int choice { get; set; }
        [JsonIgnore]
        public virtual ICollection<answerlist> Answerlists { get; set; }
        [JsonIgnore]
        public virtual theme Themes { get; set; }
        [JsonIgnore]
        public virtual ICollection<questionlist> Questionslists { get; set; }
    }
}
