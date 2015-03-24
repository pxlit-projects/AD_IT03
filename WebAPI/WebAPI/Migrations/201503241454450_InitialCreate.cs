namespace WebAPI.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class InitialCreate : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Users",
                c => new
                    {
                        UserId = c.Int(nullable: false, identity: true),
                        Firstname = c.String(),
                        Login = c.String(),
                        Lastname = c.String(),
                        Screenname = c.String(),
                        Password = c.String(),
                        Email = c.String(),
                        Straat = c.String(),
                        Gemeente = c.String(),
                        Postcode = c.String(),
                        Geboortedatum = c.String(),
                    })
                .PrimaryKey(t => t.UserId);
        }
        
        public override void Down()
        {
            DropTable("dbo.Users");
        }
    }
}
