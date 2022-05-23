## Android Fundamental ##
  __1. Explore the Gradle Scripts folder__
- The `Gradle build system` in `Android Studio` makes it easy to include `external binaries` or `other library modules` to your build as `dependencies`.
- When you first create an app project, the `Project` > `Android pane` appears with the `Gradle Scripts folder` expanded as shown below.

 ![image](https://user-images.githubusercontent.com/82217333/169727441-afb78ceb-abba-4214-a931-618d326c7dbf.png)
 - This folder contains all the files needed by the build system: Look for the `settings.gradle (Project Settings)` file.
 - This is where you'll find the `project-level repository settings` and the `modules` to include when building your app
 - The `Gradle settings` file is part of the `standard project structure` for an `Android app`.
 - Most of the time, you won't need to make any changes to this file, but it's still useful to understand its contents. 
 - By default, the settings file uses the `pluginManagement` block to configure the repositories Gradle uses to search or download the Gradle plugins and their transitive dependencies. 
 - The `dependencyResolutionManagement` block configures the `repositories` and `dependencies` used by all modules in your project, such as libraries that you are using to create your application.
 - When your dependency is something other than a `local library` or `file tree`, Gradle looks for the files in whichever `online repositories` are specified in the repositories block of this file.
 - By default, new `Android Studio projects` declare `MavenCentral` and `Google `(which includes the `Google Maven` repository) as the repository locations:
 ```
 dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

 ``` 
 - Look for the `build.gradle` (Project: Hello_World) file.
 ```
 // Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id 'com.android.application' version '7.2.0' apply false
    id 'com.android.library' version '7.2.0' apply false
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
 ```
 - In addition to the project-level `build.gradle` file, each module has a `build.gradle` file of its own, which allows you to configure build settings for each specific module (the HelloWorld app has only one module).
 - Configuring these `build settings` allows you to provide custom packaging options, such as additional build types and product flavors 
 - You can also override settings in the `AndroidManifest.xml` file or the top-level `build.gradle` file.
 - This file is most often the file to edit when changing `app-level configurations`, such as declaring dependencies in the dependencies section. 
 - You can declare a library dependency using one of several different dependency configurations.
 - Each dependency configuration provides `Gradle` different instructions about how to use the library. 
 - For example, the statement `implementation fileTree(dir: 'libs', include: ['*.jar'])` adds a dependency of all ".jar" files inside the libs directory.
 - The following is the `build.gradle (Module: Hello_World.app)` file for the HelloWorld app:
```
plugins {
    id 'com.android.application'
}

android {
    compileSdk 32

    defaultConfig {
        applicationId "com.example.helloworld"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
![image](https://user-images.githubusercontent.com/82217333/169730483-7c8cb320-1869-4dff-8c89-80869bf5bb05.png)

dependencies {

    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support.constraint:constraint-layout:2.0.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'com.android.support.test:runner:1.0.2'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
}
```
__2. Explore the app and res folders_ 
- All `code` and `resources` for the `app` are located within the `app` and `res` folders.
- Expand the `app` folder, the `java` folder, and the `com.example.helloworld` folder to see the `MainActivity` java file. Double-clicking the file opens it in the code editor.

![image](https://user-images.githubusercontent.com/82217333/169730493-ab960e20-9b72-45c1-b05c-50248d457e3c.png)

- The `java` folder includes `Java class` files in three subfolders, as shown in the figure above. 

![image](https://user-images.githubusercontent.com/82217333/169730689-9ca7774a-8ea9-4adc-9eda-8e229e065cc7.png)

- The `com.example.helloworld` (or the domain name you have specified) folder contains all the files for an app package. 
- The other two folders are used for testing and described in another lesson.
- The name of the first `Activity` (screen) the user sees, which also initializes app-wide resources, is customarily called `MainActivity` (the file extension is omitted in the `Project > Android` pane).
👉🏻 Expand the `res` folder and the `layout` folder, and double-click the `activity_main.xml` file to open it in the layout editor.

![image](https://user-images.githubusercontent.com/82217333/169731150-0dbbe6ce-f254-4ae0-a554-082aaa6bb4b6.png)

- The `res` folder holds resources, such as layouts, strings, and images. An Activity is usually associated with a layout of UI views defined as an XML file. This file is usually named after its Activity.





