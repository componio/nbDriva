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
package net.componio.opencms.projectstructure.plugin;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.templates.TemplateRegistration;
import org.netbeans.spi.project.ui.support.ProjectChooser;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

// TODO define position attribute
@TemplateRegistration(folder = "Project/OpenCms", displayName = "#OpenCmsProjectStructureTemplate_displayName", description = "OpenCmsProjectStructureTemplateDescription.html", iconBase = "net/componio/opencms/projectstructure/plugin/OpenCmsProjectStructureTemplate.png", content = "OpenCmsProjectStructureTemplateProject.zip")
@Messages("OpenCmsProjectStructureTemplate_displayName=OpenCmsProjectStructureTemplate")
public class OpenCmsProjectStructureTemplateWizardIterator implements WizardDescriptor./*Progress*/InstantiatingIterator {

    private int index;
    private WizardDescriptor.Panel[] panels;
    private WizardDescriptor wiz;

    public OpenCmsProjectStructureTemplateWizardIterator() {
    }

    public static OpenCmsProjectStructureTemplateWizardIterator createIterator() {
        return new OpenCmsProjectStructureTemplateWizardIterator();
    }

    private WizardDescriptor.Panel[] createPanels() {
        return new WizardDescriptor.Panel[]{
            new OpenCmsProjectStructureTemplateWizardPanel(),};
    }

    private String[] createSteps() {
        return new String[]{
            NbBundle.getMessage(OpenCmsProjectStructureTemplateWizardIterator.class, "LBL_CreateProjectStep")
        };
    }

    public Set/*<FileObject>*/ instantiate(/*ProgressHandle handle*/) throws IOException {
        Set<FileObject> resultSet = new LinkedHashSet<FileObject>();
        File dirF = FileUtil.normalizeFile((File) wiz.getProperty("projdir"));
        dirF.mkdirs();

        FileObject template = Templates.getTemplate(wiz);
        FileObject dir = FileUtil.toFileObject(dirF);
        unZipFile(template.getInputStream(), dir);

        // Always open top dir as a project:
        resultSet.add(dir);
        // Look for nested projects to open as well:
        Enumeration<? extends FileObject> e = dir.getFolders(true);
        while (e.hasMoreElements()) {
            FileObject subfolder = e.nextElement();
            if (ProjectManager.getDefault().isProject(subfolder)) {
                resultSet.add(subfolder);
            }
        }

        File parent = dirF.getParentFile();
        if (parent != null && parent.exists()) {
            ProjectChooser.setProjectsFolder(parent);
        }

        return resultSet;
    }

