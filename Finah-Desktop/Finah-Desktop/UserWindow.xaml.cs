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

    public enum UserWindowUse
    {
        LOOK,
        EDIT,
        CREATE
    };

    /// <summary>
    /// Interaction logic for UserWindow.xaml
    /// </summary>
    public partial class UserWindow : Window
    {
        private User user;

        public UserWindow(UserWindowUse use, User currentUser)
        {
            InitializeComponent();

            user = currentUser;

            if (use == UserWindowUse.EDIT)
            {
                this.Title = "Gebruiker wijzigen";
                this.titleLabel.Content = "Gebruiker wijzigen";
                SetUser(user);
            }
            else if (use == UserWindowUse.CREATE) 
            {
                this.Title = "Gebruiker toevoegen";
                this.titleLabel.Content = "Gebruiker toevoegen";
            }
            else
            {
                this.Title = "Gebruiker bekijken";
                this.titleLabel.Content = "Gebruiker bekijken";
                disableInputs(true);
                SetUser(user);
            }

            setVisibilityButtons(use);
        }

        // set buttons to visible or hidden when look, create or edit
        private void setVisibilityButtons(UserWindowUse use)
        {
            if (use == UserWindowUse.LOOK)
            {
                this.editButton.Visibility = Visibility.Visible;
                this.deleteButton.Visibility = Visibility.Visible;
                this.backButton.Visibility = Visibility.Visible;
                this.resetButton.Visibility = Visibility.Hidden;
            }
            else
            {
                this.editButton.Visibility = Visibility.Hidden;
                this.deleteButton.Visibility = Visibility.Hidden;
                this.backButton.Visibility = Visibility.Hidden;
                this.resetButton.Visibility = Visibility.Visible;
            }

            if (use == UserWindowUse.CREATE)
            {
                this.createButton.Visibility = Visibility.Visible;
                this.cancelButton.Visibility = Visibility.Visible;
                this.backButton.Visibility = Visibility.Hidden;
            }
            else
            {
                this.createButton.Visibility = Visibility.Hidden;
                this.cancelButton.Visibility = Visibility.Hidden;
                this.backButton.Visibility = Visibility.Visible;
            }

            if (use == UserWindowUse.EDIT)
            {
                this.saveEditButton.Visibility = Visibility.Visible;
            }
            else
            {
                this.saveEditButton.Visibility = Visibility.Hidden;
            }

        }

        private void disableInputs(bool isTrue)
        {
            if (isTrue == true) { 
                this.firstnameTextBox.IsEnabled = !IsEnabled;
                this.lastnameTextBox.IsEnabled = !IsEnabled;
                this.loginTextBox.IsEnabled = !IsEnabled;
                this.passwordTextBox.IsEnabled = !IsEnabled;
                this.streetTextBox.IsEnabled = !IsEnabled;
                this.cityTextBox.IsEnabled = !IsEnabled;
                this.zipTextBox.IsEnabled = !IsEnabled;
                this.emailTextBox.IsEnabled = !IsEnabled;
                this.dateTextBox.IsEnabled = !IsEnabled;
                this.userImage.IsEnabled = !IsEnabled;
                this.uploadPhoto.IsEnabled = !IsEnabled;
                this.inputHulpverlener.IsEnabled = !IsEnabled;
                this.inputMantelzorger.IsEnabled = !IsEnabled;
            }
            else
            {
                this.firstnameTextBox.IsEnabled = IsEnabled;
                this.lastnameTextBox.IsEnabled = IsEnabled;
                this.loginTextBox.IsEnabled = IsEnabled;
                this.passwordTextBox.IsEnabled = IsEnabled;
                this.streetTextBox.IsEnabled = IsEnabled;
                this.cityTextBox.IsEnabled = IsEnabled;
                this.zipTextBox.IsEnabled = IsEnabled;
                this.emailTextBox.IsEnabled = IsEnabled;
                this.dateTextBox.IsEnabled = IsEnabled;
                this.userImage.IsEnabled = IsEnabled;
                this.uploadPhoto.IsEnabled = IsEnabled;
                this.inputHulpverlener.IsEnabled = IsEnabled;
                this.inputMantelzorger.IsEnabled = IsEnabled;
            }
        }

        private void reset_form(object sender, RoutedEventArgs e)
        {
            this.firstnameTextBox.Clear();
            this.lastnameTextBox.Clear();
            this.loginTextBox.Clear();
            this.passwordTextBox.Clear();
            this.streetTextBox.Clear();
            this.cityTextBox.Clear();
            this.zipTextBox.Clear();
            this.emailTextBox.Clear();
            this.dateTextBox.SelectedDate = DateTime.Now;
            this.userImage.Source = null;
        }

        private void cancel(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void editButton_click(object sender, RoutedEventArgs e)
        {
            this.Close();

            UserWindow editUserWindow = new UserWindow(UserWindowUse.EDIT, user); // persoon
            editUserWindow.ShowDialog();
        }

        private void deleteButton_click(object sender, RoutedEventArgs e)
        {
            if (MessageBox.Show("Gebruiker " + firstnameTextBox.Text + " " + lastnameTextBox.Text + " verwijderen?", "Gebruiker verwijderen", MessageBoxButton.YesNo, MessageBoxImage.Warning) == MessageBoxResult.Yes)
            {
                try
                {
                    UserDB.DeleteUser(user);
                    this.Close();
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message, ex.GetType().ToString());
                }
            }


        }

        private void backButton_click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void saveEditButton_click(object sender, RoutedEventArgs e)
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
                Id = user.Id,
                Firstname = Voornaam,
                Lastname = Achternaam,
                Login = Login,
                Password = Paswoord,
                Straat = Straat,
                Gemeente = Gemeente,
                Postcode = Postcode,
                Email = Email,
                Geboortedatum = Geboortedatum
            };

            try
            {
                UserDB.UpdateUser(newUser);
                this.DialogResult = true;
                this.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, ex.GetType().ToString());
            }
        }

        private void createButton_Click(object sender, RoutedEventArgs e)
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
                Password = Paswoord,
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

        private void cancelButton_click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void SetUser(User user){
            firstnameTextBox.Text = user.Firstname;
            lastnameTextBox.Text = user.Lastname;
            loginTextBox.Text = user.Login;
            passwordTextBox.Text = user.Password;
            streetTextBox.Text = user.Straat;
            cityTextBox.Text = user.Gemeente;
            zipTextBox.Text = user.Postcode;
            emailTextBox.Text = user.Email;
            dateTextBox.Text = user.Geboortedatum;
        }

    }
}
