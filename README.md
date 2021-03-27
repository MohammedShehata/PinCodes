# Pinscodes

Generic verify pin codes library.

## Getting Started


### Prerequisites

Android studio project.

### Installing

Using Gradle:

Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.MohammedShehata:PinCodes:1.0.0'
	}


Or Maven:

Step 1. Add the JitPack repository to your build file

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

Step 2. Add the dependency

	<dependency>
	    <groupId>com.github.MohammedShehata</groupId>
	    <artifactId>PinCodes</artifactId>
	    <version>1.0.0</version>
	</dependency>

## How do I use Pincodes?


```
PinCodes.newInstance(
            object : PinCodes.OnPinsCompleteListener {
                override fun onPinsCompleted() {
                    
                }

            },
            editPin1,
            editPin2,
            editPin3,
            editPin4
        )
```
Where editPin1, editPin2, .... are edit text views and the pincodes library will group them to act as the next sample.

<p align="center">
  <img src="static/pinscode_sample.gif" width="30%"/>
</p>

You can also check the sample code.
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* Kotlin

## Versioning

Using [Jiback](https://jitpack.io/) for versioning. For the versions available, see the [tags on this repository](https://github.com/MohammedShehata/PinCodes/tags). 

## Authors

* **Mohammed Shehata** - *Initial work*

See also the list of [contributors](https://github.com/MohammedShehata/Speedometer/contributors) who participated in this project.

## License

This project is licensed under the [Apache2 License](https://www.apache.org/licenses/LICENSE-2.0)

