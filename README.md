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
    <artifactId>my-artifact</artifactId>
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

### To run:
```
mvn fs-extractor:fs-extract
```

### Going further

As mentioned above, you could now deploy the downloaded/extracted files to your own repository:

```xml
...
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
        
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-deploy-plugin</artifactId>
            <version>2.8.2</version>
            <executions>
                <execution>
                    <id>deploy-fs-client</id>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${basedir}/target/debug/fs-client.jar</file>
                        <repositoryId>${your.repo.id}</repositoryId>
                        <url>${your.repo.url}</url>
                        <artifactId>client</artifactId>
                        <groupId>de.espirit.firstspirit</groupId>
                        <version>${fs.version}</version>
                        <packaging>jar</packaging>
                    </configuration>
                </execution>
                <execution>
                    <id>deploy-fs-access</id>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${basedir}/target/misc/fs-access.jar</file>
                        <repositoryId>${your.repo.id}</repositoryId>
                        <url>${your.repo.url}</url>
                        <artifactId>fs-access</artifactId>
                        <groupId>de.espirit.firstspirit</groupId>
                        <version>${fs.version}</version>
                        <packaging>jar</packaging>
                    </configuration>
                </execution>
                <execution>
                    <id>deploy-fs-api</id>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${basedir}/target/misc/fs-api.jar</file>
                        <repositoryId>${your.repo.id}</repositoryId>
                        <url>${your.repo.url}</url>
                        <artifactId>fs-api</artifactId>
                        <groupId>de.espirit.firstspirit</groupId>
                        <version>${fs.version}</version>
                        <packaging>jar</packaging>
                    </configuration>
                </execution>
                <execution>
                    <id>deploy-fs-webrt</id>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${basedir}/target/misc/fs-webrt.jar</file>
                        <repositoryId>${your.repo.id}</repositoryId>
                        <url>${your.repo.url}</url>
                        <artifactId>fs-webrt</artifactId>
                        <groupId>de.espirit.firstspirit</groupId>
                        <version>${fs.version}</version>
                        <packaging>jar</packaging>
                    </configuration>
                </execution>
                <execution>
                    <id>deploy-fs-isolated-webrt</id>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${basedir}/target/misc/fs-isolated-webrt.jar</file>
                        <repositoryId>${your.repo.id}</repositoryId>
                        <url>${your.repo.url}</url>
                        <artifactId>fs-isolated-webrt</artifactId>
                        <groupId>de.espirit.firstspirit</groupId>
                        <version>${fs.version}</version>
                        <packaging>jar</packaging>
                    </configuration>
                </execution>
                <execution>
                    <id>deploy-fs-isolated-runtime</id>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${basedir}/target/misc/fs-isolated-runtime.jar</file>
                        <repositoryId>${your.repo.id}</repositoryId>
                        <url>${your.repo.url}</url>
                        <artifactId>fs-isolated-runtime</artifactId>
                        <groupId>de.espirit.firstspirit</groupId>
                        <version>${fs.version}</version>
                        <packaging>jar</packaging>
                    </configuration>
                </execution>
                <execution>
                    <id>deploy-fs-integration</id>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${basedir}/target/modules/lib/fs-integration-rt.jar</file>
                        <repositoryId>${your.repo.id}</repositoryId>
                        <url>${your.repo.url}</url>
                        <artifactId>fs-integration</artifactId>
                        <groupId>de.espirit.firstspirit</groupId>
                        <version>${fs.version}</version>
                        <packaging>jar</packaging>
                    </configuration>
                </execution>
                <execution>
                    <id>deploy-personalisation</id>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${basedir}/target/modules/lib/personalisation.jar</file>
                        <repositoryId>${your.repo.id}</repositoryId>
                        <url>${your.repo.url}</url>
                        <artifactId>personalisation</artifactId>
                        <groupId>de.espirit.firstspirit</groupId>
                        <version>${fs.version}</version>
                        <packaging>jar</packaging>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    
    </build>

</project>

```
