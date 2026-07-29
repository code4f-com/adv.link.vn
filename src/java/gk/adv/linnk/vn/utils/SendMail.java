/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.utils;

import gk.adv.linnk.vn.admin.Account;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SubjectTerm;

/**
 *
 * @author TUANPLA
 */
public class SendMail {

    public static void main(String[] args) {
        String[] toEmail = {"tuanpla@gmail.com"};
        SendMail.sendMail(toEmail, "Gửi thu Unicode", null);
    }

    private static void mailReceiver() {
// SUBSTITUTE YOUR ISP's POP3 SERVER HERE!!!
        String host = "pop.gmail.com";
// SUBSTITUTE YOUR USERNAME AND PASSWORD TO ACCESS E-MAIL HERE!!!
        String user = "homlinhtinh";
        String password = "123321";
// SUBSTITUTE YOUR SUBJECT SUBSTRING TO SEARCH HERE!!!
        String subjectSubstringToSearch = "Test E-Mail through Java";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
// To see what is going on behind the scene
        props.put("mail.debug", "true");
        props.put("mail.imap.port", "25");

// Get a session.Use a blank Properties object.
        Session session = Session.getInstance(props, null);

        try {

// Get a Store object
            Store store = session.getStore("pop3");
            store.connect(host, user, password);

// Get "INBOX"
            Folder fldr = store.getFolder("INBOX");
            fldr.open(Folder.READ_WRITE);
            int count = fldr.getMessageCount();
            System.out.println(count + " total messages");

// Message numebers start at 1
            for (int i = 1; i <= count; i++) {
// Geta message by its sequence number
                Message m = fldr.getMessage(i);

// Get some headers
                Date date = m.getSentDate();
                Address[] from = m.getFrom();
                String subj = m.getSubject();
                String mimeType = m.getContentType();
                System.out.println(date + "\t" + from[0] + "\t"
                        + subj + "\t" + mimeType);
            }

// Search for e-mails by some subject substring
            String pattern = subjectSubstringToSearch;
            SubjectTerm st = new SubjectTerm(pattern);
// Get some message references
            Message[] found = fldr.search(st);

            System.out.println(found.length
                    + " messages matched Subject pattern \""
                    + pattern + "\"");

            for (int i = 0; i < found.length; i++) {
                Message m = found[i];
// Get some headers
                Date date = m.getSentDate();
                Address[] from = m.getFrom();
                String subj = m.getSubject();
                String mimeType = m.getContentType();
                System.out.println(date + "\t" + from[0] + "\t"
                        + subj + "\t" + mimeType);

                Object o = m.getContent();
                if (o instanceof String) {
                    System.out.println("**This is a String Message**");
                    System.out.println((String) o);
                } else if (o instanceof Multipart) {
                    System.out.print("**This is a Multipart Message.");
                    Multipart mp = (Multipart) o;
                    int count3 = mp.getCount();
                    System.out.println("It has " + count3
                            + " BodyParts in it**");
                    for (int j = 0; j < count3; j++) {
// Part are numbered starting at 0
                        BodyPart b = mp.getBodyPart(j);
                        String mimeType2 = b.getContentType();
                        System.out.println("BodyPart " + (j + 1)
                                + " is of MimeType " + mimeType);

                        Object o2 = b.getContent();
                        if (o2 instanceof String) {
                            System.out.println("**This is a String BodyPart**");
                            System.out.println((String) o2);
                        } else if (o2 instanceof Multipart) {
                            System.out.print(
                                    "**This BodyPart is a nested Multipart.");
                            Multipart mp2 = (Multipart) o2;
                            int count2 = mp2.getCount();
                            System.out.println("It has " + count2
                                    + "further BodyParts in it**");
                        } else if (o2 instanceof InputStream) {
                            System.out.println(
                                    "**This is an InputStream BodyPart**");
                        }
                    } //End of for
                } else if (o instanceof InputStream) {
                    System.out.println("**This is an InputStream message**");
                    InputStream is = (InputStream) o;
// Assumes character content (not binary images)
                    int c;
                    while ((c = is.read()) != -1) {
                        System.out.write(c);
                    }
                }

// Uncomment to set "delete" flag on the message
//m.setFlag(Flags.Flag.DELETED,true);
            } //End of for

// "true" actually deletes flagged messages from folder
            fldr.close(true);
            store.close();

        } catch (MessagingException mex) {
// Prints all nested (chained) exceptions as well
            mex.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();

        }
    }

