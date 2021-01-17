import me.illegalHackor.ValueNotFoundException;
import me.illegalHackor.ZippX;

import java.net.MalformedURLException;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) throws MalformedURLException{
        ZippX link=new ZippX();
        Scanner sc=new Scanner(System.in);
        String url=sc.nextLine();
        String directLink="Not Found";
        int size=0;
        String title="Not Found";
        try{
            directLink=link.getDirectDownloadLink(url);
            title=link.getTitle(url);
            size =link.getSizeInKilobyte(url)/1024;
        }catch (ValueNotFoundException  e){
            System.out.println(e.getMessage());
        }
        System.out.println("Title "+title);
        System.out.println("Direct Download Link "+directLink);
        System.out.println("Size in Megabyte "+size);

        String [] urls=new String[4];
        for(int i=0;i< urls.length;i++){
            urls[i]=sc.nextLine();
        }

        String [] links;
        int [] sizes;
        String [] titles;
        try{
            links=link.getDirectDownloadLink(urls);
            sizes=link.getSizeInKilobyte(urls);
            titles=link.getTitle(urls);
            for (int i=0;i< urls.length;i++){
                System.out.println("Title "+titles[i]);
                System.out.println("Link "+links[i]);
                System.out.println("Size "+sizes[i]);
            }
        }catch(ValueNotFoundException e){
            System.out.println(e.getMessage());
        }


    }
}
