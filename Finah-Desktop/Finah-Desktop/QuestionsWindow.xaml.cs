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

            SetResources();

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
                if (MessageBox.Show(Properties.Resources.DeleteAllQuestionsWarning + "\n\n" + questions, Properties.Resources.DeleteAllQuestionsWarningTitle,
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

        private void SetResources()
        {
            Title = Properties.Resources.EditTheme;

            Id.Header = Properties.Resources.Id;
            QuestionTitle.Header = Properties.Resources.Title;
            Description.Header = Properties.Resources.Description;

            AddQuestion.Content = Properties.Resources.Add;
            EditQuestion.Content = Properties.Resources.Edit;
            DeleteQuestion.Content = Properties.Resources.Delete;
            CancelQuestion.Content = Properties.Resources.Cancel;
        }

    }
}
