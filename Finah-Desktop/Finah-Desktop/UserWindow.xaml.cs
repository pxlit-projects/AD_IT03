/* User class:  Display user, edit user and delete user. */

using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;
using System.Windows;
using System.Windows.Input;
using DataObjects;
using Database;


namespace DesktopApplication
{

    public enum UserWindowUse
    {
        Look,
        Edit,
        Create
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

            if (use == UserWindowUse.Edit)
            {
                Title = Properties.Resources.EditUserLabel;
                TitleLabel.Content = Properties.Resources.EditUserLabel;
                SetUser(user);
            }
            else if (use == UserWindowUse.Create) 
            {
                Title = Properties.Resources.AddUserLabel;
                TitleLabel.Content = Properties.Resources.AddUserLabel;
            }
            else
            {
                Title = Properties.Resources.LookUserLabel;
                TitleLabel.Content = Properties.Resources.LookUserLabel;
                DisableInputs(true);
                SetUser(user);
            }

            SetResources();

            SetFunctions();
            SetVisibilityButtons(use);
        }

        // set buttons to visible or hidden when look, create or edit
        private void SetVisibilityButtons(UserWindowUse use)
        {
            if (use == UserWindowUse.Look)
            {
                this.EditButton.Visibility = Visibility.Visible;
                this.DeleteButton.Visibility = Visibility.Visible;
                this.BackButton.Visibility = Visibility.Visible;
                this.ResetButton.Visibility = Visibility.Hidden;
            }
            else
            {
                this.EditButton.Visibility = Visibility.Hidden;
                this.DeleteButton.Visibility = Visibility.Hidden;
                this.BackButton.Visibility = Visibility.Hidden;
                this.ResetButton.Visibility = Visibility.Visible;
            }

            if (use == UserWindowUse.Create)
            {
                this.CreateButton.Visibility = Visibility.Visible;
                this.CancelButton.Visibility = Visibility.Visible;
                this.BackButton.Visibility = Visibility.Hidden;
            }
            else
            {
                this.CreateButton.Visibility = Visibility.Hidden;
                this.CancelButton.Visibility = Visibility.Hidden;
                this.BackButton.Visibility = Visibility.Visible;
            }

            if (use == UserWindowUse.Edit)
                this.SaveEditButton.Visibility = Visibility.Visible;
            else
                this.SaveEditButton.Visibility = Visibility.Hidden;

        }
      
        private void DisableInputs(bool isTrue)
        {
            if (isTrue == true) { 
                this.FirstnameTextBox.IsEnabled = !IsEnabled;
                this.LastnameTextBox.IsEnabled = !IsEnabled;
                this.LoginTextBox.IsEnabled = !IsEnabled;
                this.PasswordTextBox.IsEnabled = !IsEnabled;
                this.StreetTextBox.IsEnabled = !IsEnabled;
                this.CityTextBox.IsEnabled = !IsEnabled;
                this.ZipTextBox.IsEnabled = !IsEnabled;
                this.EmailTextBox.IsEnabled = !IsEnabled;
                this.DateTextBox.IsEnabled = !IsEnabled;
                this.UserImage.IsEnabled = !IsEnabled;
                this.UploadPhoto.IsEnabled = !IsEnabled;
                this.FunctionBox.IsEnabled = !IsEnabled;
            }
            else
            {
                this.FirstnameTextBox.IsEnabled = IsEnabled;
                this.LastnameTextBox.IsEnabled = IsEnabled;
                this.LoginTextBox.IsEnabled = IsEnabled;
                this.PasswordTextBox.IsEnabled = IsEnabled;
                this.StreetTextBox.IsEnabled = IsEnabled;
                this.CityTextBox.IsEnabled = IsEnabled;
                this.ZipTextBox.IsEnabled = IsEnabled;
                this.EmailTextBox.IsEnabled = IsEnabled;
                this.DateTextBox.IsEnabled = IsEnabled;
                this.UserImage.IsEnabled = IsEnabled;
                this.UploadPhoto.IsEnabled = IsEnabled;
                this.FunctionBox.IsEnabled = IsEnabled;
            }
        }

        private void reset_form(object sender, RoutedEventArgs e)
        {
            this.FirstnameTextBox.Clear();
            this.LastnameTextBox.Clear();
            this.LoginTextBox.Clear();
            this.PasswordTextBox.Clear();
            this.StreetTextBox.Clear();
            this.CityTextBox.Clear();
            this.ZipTextBox.Clear();
            this.EmailTextBox.Clear();
            this.DateTextBox.SelectedDate = DateTime.Now;
            this.UserImage.Source = null;
        }

