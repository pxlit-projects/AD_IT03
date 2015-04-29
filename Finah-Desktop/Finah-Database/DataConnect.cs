using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DataObjects;

namespace Database
{
    public class DataConnect
    {

        public DataConnect()
        {

        }

        public List<User> getUsers()
        {

            getUsers(1);

            String input = WebAPIConnect.GetConnectionString("User");
            List<User> userList = new List<User>();
            UsertypesObj userList2;
            User user;

            Console.WriteLine(input);

            // set user
	        //string output = Newtonsoft.Json.JsonConvert.SerializeObject(user);
            try
            {
                userList2 = Newtonsoft.Json.JsonConvert.DeserializeObject<UsertypesObj>(input);

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(e);
            }

            
            return userList;

        }


        public User getUsers(int id)
        {

            String input ;//= WebAPIConnect.GetConnectionStringWithId("User", 110);
            User user = new User();

            user.Id = 200;
            user.Firstname = "Bart";


            //Console.WriteLine(input);

            // set user
            /*string output = Newtonsoft.Json.JsonConvert.SerializeObject(user);

            Console.Write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:" + output);

            try
            {
                Console.Write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: " + Newtonsoft.Json.JsonConvert.DeserializeObject<User>(input));

            }
            catch (Newtonsoft.Json.JsonException e)
            {
                Console.Write(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:" + e);
            }
            */

            return user;

        }

    }


    public class UsertypesObj
    {
        public UsersList Usertypes { get; set; }
    }

    public class UsersList
    {
        public List<User> Users { get; set; }
    }

}
