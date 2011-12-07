package socialMsgPoster;
/*
 * Copyright (c) 2010-2011 Mark Allen.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import java.util.Date;
import com.restfb.BinaryAttachment;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Group;
import com.restfb.types.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Examples of RestFB's Graph API functionality.
 *
 * @author <a href="http://restfb.com">Mark Allen</a>
 */
public class DiFMsgPoster {
  /**
   * RestFB Graph API client.
   */
  private final FacebookClient facebookClient;

  /**
   * Entry point. You must provide a single argument on the command line: a
   * valid Graph API access token. In order for publishing to succeed, you must
   * use an access token for an application that has been granted stream_publish
   * and create_event rights.
   *
   * @param args
   *          Command-line arguments.
   * @throws IllegalArgumentException
   *           If no command-line arguments are provided.
   */
  /*
  public static void main(String[] args) {

    String accessToken = "AAACx3ybS5G4BAMfkxFBDezeNZAjwjrrZAb6wX82MuBfzSipJlJpKkAvioS67pWgeZAlMt42FXWr1P0BOGVz3wDiFGgLLV3ABZCDzewPqTagZDZD";

    GraphPublisherExample gp = new GraphPublisherExample(accessToken);

    gp.runEverything();
  }*/

  public DiFMsgPoster() {
    String accessToken = "AAACx3ybS5G4BAMfkxFBDezeNZAjwjrrZAb6wX82MuBfzSipJlJpKkAvioS67pWgeZAlMt42FXWr1P0BOGVz3wDiFGgLLV3ABZCDzewPqTagZDZD";
    facebookClient = new DefaultFacebookClient(accessToken);

    Connection<Group>  myGroups = facebookClient.fetchConnection("me/groups", Group.class);
    //Group myGroup = facebookClient.fetchObject("group", Group.class);
    out.println(myGroups.getData().size());
    for(int i = 0; i <myGroups.getData().size(); i++){
        out.println("|INFO| Group information:");
        out.println("|INFO| My group name: " + myGroups.getData().get(i).getName());
        out.println("|INFO| My group id: " + myGroups.getData().get(i).getId());
        out.println("|INFO| My group privacy: " + myGroups.getData().get(i).getPrivacy());
    }

    Connection<User>  groupMembers = facebookClient.fetchConnection("176518412424847/members", User.class);
    for(int i = 0; i <groupMembers.getData().size(); i++){
        out.println("|INFO| Group members:");
        out.println("|INFO| Member name: " + groupMembers.getData().get(i).getName() + " with ID: " + groupMembers.getData().get(i).getId());
    }
  }

  public void runEverything(String feedMsg, String groupMsg) {
    String messageId1 = publishMessage(feedMsg);
    String messageId2 = publishGroupMessage(groupMsg);
    //delete(messageId);
    //String eventId = publishEvent();
    //delete(eventId);
    //String photoId = publishPhoto();
    //delete(photoId);
  }

  public String publishMessage(String Msg) {
    out.println("* Feed publishing *");

    FacebookType publishMessageResponse =
        facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message", Msg));

    out.println("Published message ID: " + publishMessageResponse.getId());
    return publishMessageResponse.getId();
  }

 public String publishGroupMessage(String Msg) {
    out.println("* Group Feed publishing *");

    FacebookType publishMessageResponse =
        facebookClient.publish("176518412424847/feed", FacebookType.class, Parameter.with("message", Msg));

    out.println("Published message ID: " + publishMessageResponse.getId());
    return publishMessageResponse.getId();
  }

  /*
  String publishEvent() {
    out.println("* Event publishing *");

    Date tomorrow = new Date(currentTimeMillis() + 1000L * 60L * 60L * 24L);
    Date twoDaysFromNow = new Date(currentTimeMillis() + 1000L * 60L * 60L * 48L);

    FacebookType publishEventResponse =
        facebookClient.publish("me/events", FacebookType.class, Parameter.with("name", "Party"),
          Parameter.with("start_time", tomorrow), Parameter.with("end_time", twoDaysFromNow));

    out.println("Published event ID: " + publishEventResponse.getId());
    return publishEventResponse.getId();
  }*/

  /*
  String publishPhoto() {
    out.println("* Binary file publishing *");

    FacebookType publishPhotoResponse =
        facebookClient.publish("me/photos", FacebookType.class,
          BinaryAttachment.with("cat.png", getClass().getResourceAsStream("/cat.png")),
          Parameter.with("message", "Test cat"));

    out.println("Published photo ID: " + publishPhotoResponse.getId());
    return publishPhotoResponse.getId();
  }*/

  /*
  void delete(String objectId) {
    out.println("* Object deletion *");
    out.println(format("Deleted %s: %s", objectId, facebookClient.deleteObject(objectId)));
  }*/
}