        private void Cancel(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void editButton_click(object sender, RoutedEventArgs e)
        {
            this.Close();

            UserWindow editUserWindow = new UserWindow(UserWindowUse.Edit, user); // persoon
            editUserWindow.ShowDialog();
        }

        private void deleteButton_click(object sender, RoutedEventArgs e)
        {
            if (MessageBox.Show(Properties.Resources.DoYouWantToDeleteUserX1 + FirstnameTextBox.Text + " " + LastnameTextBox.Text + Properties.Resources.DoYouWantToDeleteUserX2, Properties.Resources.DeleteUserLabel, MessageBoxButton.YesNo, MessageBoxImage.Warning) == MessageBoxResult.Yes)
            {
                try
                {
                    UserDataConnect.DeleteUser(user);
                    this.Close();
                }
                catch (Exception ex)
                {
                    MessageBox.Show(Properties.Resources.DeleteUserError, ex.GetType().ToString());
                }
            }

        }

        private void backButton_click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void saveEditButton_click(object sender, RoutedEventArgs e)
        {
            User editUser = GetUserOfUi();

            try
            {
                UserDataConnect.UpdateUser(editUser);
                this.DialogResult = true;
                this.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(Properties.Resources.UpdateUserError, ex.GetType().ToString());
            }
        }

        private void createButton_Click(object sender, RoutedEventArgs e)
        {
            User newUser = GetUserOfUi();

            try
            {
                UserDataConnect.AddUser(newUser);
                this.DialogResult = true;
                this.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(Properties.Resources.FillAllInputs, Properties.Resources.Error);
            }
        }

        private void cancelButton_click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void SetUser(User userInfo){

            if (userInfo == null) 
                throw new ArgumentNullException("userInfo");

            FirstnameTextBox.Text = userInfo.Firstname;
            LastnameTextBox.Text = userInfo.Lastname;
            LoginTextBox.Text = userInfo.Login;
            PasswordTextBox.Text = userInfo.Password;
            StreetTextBox.Text = userInfo.Street;
            CityTextBox.Text = userInfo.Town;
            ZipTextBox.Text = userInfo.Zipcode.ToString();
            EmailTextBox.Text = userInfo.Email;
            DateTextBox.SelectedDate = userInfo.Birthdate;
            FunctionBox.SelectedIndex = userInfo.Type - 1;
        }

        private void SetFunctions()
        {
            List<UserType> list = UserDataConnect.GetUserTypes();

            foreach (UserType userType in list)
            {
                FunctionBox.Items.Add(userType.Screenname);
            }
        }

        private User GetUserOfUi()
        {
            // if user exists, give ID else, id = 0
            int id = user == null ? 0 : user.Id;
            String voornaam = FirstnameTextBox.Text;
            String achternaam = LastnameTextBox.Text;
            String login = LoginTextBox.Text;
            String paswoord = PasswordTextBox.Text;
            String straat = StreetTextBox.Text;
            String gemeente = CityTextBox.Text;
            int postcode = ZipTextBox.Text.Equals("") ? 0 : Int32.Parse(ZipTextBox.Text);
            String email = EmailTextBox.Text;

            DateTime geboortedatum = DateTextBox.SelectedDate != null ? DateTextBox.SelectedDate.Value : new DateTime();

            int function = FunctionBox.SelectedIndex + 1;


                User newUser = new User()
                {
                    Id = id,
                    Firstname = voornaam,
                    Lastname = achternaam,
                    Login = login,
                    Password = paswoord,
                    Street = straat,
                    Town = gemeente,
                    Zipcode = postcode,
                    Email = email,
                    Birthdate = geboortedatum,
                    Type = function
                };

            return newUser;
        }

        // only allows numeric values in textbox
        private void NumberValidationTextBox(object sender, TextCompositionEventArgs e)
        {
            Regex regex = new Regex("[^0-9]+");
            e.Handled = regex.IsMatch(e.Text);
        }

        private void SetResources()
        {
            FirstnameLabel.Content = Properties.Resources.Firstname + ":";
            LastnameLabel.Content = Properties.Resources.Lastname + ":";
            LoginLabel.Content = Properties.Resources.Login + ":";
            PasswordLabel.Content = Properties.Resources.Password + ":";
            StreetLabel.Content = Properties.Resources.Street + ":";
            TownLabel.Content = Properties.Resources.Town + ":";
            ZipcodeLabel.Content = Properties.Resources.Zipcode + ":";
            BirthdateLabel.Content = Properties.Resources.Birthdate + ":";

            CreateButton.Content = Properties.Resources.Create;
            ResetButton.Content = Properties.Resources.Reset;
            CancelButton.Content = Properties.Resources.Cancel;

            EditButton.Content = Properties.Resources.Edit;
            DeleteButton.Content = Properties.Resources.Delete;
            BackButton.Content = Properties.Resources.Back;
            SaveEditButton.Content = Properties.Resources.Save;
        }
    }
}
