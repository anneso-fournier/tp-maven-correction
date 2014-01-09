package fr.gphy.m2.keuv.protviewer;

import java.io.IOException;
import java.net.URL;
import org.biojava.bio.structure.Structure;
import org.biojava.bio.structure.gui.BiojavaJmol;
import org.biojava.bio.structure.io.PDBFileReader;

/**
 * The protein structure displayer will automatically download a protein
 * structure file from a given URL (such as
 * "http://www.ebi.ac.uk/pdbe/entry-files/pdb1a00.ent") then display it using
 * Jmol. For more information, please refer to PDB file format and the Jmol
 * software.
 *
 * @author keuv
 */
public class ProtViewer {

    public ProtViewer() {
    }

    public void displayProteinFrom(URL url) throws IOException {

        PDBFileReader pdbr = new PDBFileReader();
        pdbr.setAutoFetch(true);
        Structure struc = pdbr.getStructure(url);

        BiojavaJmol jmolPanel = new BiojavaJmol();
        jmolPanel.setStructure(struc);

        // send some RASMOL style commands to Jmol
//        jmolPanel.evalString("select * ; color chain;");
//        jmolPanel.evalString("select *; spacefill off; wireframe off; backbone 0.4;  ");
    }

    public static void main(String[] args) throws IOException {
        String id = (args.length == 0) ? "P07900" : args[0];
        ProtViewer pv = new ProtViewer();
        URL url = PDBUrlBuilder.getFirstPdbUrlFromUniprotEntry(id);
        pv.displayProteinFrom(url);
    }
}
