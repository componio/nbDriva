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
        id = "net.componio.opencms.projectstructure.plugin.actions.RerverseSyncUnknownFile"
)
@ActionRegistration(
        displayName = "#CTL_RerverseSyncUnknownFile"
)
@ActionReference(path = "Loaders/content/unknown/Actions", position=5, separatorAfter = 10)
@Messages("CTL_RerverseSyncUnknownFile=Reverse Sync")
public final class RerverseSyncUnknownFile extends BaseReverseSyncAction {
    RerverseSyncUnknownFile() {
    }
}
