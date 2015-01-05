/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.componio.opencms.projectstructure.plugin.dialogs;

import java.util.Properties;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Tom
 */
public class ModulePropertiesTextFieldDocumentListener implements DocumentListener {

    private JTextField field;
    private Properties props;
    private String key;

    ModulePropertiesTextFieldDocumentListener(JTextField field, Properties props, String key) {
        init(field, props, key);
    }

    private void init(JTextField field, Properties props, String key) {
        this.field = field;
        this.props = props;
        this.key = key;
    }

    private void updateProperty() {
        if (props.containsKey(key)) {
            props.put(key, field.getText());
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateProperty();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateProperty();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateProperty();
    }
}
