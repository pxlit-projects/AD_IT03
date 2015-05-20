using Database;
using DataObjects;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for QuestionsWindow.xaml
    /// </summary>
    public partial class QuestionsWindow : Window
    {
        private int themeId;

        public QuestionsWindow(int id)
        {
            InitializeComponent();

            themeId = id;

            LoadQuestionView();

        }
        private void addQuestion_click(object sender, RoutedEventArgs e) 
        {
            QuestionEditWindow window = new QuestionEditWindow(themeId);
            window.Owner = this;
            window.ShowDialog();
            LoadQuestionView();
        }

        private void editQuestion_click(object sender, RoutedEventArgs e)
        {
            Question question = (Question)QuestionListView.SelectedItem;

            if (question != null)
            {
                QuestionEditWindow window = new QuestionEditWindow(question);
                window.Owner = this;
                window.ShowDialog();
                LoadQuestionView();
            }
            
        }

        private void deleteQuestion_click(object sender, RoutedEventArgs e) 
        {
            List<Question> list = GetSelectedQuestions();

            if (list != null)
            {
                string questions = "";

                foreach (Question question in list)
                {
                    questions += question.Title + "\n";
                }

                // warning before delete
                if (MessageBox.Show("Bent u zeker dat u al deze vragen wil verwijderen? \n\n" + questions, "Vragen verwijderen",
                    MessageBoxButton.YesNo, MessageBoxImage.Warning) == MessageBoxResult.Yes)
                {
                    foreach (Question question in list)
                    {
                        QuestionDataConnect.DeleteQuestion(question);
                    }
                }
            }

            LoadQuestionView();
        }

        private void cancelQuestion_click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }


        private void LoadQuestionView()
        {
            List<Question> questionList = QuestionDataConnect.GetThemesQuestions(themeId);

            var bindingList = new BindingList<Question>(questionList);
            QuestionListView.ItemsSource = bindingList;
        }

        private List<Question> GetSelectedQuestions()
        {
            List<Question> list = new List<Question>();

            if (QuestionListView.SelectedItems != null && QuestionListView.SelectedItems.Count > 0)
            {
                foreach (Question item in QuestionListView.SelectedItems)
                {
                    list.Add(item);
                }
                return list;
            }

            return null;

        }

    }
}
