using System;
using System.Windows;
using System.Windows.Controls;

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
                this.AmountQuestionsComboxbox.Items.Add(i);
            }
            this.AmountQuestionsComboxbox.SelectedIndex = 0;
        }

        private void GenerateButton_Click(object sender, RoutedEventArgs e)
        {
            string theme = ThemeTextBox.Text;
            int amountQuestions = Convert.ToInt32(AmountQuestionsComboxbox.SelectedIndex + 1);
            string description = DescriptionTextBox.Text;

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
