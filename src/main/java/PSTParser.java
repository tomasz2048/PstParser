import com.pff.*;

import java.util.Vector;

public class PSTParser {

    JsonWriter jsonWriter;

    public static void main(String[] args) {
        PSTParser p = new PSTParser("sample.pst");
    }

    public PSTParser(String filename) {
        jsonWriter = new JsonWriter("abc.json");
        try {
            PSTFile pstFile = new PSTFile(filename);
            System.out.println(pstFile.getMessageStore().getDisplayName());
            processFolder(pstFile.getRootFolder(), "");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    int depth = -1;

    public void processFolder(PSTFolder folder, String parentPath)
            throws PSTException, java.io.IOException {
        String path = parentPath+"/"+ folder.getDisplayName();
        depth++;
        // the root folder doesn't have a display name
        if (depth > 0) {
            printDepth();
            System.out.println(folder.getDisplayName());
        }

        // go through the folders...
        if (folder.hasSubfolders()) {
            Vector<PSTFolder> childFolders = folder.getSubFolders();
            for (PSTFolder childFolder : childFolders) {
                processFolder(childFolder, path);
            }
        }

        // and now the emails for this folder
        if (folder.getContentCount() > 0) {
            depth++;
            PSTMessage email = (PSTMessage) folder.getNextChild();
            while (email != null) {
                //printDepth();
                //System.out.println("Email: " + email.getSubject());
                //System.out.println("folder: " + path);
                jsonWriter.write(new Email(folder.getDisplayName(),email.getSenderEmailAddress(),email.getSubject(),email.getBody()));
                email = (PSTMessage) folder.getNextChild();
            }
            depth--;
        }
        depth--;
    }

    public void printDepth() {
        for (int x = 0; x < depth - 1; x++) {
            System.out.print(" | ");
        }
        System.out.print(" |- ");
    }

}
