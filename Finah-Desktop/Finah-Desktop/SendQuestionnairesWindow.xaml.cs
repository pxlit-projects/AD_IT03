using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Reflection;
using System.Security.Cryptography;
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
            if (IsValidEmail(MantelzorgerEmailBox.Text) && IsValidEmail(PatientEmailBox.Text))
            {
                PrepareSendEmail(MantelzorgerEmailBox.Text, PatientEmailBox.Text);
            }
            else
            {
                SendError.Content = Properties.Resources.EmailNotValidLabel;
                SendError.Visibility = Visibility.Visible;
            }
            
        }

        public static bool IsValidEmail(string email)
        {
            try
            {
                var addr = new System.Net.Mail.MailAddress(email);
                return addr.Address == email;
            }
            catch
            {
                return false;
            }
        }

        private void PatrientEmailBox_GotFocus(object sender, RoutedEventArgs e)
        {
            SendError.Visibility = Visibility.Hidden;
        }

        private void PrepareSendEmail(string emailMantelzorger, string emailPatient)
        {
            string subject = Properties.Resources.EmailQuestionnaireSubject;

            SendEmail(emailPatient, subject, CreateBody(true));
            SendEmail(emailMantelzorger, subject, CreateBody(false));
        }

        private void SendEmail(string email, string subject, string body)
        {
            MailMessage mail = new MailMessage(Properties.Resources.EnterpriseEmail, email);
            SmtpClient client = new SmtpClient();
            client.Port = 25;
            client.DeliveryMethod = SmtpDeliveryMethod.Network;
            client.UseDefaultCredentials = false;
            client.EnableSsl = false;
            client.Host = "smtp-mail.outlook.com";

            //client.Credentials = new NetworkCredential("email", "password");

            mail.Subject = subject;
            mail.Body = body;

            try
            {
                client.Send(mail);
            }
            catch (SmtpException e)
            {
                MessageBox.Show(e.ToString()); //timothy.baert.be@gmail.com
            }
            this.Close();
        }

        private string CreateBody(bool isPatient)
        {
            string body;

            if (isPatient)
            {
                body = Properties.Resources.EmailQuestionnaireBodyPatient;
            }
            else
            {
                body = Properties.Resources.EmailQuestionnaireBodyMantelzorger;
            }

            body += "\n\n" + Properties.Resources.EmailQuestionnaireBody;

            body += "\n" + LinkCreator(isPatient);

            body += "\n\n" + Properties.Resources.EmailQuestionnaireBodyEnd;
            body += "\n" + Properties.Resources.EmailQuestionnaireBodyEnd2;
            body += "\n" + Properties.Resources.EmailQuestionnaireBodyEnd3;

            return body;
        }

        private string LinkCreator(bool isPatient)
        {
            string link = Properties.Resources.SurveyLink;

            // add user Id (patient or mantelzorger)
            if (isPatient)
            {
                link += "3/";
            }
            else
            {
                link += "4/";
            }

            link += RandomShHash();

            return link;
        }

        // create sh-1 hash on random number
        private string RandomShHash()
        {
            Random rnd = new Random();
            string random = rnd.Next(0, 1).ToString();

            return GetSha1(random);
        }

        public static string GetSha1(string value)
        {
            var data = Encoding.ASCII.GetBytes(value);
            var hashData = new SHA1Managed().ComputeHash(data);

            var hash = string.Empty;

            foreach (var b in hashData)
                hash += b.ToString("X2");

            return hash;
        }

    }
}
