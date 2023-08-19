public class Email {

    String folder;
    String sender;
    String subject;
    String body;

    public Email(String folder, String sender, String subject, String body){
        this.folder = folder;
        this.sender = sender;
        this.subject = subject;
        this.body = body;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFolder() {
        return folder;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
