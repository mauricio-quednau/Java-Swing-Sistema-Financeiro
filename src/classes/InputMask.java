package classes;

import java.awt.Component;  
import java.awt.Font;  
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  
import java.awt.Insets;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import javax.swing.JButton;  
import javax.swing.JDialog;  
import javax.swing.JFormattedTextField;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.text.MaskFormatter;  
  
public class InputMask extends JDialog implements ActionListener {  
      
    private JLabel prompt = null;  
    private MaskFormatter msk = null;  
    private JFormattedTextField text = null;  
    private JButton ok, cancel;  
    private Font font = null;  
    private String response = null;  
  
    // Declaração do Layout e constraints do Layout  
    private GridBagLayout layout;  
    private GridBagConstraints constraints;  
    private final int horizontal = GridBagConstraints.HORIZONTAL;  
  
    public InputMask(JFrame parent, String aprompt, String title, String mask){  
        super(parent, title, true);  
        layout = new GridBagLayout();  
        setLayout(layout);  
        constraints = new GridBagConstraints();  
        font = new Font("Tahoma", 0, 14);  
        constraints.weightx = 0; constraints.weighty = 0;  
        constraints.insets = new Insets(5,5,5,5);  
        setResizable(false);  
        setLocationRelativeTo(this);  
        prompt = new JLabel(aprompt);  
        prompt.setFont(font);  
        addComponent(prompt,1,0,2,1,horizontal);  
        try {  
            msk = new MaskFormatter(mask);  
            msk.setPlaceholderCharacter('_');  
        } catch (Exception ex){  
            JOptionPane.showMessageDialog(this, ex.getMessage());  
        }  
        text = new JFormattedTextField(msk);  
        text.setFont(font);  
        text.setColumns(10);  
        addComponent(text,2,0,2,1,horizontal);  
        ok = new JButton("OK");  
        ok.addActionListener(this);  
        addComponent(ok,3,0,1,1,horizontal);  
        cancel = new JButton("Cancelar");  
        cancel.addActionListener(this);  
        addComponent(cancel,3,1,1,1,horizontal);  
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
        pack();  
        setVisible(true);  
    }  
  
     private void addComponent(Component comp, int row, int column, int width, int height, int fill){  
        constraints.fill = fill;  
        constraints.gridx = column;  
        constraints.gridy = row;  
        constraints.gridwidth = width;  
        constraints.gridheight = height;  
        layout.setConstraints(comp, constraints);  
        this.add(comp);  
    }  
  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        if (e.getSource()==ok){  
            setResponse(text.getText());  
        } else if (e.getSource()==cancel){  
            setResponse("");  
        }  
        setVisible(false);  
        dispose();  
    }  
  
    public String getResponse() {  
        return response;  
    }  
  
    public void setResponse(String response) {  
        this.response = response;  
    }  
  
} 