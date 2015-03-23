using System;
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
            String Naam = naamInput.Text;
            String Login = inputLogin.Text;
            String Straat = inputStraat.Text;
            String Gemeente = inputGemeente.Text;
            String Postcode = inputPostcode.Text;
            String Email = inputEmail.Text;
            String Geboortedatum = inputDatum.ToString();
           

            int Functie;
            if (inputHulpverlener.IsChecked == true)
            {
                Functie = 2;
            }
            else
            {
                Functie = 3;
            }


            User newUser = new User();

            newUser.Firstname = Naam;
            newUser.Login = Login;
            newUser.Straat = Straat;
            newUser.Gemeente = Gemeente;
            newUser.Postcode = Postcode;
            newUser.Email = Email;
            newUser.Geboortedatum = Geboortedatum;
           


            DBConnect connectie = new DBConnect();
            connectie.InsertUSers(newUser);



        }
    }
}