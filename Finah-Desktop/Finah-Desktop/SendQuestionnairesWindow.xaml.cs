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
using Database;
using DataObjects;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for SendQuestionnairesWindow.xaml
    /// </summary>
    public partial class SendQuestionnairesWindow : Window
    {
        private HashObj hash;

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

            CreateRandomShHash();

            SendEmail(emailPatient, subject, CreateBody(true));
            SendEmail(emailMantelzorger, subject, CreateBody(false));
        }

        private void SendEmail(string email, string subject, string body)
        {
            MailMessage mail = new MailMessage(Properties.Resources.EnterpriseEmail, email);
            SmtpClient client = new SmtpClient("smtp.live.com", 587);
            client.EnableSsl = true;
            client.Timeout = 10000;
            client.DeliveryMethod = SmtpDeliveryMethod.Network;
            client.UseDefaultCredentials = false;
            
            client.Credentials = new NetworkCredential("email", "password");

            mail.Subject = subject;
            mail.Body = body;

            try
            {
                client.Send(mail);
                HashDataConnect.AddHash(hash);
            }
            catch (SmtpException e)
            {
                MessageBox.Show(e.ToString());
            }
            catch (WebException e)
            {
                MessageBox.Show(e.ToString());
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

            link += hash.Hash;

            return link;
        }

        // create sh-1 hash on random number
        private void CreateRandomShHash()
        {
            Random rnd = new Random((int)DateTime.Now.Ticks);
            string random = rnd.Next(0, 1).ToString();

            hash = new HashObj();
            hash.Hash = GetSha1(random);
            hash.Status = 0;
            hash.User = 98;


        }

        public static string GetSha1(string value)
        {
            var data = Encoding.ASCII.GetBytes(value);
            var hashData = new SHA1Managed().ComputeHash(data);

            var hash = string.Empty;

            foreach (var b in hashData)
                hash += b.ToString("x2");

            return hash;
        }

    }
}
