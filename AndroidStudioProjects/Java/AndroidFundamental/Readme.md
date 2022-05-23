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

__3. Explore the manifests folder__
- The `AndroidManifest.xml` file describes all of the components of your `Android app`. 
- All components for an app, such as each` Activity`, must be declared in this `XML file`.

__4. Change the app Gradle configuration__
  1. Change the minimum SDK version for the app
> Follow these steps:
  * Expand the `Gradle Scripts` folder if it is not already open, and double-click the `build.gradle (Module:Hello_World.app)` file.
> The content of the file appears in the code editor.
  * Within the `defaultConfig` block, change the value of `minSdkVersion` to __23__.
  * The code editor shows a notification bar at the top with the `Sync Now` link.
  2. Sync the new Gradle configuration
  - When you make changes to the `build configuration` files in a project, `Android Studio` requires that you `sync` the project files so that it can `import` the `build configuration` changes and `run some checks` to make sure the configuration won't create `build errors`.
  - To sync the project files, click `Sync Now` in the notification bar that appears when making a change (as shown in the previous figure), or click the `Sync Project with Gradle Files` icon ![image](https://user-images.githubusercontent.com/82217333/169742115-737ca9e7-65fe-4733-9538-ab32c804ecd5.png) in the toolbar.
  - When the `Gradle synchronization` is finished, the message `Gradle build finished</span` appears in the bottom left corner of the Android Studio window.
![image](https://user-images.githubusercontent.com/82217333/169742220-33d4938d-61c0-4126-876e-206de04f4b47.png)
👉🏻 For a deeper look into __Gradle__, check out the [Build System Overview](http://developer.android.com/sdk/installing/studio-build.html) and [Configuring Gradle Builds documentation](http://developer.android.com/tools/building/configuring-gradle.html).

__5. Add log statements to your app__
  - In this task, you will add `Log statements` to your app, which display messages in the `Logcat` pane. 
  - `Log` messages are a powerful `debugging tool` that you can use to `check on values`, `execution paths`, and `report exceptions`.
  
   1. View the Logcat pane
  - To see the `Logcat` pane, click the `Logcat tab` at the bottom of the `Android Studio` window as shown in the figure below.
  ![image](https://user-images.githubusercontent.com/82217333/169854341-49e1d025-913b-48ae-b1f7-72773dc84e8d.png)
  - In the figure above:
    >The __Logcat__ tab for opening and closing the Logcat pane, which displays information about your app as it is running. If you add `Log` statements to your app, `Log` messages appear here. 
    >The `Log` level menu is set to `Verbose`(chi tiết) (the default), which shows all Log messages. Other settings include `Debug`, `Error`, `Info`, and `Warn`.
    
    2. Add log statements to your app
   - `Log` statements in your app code display messages in the Logcat pane. For example:
 ```
 Log.d("MainActivity", "Hello World"); 
 ```
   The parts of the `message` are:
  - __Log__: The `Log class` for sending log messages to the `Logcat pane`.
  - __d__: The `Debug Log` level setting to filter log message display in the `Logcat pane`. Other log levels are __e__ for `Error`, __w__ for `Warn`, and __i__ for `Info`.
  - __"MainActivity"__: The `first argument` is a tag which can be used to filter messages in the `Logcat pane`. This is commonly the name of the __Activity__ from which the message originates. However, you can make this anything that is useful to you for debugging.
  - __"Hello world"__: The second argument is the `actual message`.
  
  By convention (Theo quy ước) , log tags are defined as constants for the __Activity__:
  ```
  private static final String LOG_TAG = MainActivity.class.getSimpleName();
  ```
  ⭐️ Follow these steps:
  1. Open your Hello World app in Android studio, and open `MainActivity`.
  2. To add `unambiguous imports` automatically to your project (such as `android.util.Log` required for using `Log`), choose `File > Settings` in _Windows_, or `Android Studio > Preferences` in _macOS_.
  3. Choose `Editor > General >Auto Import`. Select all checkboxes and set Insert imports on paste to Always.
  4. Click `Apply` and then click `OK`.
  5. In the `onCreate()` method of `MainActivity`, add the following statement:
```
Log.d("MainActivity", "Hello World"); 
```  
  The onCreate() method should now look like the following code:
```
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.d("MainActivity", "Hello World");
}
```
  6. If the `Logcat` pane is not already open, click the `Logcat` tab at the bottom of `Android Studio` to open it.
  7. Check that the `name of the target` and `package name` of the app are correct.
  8. Change the `Log level` in the `Logcat pane` to __Debug__ (or leave as Verbose since there are so few log messages).
<img width="402" alt="Screen Shot 2022-05-23 at 22 59 40" src="https://user-images.githubusercontent.com/82217333/169860094-16ba9741-cdd4-45c3-b33b-4e75ca162aeb.png">
  9. Run your app.
  
  The following message should appear in the `Logcat pane`:
```
11-24 14:06:59.001 4696-4696/? D/MainActivity: Hello World
```
👉🏻 _Tips_: If you are seeing a lot of extra log messages from your emulator, you can use the filter (🔍) to see only log messages that contain `MainActivity`.
  




