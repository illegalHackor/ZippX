/*Author :
*           IllegalHackor
*           Version 1.0
* (Only For Educational Purpose)
* (Don't Try To Steal Credits) */

package me.illegalHackor;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ZippX extends Exception{
     private String Getter;
    public ZippX(){
       Getter="ZippyShare";
    }
    public ZippX(String Provider){
        Getter=Provider;
    }

    //personal important code of calculations
    private String getSolvedProblem(String equation)  {
        String returnAnswer="";
        ScriptEngineManager scriptEngineManager=new ScriptEngineManager();
        ScriptEngine scriptEngine=scriptEngineManager.getEngineByName("JavaScript");
        try {
            returnAnswer=String.valueOf(scriptEngine.eval(equation));
        }catch (ScriptException e){
            System.out.println("Error Encountered : "+e.getMessage());
        }
        return returnAnswer;
    }
    private String getSource(String mainUrl){
        StringBuilder stringBuilder=new StringBuilder();
        try{
            URL url=new URL(mainUrl);
            /* HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML");
            urlConnection.setRequestProperty("content-type", "text/plain; charset=utf-8");
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setReadTimeout(5000); */


            //BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            /*while(br.readLine() !=null){
                line=br.readLine();
                stringBuilder.append(line+"\n");
                fw.write(line+"\n");
            }
            br.close(); */
            Scanner scanner=new Scanner(url.openStream());
            while(scanner.hasNextLine()){
                stringBuilder.append(scanner.nextLine()+"\n");
            }


        }catch(Exception e){
            System.out.println("Exception Caught");
        }
        return stringBuilder.toString();
    }
    private String getNeededPortion(String source) throws ValueNotFoundException {
        String searchFor="document.getElementById('dlbutton').href";
        Scanner scanner=new Scanner(source);
        int checker=0;
        String needed="";
        String temp;

        while(scanner.hasNextLine()){
            temp=scanner.nextLine();
            if(temp.contains(searchFor)){
                needed+=temp;
                checker=1;
            }
        }

        needed=needed.trim().replace(';',' ');
        needed=needed.substring(needed.indexOf('=')+1).trim();
        String equation=needed.substring(((needed.indexOf('('))+1),needed.indexOf(')'));
        String lastPart=needed.substring(needed.lastIndexOf(' '));
        lastPart=lastPart.substring((lastPart.indexOf('\"')+1),lastPart.lastIndexOf('\"'));
        ZippX zippX=new ZippX();
        String Answer=zippX.getSolvedProblem(equation.toString().trim());
        needed="/"+Answer+lastPart;

        if(checker == '0'){
           throw new ValueNotFoundException("Needed Value not Found");
        }
        return needed;
    }

    // getting public

    //Overloaded

    //Function To get Direct Download Link
    public String getDirectDownloadLink(String url) throws ValueNotFoundException {
        String directDownloadLink="";
        String lastWords="";
        String firstWords=url.replaceFirst("v","d").trim();
        firstWords=firstWords.substring(0,firstWords.lastIndexOf('/'));
        try{
            lastWords=getNeededPortion(getSource(url));
        }catch(ValueNotFoundException e){

        }


        directDownloadLink=firstWords+lastWords;

        return directDownloadLink;
    }

    //Function to Get Direct Download Links Of Multiple Urls in an Array
    public String[] getDirectDownloadLink(String urls[]) throws ValueNotFoundException{
        String [] multipleUrls=new String[urls.length];
        for(int i=0;i< urls.length;i++){
            multipleUrls[i]=getDirectDownloadLink(urls[i]);
        }

        return multipleUrls;
    }

    //Function to Get The Title of The Following File
    public String getTitle(String url) throws ValueNotFoundException {
        int t=0;
        String Title="";
        String Temp;
        String Source=getSource(url);
        Scanner scanner=new Scanner(Source);
        while(scanner.hasNextLine()){
            Temp=scanner.nextLine();
            String searchFor="<title>Zippyshare.com - ";
            if(Temp.contains(searchFor)){
                Title=Temp.replace("<title>Zippyshare.com - "," ").trim();
                Title=Title.substring(0,Title.lastIndexOf('<'));
                t=1;
                break;
            }
        }
        if(t==0){
            throw new ValueNotFoundException("Title not Found");
        }
        return Title;
    }

    //Function to get Titles of multiple Urls in An String Array
    public String[] getTitle(String []urls) throws ValueNotFoundException{
        String [] multipleTitles =new String[urls.length];
        try{
            for(int i=0;i< multipleTitles.length;i++){
                multipleTitles[i]=getTitle(urls[i]);
            }
        }catch (ValueNotFoundException e){
            throw new ValueNotFoundException("Web Error No Title Found");
        }
        return multipleTitles;
    }

    //Function to get the size of the File
    public int getSizeInKilobyte(String url) throws ValueNotFoundException, MalformedURLException {
        String size="";
        HttpURLConnection httpURLConnection=null;
        URL realUrl=new URL(getDirectDownloadLink(url));
        try{
            httpURLConnection=(HttpURLConnection)realUrl.openConnection();
            httpURLConnection.setRequestMethod("HEAD");
            size=String.valueOf((httpURLConnection.getContentLengthLong())/(1024));
            httpURLConnection.disconnect();
        }catch (Exception e){
            throw new ValueNotFoundException("Value For Size not Found");
        }
        return Integer.parseInt(size);
    }

    //Function to get The size of Multiple Urls in A String Array
    public int[] getSizeInKilobyte(String urls[]) throws ValueNotFoundException{
        int [] sizes=new int[urls.length];
        try{
            for(int i=0;i<sizes.length;i++){
                sizes[i]=getSizeInKilobyte(urls[i]);
            }
        }catch (ValueNotFoundException | MalformedURLException e){
            throw new ValueNotFoundException("Web Error Size can not be Determined");
        }
        return sizes;
    }



    }

