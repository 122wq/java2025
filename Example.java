//Created by: Jack Wang
//9/2/2025
//Add each char by 2, if it is more than 'z' then it will also minus 26 which in total is minus 24
public class Example
{
    public static void main(String[] args)
    {
        String text = "map";
        String newText = "";
        for (int i = 0; i < text.length(); i++)
         {
            if ((int)(text.charAt(i)) >= 121)
            {
                newText = newText + (char) (text.charAt(i) - 24);
            }

            else if ((int)(text.charAt(i)) < 97)
            {
                newText = newText + text.charAt(i);
            }
            else
            {
                newText = newText + (char)(text.charAt(i) + 2);
            }
            
         }
         System.out.println(newText);
    }
}
