# Easy-Internet-Connection-Checker

This is a simple internet connection checker library. Made with java. Actually, I have made this for my daily use. This will help me to avoid too much boilerplate code.

## Install

Set minimum sdk version to 19

Add it in your root `build.gradle` at the end of repositories:
```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
Add the dependency:
```gradle
dependencies {
	        implementation 'com.github.hatanvir:Easy-Internet-Connection-Checker:{latest version}'
	}
```

## Features
* Check internet connection status
* Unregister connecction listener

## Usage

 ```java
 InternetConnectionChecker implementation;
 
 //first init this
 implementation = new InternetConnectionChecker(this);
 
 implementation.getStatus( new NetWorkStatusListeners<Boolean>() {
            @Override
            public void status(boolean st) {
                if(st){
                    //do network call here
                }else {
                    Toast.makeText(MainActivity.this, "No network connection !", Toast.LENGTH_SHORT).show();
                
                }
            }
        });
        
        
        //call when app will be close
        //call it on ondestroy or onstop
        implementation.unRegister();
       
 ```
 
 Check this library and feel free to contribute.
 
 Thanks.....
 
