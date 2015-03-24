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

namespace DesktopApplication
{

    enum UserWindowUse
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
        public UserWindow(UserWindowUse use)
        {
            InitializeComponent();

            if (use == UserWindowUse.EDIT)
                ;
            else if (use == UserWindowUse.CREATE)
                ;
            else
                ;

        }

        private void create_user(object sender, RoutedEventArgs e)
        {

        }

        private void reset_form(object sender, RoutedEventArgs e)
        {
            this.inputNaam.Clear();
            this.inputLogin.Clear();
            this.inputStraat.Clear();
            this.inputGemeente.Clear();
            this.inputPostcode.Clear();
            this.inputEmail.Clear();
            this.inputDatum.SelectedDate = DateTime.Now;
            this.userImage.Source = null;
        }

        private void cancel(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

    }
}
