
package data;
import io.socket.client.Ack;
import io.socket.client.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import org.json.JSONException;
import org.json.JSONObject;
import swing.PanelStatus_Item;

//import swing.PanelStatus_Item;

public class DataFileServer {
    
   
    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }
 
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public File getOutPutPath() {
        return outPutPath;
    }

    public void setOutPutPath(File outPutPath) {
        this.outPutPath = outPutPath;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DataFileServer(int fileID, String fileName, String fileSize, File outPutPath, boolean status) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.outPutPath = outPutPath;
        this.status = status;
    }

    public DataFileServer(JSONObject json , JTable table , Socket socket) throws JSONException { // 
        fileID = json.getInt("fileID");
        fileName = json.getString("fileName");
        fileSize = json.getString("fileSize");
        fileSizeLength = json.getLong("fileSizeLength");
        item = new PanelStatus_Item();
        this.table = table;
        this.socket = socket;
        item.addEventSave(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               JFileChooser ch = new JFileChooser();
               ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
               int opt = ch.showSaveDialog(null);
               if ( opt == JFileChooser.APPROVE_OPTION){
                   outPutPath = new File(ch.getSelectedFile().getAbsolutePath()+"/"+fileName);
                   item.startfile();
                   try {
                       saveFile();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }
            }
            
        });
        item.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //    Pause and resume
            }
        });
        
    }

    private int fileID;
    private String fileName;
    private String fileSize;
    private long fileSizeLength;
    private File outPutPath;
    private boolean status;
    private PanelStatus_Item item;
    private JTable table;
    private DataWritter writter;
    private Socket socket;
    
    private void saveFile() throws IOException,JSONException{
        
       if ( writter == null) writter = new DataWritter(outPutPath,fileSizeLength);
       JSONObject data = new JSONObject();
       data.put("fileID",fileID);
       data.put("length",writter.getFileLength());
       socket.emit("request_file",data,new Ack(){
           @Override
           public void call(Object... os) {
                try {
                    if (os.length > 0) {
                        byte[] b = (byte[]) os[0];
                        writter.writeFile(b);
                        item.showStatus((int) writter.getPercentage());
                        table.repaint();
                        saveFile();
                    } else {
                        item.showStatus((int) writter.getPercentage());
                        item.done();
                        table.repaint();
                        writter.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
           }
           
       });  
    }
    
    public PanelStatus_Item getItem() {
        return item;
    }

    /*public void setItem(PanelStatus_Item item) {
        this.item = item;
    }*/
    
    
    public Object[] toTableRow(int row) {
        return new Object[]{this, row, fileName, fileSize, "Next Update"};
    }
    
}

