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
        }

        private void GenerateButton_Click(object sender, RoutedEventArgs e)
        {
            string theme = themeTextBox.Text;
            int amountQuestions;
            if (amountQuestionsTextBox.Text == string.Empty)
            {
                amountQuestions = 0;
            }
            else
            {
                amountQuestions = Convert.ToInt32(amountQuestionsTextBox.Text);
            }
            var myObject = this.Owner as CreateQuestionList;
            myObject.GenerateQuestions(theme, amountQuestions);
            this.Close();
        }
    }
}