    public void initialize(WizardDescriptor wiz) {
        this.wiz = wiz;
        index = 0;
        panels = createPanels();
        // Make sure list of steps is accurate.
        String[] steps = createSteps();
        for (int i = 0; i < panels.length; i++) {
            Component c = panels[i].getComponent();
            if (steps[i] == null) {
                // Default step name to component name of panel.
                // Mainly useful for getting the name of the target
                // chooser to appear in the list of steps.
                steps[i] = c.getName();
            }
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent) c;
                // Step #.
                // TODO if using org.openide.dialogs >= 7.8, can use WizardDescriptor.PROP_*:
                jc.putClientProperty("WizardPanel_contentSelectedIndex", new Integer(i));
                // Step name (actually the whole list for reference).
                jc.putClientProperty("WizardPanel_contentData", steps);
            }
        }
    }

    public void uninitialize(WizardDescriptor wiz) {
        this.wiz.putProperty("projdir", null);
        this.wiz.putProperty("name", null);
        this.wiz = null;
        panels = null;
    }

    public String name() {
        return MessageFormat.format("{0} of {1}",
                new Object[]{new Integer(index + 1), new Integer(panels.length)});
    }

    public boolean hasNext() {
        return index < panels.length - 1;
    }

    public boolean hasPrevious() {
        return index > 0;
    }

    public void nextPanel() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
    }

    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
    }

    public WizardDescriptor.Panel current() {
        return panels[index];
    }

    // If nothing unusual changes in the middle of the wizard, simply:
    public final void addChangeListener(ChangeListener l) {
    }

    public final void removeChangeListener(ChangeListener l) {
    }

    private void unZipFile(InputStream source, FileObject projectRoot) throws IOException {
        try {
            ZipInputStream str = new ZipInputStream(source);
            ZipEntry entry;
            while ((entry = str.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    FileUtil.createFolder(projectRoot, entry.getName());
                } else {
                    FileObject fo = FileUtil.createData(projectRoot, entry.getName());
                    if ("nbproject/project.xml".equals(entry.getName())) {
                        // Special handling for setting name of Ant-based projects; customize as needed:
                        filterProjectXML(fo, str, projectRoot.getNameExt());
                    } else if ("default.properties".equals(entry.getName())) {
                        setDefaultProperties(str, fo);
                    } else {
                        writeFile(str, fo);
                    }
                }
            }
        } finally {
            source.close();
        }
    }

    private static String copyStreamToString(InputStream str) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(str));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            builder.append(line.trim().replace(System.getProperty("line.separator"), "")).append("\n");
        }
        return builder.toString();
    }

    /**
     * Tries to get every line for a string (split by line separator of the
     * given operating system
     *
     * @param str
     * @return
     */
    private static String[] getEntriesFromString(String str) {
        String[] entries = str.split("\n");
        return entries;
    }

    /**
     * splits an entry of a property file otherwise return null
     *
     * @param entry
     * @return
     */
    private static String[] splitKeyAndValueFromPropertyEntry(String entry) {
        String[] splitted = entry.split("=");
        if (splitted.length == 2) {
            splitted[0] = splitted[0].trim();
            splitted[1] = splitted[1].trim().replace("\n", "");
            return splitted;
        } else {
            return null;
        }
    }

    private void setDefaultProperties(InputStream str, FileObject fo) throws IOException {
        String content = copyStreamToString(str);
        String[] entries = getEntriesFromString(content);
        String propk;
        StringBuilder builder = new StringBuilder();
        for (String entry : entries) {
            String[] splitted = splitKeyAndValueFromPropertyEntry(entry);
            if (splitted != null) {
                propk = splitted[0];
                if (propk.equals("modulename")) {
                    entry = propk + "=" + getProjectName();
                }
                if (propk.equals("opencms.version")) {
                    entry = propk + "=" + getOpenCmsVersion();
                }
                if (propk.equals("cmsWebInfDir")) {
                    entry = propk + "=" + getOpenCmsWebInf();
                }
            }
            builder.append(entry).append("\n");
        }
        InputStream in = new ByteArrayInputStream(builder.toString().getBytes());
        writeFile(in, fo);
        in.close();
    }

    private String getProjectName() {
        File dirF = FileUtil.normalizeFile((File) wiz.getProperty("projdir"));
        FileObject dir = FileUtil.toFileObject(dirF);
        return dir.getNameExt();
    }

    private String getOpenCmsVersion() {
        String opencmsVersion = (String) wiz.getProperty("opencmsVersion");
        return opencmsVersion;
    }

    private String getOpenCmsWebInf() {
        String openCmsWebInf = (String) wiz.getProperty("opencmsWebInf");
        return openCmsWebInf;
    }

    private static void writeFile(InputStream str, FileObject fo) throws IOException {
        OutputStream out = fo.getOutputStream();
        try {
            FileUtil.copy(str, out);
        } finally {
            out.close();
        }
    }

    private static void filterProjectXML(FileObject fo, ZipInputStream str, String name) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileUtil.copy(str, baos);
            Document doc = XMLUtil.parse(new InputSource(new ByteArrayInputStream(baos.toByteArray())), false, false, null, null);
            NodeList nl = doc.getDocumentElement().getElementsByTagName("name");
            if (nl != null) {
                for (int i = 0; i < nl.getLength(); i++) {
                    Element el = (Element) nl.item(i);
                    if (el.getParentNode() != null && "data".equals(el.getParentNode().getNodeName())) {
                        NodeList nl2 = el.getChildNodes();
                        if (nl2.getLength() > 0) {
                            nl2.item(0).setNodeValue(name);
                        }
                        break;
                    }
                }
            }
            OutputStream out = fo.getOutputStream();
            try {
                XMLUtil.write(doc, out, "UTF-8");
            } finally {
                out.close();
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            writeFile(str, fo);
        }

    }
}
