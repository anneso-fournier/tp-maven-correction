package fr.gphy.m2.keuv.protviewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * The ProteinStructureRetriever builds URL for 3D structure file (PDB file)
 * from the first Uniprot reference to 3D structure. Please see textual Uniprot
 * Entry and PDB file format.
 *
 * @author keuv
 */
public class PDBUrlBuilder {

    /**
     * Parse an Uniprot entry according to its Uniprot ID, extract the first PDB
     * id, then return the URL for this PDB id (null if none).
     *
     * @param uniprotId An Uniprot ID.
     * @return A PDB File URL.
     * @throws IOException An IOException is thrown if the URL for this Uniprot
     * ID can not be read.
     */
    public static URL getFirstPdbUrlFromUniprotEntry(String uniprotId) throws IOException {
        System.setProperty("http.proxyHost", "cache.univ-poitiers.fr");
        System.setProperty("http.proxyPort", "3128");

        URL url = new URL("http://www.uniprot.org/uniprot/" + uniprotId + ".txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String line;
        while ((line = in.readLine()) != null) {
            if (line.matches("^DR\\s+PDB;.+$")) {
                in.close();
                return new URL("http://www.ebi.ac.uk/pdbe/entry-files/pdb" + line.substring(10, 14).toLowerCase() + ".ent");
            }
        }
        in.close();
        return null;
    }

    /**
     * Parse an Uniprot entry according to its Uniprot ID, extract each PDB Id
     * then return the List of URLs for these PDB ids (empty list if none).
     *
     * @param uniprotId An Uniprot ID.
     * @return A PDB File URL.
     * @throws IOException An IOException is thrown if the URL for this Uniprot
     * ID can not be read.
     */
    public static List<URL> getPdbUrlsFromUniprotEntry(String uniprotId) throws IOException {
        URL url = new URL("http://www.uniprot.org/uniprot/" + uniprotId + ".txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        List<URL> list = new ArrayList<URL>();
        String line;
        while ((line = in.readLine()) != null) {
            if (line.matches("^DR\\s+PDB;.+$")) {
                list.add(new URL("http://www.ebi.ac.uk/pdbe/entry-files/pdb" + line.substring(10, 14).toLowerCase() + ".ent"));
            }
        }
        in.close();
        return list;
    }
}
