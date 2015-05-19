using System.Collections.Generic;
using System.Linq;
using System.Windows;
using System.Windows.Controls;
using DataObjects;
using Database;
using System.ComponentModel;

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

            if (functionId == 1)
            {
                LoadUserTab();
                LoadQuestionnaireTab();
            }
            else if (functionId == 2)
            {
                UserTab.Visibility = Visibility.Hidden;
                UserTab.Width = 0;
                LoadQuestionnaireTab();
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
            List<Theme> questions = QuestionDataConnect.GetThemes();

            var bindingList = new BindingList<Theme>(questions);
            ThemeListView.ItemsSource = bindingList;

        }

        private void TabSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            //if(select)
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
            if (GetSelectedUsers() != null)
            {

            }
            // ben je zeker dat je deze gebruikers wilt verwijderen? Messagebox..
            
            // selected users deleten
            LoadUserTab();
        }

        private List<User> GetSelectedUsers()
        {
            List<User> userList = new List<User>();

            if (UserListView.SelectedItems != null && UserListView.SelectedItems.Count > 0)
            {
                foreach(User item in UserListView.SelectedItems){
                    userList.Add(item);
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

        private void addQuestionnaireThemes_click(object sender, RoutedEventArgs e)
        {
            AmountThemeQuestions amountThemeQuestions = new AmountThemeQuestions();
            amountThemeQuestions.Owner = this;
            amountThemeQuestions.ShowDialog();
            LoadQuestionnaireTab();
        }

        private void editQuestionnaireThemes_click(object sender, RoutedEventArgs e)
        {
            if(ThemeListView.SelectedItem != null){

                Theme theme = (Theme)ThemeListView.SelectedItem;

                QuestionsWindow window = new QuestionsWindow(theme.Id);
                window.Owner = this;
                window.Show();
            }
            
            

            LoadQuestionnaireTab();
        }

        private void deleteQuestionnaireThemes_click(object sender, RoutedEventArgs e)
        {
            // ben je zeker dat je deze vragenlijsten wilt verwijderen? Messagebox..
            
            // selected questionnaires deleten

            //reload list
            LoadQuestionnaireTab();
        }
        
        

    }
}