    public static String buildContentReg(Account oneAcc) {
        String str = "<table cellspacing='0' cellpadding='0' border='0' style='border-top:0pt none;vertical-align:top;width:600px;margin:30px auto'>"
                + "<tbody><tr><td style='border-right:1px solid rgb(162,164,167);border-left:1px solid rgb(162,164,167);border-top:1px solid rgb(162,164,167);vertical-align:middle'><a><img border='0'></a></td></tr>"
                + "<tr><td style='border-right:1px solid rgb(162,164,167);border-left:1px solid rgb(162,164,167);border-top:1px solid rgb(162,164,167);background:none repeat scroll 0% 0% rgb(255,255,255)'>"
                + " <table width='100%' cellspacing='10' cellpadding='0' border='0'>"
                + " <tbody><tr>"
                + " <td align='center'><span style='color:rgb(255,132,0);font-weight:bold;font-size:16px'>Đăng ký tài khoản hitmax.vn thành công!</td>"
                + " </tr> <tr> <td style='vertical-align:top'>"
                + " <p>Chào: <b>" + oneAcc.getFullName() + "</b></p>"
                + " <p>Xin chúc mừng bạn đã đăng ký thành công tài khoản tại hitmax.vn !</p>"
                + " <p>Tuy nhiên, tuy nhiên tài khoản của bạn chưa được chứng thực, hãy chứng thực để chúng tôi biết chắc chắn là bạn chứ ko phải ai đó dùng email này để đăng ký tài khoản trên website của chúng tôi.<br>"
                + " <em>- Nếu đúng là bạn dùng Email này để đăng ký tài khoản tại hitmax.vn xin vui lòng xác thực bằng link bên dưới:<br>"
                + " - <a target='_blank' href='http://hitmax.vn/xac-thuc-tai-khoan.html?user=" + oneAcc.getUserName() + "&act=" + oneAcc.getUserName() + "'>Mời bạn ấn vào đây để hoàn tất việc đăng ký tài khoản tại hitmax.vn</a></em>"
                + " </p> <p><b style='color:red'>- Lưu ý: Link kích hoạt này chỉ có tác dụng trong 24h</b></p> </td>"
                + " </tr> <tr> <td style='background-color:rgb(255,248,204);border:1px solid rgb(255,226,34);color:rgb(51,51,51);padding:7px'> "
                + " Hoặc nếu bạn quan tâm bạn có thể tham khảo <a target='_blank' href='http://hitmax.vn/gioi-thieu-ve-chung-toi.html'>tại đây</a></td>"
                + " </tr> <tr><td style='border-bottom:1px dashed rgb(153,153,153);padding-bottom:5px'></td></tr>"
                + " <tr> <td style='background:none repeat scroll 0% 0% rgb(255,255,255)'>"
                + " <p style='font-weight:bold;margin:0px 0pt 10px'>"
                + "<strong>Chúng tôi thành thật xin lỗi nếu bạn không phải là người đăng ký và xin vui lòng bỏ qua email này.</p>"
                + " </td> </tr> </tbody></table> "
                + " </td></tr><tr></tr><tr>"
                + "<td style='background-image:none;background-color:rgb(229,229,229);padding:10px 0pt 10px 10px;border-width:1px;border-style:solid;border-color:rgb(165,165,165) rgb(162,164,167) rgb(162,164,167);background-repeat:repeat repeat'>"
                + " <p style='color:rgb(119,119,119)'>Cám ơn bạn đã quan tâm và sử dụng dịch vụ của chúng tôi,</p>"
                + " <p style='color:rgb(119,119,119);margin:0pt 0pt 15px'><strong>BQT. <a target='_blank' style='color:rgb(119,119,119);text-decoration:none' href='https://hitmax.vn' title=''>Hitmax.vn</a></strong></p>"
                //                + " <p style='color:rgb(119,119,119);margin:0pt 0pt 5px'><b>Hà Nội:</b> Tầng 12A, tòa nhà 18 Tam Trinh, quận Hai Bà Trưng. Tel/Fax: (84) 04-3632-0986, nhánh 111.</p>"
                //                + " <p style='margin:0pt 0pt 5px'><b style='color:rgb(119,119,119)'>TP.HCM:</b><font color='#777777'>&nbsp;Lầu 3, tòa nhà VTC online - 132 Cộng Hòa, P4, Quận Tân Bình;&nbsp;Tel:&nbsp;(08) 6292.0945&nbsp;(ext 114).</font></p>"
                + " <p style='color:rgb(119,119,119);margin:0pt 0pt 5px'><b>Yahoo: </b>hitmaxdotvn - <b>Skype:</b> hitmaxdotvn - <b>Email:</b> <span style='color:rgb(119,119,119)'><a target='_blank' href='mailto:support@hitmax.vn'>support@hitmax.vn</a></span></p>"
                + " <p style='color:rgb(119,119,119);margin:0pt'><b>Website:</b> <a target='_blank' style='color:rgb(119,119,119)' href='https://hitmax.vn' title=''>hitmax.vn</a></p>"
                + " </td>"
                + "</tr>"
                + "</tbody></table>";
        return str;
    }

