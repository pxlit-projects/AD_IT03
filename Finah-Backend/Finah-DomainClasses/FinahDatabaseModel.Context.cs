﻿//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Finah_DomainClasses
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class db_projectEntities : DbContext
    {
        public db_projectEntities()
            : base("name=db_projectEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public DbSet<answer> answer { get; set; }
        public DbSet<answerlist> answerlist { get; set; }
        public DbSet<question> question { get; set; }
        public DbSet<questionlist> questionlist { get; set; }
        public DbSet<theme> theme { get; set; }
        public DbSet<time> time { get; set; }
        public DbSet<user> user { get; set; }
        public DbSet<usertype> usertype { get; set; }
    }
}