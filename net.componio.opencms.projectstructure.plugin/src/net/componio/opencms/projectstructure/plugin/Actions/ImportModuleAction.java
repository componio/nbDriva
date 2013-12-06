/*
* This library is part of PiBo(tm)
*
* Copyright (c) componio GmbH, (http://www.componio.net)
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 3 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* For further information about componio GmbH, please see the
* company website: http://www.componio.net
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/
package net.componio.opencms.projectstructure.plugin.Actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.Action.NAME;
import org.apache.tools.ant.module.api.support.ActionUtils;
import org.netbeans.api.project.Project;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.awt.DynamicMenuContent;
import org.openide.filesystems.FileObject;
import org.openide.util.ContextAwareAction;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

@ActionID(
        category = "Build",
        id = "net.componio.opencms.projectstructure.plugin.Actions.ImportModuleAction")
@ActionRegistration(
        displayName = "#CTL_ImportModuleAction")
@ActionReferences({
    
    @ActionReference(path = "Projects/Actions",position = 10)
})
@Messages("CTL_ImportModuleAction=Import Module")
public final class ImportModuleAction extends AbstractAction implements LookupListener, ContextAwareAction {

    private Lookup context;
    private Project project;
    private Lookup.Result result;

    ImportModuleAction() {
        this(Utilities.actionsGlobalContext());
    }

    ImportModuleAction(Lookup context) {
        this.context = context;
        this.project = context.lookup(Project.class);
        this.result = this.context.lookupResult(Project.class);
        result.addLookupListener(this);
        resultChanged(new LookupEvent(result));
        putValue(DynamicMenuContent.HIDE_WHEN_DISABLED, true);
        putValue(NAME, Bundle.CTL_ImportModuleAction());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileObject buildScript = project.getProjectDirectory().getFileObject("build.xml");
        try {
            ActionUtils.runTarget(buildScript, new String[]{"build_import_module"}, null);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IllegalArgumentException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        this.context = Utilities.actionsGlobalContext();
        this.project = context.lookup(Project.class);
        try {
            FileObject tmp = project.getProjectDirectory().getFileObject("buildModuleOperationsWithCmsShell.xml");
            if (tmp != null) {
                if ((new File(tmp.getPath())).exists()) {
                    this.setEnabled(true);
                }
            } else {
                this.setEnabled(false);
            }
        } catch (Throwable t) {
            this.setEnabled(false);
        }
    }

    @Override
    public Action createContextAwareInstance(Lookup lkp) {
        return new ImportModuleAction(lkp);
    }
}
