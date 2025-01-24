# Easy-Internet-Connection-Checker

This is a simple internet connection checker library. Made with java and kotlin. Actually, I have made this for my daily use. This will help me to avoid too much boilerplate code.

## Install

Set minimum sdk version to 19 for version 1.0.1 and 24 for version v1.0.4

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
* Check internet capability status (v1.0.4)
* Listen network status changes


  ## Usage (version v1.0.4)

 ```kotlin
 InternetConnectionChecker implementation;
 
 //first init this
 implementation = new InternetConnectionChecker(this);

//to check individually
val connectivity = internetChecker.isConnected()
val capability = internetChecker.isCapable()

//to listen
 
 internetChecker.getStatus(object : NetworkStatusListener {
            override fun connected() {
                Log.d("STATUS", "CONNECTED")
            }

            override fun capable() {
                Log.d("STATUS", "CONNECTED_AND_CAPABLE")
            }

            override fun notCapable() {
                Log.d("STATUS", "NOT_CAPABLE")
            }

            override fun notConnected() {
                Log.d("STATUS", "NOT_CONNECTED")
            }
        })
        
        
        //call when app will be close
        //call it on ondestroy or onstop
        implementation.unRegister();
       
 ```

## Usage (version 1.0.1)

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
 
 Thanks
 
