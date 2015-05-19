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
        private void addQuestion_click(object sender, RoutedEventArgs e) {

        }

        private void editQuestion_click(object sender, RoutedEventArgs e) {

        }

        private void deleteQuestion_click(object sender, RoutedEventArgs e) {

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

    }
}
