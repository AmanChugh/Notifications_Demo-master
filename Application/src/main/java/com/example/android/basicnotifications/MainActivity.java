package com.example.android.basicnotifications;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public static final int NOTIFICATION_ID = 1;
    List<User> mUsers = new ArrayList<>();
    private static final int USER0 = 0;
    private static final int USER1 = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_layout);

    }

    /**
     * Send a sample notification using the NotificationCompat API.
     */
    public void user1(View view) {

        User user = null;
        for (int i = 0; i < mUsers.size(); i++) {
            if (mUsers.get(i).getUser_id() == USER0) {
                user = mUsers.get(i);
                break;
            }
        }

        if (user == null) {
            user = new User();
            user.setUser_id(USER0);
            mUsers.add(user);
        }
        user.setMessages("User1 Message");
        sendNotification();
    }

    public void user2(View view) {

        User user = null;
        for (int i = 0; i < mUsers.size(); i++) {
            if (mUsers.get(i).getUser_id() == USER1) {
                user = mUsers.get(i);
                break;
            }
        }

        if (user == null) {
            user = new User();
            user.setUser_id(USER1);
            mUsers.add(user);
        }
        user.setMessages("User2 Message");
        sendNotification();
    }


    public void sendNotification() {

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://google.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        if (mUsers.size() == 1 && mUsers.get(0).getMessages().size() == 1) {

            builder.setContentText(mUsers.get(0).getMessages().get(0));

            Intent dismissIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://google.com"));
            PendingIntent piDismiss = PendingIntent.getActivity(this, 0, dismissIntent, 0);

            Intent replyIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://google.com"));
            PendingIntent piReply = PendingIntent.getActivity(this, 0, replyIntent, 0);

           builder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(mUsers.get(0).getMessages().get(0)))
                    .addAction (R.drawable.ic_launcher,
                            "Cancel", piDismiss)
                    .addAction (R.drawable.ic_launcher,
                            "Reply", piReply);

        } else {

            int counter = 0;
            NotificationCompat.InboxStyle inboxStyle =
                    new NotificationCompat.InboxStyle();


            for (int i = 0; i < mUsers.size(); i++) {
                for (int j = 0; j < mUsers.get(i).getMessages().size(); j++) {
                    inboxStyle.addLine(mUsers.get(i).getMessages().get(j) + (j++));
                    counter++;
                }
            }
            builder.setStyle(inboxStyle);

            inboxStyle.setBigContentTitle(counter + " Messages");
            builder.setSubText("You got " + counter + " messages from " + mUsers.size() + " users");
            builder.setContentText(counter + " messages");
        }

        builder.setSmallIcon(R.drawable.ic_stat_notification);

        // Set the intent that will fire when the user taps the notification.
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));

        builder.setContentTitle("Updating Notification");


        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
        // END_INCLUDE(send_notification)
    }
}

