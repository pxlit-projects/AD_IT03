using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using Database;
using DataObjects;

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

            SetResources();

            for (int i = 1; i <= 20; ++i)
            {
                this.AmountQuestionsComboxbox.Items.Add(i);
            }
            this.AmountQuestionsComboxbox.SelectedIndex = 0;
        }

        private void GenerateButton_Click(object sender, RoutedEventArgs e)
        {
            string themeTitle = ThemeTextBox.Text;
            int amountQuestions = Convert.ToInt32(AmountQuestionsComboxbox.SelectedIndex + 1);
            string description = DescriptionTextBox.Text;

            if (themeTitle.Length == 0)
            {
                MessageBox.Show("Het thema moet ingevuld zijn voor u verder kan gaan.", "Opgelet!");
            }
            else
            {
                Theme theme = new Theme() {Title = themeTitle, Description = description};
                ThemeDataConnect.AddTheme(theme);
                this.Close();

                ThemesWindow themesWindow = new ThemesWindow(themeTitle, amountQuestions, description, GetLastAddedThemeId());
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

        private int GetLastAddedThemeId()
        {
            List<Theme> themes = ThemeDataConnect.GetThemes();

            return themes[themes.Count - 1].Id;
        }

        private void SetResources()
        {
            Title = Properties.Resources.AddTheme;

            ThemeTitleLabel.Content = Properties.Resources.ThemeTitleLabel;
            AmountQuestionsLabel.Content = Properties.Resources.AmountQuestionsLabel;
            DescriptionLabel.Content = Properties.Resources.DescriptionLabel;

            GenerateButton.Content = Properties.Resources.GenerateThemeButton;
            Cancel.Content = Properties.Resources.Cancel;

        }
    }
}
