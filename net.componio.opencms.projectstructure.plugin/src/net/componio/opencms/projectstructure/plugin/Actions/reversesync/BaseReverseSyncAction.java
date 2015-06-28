/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.componio.opencms.projectstructure.plugin.actions.reversesync;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.AbstractAction;
import javax.swing.Action;
import net.componio.opencms.projectstructure.plugin.helper.ModulePropertiesHelper;
import org.apache.tools.ant.module.api.support.ActionUtils;
import org.openide.actions.OpenAction;
import org.openide.awt.DynamicMenuContent;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.util.ContextAwareAction;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Tom
 */
public class BaseReverseSyncAction extends AbstractAction implements ActionListener, ContextAwareAction {

    private final DataObject context;
    private static OpenAction action = new OpenAction();
    
    public static OpenAction getOpenAction() {
        return action;
    }
    
    BaseReverseSyncAction() {
        context = null;
    }

    public BaseReverseSyncAction(DataObject cont) {
        context = cont;
        this.setEnabled(verifyProject(context.getPrimaryFile()) 
                && context.getPrimaryFile().getPath().contains("Web"));
        putValue(DynamicMenuContent.HIDE_WHEN_DISABLED, true);
        putValue(NAME, "Reverse Sync");
    }

    private boolean verifyProject(FileObject currentFileObject) {
        try {
            FileObject tmp = currentFileObject.getFileObject("buildModuleOperationsWithCmsShell.xml");
            if (tmp != null && (new File(tmp.getPath())).exists()) {
                return true;
            } else {
                if (currentFileObject.getParent() != null) {
                    return verifyProject(currentFileObject.getParent());
                } else {
                    return false;
                }
            }
        } catch (Throwable t) {
            return false;
        }
    }

    private FileObject getQueryFileObject(FileObject currentFileObject, String queryFileName) {
        try {
            FileObject tmp = currentFileObject.getFileObject(queryFileName);
            if (tmp != null && (new File(tmp.getPath())).exists()) {
                return tmp;
            } else {
                if (currentFileObject.getParent() != null) {
                    return getQueryFileObject(currentFileObject.getParent(), queryFileName);
                } else {
                    return null;
                }
            }
        } catch (Throwable t) {
            return null;
        }
    }

    private boolean generateCmsScript() throws IOException {
        FileObject scriptFolder = getQueryFileObject(context.getPrimaryFile(), "scripts");
        FileObject individualProp = getQueryFileObject(context.getPrimaryFile(), "individual.properties");
        FileObject defaultProp = getQueryFileObject(context.getPrimaryFile(), "default.properties");

        Properties moduleProperties = ModulePropertiesHelper.getModuleProperties(
                individualProp != null ? individualProp.getPath() : "", defaultProp != null ? defaultProp.getPath() : "");
        if (!moduleProperties.isEmpty()) {
            String username = moduleProperties.getProperty("username");
            String password = moduleProperties.getProperty("password");
            String modulepath = moduleProperties.getProperty("modulepath");
            String modulename = moduleProperties.getProperty("modulename");
            File scriptDir = new File(scriptFolder.getPath());
            String scriptName = "reverseSyncSingle.txt";
            if (scriptDir.exists() && scriptDir.isDirectory()) {
                String syncPath = context.getPrimaryFile().getPath().replace("\\", "/");
                File scriptFile = new File(scriptDir.getAbsolutePath() + "/" + scriptName);
                scriptFile.delete();
                scriptFile.createNewFile();
                StringBuilder content = new StringBuilder();
                content.append("echo \"on\"\n").append("login ").append("\"").append(username).append("\"")
                        .append(" ").append("\"").append(password).append("\"\n")
                        .append("setCurrentProject \"Offline\"\n")
                        .append("reverseSyncSingle ").append("\"").append(syncPath).append("\" ")
                        .append("\"").append(modulename).append("\"").append("\n")
                        .append("unlockCurrentProject\n")
                        .append("publishResources ")
                        .append("\"").append(modulepath).append("/").append(modulename).append("/\" ").append("true").append("\n");
                BufferedWriter writer = new BufferedWriter(new FileWriter(scriptFile));
                writer.append(content);
                writer.flush();
                writer.close();
            }
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getActionCommand().equals("Reverse Sync")) {
            FileObject buildScript = getQueryFileObject(context.getPrimaryFile(), "build.xml");
            try {
                boolean success = generateCmsScript();
                if (success) {
                    ActionUtils.runTarget(buildScript, new String[]{"build_cms_reverse_single"}, null);
                }
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IllegalArgumentException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        else {
            //action.setEnabled(true);
            getOpenAction().createContextAwareInstance(this.context.getNodeDelegate().getLookup());
            getOpenAction().actionPerformed(ev);
        }
    }

    @Override
    public Action createContextAwareInstance(Lookup lkp) {
        DataNode no = lkp.lookup(DataNode.class);
        return new BaseReverseSyncAction(no.getDataObject());
    }
}
