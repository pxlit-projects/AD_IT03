using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.Odbc;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Web.Http;

namespace WebAPI.Controllers
{
    public class AppDevProjectController : ApiController
    {
        // GET: api/AppDevProject
        /// <summary>
        /// Documentatie: get
        /// </summary>
        /// <returns></returns>
        public IList<string> Get()
        {
            IList<string> users = new List<string>();
            try
            {
                using (OdbcConnection connection = new OdbcConnection(ConfigurationManager.ConnectionStrings["MySQLConnStr"].ConnectionString))
                {
                    connection.Open();
                    using (OdbcCommand command = new OdbcCommand("SELECT * FROM user", connection))
                    using (OdbcDataReader dr = command.ExecuteReader())
                    {

                        while (dr.Read())
                        {
                            users.Add(dr["firstname"].ToString() + " " + dr["lastname"].ToString());
                        }
                        dr.Close();
                    }
                    connection.Close();
                    return users;
                }
            }
            catch (Exception ex)
            {
                return new string[] {("An error occured: " + ex.Message)};
            }
        }

        // GET: api/AppDevProject/5
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/AppDevProject
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/AppDevProject/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/AppDevProject/5
        public void Delete(int id)
        {
        }
    }
}
