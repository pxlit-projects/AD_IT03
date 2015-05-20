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
    /// <summary>
    /// Interaction logic for QuestionEditWindow.xaml
    /// </summary>
    public partial class QuestionEditWindow : Window
    {
        

        public QuestionEditWindow()
        {
            InitializeComponent();
        }

        private void addQuestion_click(object sender, RoutedEventArgs e)
        {
            QuestionEditWindow window = new QuestionEditWindow();
            window.Owner = this;
            window.ShowDialog();
        }

        private void saveQuestion_click(object sender, RoutedEventArgs e)
        {

        }

        private void cancelQuestion_click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
