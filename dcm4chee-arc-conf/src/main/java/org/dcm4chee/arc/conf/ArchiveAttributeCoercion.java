/*
 * *** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at https://github.com/gunterze/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * J4Care.
 * Portions created by the Initial Developer are Copyright (C) 2015
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * See @authors listed below
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * *** END LICENSE BLOCK *****
 */

package org.dcm4chee.arc.conf;

import org.dcm4che3.net.Dimse;
import org.dcm4che3.net.TransferCapability;

/**
 * @author Gunter Zeilinger <gunterze@gmail.com>
 * @since Oct 2015
 */
public class ArchiveAttributeCoercion {

    private final String commonName;
    private int priority;
    private Dimse dimse;
    private TransferCapability.Role role;
    private String[] sopClasses = {};
    private String[] aeTitles = {};
    private String xsltStylesheetURI;

    public ArchiveAttributeCoercion(String commonName) {
        this.commonName = commonName;
    }

    public String getCommonName() {
        return commonName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Dimse getDIMSE() {
        return dimse;
    }

    public void setDIMSE(Dimse dimse) {
        this.dimse = dimse;
    }

    public TransferCapability.Role getRole() {
        return role;
    }

    public void setRole(TransferCapability.Role role) {
        this.role = role;
    }

    public String[] getSOPClasses() {
        return sopClasses;
    }

    public void setSOPClasses(String... sopClasses) {
        this.sopClasses = sopClasses;
    }

    public String[] getAETitles() {
        return aeTitles;
    }

    public void setAETitles(String... aeTitles) {
        this.aeTitles = aeTitles;
    }

    public String getXSLTStylesheetURI() {
        return xsltStylesheetURI;
    }

    public void setXSLTStylesheetURI(String xsltStylesheetURI) {
        this.xsltStylesheetURI = xsltStylesheetURI;
    }

    public boolean match(String aet, TransferCapability.Role role, Dimse dimse, String sopClass) {
        return this.role == role && this.dimse == null
                && isEmptyOrContains(aeTitles, aet)
                && isEmptyOrContains(sopClasses, sopClass);
    }

    private static boolean isEmptyOrContains(Object[] a, Object o) {
        if (a.length == 0)
            return true;

        for (Object o1 : a)
            if (o1.equals(o))
                return true;
        return false;
    }
}
