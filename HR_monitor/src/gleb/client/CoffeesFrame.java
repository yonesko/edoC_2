/*
 * Copyright (c) 1995, 2011, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package gleb.client;

import gleb.server.CoffeesTableModel;
import gleb.server.DataSource;

import javax.sql.RowSetEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.RowSetListener;
import javax.sql.rowset.CachedRowSet;

public class CoffeesFrame extends JFrame implements RowSetListener {
  
  Connection connection;
  JTable table; // The table for displaying data

  JLabel label_job_title;
  JLabel label_job_id;
  JLabel label_min_salary;
  JLabel label_max_salary;

  JTextField tf_job_title;
  JTextField tf_job_id;
  JTextField tf_min_salary;
  JTextField tf_max_salary;

  JButton button_ADD_ROW;
  JButton button_UPDATE_DATABASE;
  JButton button_DISCARD_CHANGES;

  CoffeesTableModel myCoffeesTableModel;

  public CoffeesFrame() throws SQLException {
    super("Должности"); // Set window title
    
    connection = DataSource.getOracleDataSource("hr", "hr").getConnection();

    // Close connections exit the application when the user
    // closes the window

    addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          try {
            connection.close();
          } catch (SQLException sqle) {
            sqle.printStackTrace();
          }
          System.exit(0);
        }
      });

    // Initialize and lay out window controls

    CachedRowSet myCachedRowSet = DataSource.getContentsOfCoffeesTable();
    myCoffeesTableModel = new CoffeesTableModel(myCachedRowSet);
    myCoffeesTableModel.addEventHandlersToRowSet(this);

    table = new JTable(); // Displays the table
    table.setModel(myCoffeesTableModel);

    label_job_title = new JLabel();
    label_job_id = new JLabel();
    label_min_salary = new JLabel();
    label_max_salary = new JLabel();

    tf_job_id = new JTextField(10);
    tf_job_title = new JTextField(10);
    tf_min_salary = new JTextField(10);
    tf_max_salary = new JTextField(10);

    button_ADD_ROW = new JButton();
    button_UPDATE_DATABASE = new JButton();
    button_DISCARD_CHANGES = new JButton();

    label_job_title.setText("Название должности:");
    label_job_id.setText("ID:");
    label_min_salary.setText("min salary");
    label_max_salary.setText("max salary");

    tf_job_title.setText("omg");
    tf_job_id.setText("101");
    tf_min_salary.setText("0");
    tf_max_salary.setText("0");

    button_ADD_ROW.setText("Add row to table");
    button_UPDATE_DATABASE.setText("Update database");
    button_DISCARD_CHANGES.setText("Discard changes");

    // Place the components within the container contentPane; use GridBagLayout
    // as the layout.

    Container contentPane = getContentPane();
    contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    contentPane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.BOTH;
    c.anchor = GridBagConstraints.CENTER;
    c.weightx = 0.5;
    c.weighty = 1.0;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 2;
    contentPane.add(new JScrollPane(table), c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.weightx = 0.25;
    c.weighty = 0;
    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 1;
    contentPane.add(label_job_title, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.75;
    c.weighty = 0;
    c.gridx = 1;
    c.gridy = 1;
    c.gridwidth = 1;
    contentPane.add(tf_job_title, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.25;
    c.weighty = 0;
    c.anchor = GridBagConstraints.LINE_START;
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 1;
    contentPane.add(label_job_id, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.75;
    c.weighty = 0;
    c.gridx = 1;
    c.gridy = 2;
    c.gridwidth = 1;
    contentPane.add(tf_job_id, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.weightx = 0.25;
    c.weighty = 0;
    c.gridx = 0;
    c.gridy = 3;
    c.gridwidth = 1;
    contentPane.add(label_min_salary, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.75;
    c.weighty = 0;
    c.gridx = 1;
    c.gridy = 3;
    c.gridwidth = 1;
    contentPane.add(tf_min_salary, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.weightx = 0.25;
    c.weighty = 0;
    c.gridx = 0;
    c.gridy = 4;
    c.gridwidth = 1;
    contentPane.add(label_max_salary, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.75;
    c.weighty = 0;
    c.gridx = 1;
    c.gridy = 4;
    c.gridwidth = 1;
    contentPane.add(tf_max_salary, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.weightx = 0.5;
    c.weighty = 0;
    c.gridx = 0;
    c.gridy = 6;
    c.gridwidth = 1;
    contentPane.add(button_ADD_ROW, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.5;
    c.weighty = 0;
    c.gridx = 1;
    c.gridy = 6;
    c.gridwidth = 1;
    contentPane.add(button_UPDATE_DATABASE, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.LINE_START;
    c.weightx = 0.5;
    c.weighty = 0;
    c.gridx = 0;
    c.gridy = 7;
    c.gridwidth = 1;
    contentPane.add(button_DISCARD_CHANGES, c);

    // Add listeners for the buttons in the application

    button_ADD_ROW.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {

          JOptionPane.showMessageDialog(CoffeesFrame.this,
                                        new String[] {
                "Adding the following row:",
                "Coffee name: [" + tf_job_id.getText() + "]",
                "Supplier ID: [" + tf_job_title.getText() + "]",
                "Price: [" + tf_min_salary.getText() + "]",
                "Sales: [" + tf_max_salary.getText() + "]" });


          try {

            myCoffeesTableModel.insertRow(
                    Integer.parseInt(tf_job_id.getText()),
                    tf_job_title.getText(),
                    Integer.parseInt(tf_min_salary.getText()),
                    Integer.parseInt(tf_max_salary.getText())
            );
          } catch (SQLException sqle) {
            displaySQLExceptionDialog(sqle);
          }
        }
      });

    button_UPDATE_DATABASE.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
          try {
            myCoffeesTableModel.getCoffeesRowSet().acceptChanges();
          } catch (SQLException sqle) {
            displaySQLExceptionDialog(sqle);
            // Now revert back changes
            try {
              createNewTableModel();
            } catch (SQLException sqle2) {
              displaySQLExceptionDialog(sqle2);
            }
          }
        }
      });

    button_DISCARD_CHANGES.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            createNewTableModel();
          } catch (SQLException sqle) {
            displaySQLExceptionDialog(sqle);
          }
        }
      });
  }

  private void displaySQLExceptionDialog(SQLException e) {
    e.printStackTrace();
    // Display the SQLException in a dialog box
    JOptionPane.showMessageDialog(
      CoffeesFrame.this,
      new String[] {
        e.getClass().getName() + ": ",
        e.getMessage()
      }
    );
  }

  private void createNewTableModel() throws SQLException {
    myCoffeesTableModel = new CoffeesTableModel(DataSource.getContentsOfCoffeesTable());
    myCoffeesTableModel.addEventHandlersToRowSet(this);
    table.setModel(myCoffeesTableModel);
  }

  public static void main(String[] args) throws Exception {
    try {
      CoffeesFrame qf = new CoffeesFrame();
      qf.pack();
      qf.setVisible(true);
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    }
    catch (Exception e) {
      System.out.println("Unexpected exception");
      e.printStackTrace();
    }
  }

 // public void actionPerformed(ActionEvent event) {  }

  public void rowSetChanged(RowSetEvent event) {  }

  public void rowChanged(RowSetEvent event) {

    CachedRowSet currentRowSet = this.myCoffeesTableModel.getCoffeesRowSet();

    try {
      currentRowSet.moveToCurrentRow();
      myCoffeesTableModel =
        new CoffeesTableModel(myCoffeesTableModel.getCoffeesRowSet());
      table.setModel(myCoffeesTableModel);

    } catch (SQLException ex) {
      ex.printStackTrace();

      // Display the error in a dialog box.

      JOptionPane.showMessageDialog(
        CoffeesFrame.this,
        new String[] { // Display a 2-line message
          ex.getClass().getName() + ": ",
          ex.getMessage()
        }
      );
    }
  }

  public void cursorMoved(RowSetEvent event) {  }
}
