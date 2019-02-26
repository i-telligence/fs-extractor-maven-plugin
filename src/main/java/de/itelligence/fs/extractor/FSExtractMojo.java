package de.itelligence.fs.extractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * Mojo to download and possibly extract FS libraries
 * @author Alex Fuckert
 *
 */
@Mojo(name = "fs-extract",
defaultPhase = LifecyclePhase.GENERATE_RESOURCES,
requiresOnline = false, requiresProject = true,
threadSafe = false)
public class FSExtractMojo extends AbstractMojo {

    @Parameter(defaultValue="${project}", readonly=true, required=true)
    private MavenProject project;
    
    @Parameter(property="version", readonly=true, required=true)
    private String version;
    
    @Parameter(property="user", readonly=true, required=true)
    private String user;
    
    @Parameter(property="password", readonly=true, required=true)
    private String password;
    
    @Parameter(property="baseUrl", defaultValue="http://www.e-spirit.de/download/firstspirit")
    private String baseUrl;
    
    /**
     * Download file represented by specific path.
     * @param CloseableHttpClient client
     * @param String path
     * @throws MojoFailureException if file could not be downloaded
     */
    private void downloadFile(CloseableHttpClient client, String path) throws MojoFailureException{
        
        String filePath = project.getBasedir() + File.separator + "target"+path;

        File file = new File(filePath);
        
        if ( file.exists() ) return;
        
        HttpGet request = new HttpGet(baseUrl+"/"+version+path);

        HttpResponse response;
        try {
            
            response = client.execute(request);
            
            HttpEntity entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();

            getLog().info("Request url: " + request.getURI());
            getLog().info("Status code: " + statusCode);
            
            if ( statusCode != 200 ) throw new Exception("Invalid status code, expected 200 but got " + statusCode);

            InputStream is = entity.getContent();

            file.getParentFile().mkdirs();
            
            FileOutputStream fos = new FileOutputStream(new File(filePath));

            int inByte;
            while ((inByte = is.read()) != -1) {
                fos.write(inByte);
            }

            is.close();
            fos.close();

            getLog().info("Successfully downloaded: " + path);
            
        } catch (Exception e) {
            
            throw new MojoFailureException("Couldn't download: " + path, e);
            
        }
        
    }
    
    /**
     * Extract specific files from a zip|fsm file
     * @param String path The path of the zip|fsm file to extract
     * @param String... filesToExtract The files to extract.
     * @throws MojoFailureException if files could not be extracted
     */
    private void extractFile(String path, String... filesToExtract) throws MojoFailureException {
     
        try {
            String filePath = project.getBasedir() + File.separator + "target"+path;
    
            File file = new File(filePath);
            
            byte[] buffer = new byte[1024];
            ZipInputStream zis = new ZipInputStream(new FileInputStream(filePath));
            ZipEntry zipEntry = zis.getNextEntry();
       
            while(zipEntry != null){
                String filename = zipEntry.getName();
                for ( String fileToExtract : filesToExtract ){
                    if ( fileToExtract.equals(filename) ){
                        File newFile = new File(file.getParentFile() + File.separator + fileToExtract);
                        newFile.getParentFile().mkdirs();
                        FileOutputStream fos = new FileOutputStream(newFile);
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                        fos.close();
                    }
                }
                
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (IOException e){
            throw new MojoFailureException("Couldn't extract: " + filesToExtract + " from " + path, e);
        }
        
    }
    
    public void execute() throws MojoExecutionException, MojoFailureException {
        
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(user, password);
        provider.setCredentials(AuthScope.ANY, credentials);
          
        CloseableHttpClient client = HttpClientBuilder.create()
          .setDefaultCredentialsProvider(provider)
          .build();

        try {
            
            downloadFile(client, "/debug/fs-client.jar");
            downloadFile(client, "/misc/fs-access.jar");
            downloadFile(client, "/misc/fs-api.jar");
            downloadFile(client, "/misc/fs-webrt.jar");
            downloadFile(client, "/misc/fs-isolated-webrt.jar");
            downloadFile(client, "/misc/fs-isolated-runtime.jar");
            
            downloadFile(client, "/modules/fs-perso.fsm");
            downloadFile(client, "/modules/fs-integration.fsm");
            
            extractFile("/modules/fs-perso.fsm", "lib/personalisation.jar");
            extractFile("/modules/fs-integration.fsm", "lib/fs-integration-rt.jar");
            
            client.close();
            
        } catch (MojoFailureException e) {
           throw e;
        } catch (IOException e) {
            getLog().error(e);
        }

    }

}