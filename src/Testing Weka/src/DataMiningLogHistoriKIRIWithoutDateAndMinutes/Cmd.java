/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataMiningLogHistoriKIRIWithoutDateAndMinutes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Jovan Gunawan
 */
public class Cmd 
{
    public static void makeJpgUsingDotCommand()
    {
        try {
            //rt.exec(new String[]{"cmd.exe","/c","start"});
            //ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\Program Files\\Microsoft SQL Server\" && dir");
            final String dir = System.getProperty("user.dir");
            
            ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd graphviz && cd bin && dot \"" + dir + "\\tree.txt\" -o \"" + dir + "\\tree.jpg\" -Tjpg ");
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) 
                { 
                    break; 
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
