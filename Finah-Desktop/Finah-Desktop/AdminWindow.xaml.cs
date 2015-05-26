using System.Collections.Generic;
using System.Linq;
using System.Windows;
using System.Windows.Controls;
using DataObjects;
using Database;
using System.ComponentModel;
using System.Data;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for AdminWindow.xaml
    /// </summary>
    public partial class AdminWindow : Window
    {

        public AdminWindow(int functionId)
        {
            InitializeComponent();

            SetResources();


            // check user access
            if (functionId == 1)
            {
                LoadUserTab();
                LoadQuestionnaireTab();
                LoadOverviewTab();
            }
            else if (functionId == 2)
            {
                UserTab.Visibility = Visibility.Hidden;
                UserTab.Width = 0;
                LoadQuestionnaireTab();
                LoadOverviewTab();
            }
            else if (functionId == 5)
            {
                UserTab.Visibility = Visibility.Hidden;
                UserTab.Width = 0;
                Questionlist.Visibility = Visibility.Hidden;
                Questionlist.Width = 0;
                SendQuestionlistButton.Visibility = Visibility.Hidden;
                LoadOverviewTab();
            }
            else
            {
                TabOverview.Visibility = Visibility.Hidden;
            }
            
        }

        private void LoadUserTab()
        {
            List<User> userList = UserDataConnect.GetUsers();
            List<UserType> typesList = UserDataConnect.GetUserTypes();
            List<UserView> viewList = new List<UserView>();

            foreach (User user in userList)
            {
                UserView view = new UserView();
                view.Id = user.Id;
                view.Firstname = user.Firstname;
                view.Lastname = user.Lastname;
                view.Login = user.Login;

                view.Type = typesList.ElementAt(user.Type - 1).Screenname;
                
                viewList.Add(view);
            }

            var bindingList = new BindingList<UserView>(viewList);
            UserListView.ItemsSource = bindingList;


        }

        private void LoadQuestionnaireTab()
        {
            List<Theme> questions = ThemeDataConnect.GetThemes();

            var bindingList = new BindingList<Theme>(questions);
            ThemeListView.ItemsSource = bindingList;

        }

        private void LoadOverviewTab()
        {
            List<HashObj> hashesList = HashDataConnect.GetHashesList();

            // List with only the filled answerlists
            List<HashObj> filledList = hashesList.Where(obj => obj.Status == 1).ToList();

            var bindingList = new BindingList<HashObj>(filledList);
            NotificationsListView.ItemsSource = bindingList;
        }

        private void TabSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            
        }

        private void createUser_click(object sender, RoutedEventArgs e)
        {
            UserWindow userWindow = new UserWindow(UserWindowUse.Create, null);
            userWindow.Owner = this;
            userWindow.ShowDialog();
            LoadUserTab();
        }

        private void lookUser_click(object sender, RoutedEventArgs e)
        {
            if (GetSelectedUser() != null)
            {
                User user = UserDataConnect.GetUser(GetSelectedUser().Id);

                UserWindow userWindow = new UserWindow(UserWindowUse.Look, user);
                userWindow.Owner = this;
                userWindow.ShowDialog();
                LoadUserTab();
            }
        }

        private void editUser_click(object sender, RoutedEventArgs e)
        {
            if (GetSelectedUser() != null) {
                UserWindow userWindow = new UserWindow(UserWindowUse.Edit, GetSelectedUser());
                userWindow.Owner = this;
                userWindow.ShowDialog();
                LoadUserTab();
            }
        }

        private void deleteUser_click(object sender, RoutedEventArgs e)
        {
            List<User> list = GetSelectedUsers();

            if (list != null)
            {
                string names = "";

                foreach (User user in list)
                {
                    names += user.Firstname + " " + user.Lastname + "\n";
                }

                // warning before delete
                if (MessageBox.Show(Properties.Resources.DeleteAllUsersWarning + "\n\n" + names, Properties.Resources.DeleteAllUsersWarningTitle,
                    MessageBoxButton.YesNo, MessageBoxImage.Warning) == MessageBoxResult.Yes)
                {
                    foreach (User user in list)
                    {
                        UserDataConnect.DeleteUser(user);
                    }
                }
            }

            LoadUserTab();
        }

        private List<User> GetSelectedUsers()
        {
            List<User> userList = new List<User>();

            if (UserListView.SelectedItems != null && UserListView.SelectedItems.Count > 0)
            {
                foreach(UserView item in UserListView.SelectedItems){
                    userList.Add(UserDataConnect.GetUser(item.Id));
                }
                return userList;
            }

            return null;
            
        }

        private User GetSelectedUser()
        {
            if (UserListView.SelectedItems != null && UserListView.SelectedItems.Count == 1 )
            {
                return UserDataConnect.GetUser(((UserView)UserListView.SelectedItem).Id);
            }

            return null;
        }

        private void addThemes_click(object sender, RoutedEventArgs e)
        {
            AmountThemeQuestions amountThemeQuestions = new AmountThemeQuestions();
            amountThemeQuestions.Owner = this;
            amountThemeQuestions.ShowDialog();
            LoadQuestionnaireTab();
        }

        private void editThemes_click(object sender, RoutedEventArgs e)
        {
            if(ThemeListView.SelectedItem != null){

                Theme theme = (Theme)ThemeListView.SelectedItem;

                QuestionsWindow window = new QuestionsWindow(theme.Id);
                window.Owner = this;
                window.Show();
            }

            LoadQuestionnaireTab();
        }

        private void deleteThemes_click(object sender, RoutedEventArgs e)
        {
            List<Theme> list = GetSelectedThemes();

            if (list != null)
            {
                string themes = "";

                foreach (Theme theme in list)
                {
                    themes += theme.Title + "\n";
                }

                // warning before delete
                if (MessageBox.Show(Properties.Resources.DeleteThemesMessageBox + " \n\n" + themes, Properties.Resources.DeleteThemesLabel,
                    MessageBoxButton.YesNo, MessageBoxImage.Warning) == MessageBoxResult.Yes)
                {
                    foreach (Theme theme in list)
                    {
                        ThemeDataConnect.DeleteTheme(theme);
                    }
                }
            }

            LoadQuestionnaireTab();
        }

        private List<Theme> GetSelectedThemes()
        {
            List<Theme> list = new List<Theme>();

            if (ThemeListView.SelectedItems != null && ThemeListView.SelectedItems.Count > 0)
            {
                foreach (Theme item in ThemeListView.SelectedItems)
                {
                    list.Add(ThemeDataConnect.GetTheme(item.Id));
                }
                return list;
            }

            return null;

        }

        private void SetResources()
        {
            Title = Properties.Resources.ProjectTitle;

            Overview.Header = Properties.Resources.Overview;
            Questionlist.Header = Properties.Resources.Questionlist;
            UserTab.Header = Properties.Resources.Users;

            SendQuestionlistButton.Content = Properties.Resources.QuestionlistSending;

            ReportId.Header = Properties.Resources.Id;
            ReportDate.Header = Properties.Resources.Date;

            ThemeId.Header = Properties.Resources.Id;
            ThemeTitle.Header = Properties.Resources.Title;
            ThemeDescription.Header = Properties.Resources.Description;

            AddThemes.Content = Properties.Resources.Add;
            EditThemes.Content = Properties.Resources.Edit;
            DeleteThemes.Content = Properties.Resources.Delete;

            Firstname.Header = Properties.Resources.Firstname;
            Lastname.Header = Properties.Resources.Lastname;
            Login.Header = Properties.Resources.Login;
            Function.Header = Properties.Resources.Function;

            CreateUser.Content = Properties.Resources.Add;
            LookUser.Content = Properties.Resources.Look;
            EditUser.Content = Properties.Resources.Edit;
            DeleteUser.Content = Properties.Resources.Delete;
        }

        private void SendQuestionlist_Click(object sender, RoutedEventArgs e)
        {
            SendQuestionnairesWindow userWindow = new SendQuestionnairesWindow();
            userWindow.Owner = this;
            userWindow.ShowDialog();
        }

        private void GenerateReport_Click(object sender, RoutedEventArgs e)
        {
            HashObj obj = GetSelectedReport();
            if (obj != null)
            {
                ReportWindow userWindow = new ReportWindow(obj);
                userWindow.Owner = this;
                userWindow.ShowDialog();
            }
            
        }

        private HashObj GetSelectedReport()
        {
            if (NotificationsListView.SelectedItems != null && NotificationsListView.SelectedItems.Count == 1)
            {
                return (HashObj) NotificationsListView.SelectedItem;
            }

            return null;
        }
    }
}
