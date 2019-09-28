# rlib
A library that handles the most common use cases that I am using in an app.
It follows a pattern with a single activity that handles all fragments

### Setup

Add jitpack dependency in project level gradle file
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
Add library dependency in app level gradle file
```
dependencies {
  implementation 'com.github.rares1200:rlib:1.0.3'
}
```

On activity class:
- extend `BaseActivity` class
- replace activity layout file with `activity_base`

On fragment classes:
- extend `BaseFragment`

On styles.xml:
- make sure your theme does not include a toolbar. Ex. `Theme.AppCompat.Light.NoActionBar` - working usage

### Basic functionalities

An application class with a Singleton pattern that can be extended and has some utility methods for displaying Toasts

A Base Activity class with included functionalities:
- setting toolbar title, color and visibility
- handles fragment transactions and back actions so app will close along with closing the last fragment
- handles Floating Action Button visibility

A Base Fragment class with included functionalities:
- opening other fragments
- showing and hiding a loading
- utility methods for Floating Action Button, Toolbar and handling of back press

A Preferences class that saves and retrieves data from Shared Preferences

A NetworkManager class that performs synchronuos requests for JSON-RPC 2.0 

