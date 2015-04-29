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
    /// Interaction logic for AmountThemeQuestions.xaml
    /// </summary>
    public partial class AmountThemeQuestions : Window
    {
        public AmountThemeQuestions()
        {
            InitializeComponent();

            for (int i = 1; i <= 20; ++i)
            {
                this.amountQuestionsComboxbox.Items.Add(i);
            }
            this.amountQuestionsComboxbox.SelectedIndex = 0;
        }

        private void GenerateButton_Click(object sender, RoutedEventArgs e)
        {
            string theme = themeTextBox.Text;
            int amountQuestions = Convert.ToInt32(amountQuestionsComboxbox.SelectedIndex + 1);
            string description = descriptionTextBox.Text;

            if (theme.Length == 0)
            {
                MessageBox.Show("Het thema moet ingevuld zijn voor u verder kan gaan.", "Opgelet!");
            }
            else
            {
                this.Close();

                ThemesWindow themesWindow = new ThemesWindow(theme, amountQuestions, description);
                themesWindow.ShowDialog();
            }
        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void amountQuestionsComboxbox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }
    }
}
