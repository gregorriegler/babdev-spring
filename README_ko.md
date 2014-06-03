babdev-spring
=========

이 예제는 [http://babdev.blogspot.co.at] 사이트의 스프링 프레임워크예제입니다.

To Build
=========

하위프로젝트를 루트디렉토리로 변경하고,  mvn package 명령어를 입력합니다.

```sh
cd <sub-project-root>
mvn package
```

### TroubleShooting
STS에서 아래와 같은 에러를 뱉어낸다면.

>"Dynamic Web Module 3.0 requires Java 1.6 or newer"

해결책은 아래와 같습니다.[링크참조]

* 프로젝트가 java 1.7을 사용하도록 구성되어있는지 확인해 보시기바랍니다. project > java Compiler 를 클릭한 후 “Compiler compliance level”을 1.7로 셋팅합니다.
* 다음엔 Project Facets > Java를 선택한 후 버전을 1.7으로 셋팅합니다. 1.7버전을 찾을수 없다면 eclipse에 추가해줍니다. Preferences > Java > Installed JREs 으로 이동 후 Add를 클릭, 설치 된 자바 경로를 잡아줍니다. 
* pom.xml파일을 열어, 아래의 plugin tag를 추가해줍니다.
```sh
<build>
    <plugins>
         <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.7</source>
                    <target>1.7</target>
                </configuration>
        </plugin>
    </plugins>
</build>
```
- project > Maven > Update Project 를 클릭해주면 마침내 해결됩니다.


[http://babdev.blogspot.co.at]:http://babdev.blogspot.co.at
[링크참조]: http://qussay.com/2013/09/13/solving-dynamic-web-module-3-0-requires-java-1-6-or-newer-in-maven-projects/