    public static boolean sendMail(String[] toEmail, String subject, Account oneAcc) {
        boolean flag = false;
        try {
            String userName = Constants.SMTP_MAIL;
            String password = Constants.SMTP_PASS;
            String hostName = Constants.MAIL_HOST;
            String fromName = Constants.FROM_NAME;
            Properties props = new Properties();
            String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.debug", Constants.MAIL_DEBUG);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
// Get the default Session object.
            Session session = Session.getInstance(props);
// Create a default MimeMessage object.
            MimeMessage messageSend = new MimeMessage(session);
// Set the RFC 822 "From" header field using the
// value of the InternetAddress.getLocalAddress method.
            messageSend.setFrom(new InternetAddress(userName, fromName));

            Address[] addresses = new Address[toEmail.length];
            for (int i = 0; i < toEmail.length; i++) {
                Address address = new InternetAddress(toEmail[i]);
                addresses[i] = address;
// Add the given addresses to the specified recipient type.
                messageSend.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail[i]));
            }
// Set the "Subject" header field.
            messageSend.setSubject(subject, "text/html;charset=utf-8");
// Sets the given String as this part's content,
// with a MIME type of "text/plain".
            Multipart mp;
            mp = new MimeMultipart("alternative");
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(buildContentReg(oneAcc), "text/html;charset=utf-8");
            mp.addBodyPart(mbp);
            messageSend.setContent(mp);
            messageSend.saveChanges();
// Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(hostName, userName, password);
            transport.sendMessage(messageSend, addresses);
            transport.close();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static int sendMail(String toEmail, String subject, Account oneAcc) {
        int flag = Constants.UNKNOW_EXCEPTION;
        try {
            String userName = Constants.SMTP_MAIL;
            String password = Constants.SMTP_PASS;
            String hostName = Constants.MAIL_HOST;
            String fromName = Constants.FROM_NAME;
            Properties props = new Properties();
            String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.debug", Constants.MAIL_DEBUG);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            // Get the default Session object.
            Session session = Session.getInstance(props);
            // Create a default MimeMessage object.
            MimeMessage messageSend = new MimeMessage(session);
            // Set the RFC 822 "From" header field using the
            // value of the InternetAddress.getLocalAddress method.
            messageSend.setFrom(new InternetAddress(userName, fromName));

            Address[] addresses = new Address[1];
            Address address = new InternetAddress(toEmail);
            addresses[0] = address;
            // Add the given addresses to the specified recipient type.
            messageSend.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            // Set the "Subject" header field.
            messageSend.setSubject(subject, "utf-8");
            // Sets the given String as this part's content,
            // with a MIME type of "text/plain".
            Multipart mp = new MimeMultipart("alternative");
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(buildContentReg(oneAcc), "text/html;charset=utf-8");
            mp.addBodyPart(mbp);
            messageSend.setContent(mp);
            messageSend.saveChanges();
            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(hostName, userName, password);
            transport.sendMessage(messageSend, addresses);
            transport.close();
            flag = Constants.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            flag = Constants.SEND_MAIL_FALSE;
        }
        return flag;
    }
}
