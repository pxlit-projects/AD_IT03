using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using WebAPI.Controllers;
using Finah_DomainClasses;
using Finah_Repository;

namespace ActionMethodTests
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void PostUserToWebApi()
        {
            //// Arrange
            //var repo = new UserRepository
            //{
            //    AddUser = item => item
            //};
            //var controller = new ProductsController(repo);

            //// Act
            //var result = controller.PostProduct(new Product { Id = 1 });

            //// Assert
            //Assert.Equal(HttpStatusCode.Created, result.StatusCode);

            //// Arrange
            //UserController controller = new UserController();
            //user User = new user() { id = 42, login = "UnitTest", firstname = "Unit", lastname = "Test" };

            //// Act
            //controller.Post(User);

            //// Assert
            ////Assert.IsInstanceOfType(, typeof(OkResult));        
        }
        [TestMethod]
        public void PutProductUpdatesRepository()
        {
            //var wasCalled = false;
            //var repo = new UserRepository
            //{
            //    UpdateUser = user => wasCalled = true
            //};
            //var controller = new ProductsController(repo);
            //var product = new Product { Id = 111 };

            //// Act
            //controller.PutProduct(111, product);

            //// Assert
            //Assert.True(wasCalled);
        }
    }
}
