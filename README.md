# Maven plugin for downloading/extracting FirstSpirit libraries for a specific version

## Basic info

This is a simple Maven plugin for downloading extracting the following libraries for a specific version:

* fs-client.jar
* fs-access.jar
* fs-api.jar
* fs-webrt.jar
* fs-isolated-webrt.jar
* fs-isolated-runtime.jar
* personalisation.jar
* fs-integration-rt.jar
                       
The files are downloaded/extracted to the target directory of the project in which the plugin is used. That is all that this plugin does.
It is up to the developer how to deal with these files. One use case is to then install/deploy these files into one's own repository manager like Archiva or Artifactory.

The plugin binds to the LifecyclePhase.GENERATE_RESOURCES phase by default.
The goal is fs-extract.

## How to use

### Include the plugin into your pom.xml

The configuration below is an example only.
```xml
<project>

    <groupId>my-group</groupId>
    <artifactId>my--artifact</artifactId>
    <version>1.2.3</version>

    <properties>
    
        <espirit.user>E-Spirit-Username</espirit.user>
        <espirit.password>E-Spirit-Password</espirit.password>
        <fs.version>2019-02</fs.version>
        
    </properties>

    <build>
    
        <plugin>
            <groupId>de.itelligence.fs</groupId>
            <artifactId>fs-extractor-maven-plugin</artifactId>
            <version>1.0-SNAPSHOT</version>
            <configuration>
                <project>${project}</project>
                <user>${espirit.user}</user>
                <password>${espirit.password}</password>
                <version>${fs.version}</version>
            </configuration>
            
        </plugin>
    
    </build>


</project>
```

To run:
```
mvn fs-extractor:fs-extract
```

