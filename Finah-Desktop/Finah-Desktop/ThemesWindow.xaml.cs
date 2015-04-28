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
    /// Interaction logic for ThemesWindow.xaml
    /// </summary>
    public partial class ThemesWindow : Window
    {
        public ThemesWindow()
        {
            InitializeComponent();
        }

        public ThemesWindow(string theme, int amount)
        {
            InitializeComponent();
            GenerateQuestions(theme, amount);
        }

        public void GenerateQuestions(string theme, int amount)
        {
            Label themeLabel = new Label();
            themeLabel.Content = theme;
            themeLabel.FontSize = 24;
            questionStackPanel.Children.Add(themeLabel);
            for (int i = 0; i < amount; i++)
            {
                Label questionLabel = new Label();
                TextBox questionTextBox = new TextBox();
                questionTextBox.Height = 40;
                questionTextBox.TextWrapping = TextWrapping.Wrap;

                questionLabel.Content = "Vraag " + (i + 1);
                questionStackPanel.Children.Add(questionLabel);
                questionStackPanel.Children.Add(questionTextBox);
            }
        }

    }
}
