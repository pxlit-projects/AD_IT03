using Database;
using System;
using System.Globalization;
using System.Threading;
using System.Windows;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            Thread.CurrentThread.CurrentUICulture = CultureInfo.GetCultureInfo("nl");

            InitializeComponent();

            this.UsernameLabel.Content = Properties.Resources.Username;
            this.PasswordLabel.Content = Properties.Resources.Password;
            this.LoginButton.Content = Properties.Resources.Login;

            LoginError.Visibility = Visibility.Hidden;
        }

        // check if login is correct, with the WEBAPI -> If ok, open adminwindow
        private void LoginClick(object sender, RoutedEventArgs e)
        {
            String login = UsernameBox.Text;
            String pass = PasswordBox.Password;

            try
            {
                int functionId = UserDataConnect.CheckLogin(login, pass);

                if ( functionId > 0)
                {
                    AdminWindow window = new AdminWindow(functionId);
                    window.Owner = this;
                    this.Hide();
                    window.ShowDialog();
                    this.Close();
                }
                else
                {
                    LoginError.Visibility = Visibility.Visible;
                    this.LoginError.Content = Properties.Resources.LoginError;
                }
            }
            catch (NullReferenceException)
            {
                LoginError.Content = Properties.Resources.LoginServerError;
                LoginError.Visibility = Visibility.Visible;
            }
            
        }

        // hide error notifier
        private void LoginLostFocus(object sender, RoutedEventArgs e)
        {
            LoginError.Visibility = Visibility.Hidden;
        }

    }
}
