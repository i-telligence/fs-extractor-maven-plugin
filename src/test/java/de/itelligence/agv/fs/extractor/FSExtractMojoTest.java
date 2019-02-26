package de.itelligence.agv.fs.extractor;

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
<<<<<<< HEAD
import org.junit.BeforeClass;
=======
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;
>>>>>>> 1e86382993aa12fbd18fa277cc0d006ad9bf89a2

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
<<<<<<< HEAD
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        String fileName = System.getProperty("fileName");
    }
=======
>>>>>>> 1e86382993aa12fbd18fa277cc0d006ad9bf89a2

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
<<<<<<< HEAD
        
        FSExtractMojo myMojo = (FSExtractMojo) lookupMojo( "fs-extract", pom );
        assertNotNull( myMojo );
        
        long start = System.currentTimeMillis();
     
        myMojo.execute();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        
        System.out.println(timeElapsed);
=======

        
        
        FSExtractMojo myMojo = (FSExtractMojo) lookupMojo( "fs-extract", pom );
        assertNotNull( myMojo );
        myMojo.execute();

>>>>>>> 1e86382993aa12fbd18fa277cc0d006ad9bf89a2
        
    }
}
