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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Spengergasse.WpfDb1 {
  /// <summary>
  /// Interaction logic for MainWindow.xaml
  /// </summary>
  public partial class MainWindow : Window {
    Schule2000Entities db = new Schule2000Entities();

    public MainWindow() {
      InitializeComponent();
    }

    private void Window_Loaded(object sender, RoutedEventArgs args) {
      ClassesList.ItemsSource = db.klassens.ToList();
    }

    private void SaveButton_Click(object sender, RoutedEventArgs e) {
      try {
        int rows = db.SaveChanges();
        ErrorBox.Text = rows + " rows updated.";
        StudentsList.UnselectAll();
        ClassesList.Items.Refresh();
      } catch (Exception ex) {
        ErrorBox.Text = ex.Message;
        for (var ie = ex.InnerException; ie != null; ie = ie.InnerException) {
          ErrorBox.Text += "\n" + ie.Message;
        }
      }
    }

    private void DeleteButton_Click(object sender, RoutedEventArgs e) {
      if (StudentsList.SelectedItem == null) return;
      db.schuelers.Remove((schueler)StudentsList.SelectedItem);
      StudentsList.Items.Refresh();
    }

    private void NewButton_Click(object sender, RoutedEventArgs e) {
      var c = (klassen)ClassesList.SelectedItem;
      if (c == null) return;

      var s = new schueler { S_Name = "Unnamed", S_K_Klasse = c.K_ID };
      c.schuelers.Add(s);

      StudentsList.Items.Refresh();
      StudentsList.SelectedItem = s;
    }
  }
}
