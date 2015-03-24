using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace WebAPI.Models
{
    public class ProjectDB : DbContext
    {
        public DbSet<User> Users { get; set; }
    }
}