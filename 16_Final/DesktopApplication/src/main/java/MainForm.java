import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainForm {

  private JPanel mainPanel;
  private JPanel startPanel;
  private JPanel resultPanel;

  private JButton joinButton;
  private JTextArea textName;
  private JTextArea textFirstName;
  private JTextArea textMiddleName;
  private JButton splitButton;
  private JTextArea textFIO;
  private JButton clearFIOButton;
  private JButton clearFirstNameButton;
  private JButton clearNameButton;
  private JButton clearMiddleNameButton;
  private JButton backButton;

  public MainForm() {
    textFirstName.setName("Фамилия");
    textName.setName("Имя");
    textMiddleName.setName("Отчество");
    textFIO.setName("ФИО");
    startPanel.setVisible(true);
    resultPanel.setVisible(false);

    joinButton.addActionListener(
        new Action() {
          @Override
          public Object getValue(String key) {
            return null;
          }

          @Override
          public void putValue(String key, Object value) {
          }

          @Override
          public boolean isEnabled() {
            return false;
          }

          @Override
          public void addPropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void removePropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void actionPerformed(ActionEvent e) {
            String firstName = textFirstName.getText();
            String name = textName.getText();
            String middleName = textMiddleName.getText();

            StringBuilder errorWrongText = new StringBuilder();
            StringBuilder errorEmptyText = new StringBuilder();
            addErrorBuilderJoinButton(textFirstName, errorWrongText, errorEmptyText);
            addErrorBuilderJoinButton(textName, errorWrongText, errorEmptyText);
            addErrorBuilderJoinButton(textMiddleName, errorWrongText, errorEmptyText);
            if (errorWrongText.length() > 0 || errorEmptyText.length() > 0) {
              JOptionPane.showMessageDialog(
                  mainPanel,
                  (errorWrongText.length() > 0 ? "Некорректрый ввод поля: " + errorWrongText : "") +
                      (errorEmptyText.length() > 0 ? "\nНе заполнено обязательное поле: "
                          + errorEmptyText : ""),
                  "ERROR",
                  JOptionPane.ERROR_MESSAGE);
              return;

            }
            textFIO.setText(firstName + " " + name + (middleName.length() > 0 ? " " + middleName
                : ""));
            startPanel.setVisible(false);
            resultPanel.setVisible(true);
          }

          @Override
          public void setEnabled(boolean b) {
          }
        });

    splitButton.addActionListener(
        new Action() {
          @Override
          public Object getValue(String key) {
            return null;
          }

          @Override
          public void putValue(String key, Object value) {
          }

          @Override
          public boolean isEnabled() {
            return false;
          }

          @Override
          public void addPropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void removePropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void actionPerformed(ActionEvent e) {
            List<String> listName = Arrays.asList(textFIO.getText().trim().split("\\s+"));
            AtomicBoolean b = new AtomicBoolean(true);
            for (String text : listName) {
              if (isCheckText(text)) {
                b.set(false);
                break;
              }
            }

            if (listName.size() > 1 && listName.size() < 4 && b.get()) {
              textFirstName.setText(listName.get(0));
              textName.setText(listName.get(1));
              textMiddleName.setText(listName.size() == 3 ? listName.get(2) : "");
              startPanel.setVisible(true);
              resultPanel.setVisible(false);
              return;
            }
            JOptionPane.showMessageDialog(
                mainPanel, "Некорректно заполнено поле: " + textFIO.getName(), "ERROR",
                JOptionPane.ERROR_MESSAGE);
          }

          @Override
          public void setEnabled(boolean b) {
          }

        });

    backButton.addActionListener(new Action() {
      @Override
      public Object getValue(String key) {
        return null;
      }

      @Override
      public void putValue(String key, Object value) {

      }

      @Override
      public void setEnabled(boolean b) {

      }

      @Override
      public boolean isEnabled() {
        return false;
      }

      @Override
      public void addPropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void removePropertyChangeListener(PropertyChangeListener listener) {

      }

      @Override
      public void actionPerformed(ActionEvent e) {
        textFirstName.setText("");
        textName.setText("");
        textMiddleName.setText("");
        startPanel.setVisible(true);
        resultPanel.setVisible(false);
      }
    });

    clearFirstNameButton.addActionListener(
        new Action() {
          @Override
          public Object getValue(String key) {
            return null;
          }

          @Override
          public void putValue(String key, Object value) {
          }

          @Override
          public boolean isEnabled() {
            return false;
          }

          @Override
          public void addPropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void removePropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void actionPerformed(ActionEvent e) {
            textFirstName.setText("");
          }

          @Override
          public void setEnabled(boolean b) {
          }
        });

    clearNameButton.addActionListener(
        new Action() {
          @Override
          public Object getValue(String key) {
            return null;
          }

          @Override
          public void putValue(String key, Object value) {
          }

          @Override
          public boolean isEnabled() {
            return false;
          }

          @Override
          public void addPropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void removePropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void actionPerformed(ActionEvent e) {
            textName.setText("");
          }

          @Override
          public void setEnabled(boolean b) {
          }
        });

    clearMiddleNameButton.addActionListener(
        new Action() {
          @Override
          public Object getValue(String key) {
            return null;
          }

          @Override
          public void putValue(String key, Object value) {
          }

          @Override
          public boolean isEnabled() {
            return false;
          }

          @Override
          public void addPropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void removePropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void actionPerformed(ActionEvent e) {
            textMiddleName.setText("");
          }

          @Override
          public void setEnabled(boolean b) {
          }
        });

    clearFIOButton.addActionListener(
        new Action() {
          @Override
          public Object getValue(String key) {
            return null;
          }

          @Override
          public void putValue(String key, Object value) {
          }

          @Override
          public boolean isEnabled() {
            return false;
          }

          @Override
          public void addPropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void removePropertyChangeListener(PropertyChangeListener listener) {
          }

          @Override
          public void actionPerformed(ActionEvent e) {
            textFIO.setText("");
          }

          @Override
          public void setEnabled(boolean b) {
          }
        });
  }


  public JPanel getMainPanel() {
    return mainPanel;
  }

  private boolean isCheckText(String text) {
    return !text.matches("[А-ЯЁ][а-яё]*");
  }

  private void addErrorBuilderJoinButton(JTextArea area, StringBuilder wrongFormatText,
      StringBuilder emptyText) {
    String text = area.getText();
    if (isCheckText(text) && text.length() > 0) {
      if (wrongFormatText.length() > 0) {
        wrongFormatText.append(", ");
      }
      wrongFormatText.append(area.getName());
    }
    if (text.length() == 0 && !area.getName().equals("Отчество")) {
      if (emptyText.length() > 0) {
        emptyText.append(", ");
      }
      emptyText.append(area.getName());
    }
  }
}
