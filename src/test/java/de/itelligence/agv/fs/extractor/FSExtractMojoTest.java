package de.itelligence.agv.fs.extractor;

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

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
        myMojo.execute();

        
    }
}
