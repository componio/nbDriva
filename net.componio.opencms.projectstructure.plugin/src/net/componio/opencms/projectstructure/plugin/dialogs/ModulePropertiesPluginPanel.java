/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.componio.opencms.projectstructure.plugin.dialogs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import net.componio.opencms.projectstructure.plugin.helper.ModulePropertiesHelper;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;

/**
 *
 * @author Tom
 */
public class ModulePropertiesPluginPanel extends javax.swing.JPanel {

    private Properties module_properties = new Properties();
    private String individualConfigPath = "";

    /**
     * Creates new form ModulePropertiesPluginPanel
     */
    public ModulePropertiesPluginPanel(String individualConfigFile, String defaultConfigFile) throws IOException {
        initComponents();
        this.individualConfigPath = individualConfigFile;
        module_properties = ModulePropertiesHelper.getModuleProperties(individualConfigFile, defaultConfigFile);
        updateComponentsWithPropertyValues();
        assignDocumentListenersToComponents();
    }

    private void assignDocumentListenersToComponents() {
        textfield_acPackage.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_acPackage, module_properties, label_acPackage.getText()));
        textfield_classpath_compile_jar.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_classpath_compile_jar, module_properties, label_classpath_compile_jar.getText()));
        textfield_cms_repo_id.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_cms_repo_id, module_properties, label_cms_repo_id.getText()));
        textfield_cms_repo_url.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_cms_repo_url, module_properties, label_cms_repo_url.getText()));
        textfield_cms_servlet_mapping.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_cms_servlet_mapping, module_properties, label_cms_servlet_mapping.getText()));
        textfield_cms_sync.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_cms_sync, module_properties, label_cms_sync.getText()));
        textfield_cms_web_inf_dir.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_cms_web_inf_dir, module_properties, label_cms_web_inf_dir.getText()));
        textfield_exclusion_file.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_exclusion_file, module_properties, label_exclusion_file.getText()));
        textfield_included_sync_folders.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_included_sync_folders, module_properties, label_included_sync_folders.getText()));
        textfield_ivy_install_version.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_ivy_install_version, module_properties, label_ivy_install_version.getText()));
        textfield_ivy_jar_dir.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_ivy_jar_dir, module_properties, label_ivy_jar_dir.getText()));
        textfield_ivy_jar_file.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_ivy_jar_file, module_properties, label_ivy_jar_file.getText()));
        textfield_ivy_resolved_libs_dir.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_ivy_resolved_libs_dir, module_properties, label_ivy_resolved_libs_dir.getText()));
        textfield_module_action_class.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_module_action_class, module_properties, label_module_action_class.getText()));
        textfield_module_dir.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_module_dir, module_properties, label_module_dir.getText()));
        textfield_module_name.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_module_name, module_properties, label_module_name.getText()));
        textfield_module_path.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_module_path, module_properties, label_module_path.getText()));
        textfield_module_version.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_module_version, module_properties, label_module_version.getText()));
        textfield_opencms_version.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_opencms_version, module_properties, label_opencms_version.getText()));
        textfield_package_path.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_package_path, module_properties, label_package_path.getText()));
        textfield_password.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_password, module_properties, label_password.getText()));
        textfield_script_dir.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_script_dir, module_properties, label_script_dir.getText()));
        textfield_username.getDocument().addDocumentListener(
                new ModulePropertiesTextFieldDocumentListener(textfield_username, module_properties, label_username.getText()));
    }

    private void updateComponentsWithPropertyValues() {
        for (String key : module_properties.stringPropertyNames()) {
            if (key.equals(label_acPackage.getText())) {
                textfield_acPackage.setText(module_properties.getProperty(key));
            } else if (key.equals(label_classpath_compile_jar.getText())) {
                textfield_classpath_compile_jar.setText(module_properties.getProperty(key));
            } else if (key.equals(label_cms_repo_id.getText())) {
                textfield_cms_repo_id.setText(module_properties.getProperty(key));
            } else if (key.equals(label_cms_repo_url.getText())) {
                textfield_cms_repo_url.setText(module_properties.getProperty(key));
            } else if (key.equals(label_cms_servlet_mapping.getText())) {
                textfield_cms_servlet_mapping.setText(module_properties.getProperty(key));
            } else if (key.equals(label_cms_sync.getText())) {
                textfield_cms_sync.setText(module_properties.getProperty(key));
            } else if (key.equals(label_cms_web_inf_dir.getText())) {
                textfield_cms_web_inf_dir.setText(module_properties.getProperty(key));
            } else if (key.equals(label_exclusion_file.getText())) {
                textfield_exclusion_file.setText(module_properties.getProperty(key));
            } else if (key.equals(label_included_sync_folders.getText())) {
                textfield_included_sync_folders.setText(module_properties.getProperty(key));
            } else if (key.equals(label_ivy_install_version.getText())) {
                textfield_ivy_install_version.setText(module_properties.getProperty(key));
            } else if (key.equals(label_ivy_jar_dir.getText())) {
                textfield_ivy_jar_dir.setText(module_properties.getProperty(key));
            } else if (key.equals(label_ivy_jar_file.getText())) {
                textfield_ivy_jar_file.setText(module_properties.getProperty(key));
            } else if (key.equals(label_ivy_resolved_libs_dir.getText())) {
                textfield_ivy_resolved_libs_dir.setText(module_properties.getProperty(key));
            } else if (key.equals(label_module_action_class.getText())) {
                textfield_module_action_class.setText(module_properties.getProperty(key));
            } else if (key.equals(label_module_dir.getText())) {
                textfield_module_dir.setText(module_properties.getProperty(key));
            } else if (key.equals(label_module_name.getText())) {
                textfield_module_name.setText(module_properties.getProperty(key));
            } else if (key.equals(label_module_path.getText())) {
                textfield_module_path.setText(module_properties.getProperty(key));
            } else if (key.equals(label_module_version.getText())) {
                textfield_module_version.setText(module_properties.getProperty(key));
            } else if (key.equals(label_opencms_version.getText())) {
                textfield_opencms_version.setText(module_properties.getProperty(key));
            } else if (key.equals(label_package_path.getText())) {
                textfield_package_path.setText(module_properties.getProperty(key));
            } else if (key.equals(label_password.getText())) {
                textfield_password.setText(module_properties.getProperty(key));
            } else if (key.equals(label_script_dir.getText())) {
                textfield_script_dir.setText(module_properties.getProperty(key));
            } else if (key.equals(label_sync_over_cmis.getText())) {
                checkbox_sync_over_cmis.setSelected(Boolean.parseBoolean(module_properties.getProperty(key)));
            } else if (key.equals(label_use_exclusion_file.getText())) {
                checkbox_use_exclusion_file.setSelected(Boolean.parseBoolean(module_properties.getProperty(key)));
            } else if (key.equals(label_username.getText())) {
                textfield_username.setText(module_properties.getProperty(key));
            }
        }
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        label_acPackage = new javax.swing.JLabel();
        label_cms_repo_id = new javax.swing.JLabel();
        label_cms_repo_url = new javax.swing.JLabel();
        label_cms_servlet_mapping = new javax.swing.JLabel();
        label_cms_sync = new javax.swing.JLabel();
        label_cms_web_inf_dir = new javax.swing.JLabel();
        label_classpath_compile_jar = new javax.swing.JLabel();
        label_exclusion_file = new javax.swing.JLabel();
        label_included_sync_folders = new javax.swing.JLabel();
        label_ivy_install_version = new javax.swing.JLabel();
        label_ivy_jar_dir = new javax.swing.JLabel();
        label_ivy_jar_file = new javax.swing.JLabel();
        label_ivy_resolved_libs_dir = new javax.swing.JLabel();
        label_module_action_class = new javax.swing.JLabel();
        label_module_dir = new javax.swing.JLabel();
        label_module_name = new javax.swing.JLabel();
        label_module_path = new javax.swing.JLabel();
        label_module_version = new javax.swing.JLabel();
        label_opencms_version = new javax.swing.JLabel();
        label_package_path = new javax.swing.JLabel();
        label_password = new javax.swing.JLabel();
        label_username = new javax.swing.JLabel();
        label_script_dir = new javax.swing.JLabel();
        label_sync_over_cmis = new javax.swing.JLabel();
        label_use_exclusion_file = new javax.swing.JLabel();
        textfield_acPackage = new javax.swing.JTextField();
        textfield_cms_repo_url = new javax.swing.JTextField();
        textfield_cms_repo_id = new javax.swing.JTextField();
        textfield_cms_servlet_mapping = new javax.swing.JTextField();
        textfield_cms_sync = new javax.swing.JTextField();
        textfield_cms_web_inf_dir = new javax.swing.JTextField();
        textfield_exclusion_file = new javax.swing.JTextField();
        textfield_included_sync_folders = new javax.swing.JTextField();
        textfield_ivy_install_version = new javax.swing.JTextField();
        textfield_ivy_jar_dir = new javax.swing.JTextField();
        textfield_ivy_jar_file = new javax.swing.JTextField();
        textfield_ivy_resolved_libs_dir = new javax.swing.JTextField();
        textfield_module_action_class = new javax.swing.JTextField();
        textfield_module_dir = new javax.swing.JTextField();
        textfield_module_name = new javax.swing.JTextField();
        textfield_module_path = new javax.swing.JTextField();
        textfield_module_version = new javax.swing.JTextField();
        textfield_opencms_version = new javax.swing.JTextField();
        textfield_package_path = new javax.swing.JTextField();
        checkbox_use_exclusion_file = new javax.swing.JCheckBox();
        checkbox_sync_over_cmis = new javax.swing.JCheckBox();
        textfield_script_dir = new javax.swing.JTextField();
        textfield_password = new javax.swing.JTextField();
        textfield_username = new javax.swing.JTextField();
        textfield_classpath_compile_jar = new javax.swing.JTextField();
        button_cms_sync = new javax.swing.JButton();
        button_cms_web_inf_dir = new javax.swing.JButton();
        button_ivy_exclusion_file = new javax.swing.JButton();
        button_ivy_jar_dir = new javax.swing.JButton();
        button_ivy_jar_file = new javax.swing.JButton();
        button_ivy_resolved_lib_dir = new javax.swing.JButton();
        button_package_path = new javax.swing.JButton();
        button_script_dir = new javax.swing.JButton();
        button_save = new javax.swing.JButton();
        button_cancel = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(label_acPackage, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_acPackage.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_cms_repo_id, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_cms_repo_id.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_cms_repo_url, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_cms_repo_url.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_cms_servlet_mapping, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_cms_servlet_mapping.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_cms_sync, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_cms_sync.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_cms_web_inf_dir, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_cms_web_inf_dir.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_classpath_compile_jar, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_classpath_compile_jar.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_exclusion_file, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_exclusion_file.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_included_sync_folders, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_included_sync_folders.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_ivy_install_version, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_ivy_install_version.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_ivy_jar_dir, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_ivy_jar_dir.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_ivy_jar_file, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_ivy_jar_file.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_ivy_resolved_libs_dir, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_ivy_resolved_libs_dir.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_module_action_class, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_module_action_class.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_module_dir, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_module_dir.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_module_name, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_module_name.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_module_path, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_module_path.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_module_version, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_module_version.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_opencms_version, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_opencms_version.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_package_path, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_package_path.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_password, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_password.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_username, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_username.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_script_dir, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_script_dir.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_sync_over_cmis, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_sync_over_cmis.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(label_use_exclusion_file, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.label_use_exclusion_file.text")); // NOI18N

        textfield_acPackage.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_acPackage.text")); // NOI18N

        textfield_cms_repo_url.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_cms_repo_url.text")); // NOI18N

        textfield_cms_repo_id.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_cms_repo_id.text")); // NOI18N

        textfield_cms_servlet_mapping.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_cms_servlet_mapping.text")); // NOI18N

        textfield_cms_sync.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_cms_sync.text")); // NOI18N

        textfield_cms_web_inf_dir.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_cms_web_inf_dir.text")); // NOI18N

        textfield_exclusion_file.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_exclusion_file.text")); // NOI18N

        textfield_included_sync_folders.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_included_sync_folders.text")); // NOI18N

        textfield_ivy_install_version.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_ivy_install_version.text")); // NOI18N

        textfield_ivy_jar_dir.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_ivy_jar_dir.text")); // NOI18N

        textfield_ivy_jar_file.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_ivy_jar_file.text")); // NOI18N

        textfield_ivy_resolved_libs_dir.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_ivy_resolved_libs_dir.text")); // NOI18N

        textfield_module_action_class.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_module_action_class.text")); // NOI18N

        textfield_module_dir.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_module_dir.text")); // NOI18N

        textfield_module_name.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_module_name.text")); // NOI18N

        textfield_module_path.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_module_path.text")); // NOI18N

        textfield_module_version.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_module_version.text")); // NOI18N

        textfield_opencms_version.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_opencms_version.text")); // NOI18N

        textfield_package_path.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_package_path.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(checkbox_use_exclusion_file, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.checkbox_use_exclusion_file.text")); // NOI18N
        checkbox_use_exclusion_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_use_exclusion_fileActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(checkbox_sync_over_cmis, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.checkbox_sync_over_cmis.text")); // NOI18N
        checkbox_sync_over_cmis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkbox_sync_over_cmisActionPerformed(evt);
            }
        });

        textfield_script_dir.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_script_dir.text")); // NOI18N
        textfield_script_dir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_script_dirActionPerformed(evt);
            }
        });

        textfield_password.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_password.text")); // NOI18N
        textfield_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_passwordActionPerformed(evt);
            }
        });

        textfield_username.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_username.text")); // NOI18N
        textfield_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_usernameActionPerformed(evt);
            }
        });

        textfield_classpath_compile_jar.setText(org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.textfield_classpath_compile_jar.text")); // NOI18N
        textfield_classpath_compile_jar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfield_classpath_compile_jarActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_cms_sync, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.button_cms_sync.text")); // NOI18N
        button_cms_sync.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_cms_syncActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_cms_web_inf_dir, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.button_cms_web_inf_dir.text")); // NOI18N
        button_cms_web_inf_dir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_cms_web_inf_dirActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_ivy_exclusion_file, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.button_ivy_exclusion_file.text")); // NOI18N
        button_ivy_exclusion_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ivy_exclusion_fileActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_ivy_jar_dir, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.button_ivy_jar_dir.text")); // NOI18N
        button_ivy_jar_dir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ivy_jar_dirActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_ivy_jar_file, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.button_ivy_jar_file.text")); // NOI18N
        button_ivy_jar_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ivy_jar_fileActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_ivy_resolved_lib_dir, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.button_ivy_resolved_lib_dir.text")); // NOI18N
        button_ivy_resolved_lib_dir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ivy_resolved_lib_dirActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_package_path, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.button_package_path.text")); // NOI18N
        button_package_path.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_package_pathActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_script_dir, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.button_script_dir.text")); // NOI18N
        button_script_dir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_script_dirActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_save, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.button_save.text")); // NOI18N
        button_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_saveActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(button_cancel, org.openide.util.NbBundle.getMessage(ModulePropertiesPluginPanel.class, "ModulePropertiesPluginPanel.button_cancel.text")); // NOI18N
        button_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_cms_repo_url)
                    .addComponent(label_cms_repo_id)
                    .addComponent(label_cms_servlet_mapping)
                    .addComponent(label_cms_sync)
                    .addComponent(label_cms_web_inf_dir)
                    .addComponent(label_exclusion_file)
                    .addComponent(label_included_sync_folders)
                    .addComponent(label_ivy_install_version)
                    .addComponent(label_ivy_jar_dir)
                    .addComponent(label_ivy_jar_file)
                    .addComponent(label_ivy_resolved_libs_dir)
                    .addComponent(label_module_action_class)
                    .addComponent(label_module_dir)
                    .addComponent(label_module_name)
                    .addComponent(label_module_path)
                    .addComponent(label_module_version)
                    .addComponent(label_opencms_version)
                    .addComponent(label_package_path)
                    .addComponent(label_password)
                    .addComponent(label_username)
                    .addComponent(label_script_dir)
                    .addComponent(label_use_exclusion_file)
                    .addComponent(label_sync_over_cmis)
                    .addComponent(label_classpath_compile_jar)
                    .addComponent(label_acPackage))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textfield_cms_repo_url)
                    .addComponent(textfield_cms_repo_id)
                    .addComponent(textfield_cms_servlet_mapping)
                    .addComponent(textfield_cms_sync)
                    .addComponent(textfield_cms_web_inf_dir)
                    .addComponent(textfield_exclusion_file)
                    .addComponent(textfield_included_sync_folders)
                    .addComponent(textfield_ivy_install_version)
                    .addComponent(textfield_ivy_jar_dir, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addComponent(textfield_ivy_jar_file)
                    .addComponent(textfield_ivy_resolved_libs_dir)
                    .addComponent(textfield_module_action_class)
                    .addComponent(textfield_module_dir)
                    .addComponent(textfield_module_name)
                    .addComponent(textfield_module_path)
                    .addComponent(textfield_module_version)
                    .addComponent(textfield_opencms_version)
                    .addComponent(textfield_package_path)
                    .addComponent(textfield_password)
                    .addComponent(textfield_username)
                    .addComponent(textfield_script_dir)
                    .addComponent(textfield_classpath_compile_jar)
                    .addComponent(textfield_acPackage)
                    .addComponent(checkbox_sync_over_cmis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkbox_use_exclusion_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(button_save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_cms_sync, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_cms_web_inf_dir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_ivy_exclusion_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_ivy_jar_dir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_ivy_jar_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_ivy_resolved_lib_dir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_package_path, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_script_dir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_cancel)
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textfield_acPackage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_acPackage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_classpath_compile_jar)
                    .addComponent(textfield_classpath_compile_jar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_cms_repo_id, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textfield_cms_repo_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label_cms_repo_url)
                    .addComponent(textfield_cms_repo_url, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_cms_servlet_mapping)
                    .addComponent(textfield_cms_servlet_mapping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_cms_sync)
                    .addComponent(textfield_cms_sync, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_cms_sync))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_cms_web_inf_dir)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textfield_cms_web_inf_dir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button_cms_web_inf_dir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_exclusion_file)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textfield_exclusion_file, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button_ivy_exclusion_file)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_included_sync_folders)
                    .addComponent(textfield_included_sync_folders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_ivy_install_version)
                    .addComponent(textfield_ivy_install_version, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_ivy_jar_dir)
                    .addComponent(textfield_ivy_jar_dir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_ivy_jar_dir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_ivy_jar_file)
                    .addComponent(textfield_ivy_jar_file, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_ivy_jar_file))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_ivy_resolved_libs_dir)
                    .addComponent(textfield_ivy_resolved_libs_dir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_ivy_resolved_lib_dir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_module_action_class)
                    .addComponent(textfield_module_action_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_module_dir)
                    .addComponent(textfield_module_dir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_module_name)
                    .addComponent(textfield_module_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_module_path)
                    .addComponent(textfield_module_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_module_version)
                    .addComponent(textfield_module_version, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_opencms_version)
                    .addComponent(textfield_opencms_version, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_package_path)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textfield_package_path, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(button_package_path)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_password)
                    .addComponent(textfield_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_username)
                    .addComponent(textfield_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_script_dir)
                    .addComponent(textfield_script_dir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_script_dir))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_sync_over_cmis, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkbox_sync_over_cmis, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_use_exclusion_file, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkbox_use_exclusion_file, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_save)
                    .addComponent(button_cancel))
                .addGap(21, 21, 21))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 811, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private JFileChooser invokeFileChooser(String dialog, int selection_mode) {
        JFileChooser chooser = new JFileChooser();
        FileUtil.preventFileChooserSymlinkTraversal(chooser, null);
        chooser.setDialogTitle(dialog);
        chooser.setFileSelectionMode(selection_mode);
        return chooser;
    }

    private void setSelectedFileAsPathInTextField(JFileChooser chooser, JTextField textfield) {
        File dir = chooser.getSelectedFile();
        String path = FileUtil.normalizeFile(dir).getAbsolutePath();
        path = path.replace("\\", "/");
        textfield.setText(path);
        textfield.updateUI();
        this.updateUI();
    }

    private void checkbox_use_exclusion_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_use_exclusion_fileActionPerformed
        if (module_properties.containsKey(label_use_exclusion_file.getText())) {
            module_properties.put(label_use_exclusion_file.getText(), Boolean.toString(checkbox_use_exclusion_file.isSelected()));
        }
    }//GEN-LAST:event_checkbox_use_exclusion_fileActionPerformed

    private void checkbox_sync_over_cmisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkbox_sync_over_cmisActionPerformed
        if (module_properties.containsKey(label_sync_over_cmis.getText())) {
            module_properties.put(label_sync_over_cmis.getText(), Boolean.toString(checkbox_sync_over_cmis.isSelected()));
        }
    }//GEN-LAST:event_checkbox_sync_over_cmisActionPerformed

    private void button_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_saveActionPerformed
        BufferedWriter writer = null;
        try {
            File individualConfig = new File(individualConfigPath);
            File backup = new File(individualConfig.getAbsolutePath() + "_backup");
            Files.copy(individualConfig.toPath(), backup.toPath(), StandardCopyOption.REPLACE_EXISTING);

            individualConfig.delete();
            individualConfig.createNewFile();
            //updateComponentsWithPropertyValues();

            StringBuilder builder = new StringBuilder();
            FileWriter filewriter = new FileWriter(individualConfig);
            writer = new BufferedWriter(filewriter);
            for (String propertyname : module_properties.stringPropertyNames()) {
                builder.append(propertyname).append("=").append(module_properties.getProperty(propertyname)).append("\n");
            }

            writer.write(builder.toString());
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setVisible(false);
            frame.setEnabled(false);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }//GEN-LAST:event_button_saveActionPerformed

    private void addFileChooserActionPerformed(JFileChooser chooser, JTextField textfield, java.awt.event.ActionEvent evt) {
        if (evt.getActionCommand().equalsIgnoreCase("ApproveSelection")) {
            setSelectedFileAsPathInTextField(chooser, textfield);
        }
    }

    private void button_cms_syncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cms_syncActionPerformed
        final JFileChooser chooser = invokeFileChooser("Select Sync Path", JFileChooser.DIRECTORIES_ONLY);
        chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileChooserActionPerformed(chooser, textfield_cms_sync, evt);
            }
        });
        chooser.showOpenDialog(chooser);
    }//GEN-LAST:event_button_cms_syncActionPerformed

    private void button_cms_web_inf_dirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cms_web_inf_dirActionPerformed
        final JFileChooser chooser = invokeFileChooser("Select OpenCms WEB-INF", JFileChooser.DIRECTORIES_ONLY);
        chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileChooserActionPerformed(chooser, textfield_cms_web_inf_dir, evt);
            }
        });
        chooser.showOpenDialog(chooser);
    }//GEN-LAST:event_button_cms_web_inf_dirActionPerformed

    private void button_ivy_exclusion_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ivy_exclusion_fileActionPerformed
        final JFileChooser chooser = invokeFileChooser("Select Exclusion File", JFileChooser.FILES_ONLY);
        chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileChooserActionPerformed(chooser, textfield_exclusion_file, evt);
            }
        });
        chooser.showOpenDialog(chooser);
    }//GEN-LAST:event_button_ivy_exclusion_fileActionPerformed

    private void button_ivy_jar_dirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ivy_jar_dirActionPerformed
        final JFileChooser chooser = invokeFileChooser("Select Ivy jar Dir", JFileChooser.DIRECTORIES_ONLY);
        chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileChooserActionPerformed(chooser, textfield_ivy_jar_dir, evt);
            }
        });
        chooser.showOpenDialog(chooser);
    }//GEN-LAST:event_button_ivy_jar_dirActionPerformed

    private void button_ivy_jar_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ivy_jar_fileActionPerformed
        final JFileChooser chooser = invokeFileChooser("Select ivy Jar File", JFileChooser.FILES_ONLY);
        chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileChooserActionPerformed(chooser, textfield_ivy_jar_file, evt);
            }
        });
        chooser.showOpenDialog(chooser);
    }//GEN-LAST:event_button_ivy_jar_fileActionPerformed

    private void button_ivy_resolved_lib_dirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ivy_resolved_lib_dirActionPerformed
        final JFileChooser chooser = invokeFileChooser("Select Ivy Resolved Libraries Directory", JFileChooser.DIRECTORIES_ONLY);
        chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileChooserActionPerformed(chooser, textfield_ivy_resolved_libs_dir, evt);
            }
        });
        chooser.showOpenDialog(chooser);
    }//GEN-LAST:event_button_ivy_resolved_lib_dirActionPerformed

    private void button_package_pathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_package_pathActionPerformed
        final JFileChooser chooser = invokeFileChooser("Select Module Package Path", JFileChooser.DIRECTORIES_ONLY);
        chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileChooserActionPerformed(chooser, textfield_package_path, evt);
            }
        });
        chooser.showOpenDialog(chooser);
    }//GEN-LAST:event_button_package_pathActionPerformed

    private void button_script_dirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_script_dirActionPerformed

        final JFileChooser chooser = invokeFileChooser("Select CmsShell Script Directory", JFileChooser.DIRECTORIES_ONLY);
        chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileChooserActionPerformed(chooser, textfield_script_dir, evt);
            }
        });
        chooser.showOpenDialog(chooser);
    }//GEN-LAST:event_button_script_dirActionPerformed

    private void button_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cancelActionPerformed
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setVisible(false);
        frame.setEnabled(false);
    }//GEN-LAST:event_button_cancelActionPerformed

    private void textfield_script_dirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_script_dirActionPerformed
        if (module_properties.containsKey(label_script_dir.getText())) {
            module_properties.put(label_script_dir.getText(), textfield_script_dir.getText());
        }
    }//GEN-LAST:event_textfield_script_dirActionPerformed

    private void textfield_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_passwordActionPerformed
        if (module_properties.containsKey(label_password.getText())) {
            module_properties.put(label_password.getText(), textfield_password.getText());
        }
    }//GEN-LAST:event_textfield_passwordActionPerformed

    private void textfield_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_usernameActionPerformed
        if (module_properties.containsKey(label_username.getText())) {
            module_properties.put(label_username.getText(), textfield_username.getText());
        }
    }//GEN-LAST:event_textfield_usernameActionPerformed

    private void textfield_classpath_compile_jarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfield_classpath_compile_jarActionPerformed
        if (module_properties.containsKey(label_classpath_compile_jar.getText())) {
            module_properties.put(label_classpath_compile_jar.getText(), textfield_classpath_compile_jar.getText());
        }
    }//GEN-LAST:event_textfield_classpath_compile_jarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_cancel;
    private javax.swing.JButton button_cms_sync;
    private javax.swing.JButton button_cms_web_inf_dir;
    private javax.swing.JButton button_ivy_exclusion_file;
    private javax.swing.JButton button_ivy_jar_dir;
    private javax.swing.JButton button_ivy_jar_file;
    private javax.swing.JButton button_ivy_resolved_lib_dir;
    private javax.swing.JButton button_package_path;
    private javax.swing.JButton button_save;
    private javax.swing.JButton button_script_dir;
    private javax.swing.JCheckBox checkbox_sync_over_cmis;
    private javax.swing.JCheckBox checkbox_use_exclusion_file;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_acPackage;
    private javax.swing.JLabel label_classpath_compile_jar;
    private javax.swing.JLabel label_cms_repo_id;
    private javax.swing.JLabel label_cms_repo_url;
    private javax.swing.JLabel label_cms_servlet_mapping;
    private javax.swing.JLabel label_cms_sync;
    private javax.swing.JLabel label_cms_web_inf_dir;
    private javax.swing.JLabel label_exclusion_file;
    private javax.swing.JLabel label_included_sync_folders;
    private javax.swing.JLabel label_ivy_install_version;
    private javax.swing.JLabel label_ivy_jar_dir;
    private javax.swing.JLabel label_ivy_jar_file;
    private javax.swing.JLabel label_ivy_resolved_libs_dir;
    private javax.swing.JLabel label_module_action_class;
    private javax.swing.JLabel label_module_dir;
    private javax.swing.JLabel label_module_name;
    private javax.swing.JLabel label_module_path;
    private javax.swing.JLabel label_module_version;
    private javax.swing.JLabel label_opencms_version;
    private javax.swing.JLabel label_package_path;
    private javax.swing.JLabel label_password;
    private javax.swing.JLabel label_script_dir;
    private javax.swing.JLabel label_sync_over_cmis;
    private javax.swing.JLabel label_use_exclusion_file;
    private javax.swing.JLabel label_username;
    private javax.swing.JTextField textfield_acPackage;
    private javax.swing.JTextField textfield_classpath_compile_jar;
    private javax.swing.JTextField textfield_cms_repo_id;
    private javax.swing.JTextField textfield_cms_repo_url;
    private javax.swing.JTextField textfield_cms_servlet_mapping;
    private javax.swing.JTextField textfield_cms_sync;
    private javax.swing.JTextField textfield_cms_web_inf_dir;
    private javax.swing.JTextField textfield_exclusion_file;
    private javax.swing.JTextField textfield_included_sync_folders;
    private javax.swing.JTextField textfield_ivy_install_version;
    private javax.swing.JTextField textfield_ivy_jar_dir;
    private javax.swing.JTextField textfield_ivy_jar_file;
    private javax.swing.JTextField textfield_ivy_resolved_libs_dir;
    private javax.swing.JTextField textfield_module_action_class;
    private javax.swing.JTextField textfield_module_dir;
    private javax.swing.JTextField textfield_module_name;
    private javax.swing.JTextField textfield_module_path;
    private javax.swing.JTextField textfield_module_version;
    private javax.swing.JTextField textfield_opencms_version;
    private javax.swing.JTextField textfield_package_path;
    private javax.swing.JTextField textfield_password;
    private javax.swing.JTextField textfield_script_dir;
    private javax.swing.JTextField textfield_username;
    // End of variables declaration//GEN-END:variables
}
