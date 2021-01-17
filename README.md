# ZippX
A java Libarary to get Direct Download Links From zippyshare website (For educational Purpose Only)

Its a very Simple Libarary which makes you work simple and here's the documentation

# Documentation
Its very easy to Use. Just Follow The Steps

1.Installation
2.How to Use

<h1>Installation</h1>
 To use it first you have to install it in your IDE. Just Download The Jar Form <a href="">Here</a>
 Then Goto your IDE (I use IntelliJ) and Find The Option to import jar. In IntelliJ you can follow this path <code>File>Project Structure>Libraries</code> Then Add The Jar file you Downloaded. You can Also use <code>ctrl+alt+shift+s</code> in IntelliJ. After Importing Click on Apply and Finish.

For Other IDEs you can find a way to import jar files or you can add these few line in your Maven, Gradle Files

<h2>maven Code</h2>

### Add This Repository First
      	   <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	   </repositories>
	
### Add The Dependency
            <dependency>
	    <groupId>com.github.illegalHackor</groupId>
	    <artifactId>ZippX</artifactId>
	    <version>-SNAPSHOT</version>
	    </dependency>

<h2>Gradle Code</h2>

### Add the JitPack repository to your build file 
           
	  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
### Add the dependency
            dependencies {
	        implementation 'com.github.illegalHackor:ZippX:-SNAPSHOT'
	}
	
	
<h1>How to Use</h1>

### First Create a Object Of The Class Like This
    ZippX zippX=new ZippX();
 
Now Using the Obejct of The Class you can easily use its functions.

### Here's Its Funtions and Details
    ...........................................................................................................
    |         Functions        |        Return Type      |                     Details                        |
    ...........................................................................................................
    | getDirectDownloadLink()  | String || String Array  | Gets The Direct Download Link By Passing an raw url|
    |                          |                         | or Passing a String array of urls                  |
    ...........................................................................................................
    | getSizeInKilobyte()      | Integer || Integer Array| Gets The Size of The File in Kilobytes by Passing  |
    |                          |                         | the raw url or Passing a Sting array of urls       |
    ...........................................................................................................
    | getTitle()               | String || String Array  | Gets The Name of The File by Passing the raw url   |
    |                          |                         | or Passing a Sting array of urls                   |
    ...........................................................................................................
 
### getDirectDownloadLink(url) Usage
    directLink=zippX.getDirectDownloadLink(url);
    String []urls=new String[//size here];
    String [] links=zippX.getDirectDownloadLink(urls);
    
### getSizeInKilobyte(url) Usage
    size =zippX.getSizeInKilobyte(url);
    
### getTitle(url) Usage
    title=zippX.getTitle(url);
