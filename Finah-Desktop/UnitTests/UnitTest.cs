using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using DataObjects;
using Database;
using DesktopApplication;


namespace UnitTests
{
    [TestClass]
    public class UnitTest
    {
        [TestMethod]
        public void TestMethod()
        {
        }

        [TestMethod]
        public void HashFucntionTest()
        {
            // arrange
            string expected = "098f6bcd4621d373cade4e832627b4f6";
            string test = "test";


            // act
            string actualHash = UserDataConnect.CalculateMd5Hash(test);

            //assert
            Assert.AreEqual(expected.ToUpper(), actualHash, false, "Hash not calculated correctly");

        }

        [TestMethod]
        public void CheckEmailTest()
        {
            // arrange
            bool expected = false;
            string test = "testhotmail.com";



            // act
            bool actual = SendQuestionnairesWindow.IsValidEmail(test);

            //assert
            Assert.IsTrue(expected == actual, "Email is not correct!");

        }

    }
}
