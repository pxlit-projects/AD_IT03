﻿using System;
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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace DesktopApplication
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            CreateQuestionList window = new CreateQuestionList();
            window.Owner = this;
            window.ShowDialog();
        }

        private void adminwindow(object sender, RoutedEventArgs e)
        {
            AdminWindow window2 = new AdminWindow();
            window2.Owner = this;
            window2.ShowDialog();
        }

    }
}