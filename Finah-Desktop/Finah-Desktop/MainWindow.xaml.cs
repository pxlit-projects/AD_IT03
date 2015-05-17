using Database;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Resources;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

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

            this.usernameLabel.Content = Properties.Resources.Username;
            this.passwordLabel.Content = Properties.Resources.Password;
            this.loginButton.Content = Properties.Resources.Login;
            this.loginError.Content = Properties.Resources.LoginError;

            loginError.Visibility = Visibility.Hidden;

            

            Console.WriteLine(Properties.Resources.Login);

        }

        // check if login is correct, with the WEBAPI -> If ok, open adminwindow
        private void loginClick(object sender, RoutedEventArgs e)
        {
            String login = usernameBox.Text;
            String pass = passwordBox.Password;

            

            if (DataConnect.checkLogin(login, pass) == true)
            {
                AdminWindow window = new AdminWindow();
                window.Owner = this;
                this.Hide();
                window.ShowDialog();
                this.Close();
            }
            else
            {
                loginError.Visibility = Visibility.Visible;
            }



        }

        private void loginLostFocus(object sender, RoutedEventArgs e)
        {
            loginError.Visibility = Visibility.Hidden;
        }

    }
}
