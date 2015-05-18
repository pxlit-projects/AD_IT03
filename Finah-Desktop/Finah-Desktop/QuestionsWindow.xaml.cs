using Database;
using DataObjects;
using System;
using System.Collections.Generic;
using System.ComponentModel;
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
    /// Interaction logic for QuestionsWindow.xaml
    /// </summary>
    public partial class QuestionsWindow : Window
    {
        private int themeId;

        public QuestionsWindow(int id)
        {
            InitializeComponent();

            themeId = id;

            loadQuestionView();

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


        private void loadQuestionView()
        {
            List<Question> questionList = QuestionDataConnect.GetThemesQuestions(themeId);

            var bindingList = new BindingList<Question>(questionList);
            QuestionListView.ItemsSource = bindingList;
        }

    }
}
