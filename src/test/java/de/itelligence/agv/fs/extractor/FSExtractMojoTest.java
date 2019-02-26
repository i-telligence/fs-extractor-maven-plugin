package de.itelligence.agv.fs.extractor;

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.junit.BeforeClass;

import de.itelligence.fs.extractor.FSExtractMojo;


public class FSExtractMojoTest extends AbstractMojoTestCase
{
    /** {@inheritDoc} */
    protected void setUp()
        throws Exception
    {
        // required
        super.setUp();

      
    }
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        String fileName = System.getProperty("fileName");
    }

    /** {@inheritDoc} */
    protected void tearDown()
        throws Exception
    {
        // required
        super.tearDown();

      
    }

    /**
     * @throws Exception if any
     */
    public void testSomething()
        throws Exception
    {
        
        File pom = getTestFile( "src/test/resources/unit/fs-extract-mojo/pom.xml" );
        assertNotNull( pom );
        assertTrue( pom.exists() );
        
        FSExtractMojo myMojo = (FSExtractMojo) lookupMojo( "fs-extract", pom );
        assertNotNull( myMojo );
        
        long start = System.currentTimeMillis();
     
        myMojo.execute();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        
        System.out.println(timeElapsed);
        
    }
}
