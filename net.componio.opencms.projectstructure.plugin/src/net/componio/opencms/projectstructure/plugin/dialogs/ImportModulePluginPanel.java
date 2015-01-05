/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.componio.opencms.projectstructure.plugin.dialogs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.tools.ant.module.api.support.ActionUtils;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;

/**
 *
 * @author Thomas
 */
public class ImportModulePluginPanel extends javax.swing.JPanel {

    final JFileChooser fileChooser = new JFileChooser();
    final List<File> allSelectedFiles = new ArrayList<File>();
    final FileObject buildScript;
    private final File nbDrivaConfigFile;

    /**
     * Creates new form ImportModulePluginPanel
     */
    public ImportModulePluginPanel(FileObject buildScript, File nbDrivaConfigFile, String startPathFileChooser) throws IOException {

        this.buildScript = buildScript;
        initComponents();
        initFileChooser(new File(startPathFileChooser));
        this.nbDrivaConfigFile = nbDrivaConfigFile;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        fileNames = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        addModules = new javax.swing.JButton();
        removeSelectedModules = new javax.swing.JButton();
        importModules = new javax.swing.JButton();

        fileNames.setModel(new javax.swing.AbstractListModel() {
            Object [] items = allSelectedFiles.toArray();
            public int getSize() { return items.length; }
            public Object getElementAt(int i) { return items[i]; }
        });
        jScrollPane1.setViewportView(fileNames);

        jSplitPane1.setRightComponent(jScrollPane1);

        org.openide.awt.Mnemonics.setLocalizedText(addModules, org.openide.util.NbBundle.getMessage(ImportModulePluginPanel.class, "ImportModulePluginPanel.addModules.text")); // NOI18N
        addModules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addModulesActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(removeSelectedModules, org.openide.util.NbBundle.getMessage(ImportModulePluginPanel.class, "ImportModulePluginPanel.removeSelectedModules.text")); // NOI18N
        removeSelectedModules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSelectedModulesActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(importModules, org.openide.util.NbBundle.getMessage(ImportModulePluginPanel.class, "ImportModulePluginPanel.importModules.text")); // NOI18N
        importModules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importModulesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(importModules, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeSelectedModules, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addModules, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(addModules)
                .addGap(5, 5, 5)
                .addComponent(removeSelectedModules)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(importModules)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel1);
        jSplitPane1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addModulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addModulesActionPerformed
        fileChooser.showOpenDialog(fileChooser);
    }//GEN-LAST:event_addModulesActionPerformed

    private void removeSelectedModulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSelectedModulesActionPerformed
        if (!fileNames.isSelectionEmpty()) {
            for (Object o : fileNames.getSelectedValuesList()) {
                File selectedFileName = (o instanceof File) ? (File) o : null;
                if (allSelectedFiles.contains(selectedFileName)) {
                    allSelectedFiles.remove(selectedFileName);
                }
            }
            fileNames.setListData(allSelectedFiles.toArray());
            fileNames.updateUI();
        }
    }//GEN-LAST:event_removeSelectedModulesActionPerformed

    private void importModulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importModulesActionPerformed
        try {
            updateNbDrivaConfigFile();
            ActionUtils.runTarget(buildScript, new String[]{"build_import_module"}, null);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setVisible(false);
            frame.setEnabled(false);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalArgumentException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_importModulesActionPerformed

    private void addFileChooserActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getActionCommand().equalsIgnoreCase("ApproveSelection")) {
            fileNames.setListData(allSelectedFiles.toArray());
            fileNames.updateUI();
        }
    }

    private void initFileChooser(File currentDirectory) {
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(currentDirectory);
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.zip", "zip"));
        fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileChooserActionPerformed(evt);
            }
        });
        //added to keep the files in the order in which they are selected
        fileChooser.addPropertyChangeListener(
                JFileChooser.SELECTED_FILES_CHANGED_PROPERTY,
                new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                List<File> selected = Arrays.asList(fileChooser.getSelectedFiles());
                for (File file : selected) {
                    if (!allSelectedFiles.contains(file)) {
                        allSelectedFiles.add(file);
                    }
                }
            }
        });
    }

    private void updateNbDrivaConfigFile() throws FileNotFoundException, IOException {
        if (nbDrivaConfigFile != null) {
            FileInputStream inputStream = new FileInputStream(nbDrivaConfigFile);
            Properties prop = new Properties();
            prop.load(inputStream);
            inputStream.close();

            //update importModules property in config File
            StringBuffer imports = new StringBuffer("");
            for (File f : allSelectedFiles) {
                String forwardedPath = f.getAbsolutePath();
                forwardedPath = forwardedPath.replace(File.separator, "/");
                imports.append(forwardedPath).append(",");
            }
            String result = imports.toString();
            result = (result.endsWith(","))
                    ? result.substring(0, result.length() - 1) : result;
            //result = result + "\n";
            prop.setProperty("importModules", result);
            FileOutputStream outputStream = new FileOutputStream(nbDrivaConfigFile);
            prop.store(outputStream, null);
            outputStream.close();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addModules;
    private javax.swing.JList fileNames;
    private javax.swing.JButton importModules;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton removeSelectedModules;
    // End of variables declaration//GEN-END:variables
}
