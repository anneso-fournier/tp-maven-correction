package fr.gphy.m2.keuv.protviewer;

import java.net.URL;
import junit.framework.TestCase;

/**
 *
 * @author keuv
 */
public class PDBUrlBuilderTest extends TestCase {

    public PDBUrlBuilderTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getFirstPdbUrlFromUniprotEntry method, of class
     * ProteinStructureRetriever. This case should work.
     */
    public void testGetFirstPdbUrlFromUniprotEntry1() throws Exception {
        System.out.println("getFirstPdbUrlFromUniprotEntry - working case");
        String uniprotId = "P07900";
        URL expResult = new URL("http://www.ebi.ac.uk/pdbe/entry-files/pdb1byq.ent");
        URL result = PDBUrlBuilder.getFirstPdbUrlFromUniprotEntry(uniprotId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirstPdbUrlFromUniprotEntry method, of class
     * ProteinStructureRetriever. This case should not work.
     */
    public void testGetFirstPdbUrlFromUniprotEntry2() throws Exception {
        System.out.println("getFirstPdbUrlFromUniprotEntry - not working case");
        String uniprotId = "P69909"; // this have no associated PDB file
        URL result = PDBUrlBuilder.getFirstPdbUrlFromUniprotEntry(uniprotId);
        assertNull(result);
    }

}
