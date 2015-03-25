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
    /// Interaction logic for CreateQuestionList.xaml
    /// </summary>
    public partial class CreateQuestionList : Window
    {
        public CreateQuestionList()
        {
            InitializeComponent();
        }
        public void GenerateQuestions(string theme, int amount)
        {
            Label themeLabel = new Label();
            themeLabel.Content = theme;
            themeLabel.FontSize = 24;
            questionStackPanel.Children.Add(themeLabel);
            for (int i = 0; i < amount; i++)
            {
                Label questionLabel = new Label();
                TextBox questionTextBox = new TextBox();
                questionTextBox.Height = 40;
                questionTextBox.TextWrapping = TextWrapping.Wrap;
           
                questionLabel.Content = "Question " + (i + 1);
                questionStackPanel.Children.Add(questionLabel);
                questionStackPanel.Children.Add(questionTextBox);
            }
        }

        private void NewThemeButton_Click(object sender, RoutedEventArgs e)
        {
            AmountThemeQuestions window = new AmountThemeQuestions();
            window.Owner = this;
            window.ShowDialog();
        }

        private void closeMenuItem_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
