//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace WebAPI
{
    using System;
    using System.Collections.Generic;
    
    public partial class Answerlist
    {
        public int id { get; set; }
        public long list { get; set; }
        public int answer { get; set; }
        public int question { get; set; }
        public int workpoint { get; set; }
        public int user { get; set; }
        public int client { get; set; }
    
        public virtual Answer Answers { get; set; }
        public virtual Question Questions { get; set; }
    }
}