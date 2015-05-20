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
using Database;
using DataObjects;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for QuestionEditWindow.xaml
    /// </summary>
    public partial class QuestionEditWindow : Window
    {
        private Question newQuestion;
        private int newThemeId;

        public QuestionEditWindow(int themeId)
        {
            InitializeComponent();

            newQuestion = null;
            newThemeId = themeId;

            SetVisibility();
        }

        public QuestionEditWindow(Question question)
        {
            InitializeComponent();

            newQuestion = question;

            SetVisibility();
        }

        private void SetVisibility()
        {
            if (newQuestion != null)
            {
                AddQuestion.Visibility = Visibility.Hidden;
                SaveQuestion.Visibility = Visibility.Visible;

                QuestionBox.Text = newQuestion.Title;
                DescriptionBox.Text = newQuestion.Description;
            }
            else
            {
                AddQuestion.Visibility = Visibility.Visible;
                SaveQuestion.Visibility = Visibility.Hidden;
            }
        }

        private void addQuestion_click(object sender, RoutedEventArgs e)
        {
            if (QuestionBoxIsFilled() == true)
            {
                newQuestion = new Question();

                newQuestion.Title = QuestionBox.Text;
                newQuestion.Description = DescriptionBox.Text;
                newQuestion.Theme = newThemeId;

                QuestionDataConnect.AddQuestion(newQuestion);

                this.Close();
            }
        }

        private void saveQuestion_click(object sender, RoutedEventArgs e)
        {
            if (QuestionBoxIsFilled() == true)
            {
                newQuestion.Title = QuestionBox.Text;
                newQuestion.Description = DescriptionBox.Text;

                QuestionDataConnect.UpdateQuestion(newQuestion);
                this.Close();
            }
        }

        private void cancelQuestion_click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private bool QuestionBoxIsFilled()
        {
            if (QuestionBox.Text.Length == 0)
            {
                return false;
            }
            return true;
        }

    }
}
