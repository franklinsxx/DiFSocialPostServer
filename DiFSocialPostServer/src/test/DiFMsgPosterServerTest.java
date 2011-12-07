/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
import socialMsgPoster.*;
/**
 *
 * @author franklinsxx
 */
public class DiFMsgPosterServerTest {
   /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      System.out.println("|INFO| Please enter your message to post in the group");
      if (args.length != 1)
      throw new IllegalArgumentException("|INFO| No message entered!");

      DiFMsgPoster gp = new DiFMsgPoster();
      gp.publishGroupMessage(args[0]);
    }
}
