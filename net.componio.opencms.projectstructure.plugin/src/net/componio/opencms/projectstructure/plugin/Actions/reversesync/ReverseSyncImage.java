/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.componio.opencms.projectstructure.plugin.actions.reversesync;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Build",
        id = "net.componio.opencms.projectstructure.plugin.actions.reversesync.ReverseSyncImage"
)
@ActionRegistration(
        displayName = "#CTL_ReverseSyncImage"
)
@ActionReference(path = "Loaders/image/png-gif-jpeg-bmp/Actions", position=5,separatorAfter = 10)
@Messages("CTL_ReverseSyncImage=Reverse Sync")
public final class ReverseSyncImage extends BaseReverseSyncAction {
    public ReverseSyncImage() {
    }
}
