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
using DataObjects;
using Database;
using System.ComponentModel;
using System.Diagnostics;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for AdminWindow.xaml
    /// </summary>
    public partial class AdminWindow : Window
    {

        public AdminWindow()
        {
            InitializeComponent();
            LoadUserTab();
            LoadQuestionnaireTab();
        }

        private void LoadUserTab()
        {
            List<User> userList = DataConnect.getUsers();

            var bindingList = new BindingList<User>(userList);
            UserListView.ItemsSource = bindingList;
        }

        private void LoadQuestionnaireTab()
        {
            List<Theme> questions = DataConnect.GetThemes();

            var bindingList = new BindingList<Theme>(questions);
            ThemeListView.ItemsSource = bindingList;

        }

        private void tabSelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            //if(select)
        }

        private void createUser_click(object sender, RoutedEventArgs e)
        {
            UserWindow userWindow = new UserWindow(UserWindowUse.CREATE, null);
            userWindow.Owner = this;
            userWindow.ShowDialog();
            LoadUserTab();
        }

        private void lookUser_click(object sender, RoutedEventArgs e)
        {
            if (GetSelectedUser() != null)
            {
                User user = DataConnect.getUser(GetSelectedUser().Id);

                UserWindow userWindow = new UserWindow(UserWindowUse.LOOK, user);
                userWindow.Owner = this;
                userWindow.ShowDialog();
                LoadUserTab();
            }
        }

        private void editUser_click(object sender, RoutedEventArgs e)
        {
            if (GetSelectedUser() != null) {
                UserWindow userWindow = new UserWindow(UserWindowUse.EDIT, GetSelectedUser());
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
                return (User)UserListView.SelectedItem;
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

                // new window
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
