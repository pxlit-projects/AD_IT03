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
using Database;
using DataObjects;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for ReportWindow.xaml
    /// </summary>
    public partial class ReportWindow : Window
    {
        private HashObj hashObj;

        public ReportWindow(HashObj obj)
        {
            InitializeComponent();

            hashObj = obj;

            SetResources();

            SetReportData();
        }

        private void SetReportData()
        {
            List<AnswerList> answers = GetFilteredAnswerList();

            // split list in Patient and caretaker
            List<AnswerList> patientAnswers = answers.Where(item => item.UserType == 4).ToList();
            List<AnswerList> caretakerAnswers = answers.Where(item => item.UserType == 3).ToList();

            // get list for reportView 
            List<ReportView> reportView = GetReportViewData(patientAnswers, caretakerAnswers);

            var bindingList = new BindingList<ReportView>(reportView);
            ReportListView.ItemsSource = bindingList;
        }

        private List<ReportView> GetReportViewData(List<AnswerList> patientAnswers, List<AnswerList> caretakerAnswers)
        {
            List<ReportView> report = new List<ReportView>();
            List<Theme> themes = ThemeDataConnect.GetThemes();
            List<Question> questions = QuestionDataConnect.GetQuestions();
            List<Answer> answers = AnswerDataConnect.GetAnswers();

            for (int i = 0; i < patientAnswers.Count && i < caretakerAnswers.Count && i < questions.Count; ++i)
            {
                // checks if the patient and caretaker have the same questions, if not they are not added in the report
                if (patientAnswers.ElementAt(i).Question == caretakerAnswers.ElementAt(i).Question)
                {
                    ReportView reportView = new ReportView();
                    try
                    {
                        Question question = questions.ElementAt(patientAnswers.ElementAt(i).Question - 1);
                        // set statement
                        reportView.Question = question.Title;

                        // set theme
                        reportView.Theme = themes.ElementAt(question.Theme - 1).Title;

                        // set answers
                        reportView.AnswerPatient = answers.ElementAt(patientAnswers.ElementAt(i).Answer - 1).Title;
                        reportView.AnswerCaretaker = answers.ElementAt(caretakerAnswers.ElementAt(i).Answer - 1).Title;

                        //set helpneeded
                        reportView.HelpNeeded = GetHelpNeeded(patientAnswers.ElementAt(i).Workpoint,
                            caretakerAnswers.ElementAt(i).Workpoint);

                        report.Add(reportView);
                    }
                    catch (ArgumentOutOfRangeException e)
                    {
                        Console.WriteLine(e);
                    }
                    
                }
            }
            return report;
        }

        // returns strings with workpoints of patient and caretaker
        private string GetHelpNeeded(int workpointPatient, int workpointCaretaker)
        {
            if (workpointPatient == 1 && workpointCaretaker == 1)
            {
                return Properties.Resources.WorkpointPatientAndCaretaker;
            }
            else if (workpointPatient == 1 && workpointCaretaker == 0)
            {
                return Properties.Resources.WorkpointPatient;
            }
            else if (workpointPatient == 0 && workpointCaretaker == 1)
            {
                return Properties.Resources.WorkpointCaretaker;
            }
            else
            {
                return Properties.Resources.WorkpointNoOne;
            }
        }

        //returns list which equals the selected Hash
        private List<AnswerList> GetFilteredAnswerList()
        {
            List<AnswerList> fullList = AnswerDataConnect.GetAnswerList();

            return fullList.Where(item => item.Hash.Equals(hashObj.Hash) == true).ToList();
        }


        private void SetResources()
        {
            Title = Properties.Resources.ReportOverview;

            /*Overview.Header = Properties.Resources.Overview;
            Questionlist.Header = Properties.Resources.Questionlist;

            SendQuestionlistButton.Content = Properties.Resources.QuestionlistSending;*/

        }

    }
}
