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
    /// Interaction logic for SendQuestionnairesWindow.xaml
    /// </summary>
    public partial class SendQuestionnairesWindow : Window
    {
        public SendQuestionnairesWindow()
        {
            InitializeComponent();

            SetResources();
        }

        private void SetResources()
        {
            Title = Properties.Resources.SendQuestionnaire;

            SendWindowTitle.Content = Properties.Resources.SendQuestionnaire;

            PatientEmailLabel.Content = Properties.Resources.EmailPatientLabel;
            MantelzorgerEmailLabel.Content = Properties.Resources.EmailMantelzorgerLabel;
            SendButton.Content = Properties.Resources.QuestionlistSending;
        }

        private void SendClick(object sender, RoutedEventArgs e)
        {
            
            // warning
            if (MessageBox.Show(Properties.Resources.DeleteThemesMessageBox + " \n\n",
                Properties.Resources.DeleteThemesLabel,
                MessageBoxButton.YesNo, MessageBoxImage.Warning) == MessageBoxResult.Yes)
            {
            }

        }

        
    }
}
