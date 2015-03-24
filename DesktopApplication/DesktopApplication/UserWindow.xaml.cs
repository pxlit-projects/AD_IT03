﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using DataObjects;
using Database;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for UserWindow.xaml
    /// </summary>
    public partial class UserWindow : Window
    {
        public UserWindow()
        {
            InitializeComponent();
        }

        private void aanmaakButton_Click(object sender, RoutedEventArgs e)
        {
            String Voornaam = firstnameTextBox.Text;
            String Achternaam = lastnameTextBox.Text;
            String Login = loginTextBox.Text;
            String Paswoord = passwordTextBox.Text;
            String Straat = streetTextBox.Text;
            String Gemeente = cityTextBox.Text;
            String Postcode = zipTextBox.Text;
            String Email = emailTextBox.Text;
            String Geboortedatum = dateTextBox.ToString();


            int Functie;
            if (inputHulpverlener.IsChecked == true)
            {
                Functie = 2;
            }
            else
            {
                Functie = 3;
            }


            User newUser = new User()
            {
                Firstname = Voornaam,
                Lastname = Achternaam,
                Login = Login,
                Password =  Paswoord,
                Straat = Straat,
                Gemeente = Gemeente,
                Postcode = Postcode,
                Email = Email,
                Geboortedatum = Geboortedatum
            };

            try
            {
                UserDB.InsertUser(newUser);
                this.DialogResult = true;
                this.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, ex.GetType().ToString());
            }

        }
    }
}