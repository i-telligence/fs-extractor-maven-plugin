# Maven plugin for downloading/extracting FirstSpirit libraries for a specific version

## Warning

The plugin access the FirstSpirit download area of e-spirit.de, by default at:
http://www.e-spirit.de/download/firstspirit

You provide your username and password. You access the download area as a legitimate e-spirit user.

This plugin was written for internal use at itelligence.de GmbH to make development of FirstSpirit modules easier.
It is being shared by request.

There are probably bugs.

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
    <plugins>
        <plugin>
            <groupId>de.itelligence.fs</groupId>
            <artifactId>fs-extractor-maven-plugin</artifactId>
            <version>0.0.2</version>
            <configuration>
                <project>${project}</project>
                <user>${espirit.user}</user>
                <password>${espirit.password}</password>
                <version>${fs.version}</version>
            </configuration>
        </plugin>
    </plugins>
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
        <plugins>
        <plugin>
            <groupId>de.itelligence.fs</groupId>
            <artifactId>fs-extractor-maven-plugin</artifactId>
            <version>0.0.2</version>
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
                    <phase>deploy</phase>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${project.build.directory}/debug/fs-client.jar</file>
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
                    <phase>deploy</phase>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${project.build.directory}/misc/fs-access.jar</file>
                        <javadoc>${project.build.directory}/misc/fs-access-javadoc.jar</javadoc>
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
                    <phase>deploy</phase>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${project.build.directory}/misc/fs-api.jar</file>
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
                    <phase>deploy</phase>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${project.build.directory}/misc/fs-webrt.jar</file>
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
                    <phase>deploy</phase>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${project.build.directory}/misc/fs-isolated-webrt.jar</file>
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
                    <phase>deploy</phase>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${project.build.directory}/misc/fs-isolated-runtime.jar</file>
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
                    <phase>deploy</phase>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${project.build.directory}/modules/lib/fs-integration-rt.jar</file>
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
                    <phase>deploy</phase>
                    <goals>
                        <goal>deploy-file</goal>
                    </goals>
                    <configuration>
                        <file>${project.build.directory}/modules/lib/personalisation.jar</file>
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
    </plugins>
    </build>

</project>

```

## Contribution / Roadmap

We're not sure yet! But we are open to suggestions.
Perhaps publish to maven central?


