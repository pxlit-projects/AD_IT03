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

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for ThemesWindow.xaml
    /// </summary>
    public partial class ThemesWindow : Window
    {

        private Theme questionTheme;

        public ThemesWindow(string theme, int amount, string description)
        {
            InitializeComponent();
            //questionTheme;   (get theme id, set theme name, description??)
            GenerateQuestions(theme, amount, description);
        }

        public void GenerateQuestions(string theme, int amount, string discription)
        {
            Label themeLabel = new Label();
            themeLabel.Content = theme;
            themeLabel.FontSize = 24;
            themeLabel.HorizontalAlignment = HorizontalAlignment.Center;
            questionStackPanel.Children.Add(themeLabel);

            Label descriptionLabel = new Label();
            descriptionLabel.Content = discription;
            descriptionLabel.FontSize = 14;
            descriptionLabel.HorizontalAlignment = HorizontalAlignment.Center;
            descriptionLabel.Margin = new Thickness(0, 0, 10, 10);
            questionStackPanel.Children.Add(descriptionLabel);
            for (int i = 0; i < amount; i++)
            {
                Label questionLabel = new Label();
                questionLabel.FontSize = 14;
                questionLabel.Margin = new Thickness(10, 10, 10, 0);
                TextBox questionTextBox = new TextBox();
                questionTextBox.Height = 30;
                questionTextBox.TextWrapping = TextWrapping.Wrap;
                questionTextBox.Margin = new Thickness(0, 0, 10, 0);

                questionLabel.Content = "Vraag " + (i + 1);
                questionStackPanel.Children.Add(questionLabel);
                questionStackPanel.Children.Add(questionTextBox);


                Label questionDescriptionLabel = new Label();
                questionDescriptionLabel.Margin = new Thickness(40, 0, 10, 0);
                TextBox descriptionTextBox = new TextBox();
                descriptionTextBox.Height = 30;
                descriptionTextBox.TextWrapping = TextWrapping.Wrap;
                descriptionTextBox.Margin = new Thickness(30, 0, 10, 0);

                questionDescriptionLabel.Content = "Beschrijving vraag " + (i + 1);
                questionStackPanel.Children.Add(questionDescriptionLabel);
                questionStackPanel.Children.Add(descriptionTextBox);
            }
        }

        private void save_click(object sender, RoutedEventArgs e)
        {
            List<Question> questionList = new List<Question>();
            int amount = questionStackPanel.Children.OfType<TextBox>().Count();
            bool isFilled = true;

            for (int i = 0; i < amount && isFilled == true; ++i)
            {
                String questionStr = questionStackPanel.Children.OfType<TextBox>().ElementAt(i).Text;


                // if question is not filled show error
                if (questionStr.Length == 0)
                {
                    isFilled = false;
                    MessageBox.Show("Vraag " + (i + 1) + " moet ingevuld worden!","Opgelet!");
                }
                else
                {
                    Question question = new Question();
                    question.Id = (i + 1);
                    question.Title = questionStr;
                    //question.Description;
                    //question.Theme = questionTheme;

                    questionList.Add(question);
                }
            }

            // save 
            if (isFilled == true)
            {
                // Add questionlist
            }

        }

        private void cancel_click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

    }
}
