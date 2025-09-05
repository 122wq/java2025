import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Level4 {
    public static void main(String[] args) throws IOException, URISyntaxException 
    {
        boolean numbers = false;
        int nums = 8022;
        while (numbers == false)
        {
            
            // Make a URL to the web page
            URI uri = new URI("http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing=" + nums);
            URL url = uri.toURL();
            
            // Get the input stream through URL Connection
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            
            // Once you have the Input Stream, it's just plain old Java IO stuff.
            
            // For this case, since you are interested in getting plain-text web page
            // I'll use a reader and output the text content to System.out.
            
            // For binary content, it's better to directly read the bytes from stream and write
            // to the target file.          
            
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            String result = "";
            String line = br.readLine();
            while ((br.readLine()) != null)
            {
                line += br.readLine();
            }
            Pattern pattern = Pattern.compile("and the next nothing is (\\d+)");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find())
            {
                nums = Integer.parseInt(matcher.group(1));
                result += Integer.parseInt(matcher.group(1));
            }
            System.out.println(line);
            System.out.println(result);
            if (result.equals(""))
            {
                numbers = true;
                System.out.println(line); 
                    
            }
                
        }
        
    }
          
}
    
